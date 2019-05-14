package cn.wisdsoft.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-06 12:22
 * @ Description：学生选课子表
 */
public class StudentElectiveEntity implements Serializable {
    //标识列
    private Long studentElectiveId;
    //学生ID
    private String studentId;
    //选课ID
    private Long electiveCourseId;
    //学生名称
    private String studentName;
    //是否正在学习('learned','learning')
    private String courseFlag;
    //时间戳，退课24小时后才能重新选课
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timeStamp;
    //备注
    private String remark;

    public Long getStudentElectiveId() {
        return studentElectiveId;
    }

    public StudentElectiveEntity setStudentElectiveId(Long studentElectiveId) {
        this.studentElectiveId = studentElectiveId;
        return this;
    }

    public String getStudentId() {
        return studentId;
    }

    public StudentElectiveEntity setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public Long getElectiveCourseId() {
        return electiveCourseId;
    }

    public StudentElectiveEntity setElectiveCourseId(Long electiveCourseId) {
        this.electiveCourseId = electiveCourseId;
        return this;
    }

    public String getStudentName() {
        return studentName;
    }

    public StudentElectiveEntity setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public String getCourseFlag() {
        return courseFlag;
    }

    public StudentElectiveEntity setCourseFlag(String courseFlag) {
        this.courseFlag = courseFlag;
        return this;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public StudentElectiveEntity setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public StudentElectiveEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
