package com.sssri.server.restful.resource;

import java.util.List;

import com.sssri.server.db.model.Choice;
import com.sssri.server.db.model.Completion;
import com.sssri.server.db.model.Judgment;
import com.sssri.server.db.model.Question;


public interface IKSInfo {
	
	public List<Completion> getCompletion();
	public List<Judgment> getJudgment();
	public List<Choice> getChoice();
	public List<Choice> getMulchoice();
	public List<Question> getQuestion() ;
	public String getEmployee_id();
	public String getStatues();
	
}
