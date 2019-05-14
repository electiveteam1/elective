package cn.wisdsoft.apiController;
import cn.wisdsoft.feginService.administratorService.FeignAdministratorService;
import cn.wisdsoft.pojo.CourseLibraryEntity;
import cn.wisdsoft.pojo.ElectiveCourseEntity;
import cn.wisdsoft.pojo.TermRuleEntity;
import cn.wisdsoft.pojo.ddmsPojo.ClazzRoomEntity;
import cn.wisdsoft.pojo.vo.CourseLibraryVo;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description:    管理员后台接口调用
* @Author:         SongJunWei
* @CreateDate:     2019/4/11 10:04
* @UpdateUser:
* @UpdateDate:     2019/4/11 10:04
* @UpdateRemark:   修改内容
* @Version:        1.0

*/
@RestController
public class AdministratorController {

    @Autowired
    private FeignAdministratorService feignAdministratorService;

    /**
     * 配置学期规则
     * @param termRules
     * @return
     */
    @PostMapping("/insertTermRule")
    public ElectiveResult insertTermRule(TermRuleEntity termRules){
        ElectiveResult electiveResult = feignAdministratorService.insertTermRule(termRules);
        return electiveResult;
    }

    /**
     * 查询所有学期
     * @param page 页数
     * @param limit 条数
     * @return
     */
    @GetMapping("/selectAllTerm")
    public PageResult selectAllTerm(@RequestParam(value = "page",defaultValue = "1") int page,
                                    @RequestParam(value = "limit",defaultValue = "10")  int limit) {
        return feignAdministratorService.selectAllTerm(page, limit);
    }

    /**
     * 根据管理员身份，查询课程
     * @param page
     * @param limit
     * @param collegeName
     * @return
     */
    @GetMapping("/selectAllCourse")
    public PageResult selectAllCourse(@RequestParam(value = "page",defaultValue = "1") int page,
                               @RequestParam(value = "limit",defaultValue = "10") int limit,
                                      @RequestParam("collegeName") String collegeName,
                                      @RequestParam(value = "CourseName",required = false) String CourseName){
        return feignAdministratorService.selectAllCourse(page, limit, collegeName,CourseName);
    }

    /**
     * 根据课程编号，查询课程
     * @param courseId
     * @return
     */
    @GetMapping("/selectCourseById")
    public CourseLibraryEntity selectCourseById(@RequestParam("courseId") long courseId){
        return feignAdministratorService.selectCourseById(courseId);
    }

    /**
     * 管理员停掉人数不够的课程
     * @param electiveCourseId
     * @return
     */
    @GetMapping("/deleteCourse")
    public ElectiveResult deleteCourse(@RequestParam("electiveCourseId") long electiveCourseId){
        ElectiveResult electiveResult = feignAdministratorService.deleteCourse(electiveCourseId);
        return electiveResult;
    }

    /**
     * 打回教师上传的成绩
     * @param electiveCourseId
     * @return
     */
    @PostMapping("/updateElectiveCourseStatus")
    public ElectiveResult updateElectiveCourseStatus(@RequestParam("electiveCourseId")Integer electiveCourseId){
        ElectiveResult electiveResult = feignAdministratorService.updateElectiveCourseStatus(electiveCourseId);
        return electiveResult;
    }

    /**
     * 查询所有课组
     * @return
     */
    @GetMapping("/selectAllCourseGroup")
    public ElectiveResult selectAllCourseGroup(){
        return feignAdministratorService.selectAllCourseGroup();
    }

    /**
     * 添加课程到课程库
     * @param courseLibraryVo
     * @return
     */
    @PostMapping("/insertCourseLibrary")
    public ElectiveResult insertCourseLibrary(CourseLibraryVo courseLibraryVo){
        return feignAdministratorService.insertCourseLibrary(courseLibraryVo);
    }

    /**
     * 查询所有选课的课程信息
     * @param collegeName
     * @return
     */
    @GetMapping("/selectAllElectiveCourseInfo")
    public PageResult selectAllElectiveCourseInfo(@RequestParam(value = "page",defaultValue = "1") int page,
                                                  @RequestParam(value = "limit",defaultValue = "10") int limit,
                                                  @RequestParam(value = "collegeName") String collegeName){
        return feignAdministratorService.selectAllElectiveCourseInfo(page, limit, collegeName);
    }

    /**
     * 根据学院查询成绩
     * @param page 页码
     * @param limit 条数
     * @param collegeName 学院名称
     * @return
     */
    @GetMapping("/selectAllPerformance")
    public PageResult selectAllPerformance(@RequestParam(value = "page",defaultValue = "1") int page,
                                           @RequestParam(value = "limit",defaultValue = "10") int limit,
                                           @RequestParam(value = "collegeName",required = false) String collegeName){
        return feignAdministratorService.selectAllPerformance(page, limit, collegeName);
    }

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
    public PageResult selectPerformanceByIdOrName(@RequestParam(value = "page",defaultValue = "1") int page,
                                                  @RequestParam(value = "limit",defaultValue = "10") int limit,
                                                  @RequestParam(value = "studentId",required = false) String studentId,
                                                  @RequestParam(value = "studentName",required = false) String studentName,
                                                  @RequestParam(value = "collegeName",required = false) String collegeName){
        return feignAdministratorService.selectPerformanceByIdOrName(page,limit,studentId, studentName,collegeName);
    }

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
    public PageResult selectAllElectiveCourseByCourseOrTeaName(@RequestParam(value = "page",defaultValue = "1") int page,
                                                               @RequestParam(value = "limit",defaultValue = "10") int limit,
                                                               @RequestParam(value = "collegeName") String collegeName,
                                                               @RequestParam(value = "CourseName",required = false) String CourseName,
                                                               @RequestParam(value = "TeacherName",required = false) String TeacherName){
        return feignAdministratorService.selectAllElectiveCourseByCourseOrTeaName(page, limit, collegeName,CourseName,TeacherName);
    }

    /**
     * 查询申请修改成绩的课程
     * @param page 页码
     * @param limit 条数
     * @param collegeName 学院名称
     * @return
     */
    @GetMapping("/selectElectiveByStatus")
    public PageResult selectElectiveByStatus(@RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "limit", defaultValue = "10") int limit,
                                             @RequestParam(value = "collegeName") String collegeName) {
        return feignAdministratorService.selectElectiveByStatus(page,limit,collegeName);
    }

    /**
     * 配置课程信息
     * @param JsonDate
     * @return
     */
    @PostMapping("/insertCourse")
    public ElectiveResult insertCourse(@RequestParam("JsonDate") String JsonDate){
        ElectiveResult electiveResult = feignAdministratorService.insertCourse(JsonDate);
        return electiveResult;
    }

    /**
     * @Author 李泽宇
     * @Description 查询所有学期某课程下的所有人数
     * @Date 2019/4/24 15:56
     * @Param
     * @return
     **/

    @PostMapping("/selectCurrentNumberByCourseLibraryName")
    @ResponseBody
    public ElectiveResult selectCurrentNumberByCourseLibraryName(@RequestParam(value = "courseLibraryName") String courseLibraryName) {
        ElectiveResult electiveResult = feignAdministratorService.selectCurrentNumberByCourseLibraryName(courseLibraryName);
        return electiveResult;
    }

    /**
     * @Author 李泽宇
     * @Description 某学期下那些课程被多少人选了
     * @Date 2019/4/24 15:57
     * @Param
     * @return
     **/

    @PostMapping("/selectCurrentNumberByTermId")
    @ResponseBody
    public ElectiveResult selectCurrentNumberByTermId(@RequestParam(value = "termId") Integer termId) {
        ElectiveResult electiveResult = feignAdministratorService.selectCurrentNumberByTermId(termId);
        return electiveResult;
    }

    /**
     * @Author 李泽宇
     * @Description 某学期某课程下的这些老师被多少人选了
     * @Date 2019/4/24 15:57
     * @Param
     * @return
     **/
    @GetMapping("/selectTeacherSdNumberByTermId")
    public ElectiveResult selectTeacherSdNumberByTermId(@RequestParam(value = "termId") Integer termId,@RequestParam(value = "collegeName") String collegeName) {
        ElectiveResult electiveResult = feignAdministratorService.selectTeacherSdNumberByTermId(termId,collegeName);
        return electiveResult;
    }

    /**
     * 查询当前学期下该教师下的所有课程选课人数
     * @param termId
     * @param teacherId
     * @return
     */
    @GetMapping("/selectTeaAllCourse")
    public ElectiveResult selectTeaAllCourse(@RequestParam(value = "termId") Integer termId, @RequestParam(value = "teacherId") String teacherId) {
        ElectiveResult electiveResult = feignAdministratorService.selectTeaAllCourse(termId, teacherId);
        return electiveResult;
    }

    /**
     * 查询本学院的课程的及格率
     * @param courseId
     * @return
     */
    @GetMapping("/selectCoursePassRate")
    public ElectiveResult selectCoursePassRate(@RequestParam("courseId") String courseId){
        return feignAdministratorService.selectCoursePassRate(courseId);
    }

    /**
     * 查询选课课程名称
     * @param CollegeName
     * @return
     */
    @GetMapping("/SelectCourseName")
    public ElectiveResult SelectCourseName(@RequestParam("CollegeName")String CollegeName){
        return feignAdministratorService.SelectCourseName(CollegeName);
    }
    /**
     * 更改规则
     * @param JSONInfo
     * @return
     */
    @PostMapping("/updateTermRule")
    public ElectiveResult updateTermRule(@RequestParam("JSONInfo") String JSONInfo){
        return feignAdministratorService.updateTermRule(JSONInfo);
    }
}