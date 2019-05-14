package cn.wisdsoft.mapper;

import cn.wisdsoft.pojo.Student;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-02 13:17
 * @ Description：
 */
public interface DdmsStudentMapper {
    @Select("select s.id,s.stu_password as stuPassword,s.stu_name as stuName,c.college_name as collegeName\n" +
            "from student s,major m,college c\n" +
            "where s.id = #{username} and stu_password = #{password} and s.stu_majorId = m.id and m.major_collegeid = c.id")
    Student selectOne(String username, String password);

    /**
     * 更改密码
     *
     * @param username 用户名
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @return 成功返回1，否则返回0
     */
    @Update("update student set stu_password = #{newPwd} where id = #{username} and stu_password = #{oldPwd}")
    Integer updatePwd(String username, String oldPwd, String newPwd);
}
