package cn.wisdsoft.mapper;

import cn.wisdsoft.pojo.ElectiveCourseEntity;
import cn.wisdsoft.pojo.InformationEntity;
import cn.wisdsoft.pojo.vo.CourseSummaryVo;
import cn.wisdsoft.util.EchartsUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ElectiveCourseMapper {

    /**
     * 配置课程信息
     * @param electiveCourse
     * @return
     */
    int insertCourse(@Param("electiveCourse") List<ElectiveCourseEntity> electiveCourse);

    /**
     * 管理员停掉某门课程
     * @param electiveCourseId 选课编号
     * @param deleteFlag 删除标识
     * @return
     */
    int deleteByPrimaryKey(@Param("electiveCourseId") Integer electiveCourseId, @Param("deleteFlag") int deleteFlag);

    /**
     * 根据选课编号，查询学生信息
     * @param electiveCourseId
     * @return
     */
    List<InformationEntity> selectStuByCourseId(long electiveCourseId);

    /**
     * 0可修改状态或打回状态，1申请修改
     * @param electiveCourseStatus
     * @param electiveCourseId
     * @return
     */
    int updateElectiveCourseStatus(@Param("electiveCourseStatus") Integer electiveCourseStatus, @Param("electiveCourseId") long electiveCourseId);

    /**
     * 查询所有选课信息
     * @param collegeName
     * @return
     */
    List<ElectiveCourseEntity> selectAllElectiveCourseInfo(String collegeName);

    /**
     * 根据课程或教师姓名查询课程
     * @param collegeName
     * @param CourseName
     * @param TeacherName
     * @return
     */
    List<ElectiveCourseEntity> selectAllElectiveCourseByCourseOrTeaName(@Param("collegeName") String collegeName,
                                                                        @Param("CourseName") String CourseName,
                                                                        @Param("TeacherName") String TeacherName);

    /**
     * 查询修改成绩的课程信息
     * @param collegeName 学院名称
     * @param electiveCourseStatus 是否可修改状态
     * @return
     */
    List<ElectiveCourseEntity> selectElectiveByStatus(@Param("collegeName") String collegeName, @Param("electiveCourseStatus") Integer electiveCourseStatus);

    /**
     * @Author 李泽宇
     * @Description 查询所有学期某课程下的所有人数
     * @Date 2019/4/24 10:15
     * @Param
     * @return
     **/
    List<CourseSummaryVo> selectCurrentNumberByCourseLibraryName(@Param("courseLibraryName") String courseLibraryName);

    /**
     * @Author 李泽宇
     * @Description 某学期下那些课程被多少人选了
     * @Date 2019/4/24 10:17
     * @Param
     * @return
     **/
    List<CourseSummaryVo> selectCurrentNumberByTermId(@Param("termId") Integer termId);

    /**
     * @Author 李泽宇
     * @Description 某学期某课程下的这些老师被多少人选了
     * @Date 2019/4/24 10:19
     * @Param
     * @return
     **/
    List<CourseSummaryVo> selectTeacherSdNumberByTermId(@Param("termId") Integer termId, @Param("collegeName") String collegeName);

    /**
     * 查询当前学期下该教师下的所有课程选课人数
     * @param termId
     * @param teacherId
     * @return
     */
    List<EchartsUtils> selectTeaAllCourse(@Param("termId") Integer termId, @Param("teacherId") String teacherId);

    /**
     * 查询课程名称
     * @param CollegeName
     * @return
     */
    List<ElectiveCourseEntity> SelectCourseName(String CollegeName);
}