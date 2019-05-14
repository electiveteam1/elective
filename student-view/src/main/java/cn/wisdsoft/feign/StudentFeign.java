package cn.wisdsoft.feign;

import cn.wisdsoft.fallback.StudentFallBack;
import cn.wisdsoft.pojo.StudentElectiveEntity;
import cn.wisdsoft.pojo.StudentEntity;
import cn.wisdsoft.util.CourseResult;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.vo.StudentVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-03-29 09:20
 * @ Description：
 */
@FeignClient(value = "student", fallback = StudentFallBack.class)
@Component
public interface StudentFeign {

    /**
     * 学生登录---测试成功
     *
     * @param username 学号
     * @param password 密码
     * @return JSON数据
     */
    @PostMapping("/elective/student/slogin")
    public ElectiveResult login(@RequestParam("username") String username, @RequestParam("password") String password);

    /**
     * 插入学生信息
     *
     * @param studentEntity 学生实体
     * @return
     */
    @PostMapping("/elective/student/insert")
    public ElectiveResult insert(@RequestBody StudentEntity studentEntity);

    /**
     * 选择课程---测试成功（暂无事务回滚）
     *
     * @param token            用户令牌
     * @param electiveCourseId 选课ID
     * @param courseGroupName  课组名称
     * @param session          session
     * @return JSON数据
     */
    @PostMapping("/elective/student/getcourse")
    public ElectiveResult getCourse(@RequestParam("electiveCourseId") Long electiveCourseId,
                                    @RequestParam("courseGroupName") String courseGroupName, @RequestBody StudentVo studentVo);

    /**
     * 退课---测试成功
     *
     * @param token            用户令牌
     * @param electiveCourseId 选课ID
     * @param session          session
     * @return JSON数据
     */
    @PostMapping("/elective/student/dropcourse")
    public ElectiveResult dropCourse(@RequestParam("electiveCourseId") Long electiveCourseId, @RequestBody StudentVo studentVo);

    /**
     * 我的选课---测试成功
     *
     * @param token      用户令牌
     * @param courseFlag 是否正在学习
     * @param session    session
     * @return JSON数据
     */
    @PostMapping(value = "/elective/student/mycourse", consumes = "application/json")
    public ElectiveResult myCourse(@RequestParam("courseFlag") String courseFlag, @RequestBody StudentVo studentVo);

    /**
     * 查询所有课程---测试成功
     *
     * @param token     用户令牌
     * @param nature    课程类别（school-》校选；college-》院选；all-》所有）
     * @param volume    容量（all-》所有；have-》有；nothave-》无）
     * @param locations 地点（all-》所有；其他对应楼号）
     * @param time      上课时间（all-》所有；其他对应星期）
     * @param page      当前页数
     * @param limit     每页数量
     * @param session   session
     * @return 课程信息
     */
    @PostMapping(value = "/elective/course/allcourse")
    public CourseResult allCourse(@RequestParam("nature") String nature, @RequestParam("volume") String volume,
                                  @RequestParam("locations") String locations, @RequestParam("time") String time,
                                  @RequestParam("page") Integer page, @RequestParam("limit") Integer limit, @RequestBody StudentVo studentVo);
//    @PostMapping(value = "/elective/course/allcourse")
//    public CourseResult allCourse(String nature, String volume,
//                                  String locations, String time,
//                                  Integer page, Integer limit, StudentVo studentVo);
//    @RequestMapping(value = "/elective/course/allcourse",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
//    public CourseResult allCourse(@RequestBody Map<String,Object> map);

    /**
     * 查询所有备选课程---测试成功
     *
     * @param token   用户令牌
     * @param page    当前页数
     * @param limit   每页数量
     * @param session session
     * @return 课程信息
     */
    @PostMapping(value = "/elective/course/optioncourse", consumes = "application/json")
    public CourseResult optionCourse(@RequestParam("page") Integer page,
                                     @RequestParam("limit") Integer limit, @RequestBody StudentVo studentVo);

    /**
     * 查询成绩
     *
     * @param token   用户另怕爱
     * @param session session
     * @return 成绩信息
     */
    @PostMapping(value = "/elective/achievement/grade", consumes = "application/json")
    public ElectiveResult grade(@RequestParam("term") String term, @RequestBody StudentVo studentVo);

    /**
     * 查询课程总数
     *
     * @return JSON数据
     */
    @PostMapping("/elective/course/allcoursenum")
    public ElectiveResult allCourseNum();

    /**
     * 查询本学期可选课程数量
     *
     * @param termId 学期ID
     * @param grade  年级
     * @return JSON数据
     */
    @PostMapping("/elective/course/elective/course/selectnum")
    public ElectiveResult selectNum(@RequestParam("termId") Integer termId, @RequestParam("grade") Integer grade);

    /**
     * 查询选择人数前三的课程
     *
     * @param termId 学期ID
     * @param grade  年级
     * @return JSON数据
     */
    @PostMapping("/elective/course/top")
    public ElectiveResult top(@RequestParam("termId") Integer termId, @RequestParam("grade") Integer grade, @RequestParam("size") Integer size);

    /**
     * 查询课程详情
     *
     * @param electiveCourseId 选课ID
     * @return 课程信息
     */
    @PostMapping("/elective/course/detail")
    public ElectiveResult detail(@RequestParam("electiveCourseId") Integer electiveCourseId);

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @return JSON数据
     */
    @PostMapping("/elective/student/updatePwd")
    public ElectiveResult updatePwd(@RequestParam("username") String username, @RequestParam("oldPwd") String oldPwd,
                                    @RequestParam("newPwd") String newPwd);

    /**
     * 所有信息
     *
     * @param studentId 学生ID
     * @return 消息数据
     */
    @PostMapping("/elective/information/allTip")
    public ElectiveResult allTip(@RequestParam("studentId") String studentId);

    /**
     * 查询是否有未查看消息
     *
     * @param studentId 学生ID
     * @return JSON数据
     */
    @PostMapping("/elective/information/redPoint")
    public ElectiveResult redPoint(@RequestParam("studentId") String studentId);

    /**
     * 查询消息详情
     *
     * @param informationId 消息ID
     * @return JSON数据
     */
    @PostMapping("/elective/information/detail")
    public ElectiveResult detail(@RequestParam("informationId") Long informationId);
//    /**
//     * 选择课程
//     *
//     * @param token            用户令牌
//     * @param electiveCourseId 选课ID
//     * @return JSON数据
//     */
//    @PostMapping("/elective/student/getcourse")
//    public ElectiveResult getCourse(@RequestParam("token") String token, @RequestParam("electiveCourseId") Long electiveCourseId, @RequestParam("courseGroupId") String courseGroupId, @RequestParam("session") HttpSession session);
//
//    /**
//     * 退课
//     *
//     * @param token            用户令牌
//     * @param electiveCourseId 选课ID
//     * @return JSON数据
//     */
//    @PostMapping("/elective/student/dropcourse")
//    public ElectiveResult dropCourse(@RequestParam("token") String token, @RequestParam("electiveCourseId") Long electiveCourseId, @RequestParam("session") HttpSession session);
//
//    /**
//     * 我的选课
//     *
//     * @param token      用户令牌
//     * @param courseFlag 是否正在学习
//     * @param session    session
//     * @return JSON数据
//     */
//    @GetMapping("/elective/student/mycourse")
//    public ElectiveResult myCourse(@RequestParam("token") String token, @RequestParam("courseFlag") String courseFlag, @RequestParam("session") HttpSession session);
}
