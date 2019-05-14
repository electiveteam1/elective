package cn.wisdsoft.feign;

import cn.wisdsoft.fallback.DDMSFallBack;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-02 15:50
 * @ Description：
 */
@FeignClient(value = "DDMS-INTERFACE",fallback = DDMSFallBack.class)
public interface DdmsFeign {

    /**
     * 调用远程学生登录接口
     * @param username 学号
     * @param password 密码
     * @return JSON数据
     */
    @PostMapping("/selectStudent")
    ElectiveResult selectOne(@RequestParam("username") String username, @RequestParam("password") String password);

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @return JSON数据
     */
    @PostMapping("/changepwd")
    public ElectiveResult changePwd(@RequestParam("username") String username,@RequestParam("oldPwd") String oldPwd
            ,@RequestParam("newPwd") String newPwd);

    /**
     * 查询教师详情
     *
     * @param username 职工号
     * @return JSON数据
     */
    @PostMapping("/detail")
    public ElectiveResult detail(@RequestParam("username") String username);
}
