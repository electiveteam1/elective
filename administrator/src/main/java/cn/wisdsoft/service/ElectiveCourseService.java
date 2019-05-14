package cn.wisdsoft.service;

import cn.wisdsoft.pojo.ElectiveCourseEntity;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/19 18:49
 * @Description:
 */
public interface ElectiveCourseService {
    /**
     * 查询所有的选课信息
     *
     * @param page        页码
     * @param limit       条数
     * @param collegeName 学院名称
     * @return
     */
    PageResult selectAllElectiveCourseInfo(int page, int limit, String collegeName);

    /**
     * 根据教师名称或者课程名称查询
     *
     * @param page        页码
     * @param limit       调试
     * @param collegeName 学院名称
     * @param CourseName  课程名称
     * @param TeacherName 教师名称
     * @return
     */
    PageResult selectAllElectiveCourseByCourseOrTeaName(int page, int limit, String collegeName, String CourseName, String TeacherName);

    /**
     * 查询申请修改成绩的课程
     *
     * @param page        页码
     * @param limit       条数
     * @param collegeName 学院名称
     * @return
     */
    PageResult selectElectiveByStatus(int page, int limit, String collegeName);

    /**
     * 配置教师上课信息
     *
     * @param electiveCourse
     * @return
     */
    ElectiveResult insertCourse(List<ElectiveCourseEntity> electiveCourse);

    /**
     * 停止课程
     *
     * @param electiveCourseId
     * @return
     */
    ElectiveResult deleteCourse(Integer electiveCourseId);

    /**
     * 将教师申请修改成绩的课程打回
     *
     * @param electiveCourseId
     * @return
     */
    ElectiveResult updateElectiveCourseStatus(Integer electiveCourseId);

    /**
     * 查询课程名
     * @param CollegeName
     * @return
     */
    ElectiveResult SelectCourseName(String CollegeName);
}
