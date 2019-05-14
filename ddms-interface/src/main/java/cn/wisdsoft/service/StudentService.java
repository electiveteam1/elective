package cn.wisdsoft.service;

import cn.wisdsoft.util.ElectiveResult;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-02 13:24
 * @ Description：
 */
public interface StudentService {
    ElectiveResult selectOne(String username, String password);

    /**
     * 更新密码
     *
     * @param username 用户名
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @return JSON数据
     */
    ElectiveResult updatePwd(String username, String oldPwd, String newPwd);
}
