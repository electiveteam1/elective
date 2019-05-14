package cn.wisdsoft.service.impl;

import cn.wisdsoft.mapper.TermMapper;
import cn.wisdsoft.mapper.TermRuleMapper;
import cn.wisdsoft.pojo.TermEntity;
import cn.wisdsoft.pojo.TermRuleEntity;
import cn.wisdsoft.pojo.vo.TermVo;
import cn.wisdsoft.service.TermService;
import cn.wisdsoft.util.ConstCommon;
import cn.wisdsoft.util.ElectiveResult;
import cn.wisdsoft.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/22 19:53
 * @Description:
 */
@Service
@Transactional
public class TermServiceImpl implements TermService
{

    @Autowired
    private TermMapper termMapper;
    @Autowired
    private TermRuleMapper termRuleMapper;
    /**
     * 添加学期
     *
     * @param term
     * @return
     */
    @Override
    public ElectiveResult insertTerm(TermEntity term) {
        term.setTermStatus(ConstCommon.SEMESTER_STATUS.config_teacher.getCode());
        int count = termMapper.insertSelective(term);
        if (count < 0) {
            return ElectiveResult.build(420, "添加失败，请联系后台");
        }
        return ElectiveResult.build(200, "添加成功");
    }

    /**
     * 结束学期
     *
     * @param termStatus
     * @return
     */
    @Override
    public int breakTerm(String termStatus) {
        return termMapper.breakTerm(termStatus);
    }
    /***
     * 查询所有学期
     * @param page 页码
     * @param limit 条数
     * @return
     */
    @Override
    public PageResult selectAllTerm(int page, int limit) {
        PageHelper.startPage(page, limit, "term_id desc");
        List<TermVo> terms = termMapper.selectAllTerm();
        //查询学期规则
        List<TermRuleEntity> termRuleEntityList = termRuleMapper.selectTermRule();
        if(termRuleEntityList.size()!=0){
            terms.get(0).setOpenTime(termRuleEntityList.get(0).getCron()).setOpenTimeId(termRuleEntityList.get(0).getTermRuleId());
            terms.get(0).setCloseTime(termRuleEntityList.get(1).getCron()).setCloseTimeId(termRuleEntityList.get(1).getTermRuleId());
            terms.get(0).setSemesterEnds(termRuleEntityList.get(2).getCron()).setSemesterEndsId(termRuleEntityList.get(2).getTermRuleId());
        }
        PageInfo<TermVo> pageInfo = new PageInfo(terms);
        return PageResult.ok(terms, pageInfo.getTotal());
    }

    @Override
    public TermEntity selectTermId() {
        int TermId = Integer.parseInt(ConstCommon.SEMESTER_STATUS.semester_ends.getCode());
        return termMapper.selectTermId(TermId);
    }

    @Override
    public ElectiveResult selectTermByStuNum(String StudentId) {
        String substring = StudentId.substring(0, 2);
        Calendar cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        String sub1 = (year+"").substring(0,2);
        int years = Integer.parseInt(sub1+substring);
        List list = new ArrayList();
        for(int i=years;i<=years+4;i++){
            String termName =null;
            for (int j=1;j<=2;j++){
                String t = null;
                System.out.println(i);
                if(j==1){ t="一"; }else if(j==2){t="二";}
                termName = i+"-"+(i+1)+"学年第"+t+"学期";
                list.add(termName);
            }
        }
        List<TermEntity> termEntities = termMapper.selectTermByStuNum(list);
        return ElectiveResult.ok(termEntities);
    }
}
