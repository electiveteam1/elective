package cn.wisdsoft.pojo;

import java.io.Serializable;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-06 12:11
 * @ Description：成绩表
 */
public class PerformanceEntity implements Serializable {
    //主键
    private Long performanceId;
    //学生编号
    private String studentId;
    //规则编号
    private Long performanceRuleId;
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
    //是否及格（0：不及格，1：及格）
    private Short passFlag;
    //是否发布（0：不允许修改，1：允许修改）
    private Short allowFlag;
    //学期编号
    private Integer termId;
    //备注
    private String remark;
    //选课ID
    private Long electiveCourseId;

    public Long getElectiveCourseId() {
        return electiveCourseId;
    }

    public void setElectiveCourseId(Long electiveCourseId) {
        this.electiveCourseId = electiveCourseId;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public PerformanceEntity setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
        return this;
    }

    public String getStudentId() {
        return studentId;
    }

    public PerformanceEntity setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public Long getPerformanceRuleId() {
        return performanceRuleId;
    }

    public PerformanceEntity setPerformanceRuleId(Long performanceRuleId) {
        this.performanceRuleId = performanceRuleId;
        return this;
    }

    public Double getPeacetimePerformance() {
        return peacetimePerformance;
    }

    public PerformanceEntity setPeacetimePerformance(Double peacetimePerformance) {
        this.peacetimePerformance = peacetimePerformance;
        return this;
    }

    public Double getMidTermPerformance() {
        return midTermPerformance;
    }

    public PerformanceEntity setMidTermPerformance(Double midTermPerformance) {
        this.midTermPerformance = midTermPerformance;
        return this;
    }

    public Double getFinalPerformance() {
        return finalPerformance;
    }

    public PerformanceEntity setFinalPerformance(Double finalPerformance) {
        this.finalPerformance = finalPerformance;
        return this;
    }

    public Double getSkillAssessment() {
        return skillAssessment;
    }

    public PerformanceEntity setSkillAssessment(Double skillAssessment) {
        this.skillAssessment = skillAssessment;
        return this;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public PerformanceEntity setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public Short getPassFlag() {
        return passFlag;
    }

    public PerformanceEntity setPassFlag(Short passFlag) {
        this.passFlag = passFlag;
        return this;
    }

    public Short getAllowFlag() {
        return allowFlag;
    }

    public PerformanceEntity setAllowFlag(Short allowFlag) {
        this.allowFlag = allowFlag;
        return this;
    }

    public Integer getTermId() {
        return termId;
    }

    public PerformanceEntity setTermId(Integer termId) {
        this.termId = termId;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public PerformanceEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
