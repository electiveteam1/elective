package cn.wisdsoft.mapper;
import cn.wisdsoft.pojo.ddmsPojo.ClazzRoomEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/17 12:51
 * @Description:
 */
public interface ClazzRoomMapper {
    /**
     * 查询所有楼
     * @return
     */
    @Select("SELECT DISTINCT clazzroom.cr_location FROM clazzroom")
    List<ClazzRoomEntity> selectAllClazzRoom();

    /**
     * 根据楼查询教室
     * @param crLocation
     * @return
     */
    @Select("select cr_roomNum,cr_maxStuNum from clazzroom where cr_location=#{crLocation}")
    List<ClazzRoomEntity> selectAllClazzRoomByLou(String crLocation);

    @Select("select cr_roomNum,cr_maxStuNum from clazzroom where cr_location=#{crLocation} and cr_roomNum=#{crRoomNum}")
    ClazzRoomEntity selectAllClazzRoomByLouAndRoom(@Param("crLocation")String crLocation,@Param("crRoomNum") String crRoomNum);
}
