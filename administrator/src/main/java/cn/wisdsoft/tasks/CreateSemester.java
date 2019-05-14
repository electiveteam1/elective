package cn.wisdsoft.tasks;

import cn.wisdsoft.common.ChinaDate;
import cn.wisdsoft.mapper.TermMapper;
import cn.wisdsoft.pojo.TermEntity;
import cn.wisdsoft.service.TermService;
import cn.wisdsoft.util.ConstCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/1 21:22
 * @Description: 创建学期
 */
@Component
@EnableScheduling
public class CreateSemester implements SchedulingConfigurer {
    private static int flag = 1;
    private static String TermName = "";
    private static String cron = "";
    private Date firstTerm;
    private Date secondTerm;
    private Date endTime;
    private String termbegin;
    private Date currettime;

    private final TermService termService;
    private final TermMapper termMapper;


    public CreateSemester(TermService termService, TermMapper termMapper) {
        this.termService = termService;
        this.termMapper = termMapper;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        TermEntity term = new TermEntity();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            currettime = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            termbegin = ChinaDate.lunarToSolar(year + "-01-05", false);
            secondTerm = simpleDateFormat.parse(termbegin);
            firstTerm = simpleDateFormat.parse(year + "-07-15");
            String solar = ChinaDate.lunarToSolar(year + "-12-25", false);
            endTime = simpleDateFormat.parse(solar);
        } catch (Exception ignored) {
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                //任务逻辑代码部分.
                if (flag == 2) {
                    //关闭学期
                    termService.breakTerm(ConstCommon.SEMESTER_STATUS.semester_ends.getCode());
                    TermName = year + "-" + (year + 1) + "学年第二学期";
                    term.setTermName(TermName);
                    termService.insertTerm(term);
                    ConstCommon.Term_Id = term.getTermId();
                } else if (flag == 1) {
                    //关闭学期
                    termService.breakTerm(ConstCommon.SEMESTER_STATUS.semester_ends.getCode());
                    TermName = year + "-" + (year + 1) + "学年第一学期";
                    term.setTermName(TermName);
                    termService.insertTerm(term);
                    ConstCommon.Term_Id = term.getTermId();
                }
            }
        };
        Trigger trigger = new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                //每一次任务触发，执行这里的方法一次，并重新获取下一次的执行时间
                try {
                    if (flag == 1) {
                        cron = ChinaDate.cron(termbegin);
                        //在规定时间内没有学期
                        if (currettime.compareTo(secondTerm) > 0 && currettime.compareTo(firstTerm) < 0) {
                            String temp = year + "-" + (year + 1) + "学年第二学期";
                            TermEntity termEntity = termMapper.selectTermNameByName(temp);
                            if (termEntity==null) {
                                TermEntity termEntity1 = new TermEntity();
                                termEntity1.setTermName(temp).setTermStatus(ConstCommon.SEMESTER_STATUS.config_teacher.getCode());
                                termMapper.insertSelective(termEntity1);
                            }
                        }
                        flag = 2;
                    } else if (flag == 2) {
                        String currenttime = year + "-07-15";
                        cron = ChinaDate.cron(currenttime);
                        //在规定时间内没有学期
                        if (currettime.compareTo(firstTerm) > 0 && currettime.compareTo(endTime) < 0) {
                            String temp = year + "-" + (year + 1) + "学年第一学期";
                            TermEntity termEntity = termMapper.selectTermNameByName(temp);
                            if (termEntity == null) {
                                TermEntity termEntity1 = new TermEntity();
                                termEntity1.setTermName(temp).setTermStatus(ConstCommon.SEMESTER_STATUS.config_teacher.getCode());
                                termMapper.insertSelective(termEntity1);
                            }
                        }
                        flag = 1;
                        cron = "* * 0/1 * * *";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CronTrigger trigger = new CronTrigger(cron);
                return trigger.nextExecutionTime(triggerContext);
            }
        };
        scheduledTaskRegistrar.addTriggerTask(task, trigger);
    }
}
