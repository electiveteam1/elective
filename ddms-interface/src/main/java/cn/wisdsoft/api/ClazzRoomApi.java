package cn.wisdsoft.api;

import cn.wisdsoft.pojo.ddmsPojo.ClazzRoomEntity;
import cn.wisdsoft.service.ClazzRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/17 12:51
 * @Description:
 */
@RestController
public class ClazzRoomApi {

    @Autowired
    private ClazzRoomService clazzRoomService;

    /**
     * 查询教室
     * @return
     */
    @GetMapping("/selectAllClazzRoom")
    public List<ClazzRoomEntity> selectClazzRoom(){
        List<ClazzRoomEntity> clazzRoomEntities = clazzRoomService.selectAllClazzRoom();
        return clazzRoomEntities;
    }

    /**
     * 根据楼号查询教室
     * @param crLocation
     * @return
     */
    @GetMapping("/selectAllClazzRoomByLou")
    public  List<ClazzRoomEntity> selectAllClazzRoomByLou(@RequestParam("crLocation") String crLocation){
        return clazzRoomService.selectAllClazzRoomByLou(crLocation);
    }

    /**
     * 根据楼号和教室，查最大容纳量
     * @param crLocation
     * @param crMaxStuNum
     * @return
     */
    @GetMapping("/selectAllClazzRoomByLouAndRoom")
    public ClazzRoomEntity selectAllClazzRoomByLouAndRoom(@RequestParam("crLocation") String crLocation,
                                                   @RequestParam("crRoomNum") String crRoomNum){
        return clazzRoomService.selectAllClazzRoomByLouAndRoom(crLocation,crRoomNum);
    }
}
