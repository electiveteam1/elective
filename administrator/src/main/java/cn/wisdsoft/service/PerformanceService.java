package cn.wisdsoft.service;

import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/20 15:46
 * @Description: 成绩模块
 */
public interface PerformanceService {

    /**
     * 根据学院查询成绩
     * @param page 页码
     * @param limit 条数
     * @param collegeName 学院名称
     * @return
     */
    PageResult selectAllPerformance(int page, int limit, String collegeName);

    /**
     * 根据姓名和ID查询成绩
     * @param page 页码
     * @param limit 条数
     * @param studentId 学生编号
     * @param studentName 学生姓名
     * @param collegeName 学院名称
     * @return
     */
    PageResult selectPerformanceByIdOrName(int page, int limit, String studentId, String studentName, String collegeName);

    /**
     * 查询本学院的课程的及格率
     * @param courseId
     * @return
     */
    ElectiveResult selectCoursePassRate(String courseId);
}
