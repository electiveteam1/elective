package cn.wisdsoft.service.impl;


import cn.wisdsoft.mapper.CourseLibraryMapper;
import cn.wisdsoft.mapper.PerformanceRuleMapper;
import cn.wisdsoft.pojo.CourseLibraryEntity;
import cn.wisdsoft.pojo.PerformanceRuleEntity;
import cn.wisdsoft.pojo.vo.CourseLibraryVo;
import cn.wisdsoft.service.CourseLibraryService;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/22 19:52
 * @Description:
 */
@Service
public class CourseLibraryServiceImpl implements CourseLibraryService {

    @Autowired
    private CourseLibraryMapper courseLibraryMapper;
    @Autowired
    private PerformanceRuleMapper performanceRuleMapper;

    /**
     * 根据管理员身份，查询所有课程
     *
     * @param page        页码
     * @param limit       条数
     * @param collegeName 学院名称
     * @return
     */
    @Override
    public PageResult selectAllCourse(int page, int limit, String collegeName, String CourseName) {
        PageHelper.startPage(page, limit, "course_library_id desc");
        List<CourseLibraryVo> courseLibraries = courseLibraryMapper.selectAllCourse(collegeName, CourseName);
        PageInfo<CourseLibraryVo> pageInfo = new PageInfo<>(courseLibraries);
        return PageResult.ok(courseLibraries, pageInfo.getTotal());
    }

    /**
     * 添加课程到课程库
     *
     * @param courseLibraryVo
     * @return
     */
    @Transactional
    @Override
    public ElectiveResult insertCourseLibrary(CourseLibraryVo courseLibraryVo) {
        int a = 0, b = 0, c = 0, d = 0;
        //期末成绩
        String finalPerformanceProportion = courseLibraryVo.getFinalPerformanceProportion();
        if (!finalPerformanceProportion.equals("")) {
            a = Integer.parseInt(finalPerformanceProportion.substring(0, finalPerformanceProportion.indexOf("%")));
        }
        //期中成绩
        String midTermPerformanceProportion = courseLibraryVo.getMidTermPerformanceProportion();
        if (!midTermPerformanceProportion.equals("")) {
            b = Integer.parseInt(midTermPerformanceProportion.substring(0, midTermPerformanceProportion.indexOf("%")));
        }
        //平时成绩
        String peacetimPerformanceProportion = courseLibraryVo.getPeacetimePerformanceProportion();
        if (!peacetimPerformanceProportion.equals("")) {
            c = Integer.parseInt(peacetimPerformanceProportion.substring(0, peacetimPerformanceProportion.indexOf("%")));
        }
        //技能考核
        String skillAssessmentProportion = courseLibraryVo.getSkillAssessmentProportion();
        if (!skillAssessmentProportion.equals("")) {
            d = Integer.parseInt(skillAssessmentProportion.substring(0, skillAssessmentProportion.indexOf("%")));
        }
        int allProportion = a + b + c + d;
        if (allProportion != 100) {
            return ElectiveResult.build(420, "成绩规则总分为100分哦");
        }
        //添加课程规则
        PerformanceRuleEntity performanceRuleEntity = new PerformanceRuleEntity();
        performanceRuleEntity.setFinalPerformanceProportion(a).setMidTermPerformanceProportion(b).setPeacetimePerformanceProportion(c).setSkillAssessmentProportion(d);
        int performanceRuleCount = performanceRuleMapper.insertPerformanceRule(performanceRuleEntity);
        if (performanceRuleCount < 0) {
            return ElectiveResult.build(420, "添加成绩规则失败");
        }
        courseLibraryVo.setPerformanceRuleId(performanceRuleEntity.getPerformanceRuleId());
        //如果课程组为空，则将课程名称赋值为课组组
        if (courseLibraryVo.getCourseGroupName().equals("")) {
            courseLibraryVo.setCourseGroupName(courseLibraryVo.getCourseLibraryName());
        }
        //添加课程
        int count = courseLibraryMapper.insertCourseLibrary(courseLibraryVo);
        if (count < 0) {
            return ElectiveResult.build(420, "添加课程失败");
        }
        return ElectiveResult.ok();
    }


    /**
     * 查询所有课组
     *
     * @return
     */
    @Override
    public ElectiveResult selectAllCourseGroup() {
        List<CourseLibraryEntity> courseLibraryEntities = courseLibraryMapper.selectAllCourseGroup();
        return ElectiveResult.ok(courseLibraryEntities);
    }
}
