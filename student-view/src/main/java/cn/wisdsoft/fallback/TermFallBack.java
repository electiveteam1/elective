package cn.wisdsoft.fallback;

import cn.wisdsoft.feign.TermFeign;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-05-13 07:36
 * @ Description：
 */
@Component
public class TermFallBack implements TermFeign {

    @Override
    public ElectiveResult selectTermId() {
        return ElectiveResult.build(410,"服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult selectTermByStuNum(String StudentId) {
        return ElectiveResult.build(410,"服务器忙，请稍后再试！");
    }
}
