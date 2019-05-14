package cn.wisdsoft.mapper;

import cn.wisdsoft.mapper.SQL.SQLUtil;
import cn.wisdsoft.pojo.ElectiveCourseEntity;
import cn.wisdsoft.pojo.ElectiveCourseDo;
import cn.wisdsoft.pojo.TermEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-10 10:39
 * @ Description：
 */
public interface CourseMapper {
    /**
     * 查询课程
     *
     * @param grade    年级
     * @param termId   学期ID
     * @param college  学院
     * @param volume   有无容量
     * @param nature   课程性质
     * @param week     星期
     * @param location 地点
     * @return 课程列表
     */
//    @Select("<script>" +
//            "SELECT elective_course_id, course_library_id," +
//            "course_library_name, course_group_name," +
//            "teacher_name, time_json, locations_json," +
//            "max_number, min_number, class_hour," +
//            "credit, course_library_introduction, current_number" +
//            "FROM elective_course " +
//            "WHERE restricted_grade = #{grade} " +
//            "AND current_number != 0 " +
//            "AND term_id = #{termId}" +
//            "AND college_name = #{college}" +
//            "AND delete_flag = 0" +
//            "ORDER BY current_number DESC" +
//            "</script>")
    @SelectProvider(type = SQLUtil.class, method = "allCourse")
    List<ElectiveCourseDo> allCourse(Integer grade, Integer termId, String college, String volume, String nature, String week, String location);

    /**
     * 查询备选课程
     *
     * @param grade   年级
     * @param termId  学期
     * @param college 学院
     * @return 课程列表
     */
    @SelectProvider(type = SQLUtil.class, method = "optionCourse")
    List<ElectiveCourseDo> optionCourse(Integer grade, Integer termId, String college);

    /**
     * 查询是否可以选课
     *
     * @param termId 学期ID
     * @return 学期实体类
     */
    @Select("select * from term where term_status = 2 and term_id = #{termId}")
    TermEntity selectOne(Integer termId);

    /**
     * 查询课程总数
     *
     * @return 数量
     */
    @Select("select count(*) from course_library")
    Integer selectAllCourseNum();

    /**
     * 查询本学期可选课程数量
     *
     * @param termId 学期ID
     * @param grade  年级
     * @return 数量
     */
    @Select("select count(*) from elective_course " +
            "where term_id = #{termId} " +
            "and delete_flag = 0 " +
            "and option_flag = 1 " +
            "and elective_course_status = 0 " +
            "and current_number < max_number " +
            "and restricted_grade = #{grade}")
    Integer selectNum(Integer termId, Integer grade);

    /**
     * 查询选课人数前三的课程
     *
     * @param termId 学期ID
     * @param grade  年级
     * @return 数据信息
     */
    @Select("select elective_course_id, course_library_id," +
            "course_library_name, course_group_name," +
            "teacher_id,teacher_name, time_and_place," +
            "max_number, min_number, class_hour," +
            "credit, course_library_introduction, current_number" +
            " from elective_course where term_id = #{termId} " +
            "and elective_course_status = 0 " +
            "and delete_flag = 0 " +
            "and option_flag = 1 " +
            "and restricted_grade = #{grade} " +
            "group by course_library_name order by SUM(current_number) desc limit 0,#{size}")
    List<ElectiveCourseDo> top(Integer termId, Integer grade, Integer size);

    /**
     * 查询课程详情
     *
     * @param electiveCourseId 选课ID
     * @return 课程信息
     */
    @Select("select e.elective_course_id, e.course_library_id," +
            "e.course_library_name, e.course_group_name," +
            "e.teacher_name, e.time_and_place," +
            "e.max_number, e.min_number, e.class_hour," +
            "e.credit, e.course_library_introduction, e.current_number," +
            "p.peacetime_performance_proportion,p.mid_term_performance_proportion," +
            "p.final_performance_proportion,p.skill_assessment_proportion" +
            " from elective_course as e," +
            "course_library as c," +
            "performance_rule as p " +
            "where elective_course_id = #{electiveCourseId} " +
            "and e.course_library_id = c.course_library_id " +
            "and c.performance_rule_id = p.performance_rule_id")
    ElectiveCourseDo detail(Integer electiveCourseId);
}
