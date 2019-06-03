package com.sssri.server.db.model;

import java.util.Date;

//`Mark_id` int(11) NOT NULL,
//`User_id` int(11) DEFAULT NULL,
//`Completon_score` float(11,1) DEFAULT NULL,
//`Judgment_score` float(11,1) DEFAULT NULL,
//`Choice_score` float(11,1) DEFAULT NULL,
//`Mchoice_socre` float(11,1) DEFAULT NULL,
//`Question_score` float(11,1) DEFAULT NULL,
//`Score_sum` float(11,1) DEFAULT NULL,
//`Exam_paper_id` int(11) DEFAULT NULL,
//`Exam_time` varchar(20) DEFAULT NULL,
//`Exam_syllabus` int(11) DEFAULT NULL,
//PRIMARY KEY (`Num_id`)
/**
 * 成绩表
 * @author sssri
 *
 */
public class Mark {
	
	private int Mark_id;
	
	private String Employee_id;
	
	private String Name;//只是连表不想弄个新个model
	
	private float Completion_score;
	
	private float  Choice_score;
	
	private float Judgment_score;
	
	private float Mchoice_score;
	
	private float Question_score;
	
	private float Score_sum;
	
	private float Float_score;
	
	private float Final_score;
	
	private int Exam_paper_id;
	
	private String Exam_date;
	
	private String Remark;
	
	private int Exam_syllabus;//保密安全?

	public int getMark_id() {
		return Mark_id;
	}

	public void setMark_id(int mark_id) {
		Mark_id = mark_id;
	}

	public String getEmployee_id() {
		return Employee_id;
	}

	public void setEmployee_id(String employee_id) {
		Employee_id = employee_id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getCompletion_score() {
		return Completion_score;
	}

	public void setCompletion_score(float completion_score) {
		Completion_score = completion_score;
	}

	public float getChoice_score() {
		return Choice_score;
	}

	public void setChoice_score(float choice_score) {
		Choice_score = choice_score;
	}

	public float getJudgment_score() {
		return Judgment_score;
	}

	public void setJudgment_score(float judgment_score) {
		Judgment_score = judgment_score;
	}

	public float getMchoice_score() {
		return Mchoice_score;
	}

	public void setMchoice_score(float mchoice_score) {
		Mchoice_score = mchoice_score;
	}

	public float getQuestion_score() {
		return Question_score;
	}

	public void setQuestion_score(float question_score) {
		Question_score = question_score;
	}

	public float getScore_sum() {
		return Score_sum;
	}

	public void setScore_sum(float score_sum) {
		Score_sum = score_sum;
	}

	public float getFloat_score() {
		return Float_score;
	}

	public void setFloat_score(float float_score) {
		Float_score = float_score;
	}

	public float getFinal_score() {
		return Final_score;
	}

	public void setFinal_score(float final_score) {
		Final_score = final_score;
	}

	public int getExam_paper_id() {
		return Exam_paper_id;
	}

	public void setExam_paper_id(int exam_paper_id) {
		Exam_paper_id = exam_paper_id;
	}

	public String getExam_date() {
		return Exam_date;
	}

	public void setExam_date(String exam_date) {
		Exam_date = exam_date;
	}

	public int getExam_syllabus() {
		return Exam_syllabus;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public void setExam_syllabus(int exam_syllabus) {
		Exam_syllabus = exam_syllabus;
	}

	@Override
	public String toString() {
		return "Mark [Mark_id=" + Mark_id + ", Employee_id=" + Employee_id + ", Name=" + Name + ", Completion_score="
				+ Completion_score + ", Choice_score=" + Choice_score + ", Judgment_score=" + Judgment_score
				+ ", Mchoice_score=" + Mchoice_score + ", Question_score=" + Question_score + ", Score_sum=" + Score_sum
				+ ", Float_score=" + Float_score + ", Final_score=" + Final_score + ", Exam_paper_id=" + Exam_paper_id
				+ ", Exam_date=" + Exam_date + ", Remark=" + Remark + ", Exam_syllabus=" + Exam_syllabus + "]";
	}

	


}
