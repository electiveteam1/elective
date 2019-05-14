package cn.wisdsoft.pojo.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/2 15:12
 * @Description:
 */

public class TermVo {
    //学期编号
    private Integer termId;
    //学期名称
    private String termName;
    //学期状态
    private String termStatus;

    private long openTimeId;
    //选课开始时间
    private String openTime;

    private long closeTimeId;
    //选课结束时间
    private String closeTime;
    private long semesterEndsId;
    //学期结束时间
    private String semesterEnds;

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermStatus() {
        return termStatus;
    }

    public void setTermStatus(String termStatus) {
        this.termStatus = termStatus;
    }

    public long getOpenTimeId() {
        return openTimeId;
    }

    public TermVo setOpenTimeId(long openTimeId) {
        this.openTimeId = openTimeId;
        return this;
    }

    public String getOpenTime() {
        return openTime;
    }

    public TermVo setOpenTime(String openTime) {
        this.openTime = openTime;
        return this;
    }

    public long getCloseTimeId() {
        return closeTimeId;
    }

    public TermVo setCloseTimeId(long closeTimeId) {
        this.closeTimeId = closeTimeId;
        return this;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public TermVo setCloseTime(String closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public long getSemesterEndsId() {
        return semesterEndsId;
    }

    public TermVo setSemesterEndsId(long semesterEndsId) {
        this.semesterEndsId = semesterEndsId;
        return this;
    }

    public String getSemesterEnds() {
        return semesterEnds;
    }

    public TermVo setSemesterEnds(String semesterEnds) {
        this.semesterEnds = semesterEnds;
        return this;
    }
}
