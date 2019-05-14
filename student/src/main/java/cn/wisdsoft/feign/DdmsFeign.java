package cn.wisdsoft.feign;

import cn.wisdsoft.feign.fallback.DdmsFallback;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-25 21:30
 * @ Description：
 */
@FeignClient(value = "DDMS-INTERFACE",fallback = DdmsFallback.class)
@Component
public interface DdmsFeign {
    /**
     * 修改密码
     *
     * @param username 用户名
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @return JSON数据
     */
    @PostMapping("/changepwd")
    public ElectiveResult changePwd(@RequestParam("username") String username, @RequestParam("oldPwd") String oldPwd
            , @RequestParam("newPwd") String newPwd);
}
