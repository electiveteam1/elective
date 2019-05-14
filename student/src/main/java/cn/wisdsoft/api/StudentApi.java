package cn.wisdsoft.api;

import cn.wisdsoft.pojo.*;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.MD5Util;
import cn.wisdsoft.service.StudentService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-03-26 14:17
 * @ Description：
 */
@RestController
@RequestMapping("/student")
public class StudentApi {
    private final StudentService studentService;

    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 学生登录---测试成功
     *
     * @param username 学号
     * @param password 密码
     * @return JSON数据
     */
    @PostMapping("/slogin")
    public ElectiveResult login(String username, String password) {
        return studentService.login(username, password);
    }

    /**
     * 插入学生信息
     *
     * @param studentEntity 学生实体
     * @return
     */
    @PostMapping("/insert")
    public ElectiveResult insert(@RequestBody StudentEntity studentEntity) {
        studentService.insert(studentEntity);
        return ElectiveResult.ok();
    }

    /**
     * 选择课程---测试成功（暂无事务回滚）
     *
     * @param electiveCourseId 选课ID
     * @param courseGroupName  课组名称
     * @param studentVo        学生信息
     * @return JSON数据
     */
    @PostMapping("/getcourse")
    public ElectiveResult getCourse(Long electiveCourseId, String courseGroupName, @RequestBody StudentVo studentVo) {
        StudentElectiveEntity entity = new StudentElectiveEntity();
        entity.setElectiveCourseId(electiveCourseId).setStudentId(studentVo.getUsername()).setStudentName(studentVo.getName())
                .setCourseFlag("learning").setTimeStamp(new Date());
        return studentService.insertStudentElective(entity, studentVo.getCollege(), courseGroupName);
    }

    /**
     * 退课---测试成功
     *
     * @param electiveCourseId 选课ID
     * @param studentVo        学生信息
     * @return JSON数据
     */
    @PostMapping("/dropcourse")
    public ElectiveResult dropCourse(Long electiveCourseId, @RequestBody StudentVo studentVo) {
        return studentService.deleteCourse(studentVo.getUsername(), electiveCourseId);
    }

    /**
     * 我的选课---测试成功
     *
     * @param courseFlag 是否正在学习
     * @param studentVo  学生信息
     * @return JSON数据
     */
    @PostMapping("/mycourse")
    public ElectiveResult myCourse(String courseFlag, @RequestBody StudentVo studentVo) {
        String studentId = studentVo.getUsername();
        return studentService.selectElectiveCourse(courseFlag, studentId);
    }

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @return JSON数据
     */
    @PostMapping("/updatePwd")
    public ElectiveResult updatePwd(String username, String oldPwd, String newPwd) {
        return studentService.changePwd(username, oldPwd, newPwd);
    }

    /**
     * 更新所有学生的学习状态为已完成
     *
     * @return 更新的数量
     */
    @PostMapping("/updateAll")
    public ElectiveResult updateAll(){
        return studentService.updateAll();
    }
}
