package cn.wisdsoft.service.impl;

import cn.wisdsoft.mapper.CollegeMapper;
import cn.wisdsoft.pojo.ddmsPojo.CollegeEntity;
import cn.wisdsoft.service.CollegeService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Auther: SongJunWei
 * @Date: 2019/4/18 09:55
 * @Description:
 */
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<CollegeEntity> selectAllCollege() {
        return collegeMapper.selectAllCollege();
    }
}
