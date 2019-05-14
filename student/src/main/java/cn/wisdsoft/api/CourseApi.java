package cn.wisdsoft.api;

import cn.wisdsoft.pojo.StudentVo;
import cn.wisdsoft.service.CourseService;
import cn.wisdsoft.util.CourseResult;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-04 08:24
 * @ Description：
 */
@RestController
@RequestMapping("/course")
public class CourseApi {
    private final CourseService courseService;

    public CourseApi(CourseService courseService) {
        this.courseService = courseService;
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
     * @param studentVo session
     * @return 课程信息
     */
    @PostMapping("/allcourse")
    public CourseResult allCourse(String nature, String volume,
                                  String locations, String time, Integer page, Integer limit, @RequestBody StudentVo studentVo) {
        page = page == 0 ? 1 : page;
        limit = limit == 0 ? 5 : limit;
        String coll = "";
        if ("school".equals(nature)){
            coll = "校选";
        } else if("college".equals(nature)){
            coll = studentVo.getCollege();
        }
        return courseService.allCourse(Integer.valueOf(studentVo.getUsername().substring(0, 2)), studentVo.getTermId(), coll,
                nature, volume, locations, time, page, limit);
    }

    /**
     * 查询所有备选课程---测试成功
     *
     * @param token     用户令牌
     * @param page      当前页数
     * @param limit     每页数量
     * @param studentVo session
     * @return
     */
    @PostMapping("/optioncourse")
    public CourseResult optionCourse(Integer page, Integer limit, @RequestBody StudentVo studentVo) {
        page = page == 0 ? 1 : page;
        limit = limit == 0 ? 5 : limit;
        return courseService.optionCourse(Integer.valueOf(studentVo.getUsername().substring(0, 2)), studentVo.getTermId(),
                studentVo.getCollege(), page, limit);
    }

    /**
     * 查询课程总数
     *
     * @return JSON数据
     */
    @PostMapping("/allcoursenum")
    public ElectiveResult allCourseNum() {
        return courseService.allCourseNum();
    }

    /**
     * 查询本学期可选课程数量
     *
     * @param termId 学期ID
     * @param grade  年级
     * @return JSON数据
     */
    @PostMapping("/selectnum")
    public ElectiveResult selectNum(Integer termId, Integer grade) {
        return courseService.selectNum(termId, grade);
    }

    /**
     * 查询选择人数前三的课程
     *
     * @param termId 学期ID
     * @param grade  年级
     * @return JSON数据
     */
    @PostMapping("/top")
    public ElectiveResult top(Integer termId, Integer grade, Integer size) {
        return courseService.top(termId, grade, size);
    }

    /**
     * 查询课程详情
     *
     * @param electiveCourseId 选课ID
     * @return 课程信息
     */
    @PostMapping("/detail")
    public ElectiveResult detail(Integer electiveCourseId) {
        return courseService.detail(electiveCourseId);
    }
}
