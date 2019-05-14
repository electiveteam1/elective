package cn.wisdsoft.pojo.vo;

import java.io.Serializable;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/15 11:17
 * @Description: 管理员添加课程到数据库
 */
public class CourseLibraryVo implements Serializable {
    //课程库编号
    private Long courseLibraryId;
    //课程名称
    private String courseLibraryName;
    //学时
    private Short classHour;
    //学分
    private Short credit;
    //学院名称
    private String collegeName;
    //课程组外键
    private String courseGroupName;
    //主键
    private Long performanceRuleId;
    //平时成绩
    private String peacetimePerformanceProportion;
    //期中成绩
    private String midTermPerformanceProportion;
    //期末成绩
    private String finalPerformanceProportion;
    //技能考核
    private String skillAssessmentProportion;

    public Long getCourseLibraryId() {
        return courseLibraryId;
    }

    public CourseLibraryVo setCourseLibraryId(Long courseLibraryId) {
        this.courseLibraryId = courseLibraryId;
        return this;
    }

    public String getCourseLibraryName() {
        return courseLibraryName;
    }

    public CourseLibraryVo setCourseLibraryName(String courseLibraryName) {
        this.courseLibraryName = courseLibraryName;
        return this;
    }

    public Short getClassHour() {
        return classHour;
    }

    public CourseLibraryVo setClassHour(Short classHour) {
        this.classHour = classHour;
        return this;
    }

    public Short getCredit() {
        return credit;
    }

    public CourseLibraryVo setCredit(Short credit) {
        this.credit = credit;
        return this;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public CourseLibraryVo setCollegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    public String getCourseGroupName() {
        return courseGroupName;
    }

    public CourseLibraryVo setCourseGroupName(String courseGroupName) {
        this.courseGroupName = courseGroupName;
        return this;
    }
    public Long getPerformanceRuleId() {
        return performanceRuleId;
    }

    public CourseLibraryVo setPerformanceRuleId(Long performanceRuleId) {
        this.performanceRuleId = performanceRuleId;
        return this;
    }
    public String getPeacetimePerformanceProportion() {
        return peacetimePerformanceProportion;
    }

    public CourseLibraryVo setPeacetimePerformanceProportion(String peacetimePerformanceProportion) {
        this.peacetimePerformanceProportion = peacetimePerformanceProportion;
        return this;
    }

    public String getMidTermPerformanceProportion() {
        return midTermPerformanceProportion;
    }

    public CourseLibraryVo setMidTermPerformanceProportion(String midTermPerformanceProportion) {
        this.midTermPerformanceProportion = midTermPerformanceProportion;
        return this;
    }

    public String getFinalPerformanceProportion() {
        return finalPerformanceProportion;
    }

    public CourseLibraryVo setFinalPerformanceProportion(String finalPerformanceProportion) {
        this.finalPerformanceProportion = finalPerformanceProportion;
        return this;
    }

    public String getSkillAssessmentProportion() {
        return skillAssessmentProportion;
    }

    public CourseLibraryVo setSkillAssessmentProportion(String skillAssessmentProportion) {
        this.skillAssessmentProportion = skillAssessmentProportion;
        return this;
    }


}
