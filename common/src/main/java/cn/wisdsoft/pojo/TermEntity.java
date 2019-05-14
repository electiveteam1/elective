package cn.wisdsoft.pojo;

import java.io.Serializable;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-06 12:25
 * @ Description：学期表
 */
public class TermEntity implements Serializable {
    //标识列
    private Integer termId;
    //学期名称
    private String termName;
    //学期状态（1配置授课教师中、2选课开始、3选课结束、4学期结束）
    private String termStatus;
    //备注
    private String remark;

    public Integer getTermId() {
        return termId;
    }

    public TermEntity setTermId(Integer termId) {
        this.termId = termId;
        return this;
    }

    public String getTermName() {
        return termName;
    }

    public TermEntity setTermName(String termName) {
        this.termName = termName;
        return this;
    }

    public String getTermStatus() {
        return termStatus;
    }

    public TermEntity setTermStatus(String termStatus) {
        this.termStatus = termStatus;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public TermEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
