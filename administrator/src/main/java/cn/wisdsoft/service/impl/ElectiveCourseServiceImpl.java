package cn.wisdsoft.service.impl;


import cn.wisdsoft.common.AdminConstant;
import cn.wisdsoft.mapper.ElectiveCourseMapper;
import cn.wisdsoft.mapper.InformationMapper;
import cn.wisdsoft.mapper.PerformanceMapper;
import cn.wisdsoft.mapper.StudentElectiveMapper;
import cn.wisdsoft.pojo.ElectiveCourseEntity;
import cn.wisdsoft.pojo.InformationEntity;
import cn.wisdsoft.service.ElectiveCourseService;
import cn.wisdsoft.util.ConstCommon;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/19 18:49
 * @Description:
 */
@Service
public class ElectiveCourseServiceImpl implements ElectiveCourseService {

    @Autowired
    private ElectiveCourseMapper electiveCourseMapper;
    @Autowired
    private StudentElectiveMapper studentElectiveMapper;
    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private PerformanceMapper performanceMapper;

    /**
     *
     * @param page 页码
     * @param limit 条数
     * @param collegeName 学院名称
     * @return
     */
    @Override
    public PageResult selectAllElectiveCourseInfo(int page,int limit,String collegeName) {
        PageHelper.startPage(page,limit,"current_number asc");
        List<ElectiveCourseEntity> electiveCourseEntities = electiveCourseMapper.selectAllElectiveCourseInfo(collegeName);
        PageInfo<ElectiveCourseEntity> pageInfo = new PageInfo<>(electiveCourseEntities);
        return PageResult.ok(electiveCourseEntities,pageInfo.getTotal());
    }

    /**
     *
     * @param page 页码
     * @param limit 调试
     * @param collegeName 学院名称
     * @param CourseName 课程名称
     * @param TeacherName 教师名称
     * @return
     */
    @Override
    public PageResult selectAllElectiveCourseByCourseOrTeaName(int page, int limit, String collegeName, String CourseName, String TeacherName) {
        PageHelper.startPage(page,limit,"current_number asc");
        List<ElectiveCourseEntity> electiveCourseEntities = electiveCourseMapper.selectAllElectiveCourseByCourseOrTeaName(collegeName,CourseName,TeacherName);
        PageInfo<ElectiveCourseEntity> pageInfo = new PageInfo<>(electiveCourseEntities);
        return PageResult.ok(electiveCourseEntities,pageInfo.getTotal());
    }

    /**
     *
     * @param page        页码
     * @param limit       条数
     * @param collegeName 学院名称
     * @return
     */
    @Override
    public PageResult selectElectiveByStatus(int page, int limit, String collegeName) {
        PageHelper.startPage(page,limit);
        List<ElectiveCourseEntity> electiveCourseEntities = electiveCourseMapper.selectElectiveByStatus(collegeName,ConstCommon.ELECTIVE_COURSE_STATUS.ELECTIVE_COURSE_STATIC.getCode());
        PageInfo<ElectiveCourseEntity> pageInfo = new PageInfo<>(electiveCourseEntities);
        return PageResult.ok(electiveCourseEntities,pageInfo.getTotal());
    }

    /**
     * 配置课程信息
     *
     * @param electiveCourse
     * @return
     */
    @Transactional
    @Override
    public ElectiveResult insertCourse(List<ElectiveCourseEntity> electiveCourse) {
        int count = electiveCourseMapper.insertCourse(electiveCourse);
        if (count != electiveCourse.size()) {
            try {
                throw new Exception("存在错误数据导致配置课程失败");
            } catch (Exception e) {
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            return ElectiveResult.build(420, "存在错误数据导致配置课程失败");
        }

        return ElectiveResult.build(200, "配置成功");
    }

    /**
     * 管理员进程停止课程
     *
     * @param electiveCourseId 选课编号
     * @return
     */
    @Transactional
    @Override
    public ElectiveResult deleteCourse(Integer electiveCourseId) {
        //根据编号查询学生
        List<InformationEntity> informationEntities = electiveCourseMapper.selectStuByCourseId(electiveCourseId);
        if (informationEntities.size() == 0) {
            electiveCourseMapper.deleteByPrimaryKey(electiveCourseId, AdminConstant.deleteFlag.cut_off.getCode());
            return ElectiveResult.build(200, "配置成功");
        }
        try {
            //将停课的课程的学生信息保存到information中
            int i1 = informationMapper.insertInformationOnStu(informationEntities);
            if (i1 < 1) {
                throw new Exception("学生信息保存到information失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        try {
            //停掉课程
            int count = electiveCourseMapper.deleteByPrimaryKey(electiveCourseId, AdminConstant.deleteFlag.cut_off.getCode());
            if (count < 1) {
                throw new Exception("停止课程失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        try {
            //释放学生
            int i = studentElectiveMapper.deleteAllStuElective(electiveCourseId);
            if (i < 1) {
                throw new Exception("释放学生失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return ElectiveResult.build(420, "非法停止课程");
    }

    /**
     * 将教师申请修改成绩的课程打回
     *
     * @param electiveCourseId
     * @return
     */
    @Transactional
    @Override
    public ElectiveResult updateElectiveCourseStatus(Integer electiveCourseId) {
        try {
            //根据选课编号将成绩allow_flag改为允许修改状态
            int i = performanceMapper.updateByElectiveCourseId(electiveCourseId, 1);
            if (i < 1) {
                throw new Exception("无成绩而进行打回成绩的，非法操作");
            }
            //将选课状态改为打回状态
            int status = electiveCourseMapper.updateElectiveCourseStatus(ConstCommon.ELECTIVE_COURSE_STATUS.ELECTIVE_COURSE_BLACK.getCode(), electiveCourseId);
            if (status > 0) {
                return ElectiveResult.ok();
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return ElectiveResult.build(420, "无成绩而进行打回成绩的非法操作");
    }


    @Override
    public ElectiveResult SelectCourseName(String CollegeName) {
        List<ElectiveCourseEntity> electiveCourseEntityList = electiveCourseMapper.SelectCourseName(CollegeName);
        return ElectiveResult.ok(electiveCourseEntityList);
    }
}
