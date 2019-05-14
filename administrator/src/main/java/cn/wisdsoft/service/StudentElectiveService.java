package cn.wisdsoft.service;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/22 19:52
 * @Description:
 */
public interface StudentElectiveService {

    /**
     * 修改学习状态（将learning--->learned）
     * @return
     */
    int updateLearningStatus(String courseFlag);
}
