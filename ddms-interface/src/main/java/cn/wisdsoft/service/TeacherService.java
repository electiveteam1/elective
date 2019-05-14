package cn.wisdsoft.service;

import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-03 09:44
 * @ Description：
 */
public interface TeacherService {
    ElectiveResult selectOne(String username, String password);

    /**
     *查询所有教师姓名
     * @return
     */
    ElectiveResult selectTeacherName();

    /**
     * 查询所有教师信息
     * @return
     */
    PageResult selectTeacherInfo(int page, int limit, String teaName);

    /**
     * 修改教师权限
     * @param workNumber 工号
     * @param teaPower 权限
     * @return
     */
    ElectiveResult updateTeacherPower(String workNumber, String teaPower);

    /***
     * 更改密码
     * @param username 用户名
     * @param OldPassword 旧密码
     * @param NewPassword 新密码
     * @return
     */
    ElectiveResult updateTeacherPassword(String username, String OldPassword, String NewPassword);
}
