package cn.wisdsoft.service;

import cn.wisdsoft.pojo.StudentElectiveEntity;
import cn.wisdsoft.pojo.StudentEntity;
import cn.wisdsoft.util.ElectiveResult;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-03-29 08:13
 * @ Description：
 */
public interface StudentService {
    /**
     * 登录方法
     *
     * @param username 学号
     * @param password 密码
     * @return JSON数据
     */
    ElectiveResult login(String username, String password);

    /**
     * 插入学生信息
     *
     * @param student 学生实体类
     * @return JSON数据
     */
    ElectiveResult insert(StudentEntity student);

    /**
     * 插入学生选课信息
     *
     * @param electiveEntity  学生选课子表对象
     * @param college         学院
     * @param courseGroupName 课组名称
     * @return JSON数据
     */
    ElectiveResult insertStudentElective(StudentElectiveEntity electiveEntity, String college, String courseGroupName);

    /**
     * 查询学生的选课
     *
     * @param courseFlag 是否正在学习
     * @param studentId  学号
     * @return 课程列表
     */
    ElectiveResult selectElectiveCourse(String courseFlag, String studentId);

    /**
     * 退选课程
     *
     * @param studentId        学生ID
     * @param electiveCourseId 选课ID
     * @return JSON数据
     */
    ElectiveResult deleteCourse(String studentId, Long electiveCourseId);

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @return JSON数据
     */
    ElectiveResult changePwd(String username, String oldPwd, String newPwd);

    /**
     * 更新所有学生的学习状态为已完成
     *
     * @return 更新的数量
     */
    ElectiveResult updateAll();
}
