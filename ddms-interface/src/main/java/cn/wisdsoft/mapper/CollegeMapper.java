package cn.wisdsoft.mapper;

import cn.wisdsoft.pojo.ddmsPojo.CollegeEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/18 10:02
 * @Description:
 */
public interface CollegeMapper {

    @Select("select id,college_name from college")
    List<CollegeEntity> selectAllCollege();
}
