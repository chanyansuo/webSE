package com.sssri.server.db.model;

/**
 * 用户表模型
 * 
 * @author xmj
 *
 */
public class User {
//	 `Employee_id` varchar(20) NOT NULL,
//	  `Pwd` varchar(20) DEFAULT NULL,
//	  `User_type` int(11) DEFAULT NULL,
//	  `User_code` int(11) DEFAULT NULL,
//	  `Sex` varchar(10) DEFAULT NULL,
//	  `Name` varchar(20) DEFAULT NULL,
//	  `Department` varchar(20) DEFAULT NULL,
//	  `Online` int(11) DEFAULT NULL,
//	  `Timekeeping` varchar(20) DEFAULT NULL,
//	  `User_position` int(11) DEFAULT NULL,
//	  PRIMARY KEY (`Employee_id`)
//	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	private String Employee_id;

	private String Pwd;

	private int User_type;

	private int User_code;

	private String Sex;

	private String Name;
	
	private String Department;
	
	private int Online;
	
	private String Timekeeping;
	
	private String User_position;
	
	private int Now_Exam;

	public String getEmployee_id() {
		return Employee_id;
	}

	public void setEmployee_id(String employee_id) {
		Employee_id = employee_id;
	}

	public String getPwd() {
		return Pwd;
	}

	public void setPwd(String pwd) {
		Pwd = pwd;
	}

	public int getUser_type() {
		return User_type;
	}

	public void setUser_type(int user_type) {
		User_type = user_type;
	}

	public int getUser_code() {
		return User_code;
	}

	public void setUser_code(int user_code) {
		User_code = user_code;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public int getOnline() {
		return Online;
	}

	public void setOnline(int online) {
		Online = online;
	}

	public String getTimekeeping() {
		return Timekeeping;
	}

	public void setTimekeeping(String timekeeping) {
		Timekeeping = timekeeping;
	}

	public String getUser_position() {
		return User_position;
	}

	public void setUser_position(String user_position) {
		User_position = user_position;
	}

	public int getNow_Exam() {
		return Now_Exam;
	}

	public void setNow_Exam(int now_Exam) {
		Now_Exam = now_Exam;
	}


	
}
