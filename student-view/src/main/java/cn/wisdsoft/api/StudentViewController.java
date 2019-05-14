package cn.wisdsoft.api;

import cn.wisdsoft.feign.DdmsFeign;
import cn.wisdsoft.feign.StudentFeign;
import cn.wisdsoft.feign.TermFeign;
import cn.wisdsoft.pojo.StudentEntity;
import cn.wisdsoft.pojo.TermEntity;
import cn.wisdsoft.util.CourseResult;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.MD5Util;
import cn.wisdsoft.vo.StudentDo;
import cn.wisdsoft.vo.StudentVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-03-29 09:13
 * @ Description：
 */
@RestController
@RequestMapping("/student/view")
public class StudentViewController {
    private final StudentFeign studentFeign;

    private final DdmsFeign ddmsInterfaceFeign;

    private final TermFeign termFeign;

    public StudentViewController(StudentFeign studentFeign, DdmsFeign ddmsInterfaceFeign, TermFeign termFeign) {
        this.studentFeign = studentFeign;
        this.ddmsInterfaceFeign = ddmsInterfaceFeign;
        this.termFeign = termFeign;
    }

    /**
     * 登录
     *
     * @param username 学号
     * @param password 密码
     * @param session  session
     * @return JSON数据
     */
    @PostMapping("/login")
    public ElectiveResult login(String username, String password, HttpSession session) {
        //缺少学期，远程调用接口
//        String term = "2019-2020学年第一学期";
//        Integer termId = 1;
        //查询学期名称以及ID
        ElectiveResult termResult = termFeign.selectTermId();
        if (termResult.getStatus() != 200 || termResult.getData() == null){
            return termResult;
        }
        TermEntity termEntity = JSONObject.parseObject(JSON.toJSONString(termResult.getData()), TermEntity.class);
        String term = termEntity.getTermName();
        Integer termId = termEntity.getTermId();
        //自定义返回数据
        StudentVo studentVo = new StudentVo();

        //将输入的密码加密
        String md5Encryption = MD5Util.MD5Encryption(password);

        //查询本地表内是否有该学生
        ElectiveResult result = studentFeign.login(username, md5Encryption);

        //生成token
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        StudentEntity student;

        //如果没有，则去ddms查询，之后插入本地数据库；如果有，则返回
        if (result.getData() == null) {
            //调用DDMS的查询学生方法
            ElectiveResult electiveResult = ddmsInterfaceFeign.selectOne(username, password);
            if (electiveResult.getStatus() == 200 && electiveResult.getData() == null) {
                return ElectiveResult.build(410, "用户名或密码错误！");
            } else if (electiveResult.getStatus() == 510) {
                return ElectiveResult.build(510, electiveResult.getMsg());
            } else if (electiveResult.getStatus() == 410) {
                return ElectiveResult.build(410, electiveResult.getMsg());
            } else {
                StudentDo studentDo = JSONObject.parseObject(JSON.toJSONString(electiveResult.getData()), StudentDo.class);
                //将DDMS的学生密码加密
                String encryption = MD5Util.MD5Encryption(studentDo.getStuPassword());
                StudentEntity student1 = new StudentEntity();
                student1.setStudentId(studentDo.getId()).setStudentPassword(encryption)
                        .setStudentName(studentDo.getStuName()).setCollegeName(studentDo.getCollegeName())
                        .setGrade(Short.valueOf(studentDo.getId().substring(0, 2)));
                //插入本地数据库
                studentFeign.insert(student1);
                studentVo.setUsername(student1.getStudentId()).setName(student1.getStudentName())
                        .setCollege(student1.getCollegeName()).setTermId(termId).setTerm(term).setToken(token);
            }
        } else {
            student = JSONObject.parseObject(JSON.toJSONString(result.getData()), StudentEntity.class);
            studentVo.setUsername(student.getStudentId()).setName(student.getStudentName())
                    .setCollege(student.getCollegeName()).setTermId(termId).setTerm(term).setToken(token);
        }
        session.setAttribute(token, studentVo);
        //自定义返回实体类
        return ElectiveResult.ok(studentVo);
    }

    /**
     * 选择课程
     *
     * @param token            用户令牌
     * @param electiveCourseId 选课ID
     * @return JSON数据
     */
    @PostMapping("/getcourse")
    public ElectiveResult getCourse(String token, Long electiveCourseId,
                                    String courseGroupName, HttpSession session) {
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return ElectiveResult.build(410, "token失效！");
        }
        return studentFeign.getCourse(electiveCourseId, courseGroupName, studentVo);
    }

    /**
     * 退课
     *
     * @param token            用户令牌
     * @param electiveCourseId 选课ID
     * @return JSON数据
     */
    @PostMapping("/dropcourse")
    public ElectiveResult dropCourse(String token, Long electiveCourseId, HttpSession session) {
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return ElectiveResult.build(410, "token失效！");
        }
        return studentFeign.dropCourse(electiveCourseId, studentVo);
    }

    /**
     * 我的选课
     *
     * @param token      用户令牌
     * @param courseFlag 是否正在学习
     * @param session    session
     * @return JSON数据
     */
    @GetMapping("/mycourse")
    public ElectiveResult myCourse(String token, String courseFlag, HttpSession session) {
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return ElectiveResult.build(410, "token失效！");
        }
        return studentFeign.myCourse(courseFlag, studentVo);
    }

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
    @GetMapping("/allcourse")
    public CourseResult allcourse(String token, String nature, String volume,
                                  String locations, String time, Integer page,
                                  Integer limit, HttpSession session) {
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return CourseResult.build(410, "token失效！");
        }
        return studentFeign.allCourse(nature, volume, locations, time, page, limit, studentVo);
    }

    /**
     * 查询所有备选课程---测试成功
     *
     * @param token   用户令牌
     * @param page    当前页数
     * @param limit   每页数量
     * @param session session
     * @return 课程信息
     */
    @GetMapping("/optionCourse")
    public CourseResult optionCourse(String token, Integer page, Integer limit, HttpSession session) {
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return CourseResult.build(410, "token失效！");
        }
        return studentFeign.optionCourse(page, limit, studentVo);
    }

    /**
     * 查询成绩
     *
     * @param token   用户另怕爱
     * @param session session
     * @return 成绩信息
     */
    @GetMapping("/grade")
    public ElectiveResult grade(String token, String term, HttpSession session) {
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return ElectiveResult.build(410, "token失效！");
        }
        return studentFeign.grade(term, studentVo);
    }

    /**
     * 统计所有课程数量
     *
     * @return JSON数据
     */
    @GetMapping("/statistic/course")
    public ElectiveResult statistic() {
        return studentFeign.allCourseNum();
    }

    /**
     * 统计可选课程数量
     *
     * @return JSON数据
     */
    @GetMapping("/statistic/select")
    public ElectiveResult selectNum(String token, HttpSession session) {
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return ElectiveResult.build(410, "token失效！");
        }
        return studentFeign.selectNum(studentVo.getTermId(), Integer.valueOf(studentVo.getUsername().substring(0, 2)));
    }

    /**
     * 查询选课人数前三的课程
     *
     * @param token   用户令牌
     * @param session session
     * @return 课程信息
     */
    @GetMapping("/top")
    public ElectiveResult top(String token, Integer size, HttpSession session) {
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return ElectiveResult.build(410, "token失效！");
        }
        return studentFeign.top(studentVo.getTermId(), Integer.valueOf(studentVo.getUsername().substring(0, 2)), size);
    }

    /**
     * 查询课程详情
     *
     * @param electiveCourseId 选课ID
     * @return 课程信息
     */
    @GetMapping("/detail")
    public ElectiveResult detail(Integer electiveCourseId) {
        return studentFeign.detail(electiveCourseId);
    }

    /**
     * 修改密码
     *
     * @param token   用户令牌
     * @param oldPwd  旧密码
     * @param newPwd  新密码
     * @param session session
     * @return JSON数据
     */
    @PostMapping("/changepwd")
    public ElectiveResult changePwd(String token, String oldPwd, String newPwd, HttpSession session) {
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return ElectiveResult.build(410, "token失效！");
        }
        ElectiveResult electiveResult = studentFeign.updatePwd(studentVo.getUsername(), oldPwd, newPwd);
//        ElectiveResult electiveResult1 = ddmsFeign.changePwd(studentVo.getUsername(), oldPwd, newPwd);
        if (electiveResult.getStatus() != 200) {
            return electiveResult;
        }
        return ElectiveResult.ok();
    }

    /**
     * 查询教师详情
     *
     * @param username 职工号
     * @return JSON数据
     */
    @GetMapping("/teacher")
    public ElectiveResult teacher(String username) {
        return ddmsInterfaceFeign.detail(username);
    }

    /**
     * 消息小红点
     * @param token 用户令牌
     * @param session session
     * @return JSON数据
     */
    @GetMapping("/redpoint")
    public ElectiveResult redPoint(String token,HttpSession session){
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return ElectiveResult.build(410, "token失效！");
        }
        return studentFeign.redPoint(studentVo.getUsername());
    }

    /**
     * 查询所有消息
     * @param token 用户令牌
     * @param session session
     * @return JSON数据
     */
    @GetMapping("/alltip")
    public ElectiveResult allTip(String token,HttpSession session){
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return ElectiveResult.build(410, "token失效！");
        }
        return studentFeign.allTip(studentVo.getUsername());
    }

    /**
     * 查询消息详情
     * @param token 用户令牌
     * @param session session
     * @return JSON数据
     */
    @GetMapping("/tip/detail")
    public ElectiveResult tipDetail(String token,Long informationId,HttpSession session){
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return ElectiveResult.build(410, "token失效！");
        }
        return studentFeign.detail(informationId);
    }

    @GetMapping("/term")
    public ElectiveResult term(String token,HttpSession session){
        StudentVo studentVo = (StudentVo) session.getAttribute(token);
        if (studentVo == null) {
            return ElectiveResult.build(410, "token失效！");
        }
        return termFeign.selectTermByStuNum(studentVo.getUsername());
    }
}
