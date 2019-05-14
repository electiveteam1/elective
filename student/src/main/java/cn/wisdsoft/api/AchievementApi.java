package cn.wisdsoft.api;

import cn.wisdsoft.pojo.StudentVo;
import cn.wisdsoft.service.AchievementService;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-13 22:46
 * @ Description：
 */
@RestController
@RequestMapping("/achievement")
public class AchievementApi {
    private final AchievementService achievementService;

    public AchievementApi(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    /**
     * 查询成绩
     *
     * @param studentVo 用户信息
     * @return 成绩信息
     */
    @PostMapping("/grade")
    public ElectiveResult grade(String term, @RequestBody StudentVo studentVo) {
        return achievementService.selectAchievement(studentVo.getUsername(), term);
    }
}
