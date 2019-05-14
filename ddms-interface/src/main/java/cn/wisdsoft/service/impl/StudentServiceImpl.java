package cn.wisdsoft.service.impl;

import cn.wisdsoft.mapper.DdmsStudentMapper;
import cn.wisdsoft.pojo.Student;
import cn.wisdsoft.service.StudentService;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-02 13:25
 * @ Description：
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final DdmsStudentMapper ddmsStudentMapper;

    public StudentServiceImpl(DdmsStudentMapper ddmsStudentMapper) {
        this.ddmsStudentMapper = ddmsStudentMapper;
    }

    @Override
    public ElectiveResult selectOne(String username, String password) {
        return ElectiveResult.ok(ddmsStudentMapper.selectOne(username, password));
    }

    @Override
    public ElectiveResult updatePwd(String username, String oldPwd, String newPwd) {
        Student student = ddmsStudentMapper.selectOne(username, oldPwd);
        if (student == null){
            return ElectiveResult.build(411,"旧密码错误！");
        }
        Integer result = ddmsStudentMapper.updatePwd(username, oldPwd, newPwd);
        if (result != 1) {
            throw new RuntimeException("旧密码错误！");
        }
        return ElectiveResult.ok();
    }
}
