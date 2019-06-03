package com.sssri.server.db.model;

/**
 * `Exam_id` int(11) NOT NULL,
  *`Attendance_expect` int(11) DEFAULT NULL,
  *`Completion_num1` int(11) DEFAULT NULL,
  *`Completion_num2` int(11) DEFAULT NULL,
  *`Completion_num3` int(11) DEFAULT NULL,
  *`Completion_num4` int(11) DEFAULT NULL,
  *`Completion_num5` int(11) DEFAULT NULL,
  *`Completion_Score` float(20,1) DEFAULT NULL,
  *`Judgment_num` int(11) DEFAULT NULL,
  *`Judgment_Score` float(20,1) DEFAULT NULL,
  *`Choice_num` int(11) DEFAULT NULL,
  *`Choice_Score` float(20,1) DEFAULT NULL,
  *`MulChoice_num` int(11) DEFAULT NULL,
  *`MulChoice_Score` float(20,1) DEFAULT NULL,
  *`QA_num` int(11) DEFAULT NULL,
  *`QA_Score` float(20,1) DEFAULT NULL,
  *`Exam_date` varchar(20) DEFAULT NULL,
  *`Exam_time` int(11) DEFAULT NULL,
  *`Exam_syllabus` int(11) DEFAULT NULL,
  *`Attendance_reality` int(11) DEFAULT NULL,
 * 考卷模型
 * @author sssri
 *
 */
public class Record {
	
	private int Exam_id;
	
	/**
	 * 实际考试人数
	 */
	private int Attendance_expect;
	
	private int Completion_num1;
	
	private int Completion_num2;
	
	private int Completion_num3;
	
	private int Completion_num4;
	
	private int Completion_num5;
	
	private float Completion_Score;
	
	private int Judgment_num;
	
	private float Judgment_Score;
	
	private int Choice_num;
	
	private float Choice_Score;
	
	private int MulChoice_num;
	
	private float MulChoice_Score; 
	
	private int QA_num;
	
	private float QA_Score;
	
	private String Exam_date;
	
	private int Exam_time;//考试持续时间分钟计算
	
	private int Exam_syllabus;
	
	private int Attendance_reality;

	public int getExam_id() {
		return Exam_id;
	}

	public void setExam_id(int exam_id) {
		Exam_id = exam_id;
	}

	public int getAttendance_expext() {
		return Attendance_expect;
	}

	public void setAttendance_expext(int attendance_expext) {
		Attendance_expect = attendance_expext;
	}

	public int getCompletion_num1() {
		return Completion_num1;
	}

	public void setCompletion_num1(int completion_num1) {
		Completion_num1 = completion_num1;
	}

	public int getCompletion_num2() {
		return Completion_num2;
	}

	public void setCompletion_num2(int completion_num2) {
		Completion_num2 = completion_num2;
	}

	public int getCompletion_num3() {
		return Completion_num3;
	}

	public void setCompletion_num3(int completion_num3) {
		Completion_num3 = completion_num3;
	}

	public int getCompletion_num4() {
		return Completion_num4;
	}

	public void setCompletion_num4(int completion_num4) {
		Completion_num4 = completion_num4;
	}

	public int getCompletion_num5() {
		return Completion_num5;
	}

	public void setCompletion_num5(int completion_num5) {
		Completion_num5 = completion_num5;
	}

	public float getCompletion_Score() {
		return Completion_Score;
	}

	public void setCompletion_Score(float completion_Score) {
		Completion_Score = completion_Score;
	}

	public int getJudgment_num() {
		return Judgment_num;
	}

	public void setJudgment_num(int judgment_num) {
		Judgment_num = judgment_num;
	}

	public float getJudgment_Score() {
		return Judgment_Score;
	}

	public void setJudgment_Score(float judgment_Score) {
		Judgment_Score = judgment_Score;
	}

	public int getChoice_num() {
		return Choice_num;
	}

	public void setChoice_num(int choice_num) {
		Choice_num = choice_num;
	}

	public float getChoice_Score() {
		return Choice_Score;
	}

	public void setChoice_Score(float choice_Score) {
		Choice_Score = choice_Score;
	}

	public int getMulChoice_num() {
		return MulChoice_num;
	}

	public void setMulChoice_num(int mulChoice_num) {
		MulChoice_num = mulChoice_num;
	}

	public float getMulChoice_Score() {
		return MulChoice_Score;
	}

	public void setMulChoice_Score(float mulChoice_Score) {
		MulChoice_Score = mulChoice_Score;
	}

	public int getQA_num() {
		return QA_num;
	}

	public void setQA_num(int qA_num) {
		QA_num = qA_num;
	}

	public float getQA_Score() {
		return QA_Score;
	}

	public void setQA_Score(float qA_Score) {
		QA_Score = qA_Score;
	}

	public String getExam_date() {
		return Exam_date;
	}

	public void setExam_date(String exam_date) {
		Exam_date = exam_date;
	}

	public int getExam_time() {
		return Exam_time;
	}

	public void setExam_time(int exam_time) {
		Exam_time = exam_time;
	}

	public int getExam_syllabus() {
		return Exam_syllabus;
	}

	public void setExam_syllabus(int exam_syllabus) {
		Exam_syllabus = exam_syllabus;
	}

	public int getAttendance_reality() {
		return Attendance_reality;
	}

	public void setAttendance_reality(int attendance_reality) {
		Attendance_reality = attendance_reality;
	}

	@Override
	public String toString() {
		return "Record [Exam_id=" + Exam_id + ", Attendance_expect=" + Attendance_expect + ", Completion_num1="
				+ Completion_num1 + ", Completion_num2=" + Completion_num2 + ", Completion_num3=" + Completion_num3
				+ ", Completion_num4=" + Completion_num4 + ", Completion_num5=" + Completion_num5
				+ ", Completion_Score=" + Completion_Score + ", Judgment_num=" + Judgment_num + ", Judgment_Score="
				+ Judgment_Score + ", Choice_num=" + Choice_num + ", Choice_Score=" + Choice_Score + ", MulChoice_num="
				+ MulChoice_num + ", MulChoice_Score=" + MulChoice_Score + ", QA_num=" + QA_num + ", QA_Score="
				+ QA_Score + ", Exam_date=" + Exam_date + ", Exam_time=" + Exam_time + ", Exam_syllabus="
				+ Exam_syllabus + ", Attendance_reality=" + Attendance_reality + "]";
	}




}
