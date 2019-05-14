package cn.wisdsoft.service;

import cn.wisdsoft.pojo.TermEntity;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/22 19:53
 * @Description:
 */
public interface TermService {

    /**
     * 添加学期
     * @param term
     * @return
     */
    ElectiveResult insertTerm(TermEntity term);

    /**
     * 结束学期
     * @param termStatus
     * @return
     */
    int breakTerm(String termStatus);

    /**
     * 查询所有学期
     * @param page 页码
     * @param limit 条数
     * @return
     */
    PageResult selectAllTerm(int page, int limit);

    /**
     * 查询本学期的编号
     * @return
     */
    TermEntity selectTermId();

    ElectiveResult selectTermByStuNum(String StudentId);
}
