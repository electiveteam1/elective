package cn.wisdsoft.api;

import cn.wisdsoft.pojo.ddmsPojo.CollegeEntity;
import cn.wisdsoft.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/18 09:54
 * @Description:
 */
@RestController
public class collegeApi {
    @Autowired
    private CollegeService collegeService;

    /**
     * 查询所有的学院
     *
     * @return
     */
    @GetMapping("/selectAllCollege")
    public List<CollegeEntity> selectAllCollege() {
        return collegeService.selectAllCollege();
    }

}
