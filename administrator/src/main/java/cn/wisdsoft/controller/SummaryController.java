package cn.wisdsoft.controller;


import cn.wisdsoft.service.CourseSummaryService;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SummaryController
 * @Description
 * @Author LIZEYU
 * @Date 2019/4/24 8:41
 * @Version 1.0
 **/

@RestController
public class SummaryController {

    @Autowired
    private CourseSummaryService courseSummaryService;


    @PostMapping("/selectCurrentNumberByCourseLibraryName")
    public ElectiveResult selectCurrentNumberByCourseLibraryName(@RequestParam(value = "courseLibraryName") String courseLibraryName) {
        ElectiveResult electiveResult = courseSummaryService.selectCurrentNumberByCourseLibraryName(courseLibraryName);
        return electiveResult;
    }


    /**
     * @return
     * @Author 李泽宇
     * @Description 某学期下那些课程被多少人选了
     * @Date 2019/4/24 15:57
     * @Param
     **/

    @PostMapping("/selectCurrentNumberByTermId")
    @ResponseBody
    public ElectiveResult selectCurrentNumberByTermId(@RequestParam(value = "termId") Integer termId) {
        ElectiveResult electiveResult = courseSummaryService.selectCurrentNumberByTermId(termId);
        return electiveResult;
    }

    /**
     * 根据学期查询当前学期每个教师的选择人数（x课程，y人数，xy教师）
     *
     * @param termId
     * @param collegeName
     * @return
     */

    @GetMapping("/selectTeacherSdNumberByTermId")
    public ElectiveResult selectTeacherSdNumberByTermId(@RequestParam(value = "termId") Integer termId, @RequestParam(value = "collegeName") String collegeName) {
        ElectiveResult electiveResult = courseSummaryService.selectTeacherSdNumberByTermId(termId, collegeName);
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
        ElectiveResult electiveResult = courseSummaryService.selectTeaAllCourse(termId, teacherId);
        return electiveResult;
    }
}
