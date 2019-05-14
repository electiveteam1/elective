package cn.wisdsoft.controller;

import cn.wisdsoft.service.PerformanceService;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/20 15:45
 * @Description: 成绩模块
 */
@RestController
public class PerformanceController {
    @Autowired
    private PerformanceService performanceService;


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
        return performanceService.selectAllPerformance(page, limit, collegeName);
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
        return performanceService.selectPerformanceByIdOrName(page,limit,studentId, studentName,collegeName);
    }


    /**
     * 查询某门课程的及格率
     * @param courseId
     * @return
     */
    @GetMapping("/selectCoursePassRate")
    public ElectiveResult selectCoursePassRate(@RequestParam("courseId") String courseId){
        return performanceService.selectCoursePassRate(courseId);
    }

}
