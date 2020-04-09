package com.sssri.server.db.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import com.sssri.server.db.model.Choice;
import com.sssri.server.db.model.Completion;
import com.sssri.server.db.model.Judgment;
import com.sssri.server.db.model.Mark;
import com.sssri.server.db.model.QuestionType;
import com.sssri.server.db.model.Record;
import com.sssri.server.db.model.User;

/**
 * 用于编写复杂的sql语句增删该查
 * @author xmj
 *
 */
public class AdminInfoProvider {
	/**
	 * 批量插入全部填空
	 * @param map
	 * @return
	 */
	public String insertAllCompletion(Map map){
		 List<Completion> users = (List<Completion>) map.get("list");  
	        StringBuilder sb = new StringBuilder();  
	        sb.append("INSERT INTO tb_completion ");  
	        sb.append("(Content,Blank_num,Answer_one,Answer_two,Answer_three,Answer_four,Answer_five,Difficult,Category) ");
	        sb.append("VALUES ");  
	        MessageFormat mf = new MessageFormat("(#'{'list[{0}].content}, #'{'list[{0}].Blank_num},#'{'list[{0}].Answer_one},#'{'list[{0}].Answer_two},#'{'list[{0}].Answer_three},#'{'list[{0}].Answer_four},#'{'list[{0}].Answer_five}, #'{'list[{0}].difficult}, 1)");  
	        for (int i = 0; i < users.size(); i++) {  
	            sb.append(mf.format(new Object[]{i}));  
	            if (i < users.size() - 1) {  
	                sb.append(",");  
	            }  
	        }  
	        return sb.toString();  
	}
	
	/**
	 * 批量更新填空题
	 * @param map
	 * @return sql
	 */
	public String updateAllCompletion(Map map){//把传输数据放入map中get参数key值可以取得数据可是实现动态参数赋值
		List<Completion> qCompletions= (List<Completion>) map.get("list");
		StringBuilder sb = new StringBuilder(); 
		sb.append("UPDATE tb_completion set ");
		sb.append("Content = CASE Num_id");
		MessageFormat mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].content}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i}));
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Blank_num = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Blank_num}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Answer_one = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Answer_one}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Answer_two = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Answer_two}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Answer_three = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Answer_three}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Answer_four = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Answer_four}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Answer_five = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Answer_five}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Difficult = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].difficult}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END WHERE Num_id IN (");
		mf1=new MessageFormat("#'{'list[{0}].num_Id}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(",");  
			}  
		}
		sb.append(")");
		return sb.toString();
	}
	
	 /**
	  * SQl批量插入判断题/简答题仅使用保密插入/
	 * @param map
	 * @return
	 */
	public String insertAll(Map map) {  
	        List<QuestionType> users = (List<QuestionType>) map.get("list");  
	        StringBuilder sb = new StringBuilder();  
	        sb.append("INSERT INTO "+(String)map.get("tableName")+" ");  
	        sb.append("(Content, Answer,Difficult,Category) ");  //试用于表自增，无需增加num_id，全版(Num_Id,Content, Answer,Difficult,Category)
	        sb.append("VALUES ");  
	        MessageFormat mf = new MessageFormat("(#'{'list[{0}].content}, #'{'list[{0}].answer}, #'{'list[{0}].difficult}, 1)");  
	        for (int i = 0; i < users.size(); i++) {  
	            sb.append(mf.format(new Object[]{i}));  
	            if (i < users.size() - 1) {  
	                sb.append(",");  
	            }  
	        }  
	        return sb.toString();  
	    }  
	
	/**
	 * 更新判断题语句/简答题
	 * 自定义sql语句书写格式，复杂的批量处理
	 * @param map
	 * @return sql语句
	 */
	public String updateAll(Map map){//把传输数据放入map中get参数key值可以取得数据可是实现动态参数赋值
		List<QuestionType> judgments= (List<QuestionType>) map.get("list");
		StringBuilder sb = new StringBuilder(); 
		sb.append("UPDATE "+(String)map.get("tableName")+" set ");
		sb.append("Content = CASE Num_id");
		MessageFormat mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].content}");
		for (int i = 0; i < judgments.size(); i++) {  
			sb.append(mf1.format(new Object[]{i}));
			if (i < judgments.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Answer = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].answer}");
		for (int i = 0; i < judgments.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < judgments.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Difficult = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].difficult}");
		for (int i = 0; i < judgments.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < judgments.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END WHERE Num_id IN (");
		mf1=new MessageFormat("#'{'list[{0}].num_Id}");
		for (int i = 0; i < judgments.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < judgments.size() - 1) {  
				sb.append(",");  
			}  
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 批量插入选择题语句
	 * @param map
	 * @return
	 */
	public String insertAllChoice(Map map) {  
        List<Choice> users = (List<Choice>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO "+(String)map.get("tableName")+" ");  
        sb.append("(Content,Type,Option_one,Option_two,Option_three,Option_four,Option_five,Option_six,Answer,Difficult,Category) ");
        sb.append("VALUES ");  
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].content},#'{'list[{0}].type},#'{'list[{0}].Option_one},#'{'list[{0}].Option_two},#'{'list[{0}].Option_three},#'{'list[{0}].Option_four},#'{'list[{0}].Option_five},#'{'list[{0}].Option_six}, #'{'list[{0}].answer}, #'{'list[{0}].difficult}, 1)");  
        for (int i = 0; i < users.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < users.size() - 1) {  
                sb.append(",");  
            }  
        }  
        return sb.toString();  
    }  
	/**
	 * 批量更新选择题语句
	 * @param map
	 * @return sql语句
	 */
	public String updateAllChoice(Map map){//把传输数据放入map中get参数key值可以取得数据可是实现动态参数赋值
		List<Choice> qCompletions= (List<Choice>) map.get("list");
		StringBuilder sb = new StringBuilder(); 
		sb.append("UPDATE "+(String)map.get("tableName")+" set ");
		sb.append("Content = CASE Num_id");
		MessageFormat mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].content}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i}));
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Type = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].type}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Option_one = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Option_one}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Option_two = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Option_two}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Option_three = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Option_three}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Option_four = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Option_four}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Option_five = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Option_five}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Option_six = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].Option_six}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Difficult = CASE Num_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].num_Id} THEN #'{'list[{0}].difficult}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END WHERE Num_id IN (");
		mf1=new MessageFormat("#'{'list[{0}].num_Id}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(",");  
			}  
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 去哪远都可以用的删除通过id写的删除
	 * @param map
	 * @return
	 */
	public String deleteAllJudgment(Map map) {  
        List<Judgment> users = (List<Judgment>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("delete from "+(String)map.get("tableName")+" where Num_id in ");  
        sb.append("(");
        MessageFormat mf = new MessageFormat("#'{'list[{0}].num_Id}");  
        for (int i = 0; i < users.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < users.size() - 1) {  
                sb.append(",");  
            }  
        }  
        sb.append(")");
        return sb.toString();  
    }  
	
	/**
	 * 批量修改成绩表语句
	 * @param map
	 * @return
	 */
	public String updataAllMark(Map map){
		List<Mark> judgments= (List<Mark>) map.get("list");
		StringBuilder sb = new StringBuilder(); 
		sb.append("UPDATE tb_Mark set ");
		sb.append("Float_score = CASE Mark_id");
		MessageFormat mf1=new MessageFormat(" WHEN #'{'list[{0}].Mark_id} THEN #'{'list[{0}].Float_score}");
		for (int i = 0; i < judgments.size(); i++) {  
			sb.append(mf1.format(new Object[]{i}));
			if (i < judgments.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Final_score = CASE Mark_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Mark_id} THEN #'{'list[{0}].Final_score}");
		for (int i = 0; i < judgments.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < judgments.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Remark = CASE Mark_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Mark_id} THEN #'{'list[{0}].Remark}");
		for (int i = 0; i < judgments.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < judgments.size() - 1) {  
				sb.append(" ");  
			}  
		}
		
		sb.append(" END WHERE Mark_id IN (");
		mf1=new MessageFormat("#'{'list[{0}].Mark_id}");
		for (int i = 0; i < judgments.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < judgments.size() - 1) {  
				sb.append(",");  
			}  
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 插入record表
	 * @param map
	 * @return
	 */
	public String insertAllRecord(Map map){
		List<Record> users = (List<Record>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO tb_record "); 
        sb.append("(Attendance_expect,Completion_num1,Completion_num2,Completion_num3,Completion_num4,Completion_num5,Completion_Score,Judgment_num,Judgment_Score,Choice_num,Choice_Score,MulChoice_num,MulChoice_Score,QA_num,QA_Score,Exam_date,Exam_time,Exam_syllabus,Attendance_reality) ");
        sb.append("VALUES ");  
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].Attendance_expect}, #'{'list[{0}].Completion_num1},#'{'list[{0}].Completion_num2},#'{'list[{0}].Completion_num3},#'{'list[{0}].Completion_num4},#'{'list[{0}].Completion_num5},#'{'list[{0}].Completion_Score}, #'{'list[{0}].Judgment_num}, "
        		+ " #'{'list[{0}].Judgment_Score}, #'{'list[{0}].Choice_num}, #'{'list[{0}].Choice_Score}, #'{'list[{0}].MulChoice_num}, #'{'list[{0}].MulChoice_Score}, #'{'list[{0}].QA_num}, #'{'list[{0}].QA_Score}, #'{'list[{0}].Exam_date}, #'{'list[{0}].Exam_time}, #'{'list[{0}].Exam_syllabus}, #'{'list[{0}].Attendance_reality})");  
        for (int i = 0; i < users.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < users.size() - 1) {  
                sb.append(",");  
            }  
        }  
        return sb.toString();  
	}
	
	public String updateAllRecord(Map map){//把传输数据放入map中get参数key值可以取得数据可是实现动态参数赋值
		List<Record> qCompletions= (List<Record>) map.get("list");
		StringBuilder sb = new StringBuilder(); 
		sb.append("UPDATE tb_record set ");
		sb.append("Attendance_expect = CASE Exam_id");
		MessageFormat mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Attendance_expect}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i}));
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Completion_num1 = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Completion_num1}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Completion_num2 = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Completion_num2}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Completion_num3 = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Completion_num3}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Completion_num4 = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Completion_num4}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Completion_num5 = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Completion_num5}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Completion_Score = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Completion_Score}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Judgment_num = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Judgment_num}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Judgment_Score = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Judgment_Score}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Choice_num = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Choice_num}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Choice_Score = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Choice_Score}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,MulChoice_num = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].MulChoice_num}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,MulChoice_Score = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].MulChoice_Score}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,QA_num = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].QA_num}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		
		sb.append(" END,QA_Score = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].QA_Score}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Exam_date = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Exam_date}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Exam_time = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Exam_time}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Exam_syllabus = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Exam_syllabus}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Attendance_reality = CASE Exam_id");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].Exam_id} THEN #'{'list[{0}].Attendance_reality}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END WHERE Exam_id IN (");
		mf1=new MessageFormat("#'{'list[{0}].Exam_id}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(",");  
			}  
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 批量删除record表记录
	 * @param map
	 * @return
	 */
	public String deleteAllRecord(Map map) {  
        List<Record> users = (List<Record>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("delete from tb_record where Exam_id in ");  
        sb.append("(");
        MessageFormat mf = new MessageFormat("#'{'list[{0}].Exam_id}");  
        for (int i = 0; i < users.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < users.size() - 1) {  
                sb.append(",");  
            }  
        }  
        sb.append(")");
        return sb.toString();  
    }  
	
	/**
	 * 批量user插入
	 * @param map
	 * @return
	 */
	public String insertUserInfo(Map map){
		List<User> users = (List<User>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO tb_user "); 
        sb.append("(Employee_id,Pwd,User_type,Sex,Name,Department,Online,Timekeeping,User_position) ");
        sb.append("VALUES ");  
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].Employee_id},#'{'list[{0}].Pwd}, #'{'list[{0}].User_type},#'{'list[{0}].Sex},#'{'list[{0}].Name},#'{'list[{0}].Department},#'{'list[{0}].Online}, #'{'list[{0}].Timekeeping}, "
        		+ " #'{'list[{0}].User_position})");  
        for (int i = 0; i < users.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < users.size() - 1) {  
                sb.append(",");  
            }  
        }  
        return sb.toString();  
	}
	
	/**
	 * 批量user更新
	 * @param map
	 * @return
	 */
	public String updateUserInfo(Map map){//把传输数据放入map中get参数key值可以取得数据可是实现动态参数赋值
		List<User> qCompletions= (List<User>) map.get("list");
		StringBuilder sb = new StringBuilder(); 
		sb.append("UPDATE tb_user set ");
		sb.append("Pwd = CASE User_code");
		MessageFormat mf1=new MessageFormat(" WHEN #'{'list[{0}].User_code} THEN #'{'list[{0}].Pwd}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i}));
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,User_type = CASE User_code");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].User_code} THEN #'{'list[{0}].User_type}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,User_code = CASE User_code");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].User_code} THEN #'{'list[{0}].User_code}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Sex = CASE User_code");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].User_code} THEN #'{'list[{0}].Sex}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Name = CASE User_code");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].User_code} THEN #'{'list[{0}].Name}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Department = CASE User_code");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].User_code} THEN #'{'list[{0}].Department}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Online = CASE User_code");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].User_code} THEN #'{'list[{0}].Online}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Timekeeping = CASE User_code");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].User_code} THEN #'{'list[{0}].Timekeeping}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,User_position = CASE User_code");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].User_code} THEN #'{'list[{0}].User_position}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
		sb.append(" END,Now_Exam = CASE User_code");
		mf1=new MessageFormat(" WHEN #'{'list[{0}].User_code} THEN #'{'list[{0}].Now_Exam}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(" ");  
			}  
		}
	
		sb.append(" END WHERE User_code IN (");
		mf1=new MessageFormat("#'{'list[{0}].User_code}");
		for (int i = 0; i < qCompletions.size(); i++) {  
			sb.append(mf1.format(new Object[]{i})); 
			if (i < qCompletions.size() - 1) {  
				sb.append(",");  
			}  
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 批量User删除
	 * @param map
	 * @return
	 */
	public String deleteUserInfo(Map map) {  
        List<User> users = (List<User>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("delete from tb_user where User_code in ");  
        sb.append("(");
        MessageFormat mf = new MessageFormat("#'{'list[{0}].User_code}");  
        for (int i = 0; i < users.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < users.size() - 1) {  
                sb.append(",");  
            }  
        }  
        sb.append(")");
        return sb.toString();  
    }  
}
