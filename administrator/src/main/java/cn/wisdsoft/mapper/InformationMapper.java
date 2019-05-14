package cn.wisdsoft.mapper;

import cn.wisdsoft.pojo.InformationEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface InformationMapper {

    /**
     * 将被退课的学生插入到信息表中
     * @param list
     * @return
     */
    int insertInformationOnStu(List<InformationEntity> list);
}