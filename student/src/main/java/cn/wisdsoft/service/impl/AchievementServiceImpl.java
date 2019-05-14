package cn.wisdsoft.service.impl;

import cn.wisdsoft.mapper.AchievementMapper;
import cn.wisdsoft.service.AchievementService;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-13 22:47
 * @ Description：
 */
@Service
@Transactional
public class AchievementServiceImpl implements AchievementService {
    protected final AchievementMapper achievementMapper;

    public AchievementServiceImpl(AchievementMapper achievementMapper) {
        this.achievementMapper = achievementMapper;
    }

    @Override
    public ElectiveResult selectAchievement(String studentId, String termId) {
        return ElectiveResult.ok(achievementMapper.selectAchievement(studentId, termId));
    }
}
