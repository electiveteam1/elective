package cn.wisdsoft.controller;

import cn.wisdsoft.pojo.ElectiveCourseEntity;
import cn.wisdsoft.pojo.TermEntity;
import cn.wisdsoft.service.ElectiveCourseService;
import cn.wisdsoft.service.TermService;
import cn.wisdsoft.util.ConstCommon;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/19 18:48
 * @Description:
 */
@RestController
public class ElectiveCourseController {

    @Autowired
    private ElectiveCourseService electiveCourseService;
    @Autowired
    private TermService termService;
    /**
     * 查询所有选课的课程信息
     *
     * @param page        页码
     * @param limit       条数
     * @param collegeName
     * @return
     */
    @GetMapping("/selectAllElectiveCourseInfo")
    public PageResult selectAllElectiveCourseInfo(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "limit", defaultValue = "10") int limit,
                                                  @RequestParam(value = "collegeName") String collegeName) {
        return electiveCourseService.selectAllElectiveCourseInfo(page, limit, collegeName);
    }

    /**
     * 根据教师名称或者课程名称查询
     * @param page        页码
     * @param limit       调试
     * @param collegeName 学院名称
     * @param CourseName  课程名称
     * @param TeacherName 教师名称
     * @return
     */
    @GetMapping("/selectAllElectiveCourseByCourseOrTeaName")
    public PageResult selectAllElectiveCourseByCourseOrTeaName(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "limit", defaultValue = "10") int limit,
                                                               @RequestParam(value = "collegeName") String collegeName,
                                                               @RequestParam(value = "CourseName", required = false) String CourseName,
                                                               @RequestParam(value = "TeacherName", required = false) String TeacherName) {
        return electiveCourseService.selectAllElectiveCourseByCourseOrTeaName(page, limit, collegeName, CourseName, TeacherName);
    }

    /**
     * 查询申请修改成绩的课程
     *
     * @param page        页码
     * @param limit       条数
     * @param collegeName 学院名称
     * @return
     */
    @GetMapping("/selectElectiveByStatus")
    public PageResult selectElectiveByStatus(@RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "limit", defaultValue = "10") int limit,
                                             @RequestParam(value = "collegeName") String collegeName) {
        return electiveCourseService.selectElectiveByStatus(page, limit, collegeName);
    }

    /**
     * 配置课程信息
     * @param JsonDate
     * @return
     */
    @PostMapping("/insertCourse")
    public ElectiveResult insertCourse(@RequestParam String JsonDate) {
        JSONArray arrayList = JSONArray.parseArray(JsonDate);
        //转换为目标对象list
        List<ElectiveCourseEntity> electiveCourseEntityList = JSONObject.parseArray(arrayList.toJSONString(), ElectiveCourseEntity.class);
        if (ConstCommon.Term_Id != null) {
            for (int i=0;i<electiveCourseEntityList.size();i++){
                electiveCourseEntityList.get(i).setTermId(ConstCommon.Term_Id);
            }
        }else{
            TermEntity termEntity = termService.selectTermId();
            for (int i=0;i<electiveCourseEntityList.size();i++){
                electiveCourseEntityList.get(i).setTermId(termEntity.getTermId());
            }
        }
        ElectiveResult electiveResult = electiveCourseService.insertCourse(electiveCourseEntityList);
        return electiveResult;
    }

    /**
     * 管理员停掉人数不够的课程
     *
     * @param electiveCourseId
     * @return
     */
    @PostMapping("/deleteCourse")
    public ElectiveResult deleteCourse(Integer electiveCourseId) {
        ElectiveResult electiveResult = electiveCourseService.deleteCourse(electiveCourseId);
        return electiveResult;
    }

    /**
     * 打回教师上传的成绩
     *
     * @param electiveCourseId
     * @return
     */
    @PostMapping("/updateElectiveCourseStatus")
    public ElectiveResult updateElectiveCourseStatus(Integer electiveCourseId) {
        ElectiveResult electiveResult = electiveCourseService.updateElectiveCourseStatus(electiveCourseId);
        return electiveResult;
    }

    /**
     * 查询选课课程名称
     * @param CollegeName
     * @return
     */
    @GetMapping("/SelectCourseName")
    public ElectiveResult SelectCourseName(@RequestParam("CollegeName")String CollegeName){
        return electiveCourseService.SelectCourseName(CollegeName);
    }
}
