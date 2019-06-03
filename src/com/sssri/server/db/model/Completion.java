package com.sssri.server.db.model;

import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;

import sun.reflect.generics.tree.VoidDescriptor;

/**
 * 填空题模型
 * 	`Num_id` int(11) NOT NULL,
	`Content` varchar(255) DEFAULT NULL,
	`Blank_num` int(11) DEFAULT NULL,
	`Answer_one` varchar(20) DEFAULT NULL,
  	`Answer_two` varchar(20) DEFAULT NULL,
  	`Answer_three` varchar(20) DEFAULT NULL,
	`Answer_four` varchar(20) DEFAULT NULL,
	`Answer_five` varchar(20) DEFAULT NULL,
	 `Score` int(11) DEFAULT NULL,放入考卷表
	 `Category` int(11) DEFAULT NULL,
 * @author sssri
 *
 */
public class Completion extends QuestionType implements IQuestionFunction{
    public Completion() {
		this.setType(1);
		setUserAnswer("");
	}
    private int Blank_num;
    
	private String Answer_one;
	
	private String Answer_two;
	
	private String Answer_three;
	
	private String Answer_four;
	
	private String Answer_five;

	public int getBlank_num() {
		return Blank_num;
	}

	public void setBlank_num(int blank_num) {
		Blank_num = blank_num;
	}

	public String getAnswer_one() {
		return Answer_one;
	}

	public void setAnswer_one(String answer_one) {
		Answer_one = answer_one;
	}

	public String getAnswer_two() {
		return Answer_two;
	}

	public void setAnswer_two(String answer_two) {
		Answer_two = answer_two;
	}

	public String getAnswer_three() {
		return Answer_three;
	}

	public void setAnswer_three(String answer_three) {
		Answer_three = answer_three;
	}

	public String getAnswer_four() {
		return Answer_four;
	}

	public void setAnswer_four(String answer_four) {
		Answer_four = answer_four;
	}

	public String getAnswer_five() {
		return Answer_five;
	}

	public void setAnswer_five(String answer_five) {
		Answer_five = answer_five;
	}

	@Override
	public String getQuestionUniqueID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRightAnswers() {
		String rightAnswer="";
		if(Answer_one!=null||Answer_one!="")
			rightAnswer=Answer_one;
		if(Answer_two!=null||Answer_two!="")
			rightAnswer=rightAnswer+";"+Answer_two;
		if(Answer_three!=null||Answer_three!="")
			rightAnswer=rightAnswer+";"+Answer_three;
		if(Answer_four!=null||Answer_four!="")
			rightAnswer=rightAnswer+";"+Answer_four;
		if(Answer_five!=null||Answer_five!="")
			rightAnswer=rightAnswer+";"+Answer_five;
		return rightAnswer;
	}

	@Override
	public void addUserAnswer(String userAnser) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public boolean isAnswerRight() {
		String rigtAnswer=getRightAnswers();
		if(rigtAnswer.equals(this.userAnswer))
			return true;
		else
			return false;
	}




}
