package cn.wisdsoft.mapper;

import cn.wisdsoft.pojo.InformationEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-25 14:31
 * @ Description：
 */
public interface InformationMapper {
    /**
     * 查询学生对应的消息信息
     * @param studentId 学生ID
     * @return 消息列表
     */
    @Select("select * from information where student_id = #{studentId}")
    List<InformationEntity> selectAll(String studentId);

    /**
     * 查询是否有未查看的消息
     * @param studentId 学生ID
     * @return 大于0表示含有未查看消息
     */
    @Select("select count(*) from information where student_id = #{studentId} and remark = 0")
    Integer selectNum(String studentId);

    /**
     * 将未查看更新为已查看
     * @param studentId 学生ID
     * @return 大于0表示成功
     */
    @Update("update information set remark = 1 where student_id = #{studentId}")
    Integer update(String studentId);

    /**
     * 查询消息详情
     * @param informationId 消息ID
     * @return 消息信息
     */
    @Select("select * from information where information_id = #{informationId}")
    InformationEntity selectOne(Long informationId);
}
