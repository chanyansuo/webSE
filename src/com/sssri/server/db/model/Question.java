package com.sssri.server.db.model;

/**
 * 	 `Num_id` int(11) NOT NULL,
	  `Content` varchar(255) DEFAULT NULL,
	  `Answer` varchar(255) DEFAULT NULL,
	  `Score` int(11) DEFAULT NULL,已加入考试圈模型
	  `Category` int(11) DEFAULT NULL,
 * @author sssri
 * 简答体模型
 */
public class Question extends QuestionType implements IQuestionFunction{
	
	public Question() {
		this.setType(5);
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
