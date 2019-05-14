package cn.wisdsoft.api;

import cn.wisdsoft.service.StudentService;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-01 14:43
 * @ Description：
 */
@RestController
public class StudentLoginApi {
    private final StudentService studentService;

    public StudentLoginApi(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 根据学号和密码查询学生
     *
     * @param username 学号
     * @param password 密码
     * @return JSON数据
     */
    @PostMapping("/selectStudent")
    public ElectiveResult selectOne(String username, String password) {
        return studentService.selectOne(username, password);
    }

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @return JSON数据
     */
    @PostMapping("/changepwd")
    public ElectiveResult changePwd(String username, String oldPwd, String newPwd) {
        return studentService.updatePwd(username, oldPwd, newPwd);
    }
}
