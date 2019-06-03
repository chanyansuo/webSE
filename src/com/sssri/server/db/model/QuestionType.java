package com.sssri.server.db.model;

public class QuestionType {
	/**
	 * 题目序号
	 */
	protected int num_Id;
	
	/**
	 * 题目内容
	 */
	protected String content;
	
	/**
	 * 题目类型
	 */
	protected int type;
	
	/**
	 * 题目答案
	 */
	protected String answer;
	
	/**
	 * 题目分类，保密安全
	 */
	protected int category;
	
	/**
	 * 题目难度
	 */
	protected int difficult;
	
	/**
	 * 用户答案
	 */
	protected String userAnswer;
	
	/**
	 * 用户得分
	 */
	protected double userScore;
	
	/**
	 * 题目回答状态[0,4]表示该题目状态0未作，1已做，2做对，3做错,4未作没啥用
	 */
	protected int itemStatus;

	public int getNum_Id() {
		return num_Id;
	}

	public void setNum_Id(int num_Id) {
		this.num_Id = num_Id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getCategeory() {
		return category;
	}

	public void setCategeory(int category) {
		this.category = category;
	}

	public int getDiffcult() {
		return difficult;
	}

	public void setDiffcult(int diffcult) {
		this.difficult = diffcult;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public double getUserScore() {
		return userScore;
	}

	public void setUserScore(double userScore) {
		this.userScore = userScore;
	}

	public int getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(int itemStatus) {
		this.itemStatus = itemStatus;
	}

	@Override
	public String toString() {
		return "QuestionType [num_Id=" + num_Id + ", content=" + content + ", type=" + type + ", answer=" + answer
				+ ", categeory=" + category + ", diffcult=" + difficult + ", userAnswer=" + userAnswer + ", userScore="
				+ userScore + ", itemStatus=" + itemStatus + "]";
	}


	

}
