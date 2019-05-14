package cn.wisdsoft.tasks;

import cn.wisdsoft.mapper.TermMapper;
import cn.wisdsoft.mapper.TermRuleMapper;
import cn.wisdsoft.pojo.TermRuleEntity;
import cn.wisdsoft.service.StudentElectiveService;
import cn.wisdsoft.util.ConstCommon;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
/**
* @Description:    实现更改规则
* @Author:         SongJunWei
* @CreateDate:     2019/5/4 21:35
*/
@DisallowConcurrentExecution
public class QuartzInitVopVosFactory implements Job {
    @Autowired
    private TermMapper termMapper;
    @Autowired
    private TermRuleMapper termRuleMapper;
    @Autowired
    private StudentElectiveService studentElectiveService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        TermRuleEntity termRuleEntity = (TermRuleEntity) jobExecutionContext.getMergedJobDataMap().get("job");
        //上面这句比较坑,必须用getMergedJobDataMap，不然获取的是一个list<map>对象。不好解析，
        termMapper.updateTermStatus(termRuleEntity.getTermId(), termRuleEntity.getTermRuleState());
        termRuleMapper.updateTermRuleStatus(termRuleEntity.getTermRuleId());
        if(termRuleEntity.getTermRuleState().equals(ConstCommon.SEMESTER_STATUS.semester_ends.getCode())){
            studentElectiveService.updateLearningStatus(ConstCommon.course_flag.learned.getCode());
        }
        ConstCommon.Term_Id=termRuleEntity.getTermId();
        System.out.println("=============================================================================");
        System.out.println(termRuleEntity.toString());
    }
}