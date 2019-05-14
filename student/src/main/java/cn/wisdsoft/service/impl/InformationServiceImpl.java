package cn.wisdsoft.service.impl;

import cn.wisdsoft.mapper.InformationMapper;
import cn.wisdsoft.pojo.InformationEntity;
import cn.wisdsoft.pojo.InformationVo;
import cn.wisdsoft.service.InformationService;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-25 14:31
 * @ Description：
 */
@Service
@Transactional
public class InformationServiceImpl implements InformationService {
    private final InformationMapper informationMapper;

    public InformationServiceImpl(InformationMapper informationMapper) {
        this.informationMapper = informationMapper;
    }

    @Override
    public ElectiveResult allTip(String studentId) {
        //查询所有消息
        List<InformationEntity> informationMappers = informationMapper.selectAll(studentId);
        List<InformationVo> collect = informationMappers.stream().map(item -> {
            return new InformationVo(item.getInformationId(), item.getInformationDate(),
                    "您选择的" + item.getCourseName() + "课程已被解散，请您在选课规定时间内重新选择课程！");
        }).collect(Collectors.toList());
        //更新查看状态
        informationMapper.update(studentId);
        return ElectiveResult.ok(collect);
    }

    @Override
    public ElectiveResult redPoint(String studentId) {
        Integer num = informationMapper.selectNum(studentId);
        return num > 0 ? ElectiveResult.ok(true) : ElectiveResult.ok(false);
    }

    @Override
    public ElectiveResult detail(Long informationId) {
        return ElectiveResult.ok(informationMapper.selectOne(informationId));
    }
}
