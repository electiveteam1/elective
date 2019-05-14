package cn.wisdsoft.controller;


import cn.wisdsoft.pojo.vo.CourseLibraryVo;
import cn.wisdsoft.service.CourseLibraryService;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/22 19:51
 * @Description:
 */
@RestController
public class CourseLibraryController {

    @Autowired
    private CourseLibraryService courseLibraryService;

    /**
     * 根据管理员身份，查询课程
     * @param page 页数页码
     * @param limit 条数
     * @param collegeName
     * @return
     */
    @GetMapping("/selectAllCourse")
    public PageResult selectAllCourse(@RequestParam(value = "page",defaultValue = "1") int page,
                                      @RequestParam(value = "limit",defaultValue = "10") int limit,
                                      String collegeName, HttpSession session,String administrator,
                                      @RequestParam(value = "CourseName",required = false) String CourseName){
        session.getAttribute(administrator);
        return courseLibraryService.selectAllCourse(page, limit, collegeName,CourseName);
    }

    /**
     * 添加课程到课程库
     * @param courseLibraryVo
     * @return
     */
    @PostMapping("/insertCourseLibrary")
    public ElectiveResult insertCourseLibrary(@RequestBody CourseLibraryVo courseLibraryVo){
        return courseLibraryService.insertCourseLibrary(courseLibraryVo);
    }

    /**
     * 查询所有课组
     * @return
     */
    @GetMapping("/selectAllCourseGroup")
    public ElectiveResult selectAllCourseGroup(){
        return courseLibraryService.selectAllCourseGroup();
    }
}
