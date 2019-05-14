package cn.wisdsoft.service.impl;

import cn.wisdsoft.mapper.CourseMapper;
import cn.wisdsoft.pojo.ElectiveCourseDo;
import cn.wisdsoft.pojo.ElectiveCourseVo;
import cn.wisdsoft.pojo.TermEntity;
import cn.wisdsoft.service.CourseService;
import cn.wisdsoft.util.CourseResult;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-10 16:58
 * @ Description：
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }


    @Override
    public CourseResult allCourse(Integer grade, Integer termId, String college, String nature, String volume, String locations, String time, Integer page, Integer limit) {
        //开启分页
        PageHelper.startPage(page, limit);
        //查询所有选课
        List<ElectiveCourseDo> entities = courseMapper.allCourse(grade, termId, college, volume, nature, time, locations);
        PageInfo<ElectiveCourseDo> info = new PageInfo<>(entities);
        //根据学期ID查询学期（如果学期状态是2则可以选课 =》 如果termEntity不为null，则可以选课）
        TermEntity termEntity = courseMapper.selectOne(termId);
        ElectiveCourseVo courseVo = new ElectiveCourseVo();
        courseVo.setElectiveCourseDos(entities);
        if (termEntity == null) {
            return CourseResult.ok(info.getTotal(), false, info.getList());
        } else {
            return CourseResult.ok(info.getTotal(), true, info.getList());
        }
    }

    @Override
    public CourseResult optionCourse(Integer grade, Integer termId, String college, Integer page, Integer limit) {
        //开启分页
        PageHelper.startPage(page, limit);
        //查询所有备选课程
        List<ElectiveCourseDo> courseDos = courseMapper.optionCourse(grade, termId, college);
        PageInfo<ElectiveCourseDo> info = new PageInfo<>(courseDos);

        ElectiveCourseVo courseVo = new ElectiveCourseVo();
        courseVo.setElectiveCourseDos(courseDos).setOptionalFlag(false);
        return CourseResult.ok(info.getTotal(), false, info.getList());
    }

    @Override
    public ElectiveResult allCourseNum() {
        return ElectiveResult.ok(courseMapper.selectAllCourseNum());
    }

    @Override
    public ElectiveResult selectNum(Integer termId, Integer grade) {
        return ElectiveResult.ok(courseMapper.selectNum(termId, grade));
    }

    @Override
    public ElectiveResult top(Integer termId, Integer grade,Integer size) {
        return ElectiveResult.ok(courseMapper.top(termId, grade,size));
    }

    @Override
    public ElectiveResult detail(Integer electiveCourseId) {
        return ElectiveResult.ok(courseMapper.detail(electiveCourseId));
    }
}
