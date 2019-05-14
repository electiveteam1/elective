package cn.wisdsoft.mapper.SQL;

import org.apache.ibatis.jdbc.SQL;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-12 08:20
 * @ Description：
 */
public class SQLUtil {
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
     * @return 对应sql语句
     */
    public String allCourse(Integer grade, Integer termId, String college, String volume, String nature, String week, String location) {
        return new SQL() {
            {
                SELECT("elective_course_id, course_library_id");
                SELECT("course_library_name, course_group_name");
                SELECT("teacher_id,teacher_name, time_and_place");
                SELECT("max_number, min_number, class_hour");
                SELECT("credit, course_library_introduction, current_number,remark");
                FROM("elective_course");
                WHERE("restricted_grade = " + grade);
//                WHERE("college_name = '" + college + "'");
                WHERE("term_id = " + termId);
                WHERE("delete_flag = 0");
                if ((volume != null || !volume.equals("")) && !volume.equals("all")) {
                    if (volume.equals("have")) {
                        WHERE("current_number < max_number");
                    } else if (volume.equals("nothave")) {
                        WHERE("current_number = max_number");
                    }
                }
                if (nature != null || !nature.equals("")) {
                    if(!nature.equals("standby")){
                        WHERE("option_flag = 1");
                        if(!nature.equals("all")){
                            WHERE("college_name = '" + college + "'");
                        }
                    } else {
                        WHERE("option_flag = 0");
                        WHERE("current_number = 0");
                    }
                }
                if ((week != null || !week.equals("")) && !week.equals("all")) {
                    WHERE("time_and_place LIKE '%" + week + "%'");
                }
                if ((location != null || !location.equals("")) && !location.equals("all")) {
                    WHERE("time_and_place LIKE '%" + location + "%'");
                }
//                GROUP_BY("course_library_name");
                ORDER_BY("current_number DESC");
            }
        }.toString();
    }

    /**
     * 查询备选课程(已整合到查询课程中)
     *
     * @param grade   年级
     * @param termId  学期
     * @param college 学院
     * @return 对应sql语句
     */
    public String optionCourse(Integer grade, Integer termId, String college) {
        return new SQL() {
            {
                SELECT("elective_course_id, course_library_id");
                SELECT("course_library_name, course_group_name");
                SELECT("teacher_id,teacher_name, time_and_place");
                SELECT("max_number, min_number, class_hour");
                SELECT("credit, course_library_introduction,remark");
                FROM("elective_course");
                WHERE("option_flag = 0");
                WHERE("restricted_grade = " + grade);
                WHERE("term_id = " + termId);
//                WHERE("college_name = '" + college + "'");
                WHERE("current_number = 0");
            }
        }.toString();
    }

    /**
     * 查询成绩
     *
     * @param studentId 学生ID
     * @param termId    学期ID
     * @return 成绩信息
     */
    public String selectAchievement(String studentId, String termId) {
        return new SQL() {
            {
                SELECT("p.peacetime_performance,p.mid_term_performance");
                SELECT("p.final_performance,p.skill_assessment,p.total_score,p.pass_flag");
                SELECT("pr.peacetime_performance_proportion,pr.mid_term_performance_proportion");
                SELECT("pr.final_performance_proportion,pr.skill_assessment_proportion");
                SELECT("e.course_library_name,e.teacher_name");
                SELECT("e.class_hour,e.credit");
                FROM("performance AS p, performance_rule AS pr, elective_course AS e");
                if (!"all".equals(termId)){
                    WHERE("p.term_id = " + termId);
                }
                WHERE("p.allow_flag = 0");
                WHERE("p.elective_course_id = e.elective_course_id");
                WHERE("p.student_id = '" + studentId + "'");
                WHERE("p.performance_rule_id = pr.performance_rule_id");
            }
        }.toString();
    }

    /**
     * 查询学生已选课程
     *
     * @param studentId 学生学号
     * @param learnFlag 是否正在学习
     * @return 课程列表
     */
    public String selectElectiveCourse(String studentId, String learnFlag) {
        return new SQL() {
            {
                SELECT("s.elective_course_id,course_library_id,course_library_name,teacher_name");
                SELECT("time_and_place,class_hour,credit,time_stamp,course_flag,e.remark,t.term_status");
                FROM("student_elective AS s,elective_course AS e,term as t");
                WHERE("student_id = '" + studentId + "' AND s.elective_course_id = e.elective_course_id");
                WHERE("e.term_id = t.term_id");
                if (!learnFlag.equals("all")) {
                    WHERE("course_flag = #{learnFlag}");
                }
            }
        }.toString();
    }
}
