package cn.wisdsoft.feginService.administratorService;

import cn.wisdsoft.pojo.CourseLibraryEntity;
import cn.wisdsoft.pojo.ElectiveCourseEntity;
import cn.wisdsoft.pojo.TermEntity;
import cn.wisdsoft.pojo.TermRuleEntity;
import cn.wisdsoft.pojo.vo.CourseLibraryVo;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: TODO
 * @Author: SongJunWei
 * @CreateDate: 2019/4/11 10:06
 * @UpdateUser:
 * @UpdateDate: 2019/4/11 10:06
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@FeignClient("administrator")
@Component
public interface FeignAdministratorService {
    /**
     * 配置学期规则
     * @param termRules
     * @return
     */
    @PostMapping("/insertTermRule")
    ElectiveResult insertTermRule(@RequestBody TermRuleEntity termRules);

    /**
     * 查询所有学期
     * @param page  页数
     * @param limit 条数
     * @return
     */
    @GetMapping("/selectAllTerm")
    PageResult selectAllTerm(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "10") int limit);

    /**
     * 根据管理员身份，查询课程
     * @param page
     * @param limit
     * @param collegeName
     * @return
     */
    @GetMapping("/selectAllCourse")
    PageResult selectAllCourse(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "limit", defaultValue = "10") int limit,
                               @RequestParam("collegeName") String collegeName,
                               @RequestParam(value = "CourseName", required = false) String CourseName
    );

    /**
     * 根据课程编号，查询课程
     * @param courseId
     * @return
     */
    @GetMapping("/selectCourseById")
    CourseLibraryEntity selectCourseById(@RequestParam("courseId") long courseId);

    /**
     * 管理员停掉人数不够的课程
     * @param electiveCourseId
     * @return
     */
    @PostMapping("/deleteCourse")
    ElectiveResult deleteCourse(@RequestParam("electiveCourseId") long electiveCourseId);

    /**
     * 打回教师上传的成绩
     * @param electiveCourseId
     * @return
     */
    @PostMapping("/updateElectiveCourseStatus")
    ElectiveResult updateElectiveCourseStatus(@RequestParam("electiveCourseId") Integer electiveCourseId);

    /**
     * 查询所有课组
     * @return
     */
    @GetMapping("/selectAllCourseGroup")
    ElectiveResult selectAllCourseGroup();

    /**
     * 添加课程到课程库
     * @param courseLibraryVo
     * @return
     */
    @PostMapping("/insertCourseLibrary")
    ElectiveResult insertCourseLibrary(@RequestBody CourseLibraryVo courseLibraryVo);

    /**
     * 查询所有选课的课程信息
     * @param collegeName
     * @return
     */
    @GetMapping("/selectAllElectiveCourseInfo")
    PageResult selectAllElectiveCourseInfo(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "limit", defaultValue = "10") int limit,
                                           @RequestParam(value = "collegeName") String collegeName);

    /**
     * 根据学院查询成绩
     * @param page 页码
     * @param limit 条数
     * @param collegeName 学院名称
     * @return
     */
    @GetMapping("/selectAllPerformance")
    PageResult selectAllPerformance(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "limit", defaultValue = "10") int limit,
                                    @RequestParam(value = "collegeName", required = false) String collegeName);

    /**
     * 根据姓名和ID查询成绩
     * @param page 页码
     * @param limit 条数
     * @param studentId 学生编号
     * @param studentName 学生姓名
     * @param collegeName 学院名称
     * @return
     */
    @GetMapping("/selectPerformanceByIdOrName")
    PageResult selectPerformanceByIdOrName(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "limit", defaultValue = "10") int limit,
                                           @RequestParam(value = "studentId", required = false) String studentId,
                                           @RequestParam(value = "studentName", required = false) String studentName,
                                           @RequestParam(value = "collegeName", required = false) String collegeName);

    /**
     *  根据教师名称或者课程名称查询
     * @param page 页码
     * @param limit 调试
     * @param collegeName 学院名称
     * @param CourseName 课程名称
     * @param TeacherName 教师名称
     * @return
     */
    @GetMapping("/selectAllElectiveCourseByCourseOrTeaName")
    PageResult selectAllElectiveCourseByCourseOrTeaName(@RequestParam(value = "page", defaultValue = "1") int page,
                                                        @RequestParam(value = "limit", defaultValue = "10") int limit,
                                                        @RequestParam(value = "collegeName") String collegeName,
                                                        @RequestParam(value = "CourseName", required = false) String CourseName,
                                                        @RequestParam(value = "TeacherName", required = false) String TeacherName);

    /**
     * 查询申请修改成绩的课程
     * @param page 页码
     * @param limit 条数
     * @param collegeName 学院名称
     * @return
     */
    @GetMapping("/selectElectiveByStatus")
    PageResult selectElectiveByStatus(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "limit", defaultValue = "10") int limit,
                                      @RequestParam(value = "collegeName") String collegeName);

    /**
     * 配置课程信息
     * @param JsonDate
     * @return
     */
    @PostMapping("/insertCourse")
    ElectiveResult insertCourse(@RequestParam("JsonDate") String JsonDate);

    /**
     * @Author 李泽宇
     * @Description 查询所有学期某课程下的所有人数
     * @Date 2019/4/24 15:56
     * @Param
     * @return
     **/

    @PostMapping("/selectCurrentNumberByCourseLibraryName")
    ElectiveResult selectCurrentNumberByCourseLibraryName(@RequestParam(value = "courseLibraryName") String courseLibraryName);

    /**
     * @Author 李泽宇
     * @Description 某学期下那些课程被多少人选了
     * @Date 2019/4/24 15:57
     * @Param
     * @return
     **/

    @PostMapping("/selectCurrentNumberByTermId")
    ElectiveResult selectCurrentNumberByTermId(@RequestParam(value = "termId") Integer termId);

    /**
     * @Author 李泽宇
     * @Description 某学期某课程下的这些老师被多少人选了
     * @Date 2019/4/24 15:57
     * @Param
     * @return
     **/
    @GetMapping("/selectTeacherSdNumberByTermId")
    ElectiveResult selectTeacherSdNumberByTermId(@RequestParam(value = "termId") Integer termId, @RequestParam(value = "collegeName") String collegeName);

    /**
     * 查询当前学期下该教师下的所有课程选课人数
     * @param termId
     * @param teacherId
     * @return
     */
    @GetMapping("/selectTeaAllCourse")
    ElectiveResult selectTeaAllCourse(@RequestParam(value = "termId") Integer termId, @RequestParam(value = "teacherId") String teacherId);

    /**
     * 查询本学院的课程的及格率
     * @param courseId
     * @return
     */
    @GetMapping("/selectCoursePassRate")
    ElectiveResult selectCoursePassRate(@RequestParam("courseId") String courseId);

    /**
     * 查询选课课程名称
     * @param CollegeName
     * @return
     */
    @GetMapping("/SelectCourseName")
    ElectiveResult SelectCourseName(@RequestParam("CollegeName") String CollegeName);

    /**
     * 更改规则
     * @param JSONInfo
     * @return
     */
    @PostMapping("/updateTermRule")
    ElectiveResult updateTermRule(@RequestParam("JSONInfo") String JSONInfo);
}
