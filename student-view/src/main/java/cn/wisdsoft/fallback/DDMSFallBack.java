package cn.wisdsoft.fallback;

import cn.wisdsoft.feign.DdmsFeign;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-15 15:59
 * @ Description：
 */
public class DDMSFallBack implements DdmsFeign {
    @Override
    public ElectiveResult selectOne(String username, String password) {
        return ElectiveResult.build(410,"服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult changePwd(String username, String oldPwd, String newPwd) {
        return ElectiveResult.build(410,"服务器忙，请稍后再试！");
    }

    @Override
    public ElectiveResult detail(String username) {
        return ElectiveResult.build(410,"服务器忙，请稍后再试！");
    }
}
