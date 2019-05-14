package cn.wisdsoft.service.impl;

import cn.wisdsoft.common.AdminConstant;
import cn.wisdsoft.common.ChinaDate;
import cn.wisdsoft.mapper.TermMapper;
import cn.wisdsoft.mapper.TermRuleMapper;
import cn.wisdsoft.pojo.TermEntity;
import cn.wisdsoft.pojo.TermRuleEntity;
import cn.wisdsoft.service.TermRuleService;
import cn.wisdsoft.tasks.QuartzInitVopVosFactory;
import cn.wisdsoft.util.ConstCommon;
import cn.wisdsoft.util.ElectiveResult;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/22 19:54
 * @Description: 动态定时器
 */
@Service
@Transactional
public class TermRuleServiceImpl implements TermRuleService {

    private final TermRuleMapper termRuleMapper;
    private final TermMapper termMapper;

    private final Scheduler scheduler;

    public TermRuleServiceImpl(Scheduler scheduler, TermMapper termMapper, TermRuleMapper termRuleMapper) {
        this.scheduler = scheduler;
        this.termMapper = termMapper;
        this.termRuleMapper = termRuleMapper;
    }

    /**
     * 配置学期规则
     *
     * @param termRules
     * @return
     */
    @Override
    public ElectiveResult insertTermRule(TermRuleEntity termRules) {
        String openTime = termRules.getOpenTime();
        String closeTime = termRules.getCloseTime();
        String semesterEnds = termRules.getSemesterEnds();
        String result = testRuleTime(openTime, closeTime, semesterEnds);
        if(result!=null){
            return ElectiveResult.build(420,result);
        }
        List<TermRuleEntity> termRuleEntityList = (List<TermRuleEntity>) new TermRuleEntity();
        for(int i=0;i<3;i++){
            termRuleEntityList.get(i).setTermId(termRules.getTermId());
        }
        termRuleEntityList.get(0).setTermRuleState(ConstCommon.SEMESTER_STATUS.course_selection.getCode());
        termRuleEntityList.get(1).setTermRuleState(ConstCommon.SEMESTER_STATUS.course_selection_end.getCode());
        termRuleEntityList.get(2).setTermRuleState(ConstCommon.SEMESTER_STATUS.semester_ends.getCode());
        termRuleEntityList.get(0).setCron(termRules.getOpenTime());
        termRuleEntityList.get(1).setCron(termRules.getCloseTime());
        termRuleEntityList.get(2).setCron(termRules.getSemesterEnds());
        int count = termRuleMapper.insertTermRule(termRuleEntityList);
        if (count < 0) {
            return ElectiveResult.build(420, "学期规则录入失败");
        }
        //将打开时间 ，设置为静态变量
        AdminConstant.BeginTime = termRules.getOpenTime();
        return ElectiveResult.build(200, "学期规则录入成功");
    }

    @Transactional
    @Override
    public ElectiveResult updateTermRule(List<TermRuleEntity> termRuleEntityList) {
        String openTime = termRuleEntityList.get(0).getCron();
        String closeTime = termRuleEntityList.get(1).getCron();
        String semesterEnds = termRuleEntityList.get(2).getCron();
        String result = testRuleTime(openTime, closeTime, semesterEnds);
        if(result!=null){
            return ElectiveResult.build(420,result);
        }
        int count = termRuleMapper.updateTermRule(termRuleEntityList);
        if (count < 0) {
            try {
                throw new Exception("学期规则修改失败");
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            return ElectiveResult.build(420, "学期规则修改失败");
        }
        return ElectiveResult.ok();
    }

    private String  testRuleTime(String openTime,String closeTime,String semesterEnds){
        String result=null;
        if (openTime == null || "".equals(openTime)) {
            result= "选课开始时间不能为空哦";
        }
        if (closeTime == null || "".equals(closeTime)) {
            result= "选课结束时间不能为空哦";
        }
        if (semesterEnds == null || "".equals(semesterEnds)) {
            result= "结束时间不能为空哦";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String dateString = format.format(currentTime);
        //compareTo()方法的返回值，date1小于date2返回-1，date1大于date2返回1，相等返回0
        Date nowTime = null;
        try {
            nowTime = format.parse(dateString);
            Date beginTime = format.parse(openTime);
            Date endTime = format.parse(closeTime);
            Date SemesterEnd = format.parse(semesterEnds);
            if (nowTime.compareTo(beginTime) == 0) {
                result="选课开始时间不能为当天哦";
            } else if (nowTime.compareTo(beginTime) > 0) {
                result="选课开始时间不能小于当天哦";
            } else if (nowTime.compareTo(endTime) == 1) {
                result="选课结束时间不能小于当天哦";
            } else if (nowTime.compareTo(SemesterEnd) == 1) {
                result="学期结束时间不能小于当天哦";
            } else if (endTime.compareTo(beginTime) == -1) {
                result="选课结束时间必须大于选课开始时间哦";
            } else if (endTime.compareTo(beginTime) == 0) {
                result="选课结束时间和选课开始时间不能为一天哦";
            } else if (SemesterEnd.compareTo(endTime) == -1) {
                result="学期结束时间必须大于选课结束时间哦";
            } else if (SemesterEnd.compareTo(endTime) == 0) {
                result="学期结束时间和选课结束时间不能为一天哦";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 每隔一小时查库，并根据查询结果决定是否重新设置定时任务
     */
    @Scheduled(fixedRate = 3600000)
    public void initVopVos() {
        //这里获取任务信息数据 ，从数据库中获取所以今天需要执行的任务
        List<TermRuleEntity> jobList = termRuleMapper.selectTermRule();
        if(jobList.size()==0){
            TermEntity termEntity = termMapper.selectTermId(Integer.parseInt(ConstCommon.SEMESTER_STATUS.semester_ends.getCode()));
           if(termEntity!=null){
               ConstCommon.Term_Id=termEntity.getTermId();
           }
            return;
        }
        ConstCommon.Term_Id=jobList.get(0).getTermId();
        try {
            for (TermRuleEntity job : jobList) {
                TriggerKey triggerKey = TriggerKey.triggerKey(job.getTermRuleState(), job.getTermId().toString());
                //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                //不存在，创建一个
                if (null == trigger) {
                    JobDetail jobDetail = JobBuilder.newJob(QuartzInitVopVosFactory.class)
                            .withIdentity(job.getTermRuleState(), job.getTermId().toString()).build();
                    jobDetail.getJobDataMap().put("job", job);
                    //表达式调度构建器
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(ChinaDate.TermRulecron(job.getCron()));
                    //按新的cronExpression表达式构建一个新的trigger
                    trigger = TriggerBuilder.newTrigger().withIdentity(job.getTermRuleState(), job.getTermId().toString()).withSchedule(scheduleBuilder).build();
                    scheduler.scheduleJob(jobDetail, trigger);
                } else {
                    // Trigger已存在，那么更新相应的定时设置
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(ChinaDate.TermRulecron(job.getCron()));
                    //按新的cronExpression表达式重新构建trigger
                    trigger = trigger.getTriggerBuilder().startAt(new Date()).withIdentity(triggerKey)
                            .withSchedule(scheduleBuilder).build();
                    //scheduler.rescheduleJob如果服务器当前时间与你的表达式配置的执行时间差在两小时以内时，
                    //动态修改就会出现立即执行的情况。所以这里设置执行时间从当前时间开始
                    JobDataMap jobDataMap = trigger.getJobDataMap();//重新获取JobDataMap，并且更新参数
                    jobDataMap.put("job", job);
                    //按新的trigger重新设置job执行
                    scheduler.rescheduleJob(triggerKey, trigger);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            System.out.println("initVopVos Error");
        }
    }
}
