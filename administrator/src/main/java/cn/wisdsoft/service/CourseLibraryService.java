package cn.wisdsoft.service;

import cn.wisdsoft.pojo.vo.CourseLibraryVo;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/22 19:52
 * @Description:
 */
public interface CourseLibraryService {
    /**
     * 根据管理员所在学院查询所有课程
     * @param page 页码
     * @param limit 条数
     * @param collegeId 学院ID
     * @return
     */
    PageResult selectAllCourse(int page, int limit, String collegeId, String CourseName);


    /**
     * 添加课程到数据库
     * @param courseLibraryVo
     * @return
     */
    ElectiveResult insertCourseLibrary(CourseLibraryVo courseLibraryVo);

    /**
     * 查询所有课组
     * @return
     */
    ElectiveResult selectAllCourseGroup();

}
