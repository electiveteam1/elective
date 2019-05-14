package cn.wisdsoft.pojo.vo;

import java.io.Serializable;

/**
* @Description:     学生成绩vo
* @Author:         SongJunWei
* @CreateDate:     2019/4/20 18:18
* @UpdateUser:     
* @UpdateDate:     2019/4/20 18:18
* @UpdateRemark:   修改内容
* @Version:        1.0

*/
public class PerformanceEntityVo implements Serializable {
    //主键
    private Long performanceId;
    //学生编号
    private String studentId;
    //学生姓名
    private String studentName;
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

    public Long getPerformanceId() {
        return performanceId;
    }

    public PerformanceEntityVo setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
        return this;
    }

    public String getStudentId() {
        return studentId;
    }

    public PerformanceEntityVo setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }
    public String getStudentName() {
        return studentName;
    }

    public PerformanceEntityVo setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public Long getPerformanceRuleId() {
        return performanceRuleId;
    }

    public PerformanceEntityVo setPerformanceRuleId(Long performanceRuleId) {
        this.performanceRuleId = performanceRuleId;
        return this;
    }

    public Double getPeacetimePerformance() {
        return peacetimePerformance;
    }

    public PerformanceEntityVo setPeacetimePerformance(Double peacetimePerformance) {
        this.peacetimePerformance = peacetimePerformance;
        return this;
    }

    public Double getMidTermPerformance() {
        return midTermPerformance;
    }

    public PerformanceEntityVo setMidTermPerformance(Double midTermPerformance) {
        this.midTermPerformance = midTermPerformance;
        return this;
    }

    public Double getFinalPerformance() {
        return finalPerformance;
    }

    public PerformanceEntityVo setFinalPerformance(Double finalPerformance) {
        this.finalPerformance = finalPerformance;
        return this;
    }

    public Double getSkillAssessment() {
        return skillAssessment;
    }

    public PerformanceEntityVo setSkillAssessment(Double skillAssessment) {
        this.skillAssessment = skillAssessment;
        return this;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public PerformanceEntityVo setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public Short getPassFlag() {
        return passFlag;
    }

    public PerformanceEntityVo setPassFlag(Short passFlag) {
        this.passFlag = passFlag;
        return this;
    }

    public Short getAllowFlag() {
        return allowFlag;
    }

    public PerformanceEntityVo setAllowFlag(Short allowFlag) {
        this.allowFlag = allowFlag;
        return this;
    }

    public Integer getTermId() {
        return termId;
    }

    public PerformanceEntityVo setTermId(Integer termId) {
        this.termId = termId;
        return this;
    }
}
