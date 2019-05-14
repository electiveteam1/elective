package cn.wisdsoft.service.impl;

import cn.wisdsoft.mapper.TeacherMapper;
import cn.wisdsoft.pojo.Teacher;
import cn.wisdsoft.service.TeacherService;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-03 09:45
 * @ Description：
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public ElectiveResult selectOne(String username, String password) {
        Teacher teacher = teacherMapper.selectOne(username, password);
        if(teacher==null){
            return ElectiveResult.build(420,"用户名或密码错误");
        }
        return ElectiveResult.ok(teacher);
    }

    /**
     * 查询所有教师姓名
     * @return
     */
    @Override
    public ElectiveResult selectTeacherName() {
        List<Teacher> teachers = teacherMapper.selectTeacherName();
        return ElectiveResult.ok(teachers);
    }

    /**
     * 查询所有教师信息
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageResult selectTeacherInfo(int page,int limit,String teaName) {
        PageHelper.startPage(page,limit,"tea_power desc");
         List<Teacher> teachers = teacherMapper.selectTeacherInfo(teaName);
        PageInfo<Teacher> pageInfo = new PageInfo(teachers);
        return PageResult.ok(teachers,pageInfo.getTotal());
    }

    /**
     * 修改教师权限
     * @param workNumber 工号
     * @param teaPower 权限
     * @return
     */
    @Override
    public ElectiveResult updateTeacherPower(String workNumber, String teaPower) {
        int count = teacherMapper.updateTeacherPower(workNumber, teaPower);
        if(count<1){
            return ElectiveResult.build(410,"修改权限失败");
        }
        return ElectiveResult.ok();
    }

    /**
     * 更改密码
     * @param username 用户名
     * @param OldPassword 旧密码
     * @param NewPassword 新密码
     * @return
     */
    @Override
    public ElectiveResult updateTeacherPassword(String username, String OldPassword, String NewPassword) {
        int count = teacherMapper.updateTeacherPassword(username, OldPassword, NewPassword);
        if(count<1){
            return ElectiveResult.build(410,"修改密码失败");
        }
        return ElectiveResult.ok();
    }
}
