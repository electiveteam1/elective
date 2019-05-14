package cn.wisdsoft.service.impl;


import cn.wisdsoft.mapper.ElectiveCourseMapper;
import cn.wisdsoft.pojo.vo.CourseSummaryVo;
import cn.wisdsoft.service.CourseSummaryService;
import cn.wisdsoft.util.EchartsUtils;
import cn.wisdsoft.util.ElectiveResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName CourseSummaryServiceImpl
 * @Description
 * @Author LIZEYU
 * @Date 2019/4/24 11:13
 * @Version 1.0
 **/

@Service
public class CourseSummaryServiceImpl implements CourseSummaryService {

    @Autowired
    private ElectiveCourseMapper electiveCourseMapper;

    @Override
    public ElectiveResult selectCurrentNumberByCourseLibraryName(String courseLibraryName) {
        List<CourseSummaryVo> courseSummaryVoList = electiveCourseMapper.selectCurrentNumberByCourseLibraryName(courseLibraryName);
        return ElectiveResult.ok(courseSummaryVoList);
    }

    @Override
    public ElectiveResult selectCurrentNumberByTermId(Integer termId) {
        List<CourseSummaryVo> courseSummaryVoList = electiveCourseMapper.selectCurrentNumberByTermId(termId);
        return ElectiveResult.ok(courseSummaryVoList);
    }

    @Override
    public ElectiveResult selectTeacherSdNumberByTermId(Integer termId, String collegeName) {
        List<CourseSummaryVo> courseSummaryVoList = electiveCourseMapper.selectTeacherSdNumberByTermId(termId, collegeName);
        if (courseSummaryVoList.size() == 0) {
            return ElectiveResult.build(420, "没有数据");
        }
        List<String> list = new ArrayList<>();
        for (CourseSummaryVo summaryVo : courseSummaryVoList) {
            list.addAll(Arrays.asList(summaryVo.getCourseLibraryName().split(",")));
        }
        List<String> list1 = new ArrayList<>();
        for (String s : list) {
            if (!list1.contains(s)) {
                list1.add(s);
            }
        }
        //所有的课程
        String allCourseName = StringUtils.strip(list1.toString(), "[]");
        courseSummaryVoList.forEach(courseSummaryVo -> {
            Map<String, String> map = new HashMap<>();
            //课程名
            String[] strings = courseSummaryVo.getCourseLibraryName().split(",");
            //对应的人数
            if(courseSummaryVo.getTeacherId()==null){
                courseSummaryVo.setTeacherId("0");
            }
            String[] strings1 = courseSummaryVo.getTeacherId().split(",");
            for (int i = 0; i < strings.length; i++) {
                map.put(strings[i], strings1[i]);
            }

            StringBuffer stringBuffer = new StringBuffer();
            list1.forEach(c ->
                    stringBuffer.append(map.get(c) != null ? map.get(c) + "," : "0,")
            );
            courseSummaryVo.setCourseLibraryName(allCourseName);
            courseSummaryVo.setTeacherId(stringBuffer.toString().substring(0, stringBuffer.length() - 1));
        });
        return ElectiveResult.ok(courseSummaryVoList);
    }

    @Override
    public ElectiveResult selectTeaAllCourse(Integer termId, String teacherId) {
        List<EchartsUtils> echartsUtils = electiveCourseMapper.selectTeaAllCourse(termId, teacherId);
        return ElectiveResult.ok(echartsUtils);
    }
}
