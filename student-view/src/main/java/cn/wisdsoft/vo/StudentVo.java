package cn.wisdsoft.vo;

import java.io.Serializable;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-03-29 08:26
 * @ Description：自定义返回类型的实体
 */
public class StudentVo implements Serializable {
    /**
     * 学号
     */
    private String username;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 学院
     */
    private String college;
    /**
     * 学期ID
     */
    private Integer termId;
    /**
     * 学期名称
     */
    private String term;
    /**
     * 用户令牌
     */
    private String token;

    /**
     * 学期状态
     */
    private String termStatus;

    public String getUsername() {
        return username;
    }

    public StudentVo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getCollege() {
        return college;
    }

    public StudentVo setCollege(String college) {
        this.college = college;
        return this;
    }

    public Integer getTermId() {
        return termId;
    }

    public StudentVo setTermId(Integer termId) {
        this.termId = termId;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public StudentVo setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getToken() {
        return token;
    }

    public StudentVo setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", college='" + college + '\'' +
                ", termId=" + termId +
                ", term='" + term + '\'' +
                ", token='" + token + '\'' +
                ", termStatus='" + termStatus + '\'' +
                '}';
    }
}
