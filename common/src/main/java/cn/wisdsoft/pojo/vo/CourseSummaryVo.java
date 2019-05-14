package cn.wisdsoft.pojo.vo;

import java.io.Serializable;

/**
 * @ClassName CourseSummaryVo
 * @Description 课程汇总VO
 * @Author LIZEYU
 * @Date 2019/4/24 8:17
 * @Version 1.0
 **/

public class CourseSummaryVo implements Serializable {

    //学期编号
    private Integer termId;
    //学期名称
    private String termName;
    //课程名称
    private String courseLibraryName;
    //教师编号
    private String teacherId;
    //教师名称
    private String teacherName;
    //当前人数
    private Integer currentNumber;

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
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

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }
}
