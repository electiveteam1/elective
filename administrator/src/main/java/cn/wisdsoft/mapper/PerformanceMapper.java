package cn.wisdsoft.mapper;
import cn.wisdsoft.pojo.PerformanceEntity;
import cn.wisdsoft.pojo.vo.PerformanceEntityVo;
import cn.wisdsoft.util.EchartsUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PerformanceMapper {

    int deleteByPrimaryKey(Long performanceId);

    int insert(PerformanceEntity record);

    int insertSelective(PerformanceEntity record);

    PerformanceEntity selectByPrimaryKey(Long performanceId);

    int updateByPrimaryKeySelective(PerformanceEntity record);

    int updateByPrimaryKey(PerformanceEntity record);

    int updateByElectiveCourseId(@Param("electiveCourseId") long electiveCourseId, @Param("allowFlag") Integer allowFlag);

    List<PerformanceEntityVo> selectAllPerformance(@Param("collegeName") String collegeName, @Param("allowFlag") Integer allowFlag);

    List<PerformanceEntityVo> selectPerformanceByIdOrName(@Param("studentId") String studentId,
                                                          @Param("studentName") String studentName,
                                                          @Param("collegeName") String collegeName,
                                                          @Param("allowFlag") Integer allowFlag);

    List<EchartsUtils> selectCoursePassRate(@Param("courseId") String courseId);
}