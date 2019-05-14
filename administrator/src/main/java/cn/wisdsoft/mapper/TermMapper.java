package cn.wisdsoft.mapper;

import cn.wisdsoft.pojo.vo.TermVo;
import cn.wisdsoft.pojo.TermEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TermMapper {

    /**
     * 创建学期
     * @param termEntity
     * @return
     */
    int insertSelective(TermEntity termEntity);

    /**
     * 修改学期状态
     * @param termStatus
     * @return
     */
    int updateByTermStates(@Param("termStatus") String termStatus, @Param("Constant_termStatus") String Constant_termStatus);

    /**
     * 结束学期
     * @param termStatus
     * @return
     */
    int breakTerm(String termStatus);

    /**
     * 查询学期
     * @return
     */
    List<TermVo> selectAllTerm();

    /**
     * 修改学期状态
     * @param termId 学期编号
     * @param termStatus 学期状态
     * @return
     */
    int updateTermStatus(@Param("termId") long termId, @Param("termStatus") String termStatus);

    TermEntity selectTermId(Integer termId);

    List<TermEntity> selectTermByStuNum(List list);

    /**
     * 根据学期名称，查询学期
     * @param TermName
     * @return
     */
    @Select("select term_id,term_name from term where term_name =#{TermName}")
    TermEntity selectTermNameByName(@Param("TermName") String TermName);
}