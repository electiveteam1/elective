package cn.wisdsoft.controller;


import cn.wisdsoft.pojo.TermRuleEntity;
import cn.wisdsoft.service.TermRuleService;
import cn.wisdsoft.util.ElectiveResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/22 19:50
 * @Description:
 */
@RestController
public class TermRuleController {
    @Autowired
    private TermRuleService termRuleService;
    /**
     * 配置学期规则
     * @param termRules
     * @return
     */
    @PostMapping("/insertTermRule")
    public ElectiveResult insertTermRule(@RequestBody TermRuleEntity termRules){
        ElectiveResult electiveResult = termRuleService.insertTermRule(termRules);
        return electiveResult;
    }

    /**
     * 更改规则
     * @param JSONInfo
     * @return
     */
    @PostMapping("/updateTermRule")
    public ElectiveResult updateTermRule(@RequestParam String JSONInfo){
        JSONArray arrayList = JSONArray.parseArray(JSONInfo);
        //转换为目标对象list
        List<TermRuleEntity> termRuleEntityList = JSONObject.parseArray(arrayList.toJSONString(), TermRuleEntity.class);
        return termRuleService.updateTermRule(termRuleEntityList);
    }
}
