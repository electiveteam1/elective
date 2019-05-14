package cn.wisdsoft.controller;


import cn.wisdsoft.service.TermService;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/22 19:50
 * @Description:
 */
@RestController
public class TermController {

    @Autowired
    private TermService termService;

    /**
     * 查询所有学期
     * @param page 页数
     * @param limit 条数
     * @return
     */
    @GetMapping("/selectAllTerm")
    public PageResult selectAllTerm(@RequestParam(value = "page",defaultValue = "1") int page,
                                    @RequestParam(value = "limit",defaultValue = "10")  int limit) {
        return termService.selectAllTerm(page, limit);
    }

    /**
     * 查询学期信息
     * @return
     */
    @GetMapping("/selectTerm")
    public ElectiveResult selectTermId() {
        return ElectiveResult.ok(termService.selectTermId());
    }


    /**
     * 根据学号查询学期(使用feign调用)
     * @param StudentId
     * @return
     */
    @PostMapping("/selectTermByStuNum")
    public ElectiveResult selectTermByStuNum(String StudentId){
        return termService.selectTermByStuNum(StudentId);
    }
}
