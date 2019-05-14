package cn.wisdsoft.pojo;

import java.io.Serializable;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-06 12:20
 * @ Description：学生表
 */
public class StudentEntity implements Serializable {
    //学生ID
    private String studentId;
    //学生姓名
    private String studentName;
    //学生密码
    private String studentPassword;
    //学院名称
    private String collegeName;
    //学生年级
    private Short grade;
    //备注
    private String remark;

    public String getStudentId() {
        return studentId;
    }

    public StudentEntity setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public String getStudentName() {
        return studentName;
    }

    public StudentEntity setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public StudentEntity setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
        return this;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public StudentEntity setCollegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    public Short getGrade() {
        return grade;
    }

    public StudentEntity setGrade(Short grade) {
        this.grade = grade;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public StudentEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
