package cn.wisdsoft.feign.fallback;

import cn.wisdsoft.feign.DdmsFeign;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-25 21:32
 * @ Description：
 */
@Component
public class DdmsFallback implements DdmsFeign {
    @Override
    public ElectiveResult changePwd(String username, String oldPwd, String newPwd) {
        return ElectiveResult.build(410,"服务器忙，请稍后再试！");
    }
}
