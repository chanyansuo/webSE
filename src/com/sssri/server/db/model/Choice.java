package com.sssri.server.db.model;

/**
 * 选择题模型(包括单选题多选题)
 * 
 * <pre>建表语句:
 * 'Num_id` int(11) NOT NULL,
 * `Content` varchar(255) DEFAULT NULL,
 * `Type` int(11) DEFAULT NULL,
 * `Option_one` varchar(20) DEFAULT NULL,
 * `Option_two` varchar(20) DEFAULT NULL,
 * `Option_three` varchar(20) DEFAULT NULL,
 * `Option_four` varchar(20) DEFAULT NULL,
 * `Option_five` varchar(20) DEFAULT NULL,
 * `Option_six` varchar(20) DEFAULT NULL,
 * `Answer` varchar(20) DEFAULT NULL,
 * `Score` int(11) DEFAULT NULL,放置考试表没有在此处显示
 * `Category` int(11) DEFAULT NULL,
 * Difficult int 20	
 * </pre>
 * @author sssri
 *
 */
public class Choice extends QuestionType implements IQuestionFunction {

	//public static final String TYPE_NAME = "CHOICE_";
	
	public Choice() {
		// TODO Auto-generated constructor stub
		setUserAnswer("");//初始化答案，不然不发
	}
	private String Option_one;
	
	private String Option_two;
	
	private String Option_three;
	
	private String Option_four;
	
	private String Option_five;
	
	private String Option_six;

	public String getOption_one() {
		return Option_one;
	}

	public void setOption_one(String option_one) {
		Option_one = option_one;
	}

	public String getOption_two() {
		return Option_two;
	}

	public void setOption_two(String option_two) {
		Option_two = option_two;
	}

	public String getOption_three() {
		return Option_three;
	}

	public void setOption_three(String option_three) {
		Option_three = option_three;
	}

	public String getOption_four() {
		return Option_four;
	}

	public void setOption_four(String option_four) {
		Option_four = option_four;
	}

	public String getOption_five() {
		return Option_five;
	}

	public void setOption_five(String option_five) {
		Option_five = option_five;
	}

	public String getOption_six() {
		return Option_six;
	}

	public void setOption_six(String option_six) {
		Option_six = option_six;
	}

//	@Override
//	public String toString() {
//		return "Choice [Option_one=" + Option_one + ", Option_two=" + Option_two + ", Option_three=" + Option_three
//				+ ", Option_four=" + Option_four + ", Option_five=" + Option_five + ", Option_six=" + Option_six
//				+ ", Num_Id=" + getNum_Id() + ", Content=" + getContent() + ", Type=" + getType()
//				+ ", Answer=" + getAnswer() + ", Categeory=" + getCategeory() + ", Diffcult="
//				+ getDiffcult() + ", UserAnswer=" + getUserAnswer() + ", UserSocre=" + getUserSocre()
//				+ ", ItemStatus=" + getItemStatus() + "]";
//	}

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
