package cn.wisdsoft.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-03-26 14:25
 * @ Description：
 */
@RestController
@RequestMapping("/api")
public class IndexController {
    @Value("${zuul.routes.student.service-id}")
    private String name;

    @GetMapping("/index")
    public String index(){
        return name;
    }
}
