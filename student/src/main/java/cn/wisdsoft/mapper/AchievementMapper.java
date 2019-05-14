package cn.wisdsoft.mapper;

import cn.wisdsoft.mapper.SQL.SQLUtil;
import cn.wisdsoft.pojo.AchievementVo;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-13 22:48
 * @ Description：
 */
public interface AchievementMapper {
    /**
     * 查询成绩
     *
     * @param studentId 学生ID
     * @param termId    学期ID
     * @return 成绩信息
     */
    @SelectProvider(type = SQLUtil.class,method = "selectAchievement")
    List<AchievementVo> selectAchievement(String studentId, String termId);
}
