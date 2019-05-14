package cn.wisdsoft.service;

import cn.wisdsoft.pojo.TermRuleEntity;
import cn.wisdsoft.util.ElectiveResult;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/22 19:53
 * @Description:
 */
public interface TermRuleService {

    /**
     * 编辑学期规则
     * @param termRules
     * @return
     */
    ElectiveResult insertTermRule(TermRuleEntity termRules);

    /**
     * 修改学期规则
     * @param termRuleEntity
     * @return
     */
    ElectiveResult updateTermRule(List<TermRuleEntity> termRuleEntity);
}
