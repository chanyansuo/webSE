package com.sssri.server.db.model;

/**
 * Num_id` int(11) NOT NULL AUTO_INCREMENT,
 * Employee_id` varchar(20) DEFAULT NULL,
 * Exam_id` varchar(20) DEFAULT NULL,
 * Question_id` varchar(20) DEFAULT NULL,
 * User_answer` varchar(600) DEFAULT NULL,
 * Statues` int(11) DEFAULT NULL,
 * Question_Score` float(255,0) DEFAULT NULL,
 * PRIMARY KEY (`Num_id`)
 * @author sssri
 */
public class Qanswer {
	public Qanswer() {
		setUser_answer("");
	}

	private int Num_id;
	
	private String Employee_id;
	
	private int Exam_id;
	
	private String Exam_date;
	
	private int Question_position;
	
	private int Question_id;
	
	private String Content;//联合查询存在
	
	private String Answer;//联合查询存在
	
	private float QA_Score;//联合查询存在存储考试得分
	
	private String User_answer;
	
	private int Statues;//表示是否完成评卷0表示未完成，1表示完成
	
	private double Question_score;

	public int getNum_id() {
		return Num_id;
	}

	public void setNum_id(int num_id) {
		Num_id = num_id;
	}

	public String getEmployee_id() {
		return Employee_id;
	}

	public void setEmployee_id(String employee_id) {
		Employee_id = employee_id;
	}

	public int getExam_id() {
		return Exam_id;
	}

	public void setExam_id(int exam_id) {
		Exam_id = exam_id;
	}

	public String getExam_date() {
		return Exam_date;
	}

	public void setExam_date(String exam_date) {
		Exam_date = exam_date;
	}

	public int getQuestion_position() {
		return Question_position;
	}

	public void setQuestion_position(int question_position) {
		Question_position = question_position;
	}

	public int getQuestion_id() {
		return Question_id;
	}

	public void setQuestion_id(int question_id) {
		Question_id = question_id;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public String getUser_answer() {
		return User_answer;
	}

	public void setUser_answer(String user_answer) {
		User_answer = user_answer;
	}

	public int getStatues() {
		return Statues;
	}

	public void setStatues(int statues) {
		Statues = statues;
	}

	public double getQuestion_score() {
		return Question_score;
	}

	public void setQuestion_score(double question_score) {
		Question_score = question_score;
	}
}
