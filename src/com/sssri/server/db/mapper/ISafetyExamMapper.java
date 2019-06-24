package com.sssri.server.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sssri.server.db.model.Choice;
import com.sssri.server.db.model.Completion;
import com.sssri.server.db.model.Judgment;
import com.sssri.server.db.model.Question;
import com.sssri.server.db.model.Record;

public interface ISafetyExamMapper {
	/**
	 * 根据空格数查询所有填空题
	 * @param Blank_num
	 * @return
	 */
	@Select("select * from tb_completion where Blank_num=#{Blank_num}")
	public List<Completion> selectallComplletion(int Blank_num);
	
	/**
	 * 查询所有填空题
	 * @return
	 */
	@Select("select * from tb_completion")
	public List<Completion> selectComplletion();
	
	/**
	 * 查询所有判断题
	 * @return
	 */
	@Select("select * from Safety_judgment")
	public List<Judgment> selectallJudgment();
	
    
    /**
             * 查询所有选择题
     * @param type
     * @return
     */
    @Select("select * from Safety_choice where Type=#{type}")
	public List<Choice> selectallchoice(int type);
    
    
   
    /**
          * 查询所有简答题
     * @return
     */
    @Select("select * from Safety_question")
    public List<Question> selectallQuestion();
    
    /**
               * 根据 ExamId考试号查询考卷模型
     * @param ExamId考试模型序号
     * @return
     */
    @Select("select *from tb_record where Exam_id=#{ExamId}")
    public Record SelectExamNum(int ExamId);
    /**
             * 根据考生现在考试号查询考试规则 2017.8.10
    * @param Employee_id考生id
    * @return
    */
   @Select("select * from tb_record where Exam_id = (SELECT Now_Exam FROM tb_user WHERE Employee_id = #{Employee_id})")
   public Record SelectNowExam(String Employee_id);
}
