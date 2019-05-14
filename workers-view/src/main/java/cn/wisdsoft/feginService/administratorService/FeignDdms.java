package cn.wisdsoft.feginService.administratorService;

import cn.wisdsoft.pojo.ddmsPojo.ClazzRoomEntity;
import cn.wisdsoft.pojo.ddmsPojo.CollegeEntity;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/17 08:29
 * @Description:
 */
@FeignClient("ddms-interface")
@Component
public interface FeignDdms {

    /**
     * 查询所有教师信息
     *
     * @return
     */
    @GetMapping("/selectTeacherName")
    ElectiveResult selectTeacherName();

    /**
     * 查询教室
     *
     * @return
     */
    @GetMapping("/selectAllClazzRoom")
    List<ClazzRoomEntity> selectAllClazzRoom();

    /**
     * 根据楼号查询教室
     *
     * @param crLocation
     * @return
     */
    @GetMapping("/selectAllClazzRoomByLou")
    List<ClazzRoomEntity> selectAllClazzRoomByLou(@RequestParam("crLocation") String crLocation);

    /**
     * 根据楼号和教室，查最大容纳量
     *
     * @param crLocation
     * @param crRoomNum
     * @return
     */
    @GetMapping("/selectAllClazzRoomByLouAndRoom")
    ClazzRoomEntity selectAllClazzRoomByLouAndRoom(@RequestParam("crLocation") String crLocation,
                                                   @RequestParam("crRoomNum") String crRoomNum);
    /**
     * 查询所有教师信息
     * @return
     */
    @GetMapping("/selectTeacherInfo")
    PageResult selectTeacherInfo(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "limit", defaultValue = "10") int limit,
                                 @RequestParam(value = "teaName", required = false) String teaName);
    /**
     * 查询所有的学院
     *
     * @return
     */
    @GetMapping("/selectAllCollege")
    List<CollegeEntity> selectAllCollege();

    /**
     * 修改教师权限
     * @param workNumber
     * @param teaPower
     * @return
     */
    @PostMapping("/updateTeacherPower")
    ElectiveResult updateTeacherPower(@RequestParam("workNumber") String workNumber, @RequestParam("teaPower") String teaPower);

    /**
     * 根据工号和密码查询教师
     * @param username 工号
     * @param password 密码
     * @return JSON数据
     */
    @PostMapping("/selectTeacher")
    ElectiveResult selectOne(@RequestParam("username") String username, @RequestParam("password") String password);

    /***
     * 更改密码
     * @param username 用户名
     * @param OldPassword 旧密码
     * @param NewPassword 新密码
     * @return
     */
    @PostMapping("/updateTeacherPassword")
    ElectiveResult updateTeacherPassword(@RequestParam("username") String username,
                                         @RequestParam("OldPassword") String OldPassword, @RequestParam("NewPassword") String NewPassword);

}
