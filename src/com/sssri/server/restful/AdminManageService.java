package com.sssri.server.restful;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import javax.ws.rs.core.Response;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sssri.server.db.DatabaseUtils;
import com.sssri.server.db.mapper.IUserMapper;
import com.sssri.server.db.mapper.IgetAdminInfo;
import com.sssri.server.db.mapper.IgetExamMapper;
import com.sssri.server.db.model.Choice;
import com.sssri.server.db.model.Completion;
import com.sssri.server.db.model.Judgment;
import com.sssri.server.db.model.Mark;
import com.sssri.server.db.model.Qanswer;
import com.sssri.server.db.model.Question;
import com.sssri.server.db.model.QuestionType;
import com.sssri.server.db.model.Record;
import com.sssri.server.db.model.User;
import com.sssri.server.restful.resource.manager.KSInfoManager;

@Path("/Manage")
@Consumes({"application/json", "application/x-www-form-urlencoded"})
public class AdminManageService {
	
	/**
	 * 题库查询
	 * @param type
	 * @return
	 */
	@GET
    @Path("/getQuestions/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllCompletions(@PathParam("type") int type){
		SqlSession session = null;
		try{
			session = DatabaseUtils.getSessionFactory().openSession();
			// 数据库表操作
			IgetAdminInfo userDAO = session.getMapper(IgetAdminInfo.class);
			String rtn="{'total':0,'rows':[]}";//eazyui数据传输格式
			String tablename= "tb_completion";
		    //tablename="'"+tablename+"'";
			List<?> ts=userDAO.selecttest(tablename,type);
			List<?>allCompetions=null;//存储搜索题型
			switch (type) {
			case 1:
				allCompetions=userDAO.selectallComplletion();
				break;
			case 2:
				allCompetions=userDAO.selectallJudgment();
				break;
			case 3://选择题
				allCompetions=userDAO.selectallchoice();
				break;
			case 4://简答题
				allCompetions=userDAO.selectallQuestion();
				break;
			}
			int total= allCompetions.size();//转换成分json使用
			Gson gson=new Gson();
			String xx=gson.toJson(allCompetions);//fastjson会出现大小写变化，使用gson不会出现顺序问题和大小写问题
			rtn="{\"total\":"+total+",\"rows\":"+xx+"}";
			return rtn;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * 安全题库查询
	 * @param type
	 * @return
	 */
	@GET
    @Path("/getsafetyQuestions/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllSafetyCompletions(@PathParam("type") int type){
		SqlSession session = null;
		try{
			session = DatabaseUtils.getSessionFactory().openSession();
			// 数据库表操作
			IgetAdminInfo userDAO = session.getMapper(IgetAdminInfo.class);
			String rtn="{'total':0,'rows':[]}";//eazyui数据传输格式
			String tablename= "tb_completion";
		    //tablename="'"+tablename+"'";
			List<?> ts=userDAO.selecttest(tablename,type);
			List<?>allCompetions=null;//存储搜索题型
			switch (type) {
			case 2:
				allCompetions=userDAO.selectallsafetyJudgment();
				break;
			case 3://选择题
				allCompetions=userDAO.selectallsafetychoice();
				break;
			}
			int total= allCompetions.size();//转换成分json使用
			Gson gson=new Gson();
			String xx=gson.toJson(allCompetions);//fastjson会出现大小写变化，使用gson不会出现顺序问题和大小写问题
			rtn="{\"total\":"+total+",\"rows\":"+xx+"}";
			return rtn;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * 题库增删改查
	 * @param type
	 * @param form
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@POST
	@Path("/getChange/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getQustionsChange(@PathParam("type") int type,MultivaluedMap<String, String> form ) {
		SqlSession session = null;
     	try {
     		session = DatabaseUtils.getSessionFactory().openSession();
			// 数据库表操作
			IgetAdminInfo adminDAO = session.getMapper(IgetAdminInfo.class);
			String update = form.getFirst("updated");
			String delete = form.getFirst("deleted");
			String insert = form.getFirst("inserted");
			if (insert==null) {
				insert="[]";
			}
			if (delete==null) {
				delete="[]";
			}
			if (update==null) {
				update="[]";
			}
			List<?> updated;
			List<?> deleted;
			List<?> inserted;
			switch (type) {
			case 1://填空
				try {
					//			if (update!=null&&!(update.equals("[]"))) {//写成！=会失效
					updated = new ArrayList<Completion>(JSONArray.parseArray(update, Completion.class));
					//	}
					//			if (delete!=null&&!(delete.equals("[]"))) {
					deleted = new ArrayList<Completion>(JSONArray.parseArray(delete, Completion.class));
					//			}
					//			if (insert!=null&&!(insert.equals("[]"))) {
					inserted = new ArrayList<Completion>(JSONArray.parseArray(insert, Completion.class));
					//			}
					if (updated.size() > 0) {
						adminDAO.updateCompletions((List<Completion>)updated);
					}
					if (deleted.size() > 0) {
						adminDAO.deletetableRows("tb_completion",(List<QuestionType>) deleted);
					}
					if (inserted.size() > 0) {
						adminDAO.insertCompletion((List<Completion>)inserted);
					}
					session.commit();
					return true;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 2://判断
				updated = new ArrayList<Judgment>(JSONArray.parseArray(update, Judgment.class));
				deleted = new ArrayList<Judgment>(JSONArray.parseArray(delete, Judgment.class));
				inserted = new ArrayList<Judgment>(JSONArray.parseArray(insert, Judgment.class));
				try {
					if (updated.size() > 0) {
						adminDAO.updateAll("tb_judgment",(List<QuestionType>)updated);
					}
					if (deleted.size() > 0) {
						adminDAO.deletetableRows("tb_judgment",(List<QuestionType>) deleted);
					}
					if (inserted.size() > 0) {
						adminDAO.insertAll("tb_judgment",(List<QuestionType>)inserted);
					}
					session.commit();
					return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3://选择
				updated = new ArrayList<Choice>(JSONArray.parseArray(update, Choice.class));
				deleted = new ArrayList<Choice>(JSONArray.parseArray(delete, Choice.class));
				inserted = new ArrayList<Choice>(JSONArray.parseArray(insert, Choice.class));
				try {
					if (updated.size() > 0) {
						adminDAO.updateChoices("tb_choice",(List<Choice>)updated);
					}
					if (deleted.size() > 0) {
						adminDAO.deletetableRows("tb_choice",(List<QuestionType>) deleted);
					}
					if (inserted.size() > 0) {
						adminDAO.insertChoices("tb_choice",(List<Choice>)inserted);
					}
					session.commit();
					return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4://简答
				updated = new ArrayList<Question>(JSONArray.parseArray(update, Question.class));
				deleted = new ArrayList<Question>(JSONArray.parseArray(delete, Question.class));
				inserted = new ArrayList<Question>(JSONArray.parseArray(insert, Question.class));
				try {
					if (updated.size() > 0) {
						adminDAO.updateAll("tb_question",(List<QuestionType>)updated);
					}
					if (deleted.size() > 0) {
						adminDAO.deletetableRows("tb_question",(List<QuestionType>) deleted);
					}
					if (inserted.size() > 0) {
						adminDAO.insertAll("tb_question",(List<QuestionType>)inserted);
					}
					session.commit();
					return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			return false;
		} finally {
			if (session != null)
				session.close();
		}		
	}
	
	/**
	 * 安全题库增删改查
	 * @param type
	 * @param form
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@POST
	@Path("/getsafetyChange/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getSafetyQustionsChange(@PathParam("type") int type,MultivaluedMap<String, String> form ) {
		SqlSession session = null;
     	try {
     		session = DatabaseUtils.getSessionFactory().openSession();
			// 数据库表操作
			IgetAdminInfo adminDAO = session.getMapper(IgetAdminInfo.class);
			String update = form.getFirst("updated");
			String delete = form.getFirst("deleted");
			String insert = form.getFirst("inserted");
			if (insert==null) {
				insert="[]";
			}
			if (delete==null) {
				delete="[]";
			}
			if (update==null) {
				update="[]";
			}
			List<?> updated;
			List<?> deleted;
			List<?> inserted;
			switch (type) {
			case 2://判断
				updated = new ArrayList<Judgment>(JSONArray.parseArray(update, Judgment.class));
				deleted = new ArrayList<Judgment>(JSONArray.parseArray(delete, Judgment.class));
				inserted = new ArrayList<Judgment>(JSONArray.parseArray(insert, Judgment.class));
				try {
					if (updated.size() > 0) {
						adminDAO.updateAll("safety_judgment",(List<QuestionType>)updated);
					}
					if (deleted.size() > 0) {
						adminDAO.deletetableRows("safety_judgment",(List<QuestionType>) deleted);
					}
					if (inserted.size() > 0) {
						adminDAO.insertAll("safety_judgment",(List<QuestionType>)inserted);
					}
					session.commit();
					return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3://选择
				updated = new ArrayList<Choice>(JSONArray.parseArray(update, Choice.class));
				deleted = new ArrayList<Choice>(JSONArray.parseArray(delete, Choice.class));
				inserted = new ArrayList<Choice>(JSONArray.parseArray(insert, Choice.class));
				try {
					if (updated.size() > 0) {
						adminDAO.updateChoices("safety_choice",(List<Choice>)updated);
					}
					if (deleted.size() > 0) {
						adminDAO.deletetableRows("safety_choice",(List<QuestionType>) deleted);
					}
					if (inserted.size() > 0) {
						adminDAO.insertChoices("safety_choice",(List<Choice>)inserted);
					}
					session.commit();
					return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			return false;
		} finally {
			if (session != null)
				session.close();
		}		
	}

	/**
	 * 题库搜索
	 * @param type
	 * @param keyWords
	 * @return
	 */
	@POST
    @Path("/getSearch/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public String doSearch(@PathParam("type") int type,@FormParam("Key") String keyWords){
		SqlSession session = null;
		String tableName = "";
		List<?> questions = null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			// 数据库表操作
			IgetAdminInfo userDAO = session.getMapper(IgetAdminInfo.class);
			String rtn="{'total':0,'rows':[]}";//eazyui数据传输格式
			switch (type) {
			case 1:
				tableName="tb_completion";
				questions=userDAO.selectComSearch(tableName, keyWords);
				break;
			case 2:
				tableName="tb_judgment";
				questions=userDAO.selectSearch(tableName, keyWords);
				break;
			case 3:
				tableName="tb_choice";
				questions=userDAO.selectChoiceSearch(tableName, keyWords);
				break;
			case 4:
				tableName="tb_question";
				questions=userDAO.selectSearch(tableName, keyWords);
				break;
			}
			int total= questions.size();//转换成分json使用
			Gson gson=new Gson();
			String xx=gson.toJson(questions);
			rtn="{\"total\":"+total+",\"rows\":"+xx+"}";
			return rtn;
			
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * 安全题库搜索
	 * @param type
	 * @param keyWords
	 * @return
	 */
	@POST
    @Path("/getsafetySearch/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public String doSafetySearch(@PathParam("type") int type,@FormParam("Key") String keyWords){
		SqlSession session = null;
		String tableName = "";
		List<?> questions = null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			// 数据库表操作
			IgetAdminInfo userDAO = session.getMapper(IgetAdminInfo.class);
			String rtn="{'total':0,'rows':[]}";//eazyui数据传输格式
			switch (type) {
			case 2:
				tableName="safety_judgment";
				questions=userDAO.selectSearch(tableName, keyWords);
				break;
			case 3:
				tableName="safety_choice";
				questions=userDAO.selectChoiceSearch(tableName, keyWords);
				break;
			}
			int total= questions.size();//转换成分json使用
			Gson gson=new Gson();
			String xx=gson.toJson(questions);
			rtn="{\"total\":"+total+",\"rows\":"+xx+"}";
			return rtn;
			
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * 通过时间搜索查询考试编号
	 * @param starTime
	 * @param overTime
	 * @return
	 */
	@POST
    @Path("/searchExamId")
	@Produces(MediaType.APPLICATION_JSON)
	public String getExamId(@FormParam("startTime")String starTime,@FormParam("overTime")String overTime){
		SqlSession session = null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			IgetAdminInfo userDAO = session.getMapper(IgetAdminInfo.class);
			List<Record> LExamId = userDAO.selectExamId(starTime, overTime);//修改为record类为了增加显示
			JSONArray array = new JSONArray();
			if (LExamId.size()==0) {
				return "0";
			}
			for (int i = 0; i < LExamId.size(); i++) {
				 JSONObject map=new JSONObject();
				 map.put("id", (i+1));
				 map.put("text", LExamId.get(i).getExam_id()+" "+LExamId.get(i).getExam_date());//2017.8.15修改为显示考卷编号加时间
			     array.add(map);
			}
			return array.toJSONString();
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * 通过指定考试编号，查询该编号下考生的全部信息w
	 * @param ExamId
	 * @return
	 */
	@POST
    @Path("/getEmployeeMark")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMark(@FormParam("ExamId")String SExamId){
		SqlSession session = null;
		try {
			
			//int ExamId = Integer.parseInt(SExamId.substring(0,1));//截取第一数字考试编号
			
			//20190828修改读取试卷编号的方式
			String b[] = SExamId.split(" ");
			int ExamId = Integer.parseInt(b[0]);
			
			session = DatabaseUtils.getSessionFactory().openSession();
			IgetAdminInfo userDAO = session.getMapper(IgetAdminInfo.class);
			List<Mark> employeeMark=userDAO.selectExamScore(ExamId);
			String rtn="{'total':0,'rows':[]}";//eazyui数据传输格式
			int total= employeeMark.size();
			if (total==0) {
				return "0";
			}
			Gson gson=new Gson();
			String xx=gson.toJson(employeeMark);
			rtn="{\"total\":"+total+",\"rows\":"+xx+"}";
			return rtn;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * 查询所有未评卷的简答题
	 * @return
	 * @throws Exception 
	 */
	@GET
    @Path("/getAllQuestionAnswer")
	@Produces(MediaType.APPLICATION_JSON)
	public String getQusetionAnswer() throws Exception{
		SqlSession session = null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			IgetAdminInfo userDAO = session.getMapper(IgetAdminInfo.class);
			IgetExamMapper examDao= session.getMapper(IgetExamMapper.class);
			Record examRecord = examDao.selectMaxExamdateRecord();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //旧版时间计算以后不再使用，懒得改了都测试过了
			Date examdate,nowdate;
			nowdate= new Date();
			examdate= format.parse(examRecord.getExam_date());//要抛出异常的
			if (nowdate.getTime()>examdate.getTime()) {//最近一次考试结束移除所有考生数据（防止考试提交失败）
				KSInfoManager.getManager().removeAll();
			}
			List<Qanswer> qanswer=userDAO.selectAllUserAnswer();//2017.8.15分数写入了model中不再单独发分数值
			String rtn="{'total':0,'rows':[]}";//eazyui数据传输格式
			int total= qanswer.size();
			Gson gson=new Gson();
			String xx=gson.toJson(qanswer);
			rtn="{\"total\":"+total+",\"rows\":"+xx+"}";
//			JSONObject y = JSONObject.parseObject(rtn);
//			JSONObject map=new JSONObject();
//			map.put("Score", examRecord.getQA_Score());
//			map.put("Data",y);
//			return map.toJSONString();
			return rtn;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * 核对用户答案分数，上传数据库
	 * @param qScore
	 */
	@POST
    @Path("/CheckQanswerScore")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkQanswer(@FormParam("SCORE") String qScore){
		SqlSession session = null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			IgetAdminInfo userDao = session.getMapper(IgetAdminInfo.class);
			Qanswer qanswer= JSON.parseObject(qScore, Qanswer.class);
			IgetExamMapper examDao= session.getMapper(IgetExamMapper.class);
			Record examRecord = examDao.selectMaxExamdateRecord();
			Mark employeeMark =  userDao.selectUserMark(examRecord.getExam_id(), qanswer.getEmployee_id());//测试用考卷一
			float questionScore = employeeMark.getQuestion_score();
			float Sum = employeeMark.getScore_sum();
			questionScore+=qanswer.getQuestion_score();
			Sum+=qanswer.getQuestion_score();
			userDao.updateQanswer(qanswer.getQuestion_score(), qanswer.getNum_id());
			userDao.upadteOneMark(questionScore, Sum, examRecord.getExam_id(),qanswer.getEmployee_id());
			session.commit();
			return true;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	
	/**
	 * 更新最后考试成绩
	 * @param form
	 * @return
	 */
	@POST
    @Path("/setFinalScore")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean setFinalScore(MultivaluedMap<String, String> form ){
		SqlSession session = null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			IgetAdminInfo userDao = session.getMapper(IgetAdminInfo.class);
			String update = form.getFirst("updated");
			List<Mark> updated = new ArrayList<Mark>(JSONArray.parseArray(update, Mark.class));
			userDao.updateAllMark(updated);
			session.commit();
			return true;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	@GET
    @Path("/download")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response download(@Context HttpServletRequest request,@Context HttpServletResponse response)throws IOException{
		BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;
	    HttpSession httpsession=request.getSession();
	    SqlSession session = null;
	    try{
	    	session = DatabaseUtils.getSessionFactory().openSession();
			String userid = (String) httpsession.getAttribute("login_user");
			// 数据库表操作
			IUserMapper userDAO = session.getMapper(IUserMapper.class);
			Mark employeMark = userDAO.selectRecentMark(userid);//查询时间
			String date =employeMark.getExam_date().replaceAll(":", "");
			if (date.length()>17) {
				date=date.substring(0, 17);
			}//字符串截取固定格式
		    String filePath ="D:/doc/"+date+"/"+userid+".pdf";//"D:/doc/3306.pdf";
		    String fileName="考卷";
		    bis = new BufferedInputStream(new FileInputStream(filePath));
		    bos = new BufferedOutputStream(response.getOutputStream());
		 
		    long fileLength = new File(filePath).length();
		 
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("multipart/form-data");
		    /*
		     * 解决各浏览器的中文乱码问题
		     */
		    String userAgent = request.getHeader("User-Agent");
		    byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes()
		            : fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题
		    fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
	//	    response.setHeader("Content-disposition",
	//	            String.format("attachment; filename=\"%s\".pdf", fileName));
		 
		    response.setHeader("Content-Length", String.valueOf(fileLength));
		    response.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		    response.setHeader("Content-Disposition", "attachment; filename=Examination" + ".pdf");//编写文件名
		    byte[] buff = new byte[2048];
		    int bytesRead;
		    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
		        bos.write(buff, 0, bytesRead);
		    }
		    bis.close();
		    bos.close();
			return Response.ok(buff, MediaType.APPLICATION_OCTET_STREAM).build();
	    }finally {
			if (session != null)
				session.close();
		}
	}
	
	@GET
    @Path("/SafetyEXdownload")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response SafetyEXdownload(@Context HttpServletRequest request,@Context HttpServletResponse response)throws IOException{
		BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;
	    HttpSession httpsession=request.getSession();
	    SqlSession session = null;
	    try{
	    	session = DatabaseUtils.getSessionFactory().openSession();
			String userid = (String) httpsession.getAttribute("login_user");
			// 数据库表操作
			IUserMapper userDAO = session.getMapper(IUserMapper.class);
			Mark employeMark = userDAO.selectRecentSafetyMark(userid);//查询时间
			String date =employeMark.getExam_date().replaceAll(":", "");
			if (date.length()>17) {
				date=date.substring(0, 17);
			}//字符串截取固定格式
		    String filePath ="D:/SafeDoc/"+date+"/"+userid+".pdf";//"D:/doc/3306.pdf";
		    String fileName="考卷";
		    bis = new BufferedInputStream(new FileInputStream(filePath));
		    bos = new BufferedOutputStream(response.getOutputStream());
		 
		    long fileLength = new File(filePath).length();
		 
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("multipart/form-data");
		    /*
		     * 解决各浏览器的中文乱码问题
		     */
		    String userAgent = request.getHeader("User-Agent");
		    byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes()
		            : fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题
		    fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
	//	    response.setHeader("Content-disposition",
	//	            String.format("attachment; filename=\"%s\".pdf", fileName));
		 
		    response.setHeader("Content-Length", String.valueOf(fileLength));
		    response.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		    response.setHeader("Content-Disposition", "attachment; filename=Examination" + ".pdf");//编写文件名
		    byte[] buff = new byte[2048];
		    int bytesRead;
		    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
		        bos.write(buff, 0, bytesRead);
		    }
		    bis.close();
		    bos.close();
			return Response.ok(buff, MediaType.APPLICATION_OCTET_STREAM).build();
	    }finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * 考卷类型查询
	 * @return
	 */
	@GET
    @Path("/getAllRecord")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllRecord(){
		SqlSession session = null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			IgetAdminInfo userDAO = session.getMapper(IgetAdminInfo.class);
			List<Record> allRecord=userDAO.selectAllRecord();//查询所有考试规则
			String rtn="{'total':0,'rows':[]}";//eazyui数据传输格式
			int total= allRecord.size();
			Gson gson=new Gson();
			String xx=gson.toJson(allRecord);
			rtn="{\"total\":"+total+",\"rows\":"+xx+"}";
			return rtn;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * k考试规则表增删该查
	 * 写的不是很好以后有空再改
	 * @param form
	 * @return
	 */
	@POST
	@Path("/getRecordChange")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getRecordChange(MultivaluedMap<String, String> form )  {
		SqlSession session = null;
     	try {
     		session = DatabaseUtils.getSessionFactory().openSession();
			// 数据库表操作
			IgetAdminInfo adminDAO = session.getMapper(IgetAdminInfo.class);
			String update = form.getFirst("updated");
			String delete = form.getFirst("deleted");
			String insert = form.getFirst("inserted");
			if (insert==null) {
				insert="[]";
			}
			if (delete==null) {
				delete="[]";
			}
			if (update==null) {
				update="[]";
			}
			List<?> updated;
			List<?> deleted;
			List<?> inserted;
			updated = new ArrayList<Record>(JSONArray.parseArray(update, Record.class));
			deleted = new ArrayList<Record>(JSONArray.parseArray(delete, Record.class));
			inserted = new ArrayList<Record>(JSONArray.parseArray(insert, Record.class));
			if (updated.size() > 0) {
				adminDAO.updateRecords((List<Record>)updated);
			}
			if (deleted.size() > 0) {
				adminDAO.deleteRecords((List<Record>) deleted);
			}
			if (inserted.size() > 0) {
				adminDAO.insertRecords((List<Record>)inserted);
			}
			session.commit();
			return true;
			
		} finally {
			if (session != null)
				session.close();
		}		
	}
	
	/**
	 * 用户表管理查询用户信息
	 * @return
	 */
	@GET
    @Path("/getAllUserInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUserInfo(){
		SqlSession session = null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			IgetAdminInfo userDAO = session.getMapper(IgetAdminInfo.class);
			List<User> employeeInfo=userDAO.selectAllUserInfo();
			String rtn="{'total':0,'rows':[]}";//eazyui数据传输格式
			int total= employeeInfo.size();
			Gson gson=new Gson();
			String xx=gson.toJson(employeeInfo);
			rtn="{\"total\":"+total+",\"rows\":"+xx+"}";
			return rtn;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * 查询用户信息
	 * @param keyWords
	 * @return
	 */
	@POST
    @Path("/searchUserInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchUserInfo(@FormParam("Key") String keyWords){
		SqlSession session = null;
		try {
			session = DatabaseUtils.getSessionFactory().openSession();
			IgetAdminInfo userDAO = session.getMapper(IgetAdminInfo.class);
			List<User> employeeInfo=userDAO.searchUserInfo(keyWords);
			String rtn="{'total':0,'rows':[]}";//eazyui数据传输格式
			int total= employeeInfo.size();
			Gson gson=new Gson();
			String xx=gson.toJson(employeeInfo);
			rtn="{\"total\":"+total+",\"rows\":"+xx+"}";
			return rtn;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * 用户信息表增删该查
	 * 写的不是很好以后有空再改尽量将所有批量修改写在一个url
	 * @param form
	 * @return
	 */
	@POST
	@Path("/getUserChange")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getChange(MultivaluedMap<String, String> form )  {
		SqlSession session = null;
     	try {
     		session = DatabaseUtils.getSessionFactory().openSession();
			// 数据库表操作
			IgetAdminInfo adminDAO = session.getMapper(IgetAdminInfo.class);
			String update = form.getFirst("updated");
			String delete = form.getFirst("deleted");
			String insert = form.getFirst("inserted");
			if (insert==null) {
				insert="[]";
			}
			if (delete==null) {
				delete="[]";
			}
			if (update==null) {
				update="[]";
			}
			List<?> updated;
			List<?> deleted;
			List<?> inserted;
			updated = new ArrayList<User>(JSONArray.parseArray(update, User.class));
			deleted = new ArrayList<User>(JSONArray.parseArray(delete, User.class));
			inserted = new ArrayList<User>(JSONArray.parseArray(insert, User.class));
			if (updated.size() > 0) {
				adminDAO.updateUserInfos((List<User>)updated);
			}
			if (deleted.size() > 0) {
				adminDAO.deleteUserInfos((List<User>) deleted);
			}
			if (inserted.size() > 0) {
				adminDAO.insertUserInfos((List<User>)inserted);
			}
			session.commit();
			return true;
			
		} finally {
			if (session != null)
				session.close();
		}		
	}
}
