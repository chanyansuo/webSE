package com.sssri.server.restful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.session.SqlSession;


import com.google.gson.Gson;
import com.sssri.server.db.DatabaseUtils;
import com.sssri.server.db.mapper.IgetExamMapper;
import com.sssri.server.db.model.Choice;
import com.sssri.server.db.model.Completion;
import com.sssri.server.db.model.Examination;
import com.sssri.server.db.model.Judgment;
import com.sssri.server.db.model.Question;
import com.sssri.server.db.model.ReExam;
import com.sssri.server.db.model.Record;
import com.sssri.server.restful.resource.manager.MNinfoManager;


@Path("/Practice")
@Consumes({"application/json", "application/x-www-form-urlencoded"})
public class PracticeRestService {
	
	@POST
    @Path("/getPractice")
	@Produces(MediaType.APPLICATION_JSON)
	public String gettpyePractice(@FormParam("type")int type, @FormParam("isRandom")int mode){
		List<?> practiceList = null;
		String result = "";
		Gson gson= new Gson();
		SqlSession session = null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			Examination ex = new Examination();
			IgetExamMapper praticeDAO = session.getMapper(IgetExamMapper.class);
			switch (type) {
			case 1:
				ex.completion=praticeDAO.selectComplletion();
				practiceList=praticeDAO.selectComplletion();
//				ex.completion=(List<Completion>) practiceList;
				break;
			case 2:
				ex.judgment=praticeDAO.selectallJudgment();
				practiceList=praticeDAO.selectallJudgment();
				break;
			case 3:
				ex.choice=praticeDAO.selectallchoice(3);
				practiceList=praticeDAO.selectallchoice(3);
				break;
			case 4:
				ex.mulchoice=praticeDAO.selectallchoice(4);
				practiceList=praticeDAO.selectallchoice(4);
				break;
			case 5:
				ex.question=praticeDAO.selectallQuestion();
				practiceList=praticeDAO.selectallQuestion();
				break;
			}
			if (mode==0) {
				result= gson.toJson(ex);
				return result;
			}
			else if(mode==1){
				Random ran = new Random();//随机数
				Set<String> praticeSet= new LinkedHashSet<String>();
				List<Object> practiceResult = new ArrayList<>();
				if(practiceList.size()!=0){
					praticeSet.clear();
					while (true) {
						int a = ran.nextInt(practiceList.size());//在[0,ChoiceNumber)中随机选出ExamChoiceNum个随机数
						praticeSet.add(String.valueOf(a)); 
						if (praticeSet.size() == practiceList.size() ) {
							break;
						}
					}
					for (String s : praticeSet) {
						practiceResult.add(practiceList.get(Integer.parseInt(s)));
					}
				}
				String tableName="";
				switch (type) {
				case 1:
					tableName="completion";
					break;
				case 2:
					tableName="judgment";
					break;
				case 3:
					tableName="choice";
					break;
				case 4:
					tableName="mulchoice";
					break;
				case 5:
					tableName="question";
					break;
				}
				HashMap<String, Object> hashmap=  new HashMap<String,Object>();
				hashmap.put(tableName, practiceResult);
				return gson.toJson(hashmap);
			}
		} finally {
			if (session != null)
				session.close();
		}
		return null;
		
	}
	
	@POST
    @Path("/CheckAnswer")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean CheckOneAnswer(@FormParam("temp") String Completion){
		Gson gson = new Gson();
		Completion completion= gson.fromJson(Completion, Completion.class);
		String[] curUserAnswer=completion.getUserAnswer().split("\\$%");
		int corrAnswerCount=0;
		int unAnswerCount=0;
		int answerCount=completion.getBlank_num(); //获取答案个数
		//用户答题答案
		String userAnswer=completion.getUserAnswer();  //从后依次往前匹配
		if(userAnswer==null||userAnswer.equals("")||userAnswer.equals(" $%")||userAnswer.equals(" $% $%")||userAnswer.equals(" $% $% $%")||userAnswer.equals(" $% $% $% $%")||userAnswer.equals(" $% $% $% $% $%")){//由于存储方式方式空格不填
			return false;
		}else{
			//1.获取当前空的正确答案
			while(answerCount>0){
				String curAnswer=getCurrentAnswer(completion, answerCount);
				int retInt=checkString(curUserAnswer[answerCount-1], curAnswer);
				if(retInt==2){
					corrAnswerCount++;
				}else if(retInt==0){
					unAnswerCount++;
				}
				answerCount--;
			}
			//重新获取答案个数
			answerCount=completion.getBlank_num();
			if(corrAnswerCount==answerCount){
				return true;
			}
			if(unAnswerCount==answerCount){
				return false;
			}
		}
		return false;
		
	}
	
	/**
	 * 无shi'j考卷随机生成之选择题
	 * 测试时仅用作输出考卷内容，以后输出写在mapper中
	 */
	@GET
    @Path("/getExam")
	@Produces(MediaType.APPLICATION_JSON)
	public String getExam(@Context HttpServletRequest request) {
		String employeeId= (String) request.getSession().getAttribute("login_user");
		Examination ex= new Examination();
		Gson gson= new Gson();
		String kaojuan="";
		SqlSession session =null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();//数据库连接
			IgetExamMapper Exammapper = session.getMapper(IgetExamMapper.class);
			Record ExamRecrd = Exammapper.SelectExamNum(1);//考试卷号选择应该由前段传输测试用1
			ex= new Examination();
			ex.setExam_id(1);//仅仅测试使用
			/////2017.6.28设置考卷分值
			ex.setCompletion_Score(ExamRecrd.getCompletion_Score());
			ex.setJudgment_Score(ExamRecrd.getJudgment_Score());
			ex.setChoice_Score(ExamRecrd.getChoice_Score());
			ex.setMulChoice_Score(ExamRecrd.getMulChoice_Score());
			ex.setQA_Score(ExamRecrd.getQA_Score());
			////
			Random ran = new Random();//随机数
			Set<String> choiceSet = new LinkedHashSet<String>();//随机题号存放处
			///填空题有至多五个空
			for(int i=1;i<=5;i++){
				List<Completion> comple = Exammapper.selectallComplletion(i);
				int CompletionNum=comple.size();
				int ExamComNum = 0;
				Set<String> CompleteSet = new LinkedHashSet<String>();//填空题随机题号存放处
				switch(i){//读取题数
				case 1:
					ExamComNum=ExamRecrd.getCompletion_num1();
					break;
				case 2:
					ExamComNum=ExamRecrd.getCompletion_num2();
					break;
				case 3:
					ExamComNum=ExamRecrd.getCompletion_num3();
					break;
				case 4:
					ExamComNum=ExamRecrd.getCompletion_num4();
					break;
				case 5:
					ExamComNum=ExamRecrd.getCompletion_num5();
					break;
				}
				if(CompletionNum < ExamComNum){
					return null;
				}
				if(ExamComNum!=0){
					CompleteSet.clear();
					while (true) {
						int a = ran.nextInt(CompletionNum);//在[0,ChoiceNumber)中随机选出ExamChoiceNum个随机数
						CompleteSet.add(String.valueOf(a)); 
						if (CompleteSet.size() == ExamComNum ) {
							break;
						}
					}
					for (String s : CompleteSet) {
						ex.completion.add(comple.get(Integer.parseInt(s)));
					}
				}
			}
			
			///判断题
			List<Judgment> judgment =Exammapper.selectallJudgment();
			int JudgNumber = judgment.size();
			int ExamJugeNum = ExamRecrd.getJudgment_num();
			if(JudgNumber < ExamJugeNum){
				return null;
			}
			if(ExamJugeNum!=0){
				choiceSet.clear();
				while (true) {
					int a = ran.nextInt(JudgNumber);//在[0,ChoiceNumber)中随机选出ExamChoiceNum个随机数
					choiceSet.add(String.valueOf(a)); 
					if (choiceSet.size() == ExamJugeNum ) {
						break;
					}
				}
				
				for (String s : choiceSet) {
					ex.judgment.add(judgment.get(Integer.parseInt(s)));
				}
			}
			////单项选择题
			List<Choice> Choiceid = Exammapper.selectallchoice(3);//选择单选题
			int ChoiceNumber = Choiceid.size();//选择题总数
			int ExamChoiceNum =ExamRecrd.getChoice_num();
			if(ChoiceNumber < ExamChoiceNum){
				return null;
			}
			if(ExamChoiceNum!=0){//防止没有该题型
				choiceSet.clear();
				while (true) {
					int a = ran.nextInt(ChoiceNumber);//在[0,ChoiceNumber)中随机选出ExamChoiceNum个随机数
					choiceSet.add(String.valueOf(a)); 
					if (choiceSet.size() == ExamChoiceNum ) {
						break;
					}
				}
				
				for (String s : choiceSet) {
					ex.choice.add(Choiceid.get(Integer.parseInt(s)));
				}
			}

			
			///多项选择题
			List<Choice> MulChoiceid = Exammapper.selectallchoice(4);//选择多选题
			int MulChoiceNumber = MulChoiceid.size();//选择题总数
			int ExamMulChoiceNum =ExamRecrd.getMulChoice_num();
			if(MulChoiceNumber < ExamMulChoiceNum){
				return null;
			}
			if(ExamMulChoiceNum!=0){//防止没有该题型
				choiceSet.clear();
				while (true) {
					int a = ran.nextInt(MulChoiceNumber);//在[0,ChoiceNumber)中随机选出ExamChoiceNum个随机数
					choiceSet.add(String.valueOf(a)); 
					if (choiceSet.size() == ExamMulChoiceNum ) {
						break;
					}
				}
				for (String s : choiceSet) {
					ex.mulchoice.add(MulChoiceid.get(Integer.parseInt(s)));
				}
			}
			
			//简答题
			List<Question> question = Exammapper.selectallQuestion();
			int QuestionNumber = question.size();
			int ExamQuNum = ExamRecrd.getQA_num();
			if(QuestionNumber < ExamQuNum){
				return null;
			}
			if(ExamQuNum!=0){//防止没有该题型
				choiceSet.clear();
				while (true) {
					int a = ran.nextInt(QuestionNumber);//在[0,ChoiceNumber)中随机选出ExamChoiceNum个随机数
					choiceSet.add(String.valueOf(a)); 
					if (choiceSet.size() == ExamQuNum ) {
						break;
					}
				}
				
				for (String s : choiceSet) {
					ex.question.add(question.get(Integer.parseInt(s)));
				}
			}
			ex.setEmployee_id(employeeId);
			kaojuan=gson.toJson(ex, Examination.class);
			MNinfoManager.getManager().putKSInfo(ex);
			return kaojuan;
		} finally {
			if (session != null)
				session.close();
		}
    }
	
	/**
	 * 下一题点击存储答案
	 * @param userID
	 * @param type
	 * @param currentNum
	 * @param userAnswer
	 * @return
	 */
	@POST
    @Path("/getuserAnswer")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean setExUserAnswer(@FormParam("userid") String userID,@FormParam("type") int type,@FormParam("currentNum") int currentNum,@FormParam("userAnswer") String userAnswer){
		switch (type) {
		case 1://填空题
			MNinfoManager.getManager().getKSInfo(userID).getCompletion().get(currentNum).setUserAnswer(userAnswer);//根据userid得到考卷设置useranswer
			String xxx= MNinfoManager.getManager().getKSInfo(userID).getCompletion().get(currentNum).getUserAnswer();
			if (!userAnswer.equals("")&&!userAnswer.equals(" $%")&&!userAnswer.equals(" $% $%")&&!userAnswer.equals(" $% $% $%")&&!userAnswer.equals(" $% $% $% $%")&&!userAnswer.equals(" $% $% $% $% $%")) {
				MNinfoManager.getManager().getKSInfo(userID).getCompletion().get(currentNum).setItemStatus(1);
			}
			return true;
			
		case 2://判断题
			MNinfoManager.getManager().getKSInfo(userID).getJudgment().get(currentNum).setUserAnswer(userAnswer);
			if (!userAnswer.equals("")) {
				MNinfoManager.getManager().getKSInfo(userID).getJudgment().get(currentNum).setItemStatus(1);
			}
			return true;
			
		case 3://单选题
			MNinfoManager.getManager().getKSInfo(userID).getChoice().get(currentNum).setUserAnswer(userAnswer);
			if (!userAnswer.equals("")) {
				MNinfoManager.getManager().getKSInfo(userID).getChoice().get(currentNum).setItemStatus(1);
			}
			return true;
			
		case 4://多选题
			MNinfoManager.getManager().getKSInfo(userID).getMulchoice().get(currentNum).setUserAnswer(userAnswer);
			if (!userAnswer.equals("")) {
				MNinfoManager.getManager().getKSInfo(userID).getMulchoice().get(currentNum).setItemStatus(1);
			}
			return true;
			
		case 5://简答题
			MNinfoManager.getManager().getKSInfo(userID).getQuestion().get(currentNum).setUserAnswer(userAnswer);
			if (!userAnswer.equals("")&&!userAnswer.equals(" $%")) {
				MNinfoManager.getManager().getKSInfo(userID).getQuestion().get(currentNum).setItemStatus(1);
			}
			return true;
			
		default:
			break;
		}
		return false;
	}
	
	/**
	 * 考卷客观题评卷
	 * @param ExamId
	 * @return
	 */
	@Path("/endExam")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void endExam(@Context HttpServletRequest request){
		String employee_id=(String) request.getSession().getAttribute("login_user");
		//获取考试规则，考卷信息
		Examination examInfo=(Examination)MNinfoManager.getManager().getKSInfo(employee_id);
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
		
		List<Question> QA= examInfo.getQuestion();
		for(int i=0;i<QA.size();i++){
			QA.get(i).setItemStatus(3);
		}
		
		//统计每项总分值
		float sumScore= completionScore+jgScore+choiceScore+mulChoiceScore+(examInfo.getQA_Score()*examInfo.question.size());
		//考卷信息替换
		examInfo.setCompletion(completionList);
		examInfo.setJudgment(jgList);
		examInfo.setChoice(choiceList);
		examInfo.setMulchoice(mulChoiceList);
		examInfo.setQuestion(QA);
		examInfo.setStatues("1");
		examInfo.setSum_Score(sumScore);
		MNinfoManager.getManager().putKSInfo(examInfo);

	}
	
	@Path("/getResult")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String resultTest(@Context HttpServletRequest request){
		String employee_id=(String) request.getSession().getAttribute("login_user");
		//获取考试规则，考卷信息
		Examination examInfo=(Examination)MNinfoManager.getManager().getKSInfo(employee_id);
		Gson gson= new Gson();
		String result="";
		result=gson.toJson(examInfo, Examination.class);
//		MNinfoManager.getManager().removeKSInfo(employee_id);
		return result;
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
			int corrAnswerCount=0;
			int unAnswerCount=0;
			int answerCount=completion.getBlank_num(); //获取答案个数
			//用户答题答案
			String userAnswer=completion.getUserAnswer();  //从后依次往前匹配
			if(userAnswer==null||userAnswer.equals("")||userAnswer.equals(" $%")||userAnswer.equals(" $% $%")||userAnswer.equals(" $% $% $%")||userAnswer.equals(" $% $% $% $%")||userAnswer.equals(" $% $% $% $% $%")){//由于存储方式方式空格不填
				completion.setItemStatus(3);
				completion.setUserScore(0);
			}else{
				//1.获取当前空的正确答案
				while(answerCount>0){
					String curAnswer=getCurrentAnswer(completion, answerCount);
					int retInt=checkString(curUserAnswer[answerCount-1], curAnswer);
					if(retInt==2){
						corrAnswerCount++;
					}else if(retInt==3){
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
					completion.setItemStatus(3);
					completion.setUserScore(0);
				}
			}
			totalScore+=completion.getUserScore();
		}
		return totalScore;
	}
	
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
				judege.setItemStatus(3);
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
				choice.setItemStatus(3);
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

