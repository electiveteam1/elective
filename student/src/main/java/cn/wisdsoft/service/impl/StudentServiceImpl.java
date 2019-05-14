package cn.wisdsoft.service.impl;

import cn.wisdsoft.feign.DdmsFeign;
import cn.wisdsoft.mapper.StudentMapper;
import cn.wisdsoft.pojo.ElectiveCourseEntity;
import cn.wisdsoft.pojo.StudentElectiveEntity;
import cn.wisdsoft.pojo.StudentEntity;
import cn.wisdsoft.service.StudentService;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.MD5Util;
import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-03-29 08:15
 * @ Description：
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    private final DdmsFeign ddmsFeign;

    public StudentServiceImpl(StudentMapper studentMapper, DdmsFeign ddmsFeign) {
        this.studentMapper = studentMapper;
        this.ddmsFeign = ddmsFeign;
    }

    @Override
    public ElectiveResult login(String username, String password) {
        StudentEntity student = studentMapper.selectByUsernameAndPassword(username, password);
        return ElectiveResult.ok(student);
    }

    @Override
    public ElectiveResult insert(StudentEntity student) {
        studentMapper.insertOne(student);
        return ElectiveResult.ok();
    }

    @Override
    public ElectiveResult insertStudentElective(StudentElectiveEntity electiveEntity,String college,String courseGroupName) {
        ElectiveCourseEntity entity1 = studentMapper.selectOneElective(electiveEntity.getElectiveCourseId());
        if ("0".equals(entity1.getOptionFlag())){
            return ElectiveResult.build(414,"不能选择备选课程");
        }
        //1、判断是否达到最大选课数量
        //查询校选课的数量
        Integer schoolNums = studentMapper.selectCountForElective(electiveEntity.getStudentId(), "校选");
        //查询院选课的人数
        Integer collegeNums = studentMapper.selectCountForElective(electiveEntity.getStudentId(), college);
        //如果校选课数量大于1或者院选课数量大于3则返回“选课次数到达最大！”
        if (schoolNums > 1 || collegeNums > 3) {
            return ElectiveResult.build(413,"选课次数到达最大！");
        }

        //2、判断是否属于同一课程组
        //查询选生所选课程的课程组
        List<String> groups = studentMapper.selectCourseGroups(electiveEntity.getStudentId());
        for (String value : groups) {
            //如果有相同的，则直接返回
            if(value.equals(courseGroupName))
                return ElectiveResult.build(411,"选择的课程和已选课程相似");
        }

        //3、判断课程人数是否已满
        ElectiveCourseEntity entity = studentMapper.selectAllNums(electiveEntity.getElectiveCourseId());
        if (entity != null) {
            return ElectiveResult.build(412,"该课程已选满");
        }

        //4、插入选课
        studentMapper.insertElectiveCourse(electiveEntity);

        //5、更新课程当前人数
        studentMapper.updateCurrentNum(electiveEntity.getElectiveCourseId(),1);

        //6、更新备选状态
        //  查询当前选课对应信息
        ElectiveCourseEntity one = studentMapper.selectOneElective(electiveEntity.getElectiveCourseId());
        if(one.getCurrentNumber().equals(one.getMaxNumber())){
            //如果当前人数和最大人数相等，则将备选课程更新为可选择课程
            studentMapper.updateOptionFlag(one.getCourseLibraryName(),one.getPriority() + 1,one.getTermId(),1);
        }
        return ElectiveResult.ok();
    }

    @Override
    public ElectiveResult selectElectiveCourse(String courseFlag, String studentId) {
        return ElectiveResult.ok(studentMapper.selectElectiveCourse(studentId, courseFlag));
    }

    @Override
    public ElectiveResult deleteCourse(String studentId, Long electiveCourseId) {
        //1、查询时间判断是否可以退课
        StudentElectiveEntity entity = studentMapper.selectOne(studentId, electiveCourseId);
        if(entity == null){
            return ElectiveResult.build(411,"没有对应选课信息");
        }
        //2、必须在24小时之后退课
        Date now = new Date();
        Date stuDate = entity.getTimeStamp();
        long difference = (now.getTime() - stuDate.getTime()) / (60 * 60 * 1000);
        if(difference <= 24){
            return ElectiveResult.build(412,"退课时间过早,请于"+ (24 - difference)+"小时之后在退课！");
        }
        //3、退课
        studentMapper.deleteOne(studentId, electiveCourseId);
        //4、更新课程当前人数
        studentMapper.updateCurrentNum(electiveCourseId,-1);
        return ElectiveResult.ok();
    }

    @Override
    public ElectiveResult changePwd(String username, String oldPwd, String newPwd) {
        String oldKey = MD5Util.MD5Encryption(oldPwd);
        String newKey = MD5Util.MD5Encryption(newPwd);
        StudentEntity studentEntity = studentMapper.selectByUsernameAndPassword(username, oldKey);
        if (studentEntity == null){
            return ElectiveResult.build(411,"旧密码错误!");
        }
        studentMapper.updatePwd(username, oldKey, newKey);
        ElectiveResult electiveResult = ddmsFeign.changePwd(username, oldPwd, newPwd);
        if (electiveResult.getStatus() != 200){
            throw new RuntimeException(electiveResult.getMsg());
        }
        return ElectiveResult.ok();
    }

    @Override
    public ElectiveResult updateAll() {
        studentMapper.updateAll();
        return ElectiveResult.ok();
    }
}
