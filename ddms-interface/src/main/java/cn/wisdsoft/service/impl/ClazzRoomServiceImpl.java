package cn.wisdsoft.service.impl;

import cn.wisdsoft.mapper.ClazzRoomMapper;
import cn.wisdsoft.pojo.ddmsPojo.ClazzRoomEntity;
import cn.wisdsoft.service.ClazzRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/17 12:55
 * @Description:
 */
@Service
public class ClazzRoomServiceImpl implements ClazzRoomService {

    @Autowired
    private ClazzRoomMapper clazzRoomMapper;

    @Override
    public List<ClazzRoomEntity> selectAllClazzRoom() {
        return clazzRoomMapper.selectAllClazzRoom();
    }

    @Override
    public List<ClazzRoomEntity> selectAllClazzRoomByLou(String crLocation) {
        return clazzRoomMapper.selectAllClazzRoomByLou(crLocation);
    }

    @Override
    public ClazzRoomEntity selectAllClazzRoomByLouAndRoom(String crLocation, String crRoomNum) {
        return clazzRoomMapper.selectAllClazzRoomByLouAndRoom(crLocation,crRoomNum);
    }
}
