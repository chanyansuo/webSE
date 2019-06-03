package com.sssri.server.restful;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sssri.server.db.DatabaseUtils;
import com.sssri.server.db.mapper.IUserMapper;
import com.sssri.server.db.model.Mark;
import com.sssri.server.db.model.User;

/**
 * 
 * 黄老大例子
 * @author huangjw(mailto:huangjw@primeton.com)
 *
 */
@Path("/user")
@Consumes({"application/json", "application/x-www-form-urlencoded"})
public class UserRestService {

	@Path("/getUserInfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUsersInfo(@Context HttpServletRequest request){
		SqlSession session = null;
		User result = null;
		try{
			session = DatabaseUtils.getSessionFactory().openSession();
			HttpSession httpsession=request.getSession();
			 String userid = (String) httpsession.getAttribute("login_user");
			// 数据库表操作
			IUserMapper userDAO = session.getMapper(IUserMapper.class);
//			User result = userDAO.selectUserByID(1);
//			User result2 = userDAO.selectUserByName("设定");
//			User result = session.selectOne("com.sssri.server.db.UserMapper.selectUserByID", 1);
//			result = session.selectList("com.sssri.server.db.UserMapper.selectUserByName", "%设定%");
			result = userDAO.selectUserByID(userid);
			Mark employeMark = userDAO.selectRecentMark(userid);
			Gson gson = new Gson();
			String userInfo= gson.toJson(result, User.class);
			JSONObject y = JSONObject.parseObject(userInfo);
			JSONObject map=new JSONObject();
			double finalScore =0;
			String examDate="";
			if (employeMark!=null) {//防止未查到信息
				finalScore = employeMark.getFinal_score();
				examDate= employeMark.getExam_date();
			}
			map.put("FinalScore", finalScore);
			map.put("ExamDate", examDate);
			map.put("Data",y);
			return map.toJSONString();
		} finally {
			if (session != null)
				session.close();
		}
		
	}
	
	/**
	 * 用户登录
	 * @param userid
	 * @param password
	 * @return
	 */
	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public int login(@FormParam("userid")String Employee_id, @FormParam("password")String pwd, @Context HttpServletRequest request){//0登录失败，1用户登陆，2管理员登陆
		SqlSession session = null;
		try{
			session = DatabaseUtils.getSessionFactory().openSession();
			HttpSession httpsession=request.getSession();
			if (Employee_id.contains("admin")) {
				boolean ret= loginAdmin(Employee_id.replace("admin", ""), pwd, request);
				if (ret) {
					return 2;
				}
				return 0;
			}else{
				// 数据库表操作
				IUserMapper userDAO = session.getMapper(IUserMapper.class);
				User userInfo = userDAO.selectUserByID(Employee_id);
				if(userInfo == null){
					return 0;
				}
				boolean ret= pwd.equals(userInfo.getPwd());
				if(ret) {
					httpsession.setAttribute("login_user", Employee_id);//存储在后台
					httpsession.setAttribute("login_name", userInfo.getName());
					httpsession.setMaxInactiveInterval(10800);
					return 1;
				}
				return 0;
			}
			
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	private boolean loginAdmin(String Employee_id, String pwd,  HttpServletRequest request){
		SqlSession session = null;
		try{
			session = DatabaseUtils.getSessionFactory().openSession();
			HttpSession httpsession=request.getSession();
			// 数据库表操作
			IUserMapper userDAO = session.getMapper(IUserMapper.class);
			User userInfo = userDAO.selectUserByID(Employee_id);
			if(userInfo == null){
				return false;
			}
			if (userInfo.getUser_type()<2) {
				return false;
			}
			boolean ret= pwd.equals(userInfo.getPwd());
			if(ret) {
				httpsession.setAttribute("login_user", Employee_id);
				httpsession.setAttribute("login_name", userInfo.getName());
				httpsession.setMaxInactiveInterval(10800);
			}
			return ret;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * 登出
	 * @param request
	 * @return
	 */
	@Path("/logout")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(@Context HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return Response.status(Status.OK).entity("").build();
	}
	
	@Path("/changePwd")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean changePwd(@FormParam("OldPwd") String OldPsw,@FormParam("NewPwd") String NewPsw,@Context HttpServletRequest request){
		SqlSession session = null;
		try{
			session = DatabaseUtils.getSessionFactory().openSession();
			HttpSession httpsession=request.getSession();
			String employeeId = (String) httpsession.getAttribute("login_user");
			// 数据库表操作
			IUserMapper userDAO = session.getMapper(IUserMapper.class);
			User userInfo = userDAO.selectUserByID(employeeId);
			if (userInfo.getPwd().equals(OldPsw)) {
				userDAO.UpdateUserPw(NewPsw, employeeId);
				session.commit();
				return true;
			}
			return false;
		} finally {
			if (session != null)
				session.close();
		}

	}
}
