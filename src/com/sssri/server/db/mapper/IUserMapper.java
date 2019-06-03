package com.sssri.server.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sssri.server.db.model.Mark;
import com.sssri.server.db.model.User;

/**
 * @author xumj
 *
 */
public interface IUserMapper {
	
	//List<User> alluser=null;

	/**
	 * 根据用户工号查询用户信息
	 * @param employee_id
	 * @return
	 */
	@Select("select * from tb_user where Employee_id = #{employee_id}")
	public User selectUserByID(String employee_id);
	
	/**
	 * 根据用户姓名查询用户信息
	 * @param name
	 * @return
	 */
	@Select("select * from tb_user where userid like #{name}")
	public User selectUserByName(String name);
	
	/**
	 * @param id
	 * @param name
	 * @return
	 */
	@Select("select * from tb_user where Employee=#{employee_id} and userName=#{userName}")
	public User selectUser(User user);
	
	@Select("select * from tb_user")
	public List<User> selectAlluser();
	
	/**
	 * 用户修改密码
	 * @param PassWord
	 * @param Employee_id
	 * @return
	 */
	@Update("update tb_user set Pwd = #{Pw} where Employee_id = #{Employee_id}")
	public int UpdateUserPw(@Param("Pw") String PassWord,@Param("Employee_id") String Employee_id);
	
	/**
	 * 查询用户最近一次考试时间与成绩
	 * @param Employee_id
	 * @return
	 */
	@Select("SELECT * FROM tb_mark WHERE Employee_id = #{Employee_id} AND Exam_date=(SELECT MAX(Exam_date)  FROM tb_mark WHERE Employee_id = #{Employee_id});")
	
	public Mark selectRecentMark(@Param("Employee_id")String Employee_id);
	
}
