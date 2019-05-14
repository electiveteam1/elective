package cn.wisdsoft.service.impl;

import cn.wisdsoft.common.AdminConstant;
import cn.wisdsoft.mapper.PerformanceMapper;
import cn.wisdsoft.pojo.vo.PerformanceEntityVo;
import cn.wisdsoft.service.PerformanceService;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/20 15:47
 * @Description: 成绩模块实现类
 */
@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    private PerformanceMapper performanceMapper;

    /**
     * 根据学院名称查询成绩
     * @param page 页码
     * @param limit 条数
     * @param collegeName 学院名称
     * @return
     */
    @Override
    public PageResult selectAllPerformance(int page,int limit,String collegeName) {
        PageHelper.startPage(page,limit,"performance_id desc");
//        if(collegeName== AdminConstant.CollegeName){
//            List<PerformanceEntityVo> performanceEntities = performanceMapper.selectAllPerformance(null,AdminConstant.allowFlag.not_allow.getCode());
//            PageInfo<PerformanceEntityVo> performanceEntityPageInfo = new PageInfo<>(performanceEntities);
//            return PageResult.ok(performanceEntities,performanceEntityPageInfo.getTotal());
//        }
        List<PerformanceEntityVo> performanceEntities = performanceMapper.selectAllPerformance(collegeName, AdminConstant.allowFlag.not_allow.getCode());
        PageInfo<PerformanceEntityVo> performanceEntityPageInfo = new PageInfo<>(performanceEntities);
        return PageResult.ok(performanceEntities,performanceEntityPageInfo.getTotal());
    }

    /**
     *
     * @param page 页码
     * @param limit 条数
     * @param studentId 学生编号
     * @param studentName 学生姓名
     * @param collegeName 学院名称
     * @return
     */
    @Override
    public PageResult selectPerformanceByIdOrName(int page,int limit,String studentId, String studentName,String collegeName) {
        PageHelper.startPage(page,limit,"performance_id desc");
        if(collegeName== AdminConstant.CollegeName){
            List<PerformanceEntityVo> performanceEntities = performanceMapper.selectPerformanceByIdOrName(studentId,studentName,null,AdminConstant.allowFlag.not_allow.getCode());
            PageInfo<PerformanceEntityVo> performanceEntityPageInfo = new PageInfo<>(performanceEntities);
            return PageResult.ok(performanceEntities,performanceEntityPageInfo.getTotal());
        }
        List<PerformanceEntityVo> performanceEntities = performanceMapper.selectPerformanceByIdOrName(studentId,studentName,collegeName,AdminConstant.allowFlag.not_allow.getCode());
        PageInfo<PerformanceEntityVo> performanceEntityPageInfo = new PageInfo<>(performanceEntities);
        return PageResult.ok(performanceEntities,performanceEntityPageInfo.getTotal());
    }

    @Override
    public ElectiveResult selectCoursePassRate(String courseId) {
        List list = performanceMapper.selectCoursePassRate(courseId);
        return ElectiveResult.ok(list);
    }
}
