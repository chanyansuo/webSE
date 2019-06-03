package com.sssri.test.user.service;



//import com.sssri.test.user.dao.UserDao;
//import com.sssri.test.user.domain.User;
//
//public class UserService {
//	private UserDao userDao=new UserDao();
//	
//	public User findByid(String id){
//		User user=userDao.findByid(id);
//		return user;
//	}
//
//	public User login(User form) throws UserException {
//		User user=userDao.findByid(form.getUserid());
//		if(user==null) 
//			throw new UserException("用户名不存在");
//		if(!user.getUserpwd().equals(form.getUserpwd()))
//			throw new UserException("密码错误");
//		return user;
//	}
//	
//}
