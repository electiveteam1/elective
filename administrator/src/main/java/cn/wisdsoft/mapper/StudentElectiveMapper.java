package cn.wisdsoft.mapper;

import org.springframework.stereotype.Component;

@Component
public interface StudentElectiveMapper {

    /**
     *根据选课编号，删除对应的学生选课信息
     * @param electiveCourseId
     * @return
     */
    int deleteAllStuElective(long electiveCourseId);

    /**
     * 修改学习状态
     * @param courseFlag
     * @return
     */
    int updateLearningStatus(String courseFlag);

}