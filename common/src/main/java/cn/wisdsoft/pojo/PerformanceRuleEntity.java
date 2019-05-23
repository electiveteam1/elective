package cn.wisdsoft.pojo;

import java.io.Serializable;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-06 12:16
 * @ Description：成绩规则表
 */
public class PerformanceRuleEntity implements Serializable {
    //主键
    private Long performanceRuleId;
    //课程编号
    private Long electiveCourseId;
    //平时成绩
    private Integer peacetimePerformanceProportion;
    //期中成绩
    private Integer midTermPerformanceProportion;
    //期末成绩
    private Integer finalPerformanceProportion;
    //技能考核
    private Integer skillAssessmentProportion;
    //备注
    private String remark;

    public Long getPerformanceRuleId() {
        return performanceRuleId;
    }

    public PerformanceRuleEntity setPerformanceRuleId(Long performanceRuleId) {
        this.performanceRuleId = performanceRuleId;
        return this;
    }

    public Long getElectiveCourseId() {
        return electiveCourseId;
    }

    public PerformanceRuleEntity setElectiveCourseId(Long electiveCourseId) {
        this.electiveCourseId = electiveCourseId;
        return this;
    }

    public Integer getPeacetimePerformanceProportion() {
        return peacetimePerformanceProportion;
    }

    public PerformanceRuleEntity setPeacetimePerformanceProportion(Integer peacetimePerformanceProportion) {
        this.peacetimePerformanceProportion = peacetimePerformanceProportion;
        return this;
    }

    public Integer getMidTermPerformanceProportion() {
        return midTermPerformanceProportion;
    }

    public PerformanceRuleEntity setMidTermPerformanceProportion(Integer midTermPerformanceProportion) {
        this.midTermPerformanceProportion = midTermPerformanceProportion;
        return this;
    }

    public Integer getFinalPerformanceProportion() {
        return finalPerformanceProportion;
    }

    public PerformanceRuleEntity setFinalPerformanceProportion(Integer finalPerformanceProportion) {
        this.finalPerformanceProportion = finalPerformanceProportion;
        return this;
    }

    public Integer getSkillAssessmentProportion() {
        return skillAssessmentProportion;
    }

    public PerformanceRuleEntity setSkillAssessmentProportion(Integer skillAssessmentProportion) {
        this.skillAssessmentProportion = skillAssessmentProportion;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public PerformanceRuleEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
