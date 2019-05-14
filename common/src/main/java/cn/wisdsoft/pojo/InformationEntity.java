package cn.wisdsoft.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-06 14:41
 * @ Description：消息表
 */
public class InformationEntity implements Serializable {
    //消息编号
    private Long informationId;
    //教师编号
    private String teacherId;
    //教师名称
    private String teacherName;
    //学生编号
    private String studentId;
    //学生名称
    private String studentName;
    //课程名称
    private String courseName;
    //课程删除时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date informationDate;
    //备注
    private String remark;

    public Long getInformationId() {
        return informationId;
    }

    public InformationEntity setInformationId(Long informationId) {
        this.informationId = informationId;
        return this;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public InformationEntity setTeacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public InformationEntity setTeacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public String getStudentId() {
        return studentId;
    }

    public InformationEntity setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public String getStudentName() {
        return studentName;
    }

    public InformationEntity setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public String getCourseName() {
        return courseName;
    }

    public InformationEntity setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public Date getInformationDate() {
        return informationDate;
    }

    public InformationEntity setInformationDate(Date informationDate) {
        this.informationDate = informationDate;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public InformationEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
