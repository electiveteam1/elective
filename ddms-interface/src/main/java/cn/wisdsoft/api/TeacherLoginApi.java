package cn.wisdsoft.api;

import cn.wisdsoft.service.TeacherService;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-03 09:46
 * @ Description：
 */
@RestController
public class TeacherLoginApi {
    private final TeacherService teacherService;

    public TeacherLoginApi(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 根据工号和密码查询教师
     * @param username 工号
     * @param password 密码
     * @return JSON数据
     */
    @PostMapping("/selectTeacher")
    public ElectiveResult selectOne(HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        ElectiveResult electiveResult = teacherService.selectOne(username, password);
        String accessToken= UUID.randomUUID().toString().replaceAll("-", "");

        session.setAttribute(accessToken,electiveResult.getData());
        return electiveResult;
    }

    /**
     * 查询所有教师名称
     * @return
     */
    @GetMapping("/selectTeacherName")
    public ElectiveResult selectTeacherName(){
        return teacherService.selectTeacherName();
    }

    /**
     * 查询所有教师信息
     * @return
     */
    @GetMapping("/selectTeacherInfo")
    public PageResult selectTeacherInfo(@RequestParam(value = "page",defaultValue = "1") int page,
                                 @RequestParam(value = "limit",defaultValue = "10") int limit,
                                        @RequestParam(value = "teaName",required = false) String teaName){
        return teacherService.selectTeacherInfo(page,limit,teaName);
    }

    /**
     * 修改教师权限
     * @param workNumber
     * @param teaPower
     * @return
     */
    @PostMapping("/updateTeacherPower")
    public ElectiveResult updateTeacherPower(@RequestParam("workNumber") String workNumber,@RequestParam("teaPower")String teaPower){
        return teacherService.updateTeacherPower( workNumber,teaPower);
    }

    /***
     * 更改密码
     * @param username 用户名
     * @param OldPassword 旧密码
     * @param NewPassword 新密码
     * @return
     */
    @PostMapping("/updateTeacherPassword")
    public ElectiveResult updateTeacherPassword(@RequestParam("username") String username,
                                                @RequestParam("OldPassword") String OldPassword,@RequestParam("NewPassword") String NewPassword){
        return teacherService.updateTeacherPassword(username,OldPassword,NewPassword);
    }
}

