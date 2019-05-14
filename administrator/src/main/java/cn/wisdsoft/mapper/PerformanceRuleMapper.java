package cn.wisdsoft.mapper;
import cn.wisdsoft.pojo.PerformanceRuleEntity;
import org.springframework.stereotype.Component;

@Component
public interface PerformanceRuleMapper {

    /**
     * 添加规则
     * @param
     * @return
     */
    int insertPerformanceRule(PerformanceRuleEntity performanceRuleEntity);
}