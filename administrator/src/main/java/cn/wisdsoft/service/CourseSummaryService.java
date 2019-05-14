package cn.wisdsoft.service;

import cn.wisdsoft.pojo.vo.CourseSummaryVo;
import cn.wisdsoft.util.ElectiveResult;

import java.util.List;

/**
 * @ClassName CourseSummaryService
 * @Description
 * @Author LIZEYU
 * @Date 2019/4/24 10:27
 * @Version 1.0
 **/
public interface CourseSummaryService {
    /**
     * @Author 李泽宇
     * @Description 查询所有学期某课程下的所有人数
     * @Date 2019/4/24 10:15
     * @Param
     * @return
     **/
    ElectiveResult selectCurrentNumberByCourseLibraryName(String courseLibraryName);

    /**
     * @Author 李泽宇
     * @Description 某学期下那些课程被多少人选了
     * @Date 2019/4/24 10:17
     * @Param
     * @return
     **/
    ElectiveResult selectCurrentNumberByTermId(Integer termId);

    /**
     * @Author 李泽宇
     * @Description 某学期某课程下的这些老师被多少人选了
     * @Date 2019/4/24 10:19
     * @Param
     * @return
     **/
    ElectiveResult selectTeacherSdNumberByTermId(Integer termId, String collegeName);

    /**
     * @Author 李泽宇
     * @Description 某一学期下某个老师下的所有课程选课人数
     * @Date 2019/4/24 11:05
     * @Param
     * @return
     **/
    ElectiveResult selectTeaAllCourse(Integer termId, String teacherId);
}
