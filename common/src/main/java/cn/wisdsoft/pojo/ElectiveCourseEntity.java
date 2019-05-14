package cn.wisdsoft.pojo;

import java.io.Serializable;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-06 12:02
 * @ Description：选课表
 */
public class ElectiveCourseEntity implements Serializable {
    //选课表编号
    private Long electiveCourseId;
    //课程库编号
    private Long courseLibraryId;
    //课程名称
    private String courseLibraryName;
    //课组名称
    private String courseGroupName;
    //教师编号
    private String teacherId;
    //教师名称
    private String teacherName;
    //上课时间和地点JSON串
    private String timeAndPlace;
    //限选年级
    private String restrictedGrade;
    //最大人数
    private Integer maxNumber;
    //最小人数
    private Integer minNumber;
    //课时
    private Integer classHour;
    //学分
    private Integer credit;
    //简介
    private String courseLibraryIntroduction;
    //当前人数
    private Integer currentNumber;
    //学期编号
    private Integer termId;
    //学院名称
    private String collegeName;
    //优先级
    private Integer priority;
    //0可修改状态或打回状态，1申请修改,2已提交(成绩)
    private Integer electiveCourseStatus;
    //0未删除、1已删除
    private String deleteFlag;
    //0是备选，1不是备选
    private String optionFlag;
    //备注
    private String remark;

    public Long getElectiveCourseId() {
        return electiveCourseId;
    }

    public ElectiveCourseEntity setElectiveCourseId(Long electiveCourseId) {
        this.electiveCourseId = electiveCourseId;
        return this;
    }

    public Long getCourseLibraryId() {
        return courseLibraryId;
    }

    public ElectiveCourseEntity setCourseLibraryId(Long courseLibraryId) {
        this.courseLibraryId = courseLibraryId;
        return this;
    }

    public String getCourseLibraryName() {
        return courseLibraryName;
    }

    public ElectiveCourseEntity setCourseLibraryName(String courseLibraryName) {
        this.courseLibraryName = courseLibraryName;
        return this;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public ElectiveCourseEntity setTeacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public ElectiveCourseEntity setTeacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public String getRestrictedGrade() {
        return restrictedGrade;
    }

    public ElectiveCourseEntity setRestrictedGrade(String restrictedGrade) {
        this.restrictedGrade = restrictedGrade;
        return this;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public ElectiveCourseEntity setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
        return this;
    }

    public Integer getMinNumber() {
        return minNumber;
    }

    public ElectiveCourseEntity setMinNumber(Integer minNumber) {
        this.minNumber = minNumber;
        return this;
    }

    public Integer getClassHour() {
        return classHour;
    }

    public ElectiveCourseEntity setClassHour(Integer classHour) {
        this.classHour = classHour;
        return this;
    }

    public Integer getCredit() {
        return credit;
    }

    public ElectiveCourseEntity setCredit(Integer credit) {
        this.credit = credit;
        return this;
    }

    public String getCourseLibraryIntroduction() {
        return courseLibraryIntroduction;
    }

    public ElectiveCourseEntity setCourseLibraryIntroduction(String courseLibraryIntroduction) {
        this.courseLibraryIntroduction = courseLibraryIntroduction;
        return this;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public ElectiveCourseEntity setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
        return this;
    }

    public Integer getTermId() {
        return termId;
    }

    public ElectiveCourseEntity setTermId(Integer termId) {
        this.termId = termId;
        return this;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public ElectiveCourseEntity setCollegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public ElectiveCourseEntity setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public Integer getElectiveCourseStatus() {
        return electiveCourseStatus;
    }

    public ElectiveCourseEntity setElectiveCourseStatus(Integer electiveCourseStatus) {
        this.electiveCourseStatus = electiveCourseStatus;
        return this;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public ElectiveCourseEntity setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public ElectiveCourseEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getTimeAndPlace() {
        return timeAndPlace;
    }

    public ElectiveCourseEntity setTimeAndPlace(String timeAndPlace) {
        this.timeAndPlace = timeAndPlace;
        return this;
    }

    public String getOptionFlag() {
        return optionFlag;
    }

    public ElectiveCourseEntity setOptionFlag(String optionFlag) {
        this.optionFlag = optionFlag;
        return this;
    }

    public String getCourseGroupName() {
        return courseGroupName;
    }

    public ElectiveCourseEntity setCourseGroupName(String courseGroupName) {
        this.courseGroupName = courseGroupName;
        return this;
    }
}
