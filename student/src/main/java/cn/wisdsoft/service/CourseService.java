package cn.wisdsoft.service;

import cn.wisdsoft.util.CourseResult;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-10 16:57
 * @ Description：
 */
public interface CourseService {
    /**
     * 查询所有课程
     *
     * @param grade     年级
     * @param termId    学期ID
     * @param nature    课程类别（school-》校选；college-》院选；all-》所有）
     * @param volume    容量（all-》所有；have-》有；nothave-》无）
     * @param locations 地点（all-》所有；其他对应楼号）
     * @param time      上课时间（all-》所有；其他对应星期）
     * @param page      当前页数
     * @param limit     每页数量
     * @return 课程信息
     */
    CourseResult allCourse(Integer grade, Integer termId, String college, String nature, String volume,
                           String locations, String time, Integer page, Integer limit);

    /**
     * 查询备选课程
     *
     * @param grade   年级
     * @param termId  学期
     * @param college 学院
     * @param page    当前页数
     * @param limit   每页数量
     * @return 课程信息
     */
    CourseResult optionCourse(Integer grade, Integer termId, String college, Integer page, Integer limit);

    /**
     * 查询课程总数
     *
     * @return JSON数据
     */
    ElectiveResult allCourseNum();

    /**
     * 查询本学期可选课程数量
     *
     * @param termId 学期ID
     * @param grade  年级
     * @return JSON数据
     */
    ElectiveResult selectNum(Integer termId, Integer grade);

    /**
     * 查询选课人数前三的课程
     *
     * @param termId 学期ID
     * @param grade  年级
     * @return JSON数据
     */
    ElectiveResult top(Integer termId, Integer grade, Integer size);

    /**
     * 查询课程详情
     *
     * @param electiveCourseId 选课ID
     * @return 课程信息
     */
    ElectiveResult detail(Integer electiveCourseId);
}
