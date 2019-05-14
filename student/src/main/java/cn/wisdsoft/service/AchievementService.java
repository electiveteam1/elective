package cn.wisdsoft.service;

import cn.wisdsoft.util.ElectiveResult;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-13 22:47
 * @ Description：
 */
public interface AchievementService {
    /**
     * 查询成绩（也可做筛选）
     *
     * @param studentId 学生ID
     * @param termId    学期ID
     * @return 成绩信息
     */
    ElectiveResult selectAchievement(String studentId, String termId);
}
