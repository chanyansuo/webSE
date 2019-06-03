package com.sssri.server.db.mapper;

import java.util.List;

import javax.ws.rs.OPTIONS;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.sssri.server.db.model.Choice;
import com.sssri.server.db.model.Completion;
import com.sssri.server.db.model.Judgment;
import com.sssri.server.db.model.Mark;
import com.sssri.server.db.model.Qanswer;
import com.sssri.server.db.model.Question;
import com.sssri.server.db.model.ReExam;
import com.sssri.server.db.model.Record;


/**
 * 在线正式考试相关内容
 * @author sssri
 *
 */
public interface IgetExamMapper {
	
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
	@Select("select * from tb_judgment")
	public List<Judgment> selectallJudgment();
	
    
    /**
     * 查询所有选择题
     * @param type
     * @return
     */
    @Select("select * from tb_choice where Type=#{type}")
	public List<Choice> selectallchoice(int type);
    
    
//    /**
//     * 查询所有多选题
//     * @return
//     */
//    @Select("select * from tb_choice where Type=2")
//    public List<TestChoice> selectallMulChoice();
//    
    /**
     * 查询所有简答题
     * @return
     */
    @Select("select * from tb_question")
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
    /**
     * 
     * 测试显示所有选择题界面
     * @return
     */
    @Select("select * from tb_judgment")
    public List<Judgment> SelectAllJudgement();
    
    /**
     * 在exam表中插入生成的考卷测试用单独插入
     * @param Content考卷题号
     * @param userid考生id
     * @param examid考试模型序号
     * @return
     */
    @Insert("insert into tb_examination (Content,Employee_id,Exam_id) values (#{Content},#{Employee_id},#{Exam_id})") 
    @Options(useGeneratedKeys=true,keyProperty="Exam_paper_id")
    public int insertContent(ReExam reexam); 
    
    /**
     * 客观题评分完毕后插入成绩表（单一插入例子）如果有重复就更新
     * @param userMark
     * @return
     */
    @Insert("insert into tb_mark (Employee_id,Completion_score,Judgment_score,Choice_score,Mchoice_score,Exam_paper_id"
    		+ ",Exam_date,Score_sum) values (#{Employee_id},#{Completion_score},#{Judgment_score},#{Choice_score}"
    		+ ",#{Mchoice_score},#{Exam_paper_id},#{Exam_date},#{Score_sum}) ON DUPLICATE KEY UPDATE "
    		+ "Completion_score=VALUES(Completion_score),Judgment_score=VALUES(Judgment_score),Choice_score=VALUES(Choice_score)"
    		+ ",Mchoice_score=VALUES(Mchoice_score),Score_sum=VALUES(Score_sum)") 
    @Options(useGeneratedKeys=true,keyProperty="Mark_id")
    public int insertMark(Mark userMark);
    
    /**
     * 主观题插入数据库测试使用会改为批量插入
     * @param qanswer
     * @return
     */
    @InsertProvider(type = ExamInfoProvider.class, method = "insertQuestionAnswer") 
    @Options(useGeneratedKeys=true,keyProperty="list[0].Num_id")
    public int inserQanswer(List<Qanswer> qanswerList);
    
    @Select("SELECT * FROM tb_record WHERE Exam_date = (SELECT MAX(Exam_date) FROM tb_record)")
    public Record selectMaxExamdateRecord();
    
    /**
     * 评卷完成后更新用户当前考试卷号为0
     * @param Employee_id
     * @return
     */
    @Update("UPDATE tb_user SET Now_Exam =0 WHERE Employee_id=#{Employee_id}")
    public int updateUserNowExamStatue(@Param("Employee_id")String Employee_id);
    
    
}
