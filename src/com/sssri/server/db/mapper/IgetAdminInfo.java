package com.sssri.server.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.sssri.server.db.model.Choice;
import com.sssri.server.db.model.Completion;
import com.sssri.server.db.model.Judgment;
import com.sssri.server.db.model.Mark;
import com.sssri.server.db.model.Qanswer;
import com.sssri.server.db.model.Question;
import com.sssri.server.db.model.QuestionType;
import com.sssri.server.db.model.Record;
import com.sssri.server.db.model.User;

/**
 * @author maxuying
 *
 */
public interface IgetAdminInfo {
	
	/**
	 * 查询所有填空题
	 * @param Blank_num
	 * @return
	 */
	@Select("select * from tb_completion")
	public List<Completion> selectallComplletion();
	
	/**
	 * 查询所有判断题
	 * @return
	 */
	@Select("select * from tb_judgment")
	public List<Judgment> selectallJudgment();
	
	/**
	 * 查询所有安全判断题
	 * @return
	 */
	@Select("select * from safety_judgment")
	public List<Judgment> selectallsafetyJudgment();	
	
	  /**
     * 查询所有选择题
     * @param type
     * @return
     */
    @Select("select * from tb_choice")
	public List<Choice> selectallchoice();
    
    /**
     * 查询所有安全选择题
     * @param type
     * @return
     */
    @Select("select * from safety_choice")
	public List<Choice> selectallsafetychoice();
    
    /**
     * 查询所有简答题
     * @return
     */
    @Select("select * from tb_question")
    public List<Question> selectallQuestion();
    
    /**
     * 题库搜索因为类型不一样所以没办法要写三个
     * @param tablename
     * @param keyWords
     * @return
     */
    @Select("select * from ${param1} where Content like CONCAT('%',#{param2},'%')")
    public List<Completion> selectComSearch(String tablename,String keyWords);
    
    @Select("select * from ${param1} where Content like CONCAT('%',#{param2},'%')")
    public List<Choice> selectChoiceSearch(String tablename,String keyWords);
    
    @Select("select * from ${param1} where Content like CONCAT('%',#{param2},'%')")
    public List<QuestionType> selectSearch(String tablename,String keyWords);
    
    /**
     * 动态表名查询语句只要不乱来都可以用
     * @param tablename
     * @param Blank_num
     * @return
     */
    @Select("select * from ${param1} where Blank_num=#{param2}")//动态查询表明是只能用0，1表示参数或者param1，2，3表示参数
    public List<Completion> selecttest(String tablename,int Blank_num);
    
    /**
     * 批量插入填空题
     * @param Completions
     * @return
     */
    @InsertProvider(type = AdminInfoProvider.class, method = "insertAllCompletion") 
    @Options(useGeneratedKeys=true,keyProperty="list[0].num_Id")
    public 	int insertCompletion(List<Completion> Completions);
    
    /**
     * 批量更新填空题
     * @param judge
     * @return
     */
    @UpdateProvider(type = AdminInfoProvider.class,method = "updateAllCompletion")
    public int updateCompletions(List<Completion> completions);
    /** 
     * 批量插入判断题/简答
     * @param judge
     * @return
     */
    @InsertProvider(type = AdminInfoProvider.class, method = "insertAll") 
    @Options(useGeneratedKeys=true,keyProperty="list[0].num_Id")//要设置表格主键自增否则语句无效会报错
    public int insertAll(@Param("tableName")String tableName,@Param("list")List<QuestionType> judge);
    
    
    /**
     * 批量更新判断题/简答
     * @param judge
     * @return
     */
    @UpdateProvider(type = AdminInfoProvider.class,method = "updateAll")
    public int updateAll(@Param("tableName")String tableName,@Param("list")List<QuestionType> judge);
    
    /**
     * 批量插入选择题包括单选多选
     * @param choices
     * @return
     */
    @InsertProvider(type = AdminInfoProvider.class, method = "insertAllChoice") 
    @Options(useGeneratedKeys=true,keyProperty="list[0].num_Id")
    public 	int insertChoices(@Param("tableName")String tableName,@Param("list")List<Choice> choices);
    
    /**
     * 批量更新单选多选题
     * @param choices
     * @return
     */
    @UpdateProvider(type = AdminInfoProvider.class,method = "updateAllChoice")
    public int updateChoices(@Param("tableName")String tableName,@Param("list")List<Choice> choices);
    
    /**
     * 根据表名输入，删除表内信息要输入表名
     * @param tableName
     * @param judge
     * @return
     */
    @DeleteProvider(type = AdminInfoProvider.class,method = "deleteAllJudgment")
    public int deletetableRows(@Param("tableName")String tableName,@Param("list")List<QuestionType> judge);
    
    /**
     * 根据起始时间终止时间查询考试编号
     * @param starttime
     * @param overtime
     * @return
     */
    @Select("select * from tb_record WHERE Exam_date between #{0} AND #{1}")
    public List<Record> selectExamId(String starttime,String overtime);
    
    /**
     * 根据选择到的查询考试编号，参与考试的考生信息
     * @param examId
     * @return
     */
    @Select("SELECT tb_mark.Mark_id,tb_mark.Employee_id,tb_user.`Name`,Completion_score,Judgment_score,Choice_score,Mchoice_score,Question_score,Score_sum,Float_score,Final_score FROM tb_mark,tb_user WHERE Exam_paper_id = #{examid} AND tb_mark.Employee_id = tb_user.Employee_id")
    public List<Mark> selectExamScore(@Param("examid")int examId);
    
    /**
     * 根据考试批次查询所有需要评卷的主观题
     * @param examId
     * @return
     */
    @Select("SELECT tb_question_answer.Num_id,Employee_id,tb_question_answer.Exam_date,Question_position,tb_question_answer.Exam_id,Question_id,Content,Answer,User_answer,QA_Score,Statues,Question_Score FROM tb_question,tb_question_answer ,tb_record WHERE  tb_question_answer.Exam_id = tb_record.Exam_id AND tb_question.Num_id=tb_question_answer.Question_id AND tb_question_answer.Statues=0")
    @Results(
    		 {
    			 @Result(id = true, column = "tb_question_answer.Num_id", property = "Num_id"),
    			 @Result(column = "tb_question_answer.Exam_date", property = "Exam_date"),
    			 @Result(column = "tb_question_answer.Exam_id", property = "Exam_id")
    		 }
    		)
    public List<Qanswer> selectAllUserAnswer();
    
    /**
     * 将评分成绩插入系统用于单人简答题成绩更新
     * @param Score
     * @param Exam_id
     * @param Employee_id
     * @param Question_id
     * @return
     */
    @Update("UPDATE tb_question_answer SET Statues = 1, Question_Score = #{Score} WHERE Num_id = #{NumId}")
    public int updateQanswer(@Param("Score")double Score,@Param("NumId") int Num_id);

    /**
     * 查询单人最近一次考试成绩成绩用于评卷录入
     * @param ExamId
     * @param EmployeeId
     * @return
     */
    @Select("SELECT * FROM tb_mark WHERE Exam_paper_id = #{ExamId} AND Employee_id = #{EmployeeId} AND Exam_date = (SELECT MAX(Exam_date) FROM tb_mark WHERE Employee_id = #{EmployeeId} AND Exam_paper_id = #{ExamId} )")
	public Mark selectUserMark(@Param("ExamId") int ExamId,@Param("EmployeeId") String EmployeeId);	
    

    @Update("UPDATE tb_mark SET Question_score = #{Question_score},Score_sum = #{Score_sum} WHERE Exam_paper_id = #{ExamId} AND Employee_id = #{EmployeeId}")
    public int upadteOneMark(@Param("Question_score") double QScore,@Param("Score_sum") double ScoreSum,@Param("ExamId")int Exam_id,@Param("EmployeeId")String Employee_id);

    /**
     * 批量更新填空题
     * @param judge
     * @return
     */
    @UpdateProvider(type = AdminInfoProvider.class,method = "updataAllMark")
    public int updateAllMark(List<Mark> Mark);
    
    /**
     * 查询所有考试规则表
     * @return
     */
    @Select("SELECT * FROM tb_record")
    public List<Record> selectAllRecord();
    
    /**
     * Record表批量增删改查
     * @param Records
     * @return
     */
    @InsertProvider(type = AdminInfoProvider.class, method = "insertAllRecord") 
    @Options(useGeneratedKeys=true,keyProperty="list[0].Exam_id")
    public 	int insertRecords(List<Record> Records);
    
    
    @UpdateProvider(type = AdminInfoProvider.class,method = "updateAllRecord")
    public int updateRecords(List<Record> Records);
    
   
    @DeleteProvider(type = AdminInfoProvider.class,method = "deleteAllRecord")
    public int deleteRecords(@Param("list")List<Record> Records);
    
    /**
     * 用户信息搜索
     * @param keyWord
     * @return
     */
    @Select("SELECT * FROM tb_user WHERE Employee_id like CONCAT('%',#{keyWord},'%')  OR tb_user.`Name` like CONCAT ('%',#{keyWord},'%')")
    public List<User> searchUserInfo(@Param("keyWord") String keyWord);
    
    /**
     * 查询所有用户信息
     * @return
     */
    @Select("SELECT * FROM tb_user")
    public List<User> selectAllUserInfo();
    
    @InsertProvider(type = AdminInfoProvider.class, method = "insertUserInfo") 
    @Options(useGeneratedKeys=true,keyProperty="list[0].Employee_id")
    public 	int insertUserInfos(List<User> Users);
    
    
    @UpdateProvider(type = AdminInfoProvider.class,method = "updateUserInfo")
    public int updateUserInfos(List<User> Users);
    
   
    @DeleteProvider(type = AdminInfoProvider.class,method = "deleteUserInfo")
    public int deleteUserInfos(@Param("list")List<User> Users);
}

