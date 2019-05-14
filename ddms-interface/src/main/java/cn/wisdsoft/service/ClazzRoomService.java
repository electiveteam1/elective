package cn.wisdsoft.service;

import cn.wisdsoft.pojo.ddmsPojo.ClazzRoomEntity;
import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/17 12:54
 * @Description:
 */
public interface ClazzRoomService {
    /**
     * 查询所有教室
     * @return
     */
    List<ClazzRoomEntity> selectAllClazzRoom();

    List<ClazzRoomEntity> selectAllClazzRoomByLou(String crLocation);

    ClazzRoomEntity selectAllClazzRoomByLouAndRoom(String crLocation,String crRoomNum);
}
