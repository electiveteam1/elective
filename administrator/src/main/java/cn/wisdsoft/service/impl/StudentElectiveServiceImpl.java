package cn.wisdsoft.service.impl;


import cn.wisdsoft.mapper.StudentElectiveMapper;
import cn.wisdsoft.service.StudentElectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/22 19:53
 * @Description:
 */
@Service
@Transactional
public class StudentElectiveServiceImpl implements StudentElectiveService {

    @Autowired
    private StudentElectiveMapper studentElectiveMapper;

    @Override
    public int updateLearningStatus(String courseFlag) {
        return studentElectiveMapper.updateLearningStatus(courseFlag);
    }
}
