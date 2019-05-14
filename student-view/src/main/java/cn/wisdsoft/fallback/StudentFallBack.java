package cn.wisdsoft.fallback;

import cn.wisdsoft.feign.StudentFeign;
import cn.wisdsoft.pojo.StudentEntity;
import cn.wisdsoft.util.CourseResult;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.vo.StudentVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-03-29 09:22
 * @ Description：
 */
@Component
public class StudentFallBack implements StudentFeign {

    @Override
    public ElectiveResult login(String username, String password) {
        return ElectiveResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult insert(StudentEntity studentEntity) {
        return ElectiveResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult getCourse(Long electiveCourseId, String courseGroupId, StudentVo studentVo) {
        return ElectiveResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult dropCourse(Long electiveCourseId, StudentVo studentVo) {
        return ElectiveResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult myCourse(String courseFlag, StudentVo studentVo) {
        return ElectiveResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public CourseResult allCourse(String nature, String volume, String locations, String time, Integer page, Integer limit, StudentVo studentVo) {
        return CourseResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public CourseResult optionCourse(Integer page, Integer limit, StudentVo studentVo) {
        return CourseResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult grade(String term, StudentVo studentVo) {
        return ElectiveResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult allCourseNum() {
        return ElectiveResult.ok(128);
    }

    @Override
    public ElectiveResult selectNum(Integer termId, Integer grade) {
        return ElectiveResult.ok(0);
    }

    @Override
    public ElectiveResult top(Integer termId, Integer grade,Integer size) {
        return ElectiveResult.ok();
    }

    @Override
    public ElectiveResult detail(Integer electiveCourseId) {
        return ElectiveResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult updatePwd(String username, String oldPwd, String newPwd) {
        return ElectiveResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult allTip(String studentId) {
        return ElectiveResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult redPoint(String studentId) {
        return ElectiveResult.build(410, "服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult detail(Long informationId) {
        return ElectiveResult.build(410, "服务器忙，请稍后再试！");
    }
}
