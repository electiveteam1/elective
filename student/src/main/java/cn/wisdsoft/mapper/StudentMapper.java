package cn.wisdsoft.mapper;

import cn.wisdsoft.mapper.SQL.SQLUtil;
import cn.wisdsoft.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-03-29 08:16
 * @ Description：
 */
public interface StudentMapper {
    /**
     * 查询学生信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 学生对象
     */
    @Select("select * from student where student_id = #{username} and student_password = #{password}")
    StudentEntity selectByUsernameAndPassword(String username, String password);

    /**
     * 插入学生信息
     *
     * @param student 学生对象
     * @return 成功返回1，否则返回0
     */
    @Insert("insert into student(student_id,student_name,student_password,college_name,grade) " +
            "values(#{studentId},#{studentName},#{studentPassword},#{collegeName},#{grade})")
    Integer insertOne(StudentEntity student);

    /**
     * 插入学生选课信息
     *
     * @param electiveEntity 学生选课子表对象
     * @return 成功返回1，否则返回0
     */
    @Insert("insert into student_elective(student_id,elective_course_id,student_name,course_flag,time_stamp,remark) " +
            "values(#{studentId},#{electiveCourseId},#{studentName},#{courseFlag},#{timeStamp},#{remark})")
    Integer insertElectiveCourse(StudentElectiveEntity electiveEntity);

    /**
     * 查询学生已选课程
     *
     * @param studentId 学生学号
     * @param learnFlag 是否正在学习
     * @return 课程列表
     */
    @SelectProvider(type = SQLUtil.class, method = "selectElectiveCourse")
    List<StudentElectiveVo> selectElectiveCourse(String studentId, String learnFlag);

    /**
     * 查询学生的选课条数
     *
     * @param studentId 学生ID
     * @param college   学院名称
     * @return 选课数量
     */
    @Select("select " +
            "count(*) from student_elective s,elective_course e " +
            "where s.elective_course_id = e.elective_course_id and s.student_id = #{studentId} and e.college_name = #{college}")
    Integer selectCountForElective(String studentId, String college);

    /**
     * 查询学生所选课程对应的课程组
     *
     * @param studentId 学生ID
     * @return 集合
     */
    @Select("select " +
            "DISTINCT course_group_name from student_elective s,elective_course e " +
            "where s.elective_course_id = e.elective_course_id and s.student_id = #{studentId}")
    List<String> selectCourseGroups(String studentId);

    /**
     * 查询课程是否已选满
     *
     * @param electiveCourseId 选课ID
     * @return ElectiveCourseEntity对象
     */
    @Select("select * from elective_course " +
            "where elective_course_id = #{electiveCourseId} and max_number = current_number;")
    ElectiveCourseEntity selectAllNums(Long electiveCourseId);

    /**
     * 更新选课表的当前人数
     *
     * @param electiveCourseId 选课ID
     * @param num              数量
     * @return 成功返回1，否则返回0
     */
    @Select("update elective_course set current_number = current_number + #{num} where elective_course_id = #{electiveCourseId}")
    Integer updateCurrentNum(Long electiveCourseId, Integer num);

    /**
     * 查询学生选课
     *
     * @param studentId        学号
     * @param electiveCourseId 选课ID
     * @return StudentElectiveEntity对象
     */
    @Select("select * from student_elective where student_id = #{studentId} and elective_course_id = #{electiveCourseId}")
    StudentElectiveEntity selectOne(String studentId, Long electiveCourseId);

    /**
     * 删除选课信息
     *
     * @param studentId        学生ID
     * @param electiveCourseId 选课ID
     * @return 成功返回1，否则返回0
     */
    @Delete("delete from student_elective where student_id = #{studentId} and elective_course_id = #{electiveCourseId}")
    Integer deleteOne(String studentId, Long electiveCourseId);

    /**
     * 将备选状态改为0（表示已选满）
     *
     * @param electiveCourseId 选课ID
     * @param courseName       课程名称
     * @param priority         优先级
     * @param termId           学期ID
     * @param optionFlag       备选状态
     * @return 成功返回1，否则返回0
     */
    @Update("update elective_course set option_flag = #{optionFlag} " +
            "where delete_flag = 0 " +
            "and course_library_name = #{courseName} " +
            "and priority = #{priority} " +
            "and term_id = #{termId}")
    Integer updateOptionFlag(String courseName, Integer priority, Integer termId, Integer optionFlag);

    /**
     * 查询某一条选课信息
     *
     * @param electiveCourseId 选课ID
     * @return 课程信息
     */
    @Select("select * from elective_course where elective_course_id = #{electiveCourseId}")
    ElectiveCourseEntity selectOneElective(Long electiveCourseId);

    /**
     * 更改密码
     *
     * @param username 用户名
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @return 成功返回1，否则返回0
     */
    @Update("update student set student_password = #{newPwd} where student_id = #{username} and student_password = #{oldPwd}")
    Integer updatePwd(String username, String oldPwd, String newPwd);

    /**
     * 更新所有学生的学习状态为学习完成
     * @return 更新的数量
     */
    @Update("update student_elective set course_flag = 'learned'")
    Integer updateAll();
}
