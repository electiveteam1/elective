package cn.wisdsoft.mapper;

import cn.wisdsoft.pojo.TermRuleEntity;
import cn.wisdsoft.util.ElectiveResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TermRuleMapper {

    /**
     * 配置学期规则
     * @param termRules
     * @return
     */
    int insertTermRule(List<TermRuleEntity> termRules);

    /**
     * 查询规则
     * @return
     */
    List<TermRuleEntity> selectTermRule();

    /**
     * 修改学期规则
     * @param termRuleEntity
     * @return
     */
    int updateTermRule(@Param("termRuleEntity") List<TermRuleEntity> termRuleEntity);

    /**
     * 修改学期规则状态
     * @param termRuleId
     * @return
     */
    int updateTermRuleStatus(long termRuleId);
}