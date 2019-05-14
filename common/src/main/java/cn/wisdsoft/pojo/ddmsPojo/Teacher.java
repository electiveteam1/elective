package cn.wisdsoft.pojo.ddmsPojo;

import java.io.Serializable;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-03 09:35
 * @ Description：
 */
public class Teacher implements Serializable {
    /**
     * 权限
     */
    private String teaPower;

    /**
     * 工号
     */
    private String workNumber;

    /**
     * 密码
     */
    private String teaPassword;

    /**
     * 教师名称
     */
    private String teaName;

    /**
     * 教师照片
     */
    private String teaPicture;

    /**
     * 教师性别
     */
    private String teaSex;

    /**
     * 手机号
     */
    private String teaPhone;

    /**
     * 教育程度
     */
    private String teaDegreeEdu;

    /**
     * 职称
     */
    private String teaJobTitle;

    /**
     * 简介
     */
    private String remark;

    public String getTeaPower() {
        return teaPower;
    }

    public void setTeaPower(String teaPower) {
        this.teaPower = teaPower;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getTeaPassword() {
        return teaPassword;
    }

    public void setTeaPassword(String teaPassword) {
        this.teaPassword = teaPassword;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaPicture() {
        return teaPicture;
    }

    public void setTeaPicture(String teaPicture) {
        this.teaPicture = teaPicture;
    }

    public String getTeaSex() {
        return teaSex;
    }

    public void setTeaSex(String teaSex) {
        this.teaSex = teaSex;
    }

    public String getTeaPhone() {
        return teaPhone;
    }

    public void setTeaPhone(String teaPhone) {
        this.teaPhone = teaPhone;
    }

    public String getTeaDegreeEdu() {
        return teaDegreeEdu;
    }

    public void setTeaDegreeEdu(String teaDegreeEdu) {
        this.teaDegreeEdu = teaDegreeEdu;
    }

    public String getTeaJobTitle() {
        return teaJobTitle;
    }

    public void setTeaJobTitle(String teaJobTitle) {
        this.teaJobTitle = teaJobTitle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
