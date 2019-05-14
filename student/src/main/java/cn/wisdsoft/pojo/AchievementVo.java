package cn.wisdsoft.pojo;

import java.io.Serializable;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-13 22:49
 * @ Description：成绩视图层
 */
public class AchievementVo implements Serializable {
    //平时成绩
    private Double peacetimePerformance;
    //期中成绩
    private Double midTermPerformance;
    //期末成绩
    private Double finalPerformance;
    //技能考核
    private Double skillAssessment;
    //总分
    private Double totalScore;
    //平时成绩占比
    private Integer peacetimePerformanceProportion;
    //其中成绩占比
    private Integer midTermPerformanceProportion;
    //期末成绩占比
    private Integer finalPerformanceProportion;
    //技能考核占比
    private Integer skillAssessmentProportion;
    //课程名称
    private String courseLibraryName;
    //教师名称
    private String teacherName;
    //学时
    private Integer classHour;
    //学分
    private Integer credit;
    //是否及格
    private Short passFlag;

    public Double getPeacetimePerformance() {
        return peacetimePerformance;
    }

    public AchievementVo setPeacetimePerformance(Double peacetimePerformance) {
        this.peacetimePerformance = peacetimePerformance;
        return this;
    }

    public Double getMidTermPerformance() {
        return midTermPerformance;
    }

    public AchievementVo setMidTermPerformance(Double midTermPerformance) {
        this.midTermPerformance = midTermPerformance;
        return this;
    }

    public Double getFinalPerformance() {
        return finalPerformance;
    }

    public AchievementVo setFinalPerformance(Double finalPerformance) {
        this.finalPerformance = finalPerformance;
        return this;
    }

    public Double getSkillAssessment() {
        return skillAssessment;
    }

    public AchievementVo setSkillAssessment(Double skillAssessment) {
        this.skillAssessment = skillAssessment;
        return this;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public AchievementVo setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public Integer getPeacetimePerformanceProportion() {
        return peacetimePerformanceProportion;
    }

    public AchievementVo setPeacetimePerformanceProportion(Integer peacetimePerformanceProportion) {
        this.peacetimePerformanceProportion = peacetimePerformanceProportion;
        return this;
    }

    public Integer getMidTermPerformanceProportion() {
        return midTermPerformanceProportion;
    }

    public AchievementVo setMidTermPerformanceProportion(Integer midTermPerformanceProportion) {
        this.midTermPerformanceProportion = midTermPerformanceProportion;
        return this;
    }

    public Integer getFinalPerformanceProportion() {
        return finalPerformanceProportion;
    }

    public AchievementVo setFinalPerformanceProportion(Integer finalPerformanceProportion) {
        this.finalPerformanceProportion = finalPerformanceProportion;
        return this;
    }

    public Integer getSkillAssessmentProportion() {
        return skillAssessmentProportion;
    }

    public AchievementVo setSkillAssessmentProportion(Integer skillAssessmentProportion) {
        this.skillAssessmentProportion = skillAssessmentProportion;
        return this;
    }

    public String getCourseLibraryName() {
        return courseLibraryName;
    }

    public AchievementVo setCourseLibraryName(String courseLibraryName) {
        this.courseLibraryName = courseLibraryName;
        return this;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public AchievementVo setTeacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public Integer getClassHour() {
        return classHour;
    }

    public AchievementVo setClassHour(Integer classHour) {
        this.classHour = classHour;
        return this;
    }

    public Integer getCredit() {
        return credit;
    }

    public AchievementVo setCredit(Integer credit) {
        this.credit = credit;
        return this;
    }

    public Short getPassFlag() {
        return passFlag;
    }

    public AchievementVo setPassFlag(Short passFlag) {
        this.passFlag = passFlag;
        return this;
    }
}
