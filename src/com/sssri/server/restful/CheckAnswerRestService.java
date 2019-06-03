package com.sssri.server.restful;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.session.SqlSession;

import com.sssri.server.db.DatabaseUtils;
import com.sssri.server.db.mapper.IgetAdminInfo;
import com.sssri.server.db.mapper.IgetExamMapper;
import com.sssri.server.db.model.Choice;
import com.sssri.server.db.model.Completion;
import com.sssri.server.db.model.Examination;
import com.sssri.server.db.model.Judgment;
import com.sssri.server.db.model.Mark;
import com.sssri.server.db.model.Qanswer;
import com.sssri.server.db.model.User;
import com.sssri.server.restful.resource.PdfExport;
import com.sssri.server.restful.resource.manager.KSInfoManager;

@Path("/checkAnswer")
@Consumes({"application/json", "application/x-www-form-urlencoded"})
public class CheckAnswerRestService {
	
	/**
	 * 考卷客观题评卷
	 * @param ExamId
	 * @return
	 */
	@Path("/endExam")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void endExam(@Context HttpServletRequest request){
		//1.从Sesssion中获取当前用户对象
		//User user=(User)request.getSession().getAttribute("login_user");
//		User user=new User();
//		user.setEmplyee_id("3306");
		String employee_id=(String) request.getSession().getAttribute("login_user");
		//获取考试规则，考卷信息
		Examination examInfo=(Examination)KSInfoManager.getManager().getKSInfo(employee_id);
		if (examInfo==null) {//没有答案的时候
			return ;
		}
		if (examInfo.getStatues().equals("1")) {
			return ;
		}
		//单选题答案判断
		List<Choice> choiceList=examInfo.getChoice();
		float choiceScore=checkChoice(choiceList, examInfo.getChoice_Score());
		//多选题答案判断
		List<Choice> mulChoiceList=examInfo.getMulchoice();
		float mulChoiceScore=checkChoice(mulChoiceList,examInfo.getMulChoice_Score());
		//判断题答案判断
		List<Judgment> jgList=examInfo.getJudgment();
		float jgScore=checkJugement(jgList,examInfo.getJudgment_Score());
		//填空题答案判断
		List<Completion> completionList=examInfo.getCompletion();
		float completionScore=CheckCompletion(completionList,examInfo.getCompletion_Score());
		
		//统计每项总分值
//		Date nowdate= new Date();
//		//设置日期格式化样式为：yyyy-MM-dd  
//		SimpleDateFormat  SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//		//格式化当前日期  
//	    SimpleDateFormat.format(nowdate.getTime()); 
		Mark employeeMark= new Mark();
		employeeMark.setEmployee_id(employee_id);
		employeeMark.setCompletion_score(completionScore);
		employeeMark.setJudgment_score(jgScore);
		employeeMark.setChoice_score(choiceScore);
		employeeMark.setMchoice_score(mulChoiceScore);
		employeeMark.setExam_paper_id(examInfo.getExam_id());
		employeeMark.setScore_sum((completionScore+jgScore+choiceScore+mulChoiceScore));
		employeeMark.setExam_date(examInfo.getStartTime());
		//考卷信息替换
		examInfo.setCompletion(completionList);
		examInfo.setJudgment(jgList);
		examInfo.setChoice(choiceList);
		examInfo.setMulchoice(mulChoiceList);
		examInfo.setStatues("1");
		KSInfoManager.getManager().putKSInfo(examInfo);
		//生成pdf考卷输入考生考卷信息，分数统计结果
		PdfExport pdfmaker = new PdfExport();
		pdfmaker.wordExport(examInfo,employeeMark);
		SqlSession session = null;
		//存储考生简答题成绩
		List<Qanswer> qanswerList=new ArrayList<Qanswer>();
		for (int i = 0; i < examInfo.question.size(); i++) {
			Qanswer qanswer = new Qanswer();
			qanswer.setEmployee_id(employee_id);
			qanswer.setExam_id(examInfo.getExam_id());
			qanswer.setExam_date(examInfo.getStartTime());
			qanswer.setQuestion_position(i);
			qanswer.setQuestion_id(examInfo.question.get(i).getNum_Id());
			qanswer.setUser_answer(examInfo.question.get(i).getUserAnswer());
			qanswerList.add(qanswer);
		}
		
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			IgetExamMapper userDAO = session.getMapper(IgetExamMapper.class);
			userDAO.insertMark(employeeMark);
			//2018.12.28修改如果没有简答题就不要提交内容
			if(examInfo.question.size()>0)
			{
				userDAO.inserQanswer(qanswerList);
			}
			userDAO.updateUserNowExamStatue(employee_id);
			session.commit();
			KSInfoManager.getManager().removeKSInfo(employee_id);//考完后清空缓存
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * 填空题按照空格进行答案判断
	 * @param completionList
	 * @param score
	 * @return
	 */
	private float CheckCompletion(List<Completion> completionList, float score) {
		float totalScore=0;
		for(Completion completion:completionList){
			//不能分割，分割就会去掉中间没做的空格
			String[] curUserAnswer=completion.getUserAnswer().split("\\$%");
			/*
			if(everyAnswer==null){  //一个答案都没有证明没做
				completion.setUserScore(0);
				completion.setItemStatus(0);
			}*/
			int corrAnswerCount=0;
			int unAnswerCount=0;
			int answerCount=completion.getBlank_num(); //获取答案个数
			//用户答题答案
			String userAnswer=completion.getUserAnswer();  //从后依次往前匹配
			if(userAnswer==null||userAnswer.equals("")||userAnswer.equals(" $%")||userAnswer.equals(" $% $%")||userAnswer.equals(" $% $% $%")||userAnswer.equals(" $% $% $% $%")||userAnswer.equals(" $% $% $% $% $%")){//由于存储方式方式空格不填
				completion.setItemStatus(0);
				completion.setUserScore(0);
			}else{
				//1.获取当前空的正确答案
				while(answerCount>0){
					String curAnswer=getCurrentAnswer(completion, answerCount);
					//顾峰以前使用，由于小马每个答案都加了$%所以可以用简便方法
					/*
					int index=userAnswer.lastIndexOf("$%");
					String curUserAnswer=null;	
					if(index!=-1){
						//当前匹配的用户答案
						curUserAnswer=userAnswer.substring(index+1);
						userAnswer=userAnswer.substring(0, index);
					}else{
						curUserAnswer=userAnswer;
					}
					*/
					int retInt=checkString(curUserAnswer[answerCount-1], curAnswer);
					if(retInt==2){
						corrAnswerCount++;
					}else if(retInt==0){
						unAnswerCount++;
					}
					answerCount--;
				}
				//统计单题得分
				completion.setUserScore(corrAnswerCount*score);
				//重新获取答案个数
				answerCount=completion.getBlank_num();
				if(corrAnswerCount==answerCount){
					completion.setItemStatus(2);
				}
				if(unAnswerCount==answerCount){
					completion.setItemStatus(0);
					completion.setUserScore(0);
				}
			}
			totalScore+=completion.getUserScore();
		}
		return totalScore;
	}
	
	/**
	 * 长度规则匹配
	 * 答案判断 1空 3正确 4错误
	 * @param curUserAnswer
	 * @param curAnswer
	 * @return
	 */
	private int checkString(String curUserAnswer, String curAnswer) {
		int ret=0;
		int charcount=curUserAnswer.length()-curAnswer.length();
		if(curUserAnswer==null ||curUserAnswer.trim().isEmpty()){
			ret=0;
		}else if(charcount<-2 || charcount>4){  //长度比较
			ret=3;
		}else if(curUserAnswer.length()<5 || curUserAnswer.matches("[0-9]+")){
			if(curUserAnswer.equals(curAnswer)){
				ret=2;
			}else{
				ret=3;
			}
		}else{
			if(curUserAnswer.contains(curAnswer) || curAnswer.contains(curUserAnswer)){
				ret=2;
			}else{
				ret=3;
			}	
		}
		return ret;
	}

	/**
	 * 获取正确答案
	 * @param completion
	 * @param num
	 * @return
	 */
	private String getCurrentAnswer(Completion completion, int num) {
		String curAnswer="";
		switch(num){
				case 1:
					curAnswer=completion.getAnswer_one().replaceAll("　", "");
					break;
				case 2:
					curAnswer=completion.getAnswer_two().replaceAll("　", "");//数据库中录入的不是空格，是个奇奇怪怪类似空格的东西
					break;
				case 3:
					curAnswer=completion.getAnswer_three().replaceAll("　", "");
					break;
				case 4:
					curAnswer=completion.getAnswer_four().replaceAll("　", "");
					break;
				case 5:
					curAnswer=completion.getAnswer_five().replaceAll("　", "");
					break;
				default:
					curAnswer=completion.getAnswer_one().replaceAll("　", "");
					break;
		}
		return curAnswer;
	}

	/**
	 * 判断题答案判断
	 * @param jgList
	 * @param score
	 * @return
	 */
	private float checkJugement(List<Judgment> jgList, float score) {
		float totalScore=0;
		for(Judgment judege:jgList){
			String answer=judege.getAnswer();
			String userAnswer=judege.getUserAnswer();
			if(userAnswer==null|| userAnswer.trim().isEmpty()){
				judege.setUserScore(0);
				judege.setItemStatus(0);
			}else if(userAnswer.toLowerCase().equals(answer.toLowerCase())){
				judege.setUserScore(score);
				judege.setItemStatus(2);
			}else{
				judege.setUserScore(0);
				judege.setItemStatus(3);
			}
			totalScore+=judege.getUserScore();
		}
		return totalScore;
	}

	/**
	 * 选择题答案判断
	 * @param choiceList
	 * @param score
	 * @return
	 */
	private float checkChoice(List<Choice> choiceList, float score) {
		float totalScore=0;
		for(Choice choice:choiceList){
			String answer=choice.getAnswer();
			String userAnswer=choice.getUserAnswer();
			if(userAnswer==null || userAnswer.trim().isEmpty()){
				choice.setUserScore(0);
				choice.setItemStatus(0);
			}else if(userAnswer.toLowerCase().equals(answer.toLowerCase())){
				choice.setUserScore(score);
				choice.setItemStatus(2);
			}else{
				choice.setUserScore(0);
				choice.setItemStatus(3);
			}
			totalScore+=choice.getUserScore();
		}
		return totalScore;
	}

	
	
}
