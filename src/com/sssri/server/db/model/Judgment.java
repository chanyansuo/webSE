package com.sssri.server.db.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * `Num_id` int(11) NOT NULL,
 * `Content` varchar(255) DEFAULT NULL,
 *`Answer` varchar(255) DEFAULT NULL,
 *`Score` int(11) DEFAULT NULL,
 *`Category` int(11) DEFAULT NULL,
 * @author sssri
 * 判断题模型
 * 
 */
public class Judgment extends QuestionType implements IQuestionFunction {
	
	public Judgment() {
		this.setType(2);
		setUserAnswer("");
	}

	@Override
	public String getQuestionUniqueID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRightAnswers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserAnswer(String userAnser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAnswerRight() {
		// TODO Auto-generated method stub
		return false;
	}



	
	
}
