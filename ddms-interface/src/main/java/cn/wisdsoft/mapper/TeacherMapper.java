package cn.wisdsoft.mapper;

import cn.wisdsoft.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-03 09:40
 * @ Description：
 */
public interface TeacherMapper {
    @Select("select " +
            "tea_power,workNumber,tea_Name,tea_picture,tea_Sex,tea_phone,tea_degreeEdu,tea_jobTitle,remark" +
            " from teacher where workNumber = #{username} and tea_password = #{password}")
    Teacher selectOne(@Param("username") String username, @Param("password") String password);

    /**
     * 查询教师信息
     * @return
     */
    @Select("select workNumber,tea_Name,tea_picture from teacher")
    List<Teacher> selectTeacherName();

    /**
     * 根据教师姓名查询
     * @param teaName
     * @return
     */
    @Select({"<script>","select tea_power,workNumber,tea_Name,tea_Sex,tea_phone,remark from teacher" +
            " <where>\n" +
            "        <if test=\"teaName!=null\">\n" +
            "          tea_Name like concat(concat('%',#{teaName},'%'))\n" +
            "        </if>\n" +
            "      </where>","</script>"})
    List<Teacher> selectTeacherInfo(@Param("teaName") String teaName);

    /**
     * 更改教师权限
     * @param workNumber
     * @param teaPower
     * @return
     */
    @Update("update teacher set tea_power=#{teaPower} where workNumber=#{workNumber}")
    int updateTeacherPower(@Param("workNumber") String workNumber, @Param("teaPower") String teaPower);

    /**
     * 更改密码
     * @param username
     * @param OldPassword
     * @param NewPassword
     * @return
     */
    @Update("update teacher set tea_password=#{NewPassword} where workNumber=#{workNumber} and tea_password=#{OldPassword}")
    int updateTeacherPassword(@Param("workNumber") String username, @Param("OldPassword") String OldPassword, @Param("NewPassword") String NewPassword);
}
