package cn.wisdsoft.service;

import cn.wisdsoft.pojo.ddmsPojo.CollegeEntity;
import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/18 09:55
 * @Description:
 */
public interface CollegeService {

    /**
     * 查询所有的学院
     * @return
     */
    List<CollegeEntity> selectAllCollege();

}
