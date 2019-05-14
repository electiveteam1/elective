package cn.wisdsoft.tasks;

import java.time.LocalTime;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @Auther: SongJunWei
 * @Date: 2019/3/28 21:19
 * @Description: 学期规则定时器
 */


@Component
public class SemesterRule implements SchedulingConfigurer {

    public static String status = "1";
    private String cron = "0/1 * * * * *";
//    @Autowired
//    private AdministratorService administratorService;

    private ScheduledTaskRegistrar scheduledTaskRegistrar;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        scheduledTaskRegistrar = taskRegistrar;
        taskRegistrar.addTriggerTask(() -> {
            String termStatus = "";
            System.out.println( LocalTime.now()+cron);
//            if (status == "2") {
//                termStatus = ConstCommon.SEMESTER_STATUS.course_selection.getCode();
//                System.out.println("选课开始" + LocalDateTime.now());
//            } else if (status == "3") {
//                termStatus = ConstCommon.SEMESTER_STATUS.course_selection_end.getCode();
//                System.out.println("选课结束" + LocalDateTime.now());
//            } else if (status == "4") {
//                termStatus = ConstCommon.SEMESTER_STATUS.semester_ends.getCode();
//                System.out.println("学期结束" + LocalDateTime.now());
//                status = "1";
//            }
//            administratorService.updateTerm(termStatus);
        }, triggerContext -> {
            //每一次任务触发，执行这里的方法一次，并重新获取下一次的执行时间
            if (status=="1"){
                this.setCron("30 40 16 19 4 *");
                status="2";
            }else if(status=="2"){
                this.setCron("30 38 15 19 4 *");
                status="3";
            }
            CronTrigger trigger = new CronTrigger(this.getCron());
            return trigger.nextExecutionTime(triggerContext);
        });

        taskRegistrar.getCronTaskList().forEach(i ->{
            System.out.println(i.getExpression());
            System.out.println("1");
        });
    }
    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        System.out.println(String.format("%s Cron已修改！修改前：%s，修改后：%s @ %s", Thread.currentThread(), this.cron, cron, LocalTime.now()));
        this.cron = cron;
    }
}
