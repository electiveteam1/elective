package cn.wisdsoft.mapper;

import cn.wisdsoft.pojo.CourseLibraryEntity;
import cn.wisdsoft.pojo.vo.CourseLibraryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CourseLibraryMapper {
    /**
     * 根据管理员身份，查询课程
     * @param collegeName
     * @return
     */
    List<CourseLibraryVo> selectAllCourse(@Param("collegeName") String collegeName, @Param("CourseName") String CourseName);

    /**
     * 根据课程编号查询课程
     * @param courseId
     * @return
     */
    CourseLibraryEntity selectByCourseId(long courseId);

    /**
     * 添加课程到课程库
     * @param courseLibraryVo
     */
    int insertCourseLibrary(CourseLibraryVo courseLibraryVo);

    /**
     * 查询所有课组
     * @return
     */
    List<CourseLibraryEntity> selectAllCourseGroup();
}