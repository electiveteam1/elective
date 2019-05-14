package cn.wisdsoft.feign;

import cn.wisdsoft.fallback.TermFallBack;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-05-13 07:27
 * @ Description：
 */
@FeignClient(value = "administrator",fallback = TermFallBack.class)
@Component
public interface TermFeign {
    /**
     * 查询学期信息
     * @return
     */
    @GetMapping("/selectTerm")
    public ElectiveResult selectTermId();

    /**
     * 根据学号查询学期(使用feign调用)
     * @param StudentId
     * @return
     */
    @PostMapping("/selectTermByStuNum")
    public ElectiveResult selectTermByStuNum(@RequestParam("StudentId") String StudentId);
}
