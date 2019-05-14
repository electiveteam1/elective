package cn.wisdsoft.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-13 10:08
 * @ Description：学生查看选课视图层
 */
public class ElectiveCourseVo implements Serializable {
    //学生查看选课实体类
    private List<ElectiveCourseDo> electiveCourseDos;
    //是否可选
    private Boolean optionalFlag;

    public List<ElectiveCourseDo> getElectiveCourseDos() {
        return electiveCourseDos;
    }

    public ElectiveCourseVo setElectiveCourseDos(List<ElectiveCourseDo> electiveCourseDos) {
        this.electiveCourseDos = electiveCourseDos;
        return this;
    }

    public Boolean getOptionalFlag() {
        return optionalFlag;
    }

    public ElectiveCourseVo setOptionalFlag(Boolean optionalFlag) {
        this.optionalFlag = optionalFlag;
        return this;
    }
}
