package cn.wisdsoft.service;

import cn.wisdsoft.util.ElectiveResult;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-25 14:31
 * @ Description：
 */
public interface InformationService {
    /**
     * 所有信息
     * @param studentId 学生ID
     * @return 消息数据
     */
    ElectiveResult allTip(String studentId);

    /**
     * 查询是否有未查看消息
     * @param studentId 学生ID
     * @return JSON数据
     */
    ElectiveResult redPoint(String studentId);

    /**
     * 查询消息详情
     * @param informationId 消息ID
     * @return JSON数据
     */
    ElectiveResult detail(Long informationId);
}
