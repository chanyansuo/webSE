package com.sssri.server.db.model;

/**
 * @author sssri
 *表examination
 */
public class ReExam {
//	DROP TABLE IF EXISTS `tb_examination`;
//	CREATE TABLE `tb_examination` (
//	  `Exam_paper_id` int(11) NOT NULL,
//	  `Content` varchar(400) DEFAULT NULL,
//	  `User_id` int(11) DEFAULT NULL,
//	  `Exam_id` int(11) DEFAULT NULL,
//	  PRIMARY KEY (`Exam_paper_id`)
//	) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	private int Exam_paper_id;
	
	private String Content;
	
	private String Employee_id;
	
	private int Exam_id;

	public int getExam_paper_id() {
		return Exam_paper_id;
	}

	public void setExam_paper_id(int exam_paper_id) {
		Exam_paper_id = exam_paper_id;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
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

	@Override
	public String toString() {
		return "ReExam [Exam_paper_id=" + Exam_paper_id + ", Content=" + Content + ", User_id=" + Employee_id + ", Exam_id="
				+ Exam_id + "]";
	}
	
	
}
