package com.sssri.server.db.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.sssri.server.restful.resource.IKSInfo;

/**
 * 考卷模型模型
 * @author xmj
 *
 */

public class Examination implements IKSInfo {
	public Examination() {
		setStatues("0");//初始化考卷状态
	}
	
	/**
	 * 考卷类型编号
	 */
	private int Exam_id;
	
	/**
	 * 考生考卷填空题
	 */
	public List<Completion> completion=new ArrayList<Completion>();//考生考考卷
	
	/**
	 * 考生考卷判断题
	 */
	public List<Judgment> judgment=new ArrayList<Judgment>();
	
	/**
	 * 考生考卷单选题
	 */
	public List<Choice> choice=new ArrayList<Choice>();
	
	/**
	 * 考生考卷多选题
	 */
	public List<Choice> mulchoice=new ArrayList<Choice>();
	
	/**
	 * 考生考卷简答题
	 */
	public List<Question> question=new ArrayList<Question>();
	
	/**
	 *考生工号
	 */
	private String Employee_id;
	
	/**
	 * 考卷完成状态，1为完成，0/2为未完成
	 */
	public String Statues;

	/**
	 * 增加单项分数系统
	 */
	private float Completion_Score;
	
	private float Judgment_Score;
	
	private float Choice_Score;
	
	private float MulChoice_Score; 
	
	private float QA_Score;
	
	private float Sum_Score;//为了练习模式新增加的
	
	private String startTime;//考试模式下的起始时间

	private int timeLeft;//剩余时间秒计算
	
	private String endTime;//考试模式下的结束时

	public int getExam_id() {
		return Exam_id;
	}

	public void setExam_id(int exam_id) {
		Exam_id = exam_id;
	}

	public void setCompletion(List<Completion> completion) {
		this.completion = completion;
	}

	public void setJudgment(List<Judgment> judgment) {
		this.judgment = judgment;
	}

	public void setChoice(List<Choice> choice) {
		this.choice = choice;
	}

	public void setMulchoice(List<Choice> mulchoice) {
		this.mulchoice = mulchoice;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public void setEmployee_id(String employee_id) {
		Employee_id = employee_id;
	}

	public void setStatues(String statues) {
		Statues = statues;
	}

	

	@Override
	public List<Completion> getCompletion() {
		return this.completion;
	}

	@Override
	public List<Judgment> getJudgment() {
		return this.judgment;
	}

	@Override
	public List<Choice> getChoice() {
		return this.choice;
	}

	@Override
	public List<Choice> getMulchoice() {
		return this.mulchoice;
	}

	@Override
	public List<Question> getQuestion() {
		return this.question;
	}

	@Override
	public String getEmployee_id() {
		return this.Employee_id;
	}

	@Override
	public String getStatues() {
		return this.Statues;
	}

	public float getCompletion_Score() {
		return Completion_Score;
	}

	public void setCompletion_Score(float completion_Score) {
		Completion_Score = completion_Score;
	}

	public float getJudgment_Score() {
		return Judgment_Score;
	}

	public void setJudgment_Score(float judgment_Score) {
		Judgment_Score = judgment_Score;
	}

	public float getChoice_Score() {
		return Choice_Score;
	}

	public void setChoice_Score(float choice_Score) {
		Choice_Score = choice_Score;
	}

	public float getMulChoice_Score() {
		return MulChoice_Score;
	}

	public void setMulChoice_Score(float mulChoice_Score) {
		MulChoice_Score = mulChoice_Score;
	}

	public float getQA_Score() {
		return QA_Score;
	}

	public void setQA_Score(float qA_Score) {
		QA_Score = qA_Score;
	}

	public float getSum_Score() {
		return Sum_Score;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setSum_Score(float sum_Score) {
		Sum_Score = sum_Score;
	}
	
}
