package com.sssri.server.restful;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sssri.server.db.DatabaseUtils;
import com.sssri.server.db.mapper.ISafetyExamMapper;
import com.sssri.server.db.mapper.IUserMapper;
import com.sssri.server.db.mapper.IgetAdminInfo;
import com.sssri.server.db.mapper.IgetExamMapper;
import com.sssri.server.db.model.Choice;
import com.sssri.server.db.model.Completion;
import com.sssri.server.db.model.Judgment;
import com.sssri.server.db.model.Mark;
import com.sssri.server.db.model.Qanswer;
import com.sssri.server.db.model.Question;
import com.sssri.server.db.model.ReExam;
import com.sssri.server.db.model.Record;
import com.sssri.server.db.model.User;
import com.sssri.server.restful.resource.PdfExport;
import com.sssri.server.restful.resource.manager.KSInfoManager;
import com.sssri.server.db.model.Examination;

@Path("/SafetyExam")
@Consumes({"application/json", "application/x-www-form-urlencoded"})
public class SafetyExam {
	
	@GET
    @Path("/checkQA")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkQualification(@Context HttpServletRequest request) throws Exception{
		String employeeId= (String) request.getSession().getAttribute("login_user");
		SqlSession session = null;
		String result="";
		Calendar nowDay = Calendar.getInstance();
		nowDay.set(Calendar.HOUR_OF_DAY, 0);;
		nowDay.clear(Calendar.MINUTE);
		nowDay.clear(Calendar.SECOND);
		Date xnowDay =nowDay.getTime();
		Calendar tomorrow = nowDay;
		tomorrow.add(Calendar.HOUR, 24);
		Date xtomrrow = tomorrow.getTime();
		try{
			session = DatabaseUtils.getSessionFactory().openSession();
			// 数据库表操作
			IUserMapper userDAO = session.getMapper(IUserMapper.class);
			IgetExamMapper ExamDao = session.getMapper(IgetExamMapper.class);
			User userInfo = userDAO.selectUserByID(employeeId);
			Record checkRecord = ExamDao.SelectNowExam(employeeId);
			JSONObject map=new JSONObject();
			//2019.6.24增加判断是否是安全考试
			if (userInfo.getNow_Exam()==0||userInfo.getNow_Exam()<10000) {
				map.put("isExam", false);
				result="您最近的考试已完成，等待管理员发布新的考试!";
				map.put("result", result);
				return map.toJSONString();
			}
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //旧版时间计算以后不再使用，懒得改了都测试过了
			Date examdate,nowdate;
			examdate= format.parse(checkRecord.getExam_date());//要抛出异常的
			Calendar enddate = Calendar.getInstance();//考试结束时间
			enddate.setTime(examdate);
			enddate.add(Calendar.MINUTE, checkRecord.getExam_time());
			Date xenddate = enddate.getTime();
			nowdate= new Date();
			if (examdate.getTime()<xnowDay.getTime()||examdate.getTime()>xtomrrow.getTime()) {//查看考试时间是否在今天
				map.put("isExam", false);
//				result="1";
				result="没有你需要参加的考试!";
				map.put("result", result);
				return map.toJSONString();

			}else if (nowdate.getTime()>=xnowDay.getTime()&&nowdate.getTime()<examdate.getTime()) {//看现在时间是否在考试时间前
				map.put("isExam", true);
				result="考试还未开始请稍后！";
				map.put("result",result);
				return map.toJSONString();
			}else if (nowdate.getTime()>=examdate.getTime()&&nowdate.getTime()<=xenddate.getTime()) {//是否在考试中
				map.put("isExam", true);
				map.put("result",1);
				return map.toJSONString();
			}else if (nowdate.getTime()>xenddate.getTime()&&nowdate.getTime()<xtomrrow.getTime()) {
				map.put("isExam", true);
				result="考试已结束！";
				map.put("result",result);
				return map.toJSONString();
			}
			map.put("isExam", false);
			result="没有你需要参加的考试!！";
			map.put("result", result);
			return map.toJSONString();		
			
		} finally {
			if (session != null)
				session.close();
		}
		
	}
	/**
	 * 考卷随机生成之选择题
	 * 测试时仅用作输出考卷内容，以后输出写在mapper中
	 * @throws Exception 
	 */
	@GET
    @Path("/getExam")
	@Produces(MediaType.APPLICATION_JSON)
	public String getExam(@Context HttpServletRequest request) throws Exception {
		String employeeId= (String) request.getSession().getAttribute("login_user");
		LocalDateTime nowtime= LocalDateTime.now();
		Examination ex= new Examination();
		ex=(Examination) KSInfoManager.getManager().getKSInfo(employeeId);
		DateTimeFormatter format =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//时间格式
		Gson gson= new Gson();
		String kaojuan="";
		//判断是否还有
		if(ex!=null){
			if(ex.choice.size()>0|| ex.completion.size()>0|| ex.judgment.size()>0||ex.mulchoice.size()>0||ex.question.size()>0){
				if (ex.getStatues().equals("1")) {
					kaojuan="false";
					return kaojuan;
				}
				LocalDateTime endTime = LocalDateTime.parse(ex.getEndTime(), format);
				long timeleft = ChronoUnit.SECONDS.between(nowtime, endTime);//计算剩余秒时间
				if (timeleft<=-2) {
					ex.setTimeLeft(-2);
					CheckAnswerRestService checkanswer = new CheckAnswerRestService();
					checkanswer.endExam(request);
					kaojuan="false";
					return kaojuan;
				}else{
					ex.setTimeLeft((int) timeleft);//发送要秒，就直接给秒了
					kaojuan=gson.toJson(ex, Examination.class);
					return kaojuan;
				}
				
			}
		}
		
		SqlSession session =null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();//数据库连接
			ISafetyExamMapper Exammapper = session.getMapper(ISafetyExamMapper.class);
			Record ExamRecrd = Exammapper.SelectNowExam(employeeId);//2017.8.10
			ex= new Examination();
			ex.setExam_id(ExamRecrd.getExam_id());//正式考试使用
			////2017.7.27设置考试时间
			String day =ExamRecrd.getExam_date();
			int stri=day.lastIndexOf(".");
			if (stri>0) {
				day=day.substring(0, stri);
			}
			ex.setStartTime(day);
			LocalDateTime endExamTime = LocalDateTime.parse(day, format);
			endExamTime=endExamTime.plusMinutes(ExamRecrd.getExam_time());
			ex.setEndTime(format.format(endExamTime));
			long timeleft = ChronoUnit.SECONDS.between(nowtime, endExamTime);//计算剩余秒时间
			ex.setTimeLeft((int) timeleft);//发送要秒，就直接给秒了
			/////2017.6.28设置考卷分值
			ex.setCompletion_Score(ExamRecrd.getCompletion_Score());
			ex.setJudgment_Score(ExamRecrd.getJudgment_Score());
			ex.setChoice_Score(ExamRecrd.getChoice_Score());
			ex.setMulChoice_Score(ExamRecrd.getMulChoice_Score());
			ex.setQA_Score(ExamRecrd.getQA_Score());
			////
			Random ran = new Random();//随机数
			String ExamId = "";//考卷序号组
			Set<String> choiceSet = new LinkedHashSet<String>();//随机题号存放处
			
			
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
					ExamId = ExamId + String.format("%04d", Long.parseLong(s));//自动补零
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
					ExamId = ExamId + String.format("%04d", Long.parseLong(s));//自动补零
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
					ExamId = ExamId + String.format("%04d", Long.parseLong(s));//自动补零
					ex.mulchoice.add(MulChoiceid.get(Integer.parseInt(s)));
				}
			}
			
			
			System.out.println(ex.question);//测试用增加考卷内容
			/////////////////////////测试用登陆后要修改
			ex.setEmployee_id(employeeId);
//			ReExam x=new ReExam();
//			x.setContent(ExamId);
//			x.setExam_id(ExamRecrd.getExam_id());
//			x.setExam_paper_id(4);
//			x.setEmployee_id(employeeId);
//			
			
			kaojuan=gson.toJson(ex, Examination.class);
			try {
//				Exammapper.insertContent(x);
//				session.commit();
				KSInfoManager.getManager().putKSInfo(ex);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			List<?> x=ChoiceMapper.selectallchoice();//使用泛型尝试
//			Choiceid.get(0).getOption_three();
//			ex.choice.add((Choice) x.get(0));
			
			return kaojuan;
		} finally {
			if (session != null)
				session.close();
		}
    }
	
	/**
	 * 2019.6.29修改
	 * 考卷客观题评卷
	 * @param ExamId
	 * @return
	 */
	@Path("/endExam")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void endExam(@Context HttpServletRequest request){
		//答案处理类
		CheckAnswerRestService checkserver = new CheckAnswerRestService();
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
		float choiceScore=checkserver.checkChoice(choiceList, examInfo.getChoice_Score());
		//多选题答案判断
		List<Choice> mulChoiceList=examInfo.getMulchoice();
		float mulChoiceScore=checkserver.checkChoice(mulChoiceList,examInfo.getMulChoice_Score());
		//判断题答案判断
		List<Judgment> jgList=examInfo.getJudgment();
		float jgScore=checkserver.checkJugement(jgList,examInfo.getJudgment_Score());
		
		
		//统计每项总分值
//		Date nowdate= new Date();
//		//设置日期格式化样式为：yyyy-MM-dd  
//		SimpleDateFormat  SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//		//格式化当前日期  
//	    SimpleDateFormat.format(nowdate.getTime()); 
		Mark employeeMark= new Mark();
		employeeMark.setEmployee_id(employee_id);
		employeeMark.setCompletion_score(0);//填空题的分数为0
		employeeMark.setJudgment_score(jgScore);
		employeeMark.setChoice_score(choiceScore);
		employeeMark.setMchoice_score(mulChoiceScore);
		employeeMark.setExam_paper_id(examInfo.getExam_id());
		employeeMark.setScore_sum((jgScore+choiceScore+mulChoiceScore));//总分计算
		employeeMark.setExam_date(examInfo.getStartTime());
		//考卷信息替换
		//examInfo.setCompletion(completionList);没有填空题
		examInfo.setJudgment(jgList);
		examInfo.setChoice(choiceList);
		examInfo.setMulchoice(mulChoiceList);
		examInfo.setStatues("1");
		KSInfoManager.getManager().putKSInfo(examInfo);
		//生成pdf考卷输入考生考卷信息，分数统计结果
		PdfExport pdfmaker = new PdfExport();
		pdfmaker.SafeExamExport(examInfo,employeeMark);
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
}
