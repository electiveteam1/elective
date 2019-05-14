package cn.wisdsoft.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-06 21:01
 * @ Description：学生已选课程实体类
 */
public class StudentElectiveVo implements Serializable {
    //学院
    private String collegeName;
    //选课ID
    private Long electiveCourseId;
    //课程ID
    private Long courseLibraryId;
    //课程名称
    private String courseLibraryName;
    //教师名称
    private String teacherName;
    //上课时间和地点JSON数据
    private String timeAndPlace;
    //学时
    private Short classHour;
    //学分
    private Short credit;
    //时间戳，选课24小时后才能退课
    private Date timeStamp;
    //是否正在学习
    private String courseFlag;
    //教师照片
    private String remark;
    //学期状态
    private Integer termStatus;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Long getElectiveCourseId() {
        return electiveCourseId;
    }

    public void setElectiveCourseId(Long electiveCourseId) {
        this.electiveCourseId = electiveCourseId;
    }

    public Long getCourseLibraryId() {
        return courseLibraryId;
    }

    public void setCourseLibraryId(Long courseLibraryId) {
        this.courseLibraryId = courseLibraryId;
    }

    public String getCourseLibraryName() {
        return courseLibraryName;
    }

    public void setCourseLibraryName(String courseLibraryName) {
        this.courseLibraryName = courseLibraryName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTimeAndPlace() {
        return timeAndPlace;
    }

    public void setTimeAndPlace(String timeAndPlace) {
        this.timeAndPlace = timeAndPlace;
    }

    public Short getClassHour() {
        return classHour;
    }

    public void setClassHour(Short classHour) {
        this.classHour = classHour;
    }

    public Short getCredit() {
        return credit;
    }

    public void setCredit(Short credit) {
        this.credit = credit;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCourseFlag() {
        return courseFlag;
    }

    public void setCourseFlag(String courseFlag) {
        this.courseFlag = courseFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTermStatus() {
        return termStatus;
    }

    public void setTermStatus(Integer termStatus) {
        this.termStatus = termStatus;
    }
}
