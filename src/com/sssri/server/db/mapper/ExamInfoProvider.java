package com.sssri.server.db.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.sssri.server.db.model.Qanswer;

public class ExamInfoProvider {

	public String insertQuestionAnswer(Map map){
		 List<Qanswer> users = (List<Qanswer>) map.get("list");  
	        StringBuilder sb = new StringBuilder();  
	        sb.append("insert into tb_question_answer ");  
	        sb.append("(Employee_id,Exam_id,Exam_date,Question_position,Question_id,User_answer,Statues) ");
	        sb.append("VALUES ");  
	        MessageFormat mf = new MessageFormat("(#'{'list[{0}].Employee_id}, #'{'list[{0}].Exam_id}, #'{'list[{0}].Exam_date},#'{'list[{0}].Question_position},#'{'list[{0}].Question_id},#'{'list[{0}].User_answer},0)");  
	        for (int i = 0; i < users.size(); i++) {  
	            sb.append(mf.format(new Object[]{i}));  
	            if (i < users.size() - 1) {  
	                sb.append(",");  
	            }  
	        }
	        sb.append(" ON DUPLICATE KEY UPDATE Question_id=VALUES(Question_id),User_answer=VALUES(User_answer)");
	        return sb.toString();  
	}
}
