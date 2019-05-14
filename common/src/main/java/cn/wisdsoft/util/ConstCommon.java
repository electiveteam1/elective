package cn.wisdsoft.util;

/**
 * @Auther: SongJunWei
 * @Date: 2019/3/28 09:50
 * @Description: 公共类
 */
public class ConstCommon {

    /**
     * 学期编号
     */
    public static Integer Term_Id=null;

    /***
     * 学期状态
     */
    public enum SEMESTER_STATUS {
        /**配置授课教师中*/
        config_teacher("1","config_teacher"),
        /**选课中*/
        course_selection("2","course_selection"),
        /**选课结束*/
        course_selection_end("3","course_selection_end"),
        /**学期结束*/
        semester_ends("4","semester_ends");
        private final String code;
        private final String desc;
        SEMESTER_STATUS(String code,String desc){
            this.code=code;
            this.desc=desc;
        }
        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum ELECTIVE_COURSE_STATUS {
        /**0可修改状态或打回状态*/
        ELECTIVE_COURSE_BLACK(0),
        /**1申请修改*/
        ELECTIVE_COURSE_STATIC(1),
        /**2已提交(成绩)*/
        ELECTIVE_COURSE_SUBMISSION(2);
        private final Integer code;
        ELECTIVE_COURSE_STATUS(Integer code){
            this.code=code;
        }
        public Integer getCode() {
            return code;
        }
    }


    public enum course_flag{
        /**学习中*/
        learning("learning"),
        /**已完成*/
        learned("learned");
        private final String code;
        course_flag(String code){
            this.code=code;
        }
        public String getCode() {
            return code;
        }
    }


}
