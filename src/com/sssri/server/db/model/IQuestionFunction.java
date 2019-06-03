package com.sssri.server.db.model;

/**
 * 试题基础接口
 * 
 * @author sssri
 *
 */
public interface IQuestionFunction {

	/**
	 * 生成试卷后试题的唯一编号
	 * @return
	 */
	public String getQuestionUniqueID();
	
	/**
	 * 标准答案
	 * @return
	 */
	public String getRightAnswers();
	
	
	/**
	 * 保存用户的答案
	 * @param userAnser
	 */
	public void addUserAnswer(String userAnser);
	
	
	/**
	 * 判断用户回答的是否正确
	 * @return
	 */
	public boolean isAnswerRight();
	
}
