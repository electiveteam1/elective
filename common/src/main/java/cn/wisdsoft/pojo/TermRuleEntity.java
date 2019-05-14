package cn.wisdsoft.pojo;

import java.io.Serializable;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-06 12:27
 * @ Description：学期规则表
 */
public class TermRuleEntity implements Serializable {
    //主键
    private Long termRuleId;
    //学期编号
    private Integer termId;
    //开始时间
    private String openTime;
    //结束时间
    private String closeTime;
    private String cron;
    //学期结束
    private String semesterEnds;
    //开始时间学期状态（选课开始，选课结束，学期结束）
    private String termRuleState;
    //备注
    private String remark;

    public Long getTermRuleId() {
        return termRuleId;
    }

    public TermRuleEntity setTermRuleId(Long termRuleId) {
        this.termRuleId = termRuleId;
        return this;
    }

    public Integer getTermId() {
        return termId;
    }

    public TermRuleEntity setTermId(Integer termId) {
        this.termId = termId;
        return this;
    }

    public String getOpenTime() {
        return openTime;
    }

    public TermRuleEntity setOpenTime(String openTime) {
        this.openTime = openTime;
        return this;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public TermRuleEntity setCloseTime(String closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public String getSemesterEnds() {
        return semesterEnds;
    }

    public TermRuleEntity setSemesterEnds(String semesterEnds) {
        this.semesterEnds = semesterEnds;
        return this;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getTermRuleState() {
        return termRuleState;
    }

    public TermRuleEntity setTermRuleState(String termRuleState) {
        this.termRuleState = termRuleState;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public TermRuleEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    @Override
    public String toString() {
        return "TermRuleEntity{" +
                "termRuleId=" + termRuleId +
                ", termId=" + termId +
                ", openTime='" + openTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", cron='" + cron + '\'' +
                ", semesterEnds='" + semesterEnds + '\'' +
                ", termRuleState='" + termRuleState + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
