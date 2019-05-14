package cn.wisdsoft.api;

import cn.wisdsoft.service.InformationService;
import cn.wisdsoft.util.ElectiveResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-25 15:25
 * @ Description：
 */
@RestController
@RequestMapping("/information")
public class InformationApi {
    private final InformationService informationService;

    public InformationApi(InformationService informationService) {
        this.informationService = informationService;
    }

    /**
     * 所有信息
     * @param studentId 学生ID
     * @return 消息数据
     */
    @PostMapping("/allTip")
    public ElectiveResult allTip(String studentId){
        return informationService.allTip(studentId);
    }

    /**
     * 查询是否有未查看消息
     * @param studentId 学生ID
     * @return JSON数据
     */
    @PostMapping("/redPoint")
    public ElectiveResult redPoint(String studentId){
        return informationService.redPoint(studentId);
    }

    /**
     * 查询消息详情
     * @param informationId 消息ID
     * @return JSON数据
     */
    @PostMapping("/detail")
    public ElectiveResult detail(Long informationId){
        return informationService.detail(informationId);
    }
}
