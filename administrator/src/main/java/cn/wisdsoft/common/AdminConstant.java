package cn.wisdsoft.common;

/**
* @Description:    静态变量
* @Author:         SongJunWei
* @CreateDate:     2019/4/24 8:15
* @UpdateUser:     
* @UpdateDate:     2019/4/24 8:15
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class AdminConstant {
    public static  String BeginTime="BEGIN_TIME";
    public static String beginTime = "";
    public static String EndTime = "";
    public static String semesterEnd = "";

    public static String CollegeName="校选";

    public enum deleteFlag {
        /**1删除*/
        cut_off(1),
        /**0未删除*/
        undelete(0);
        private final Integer code;
        deleteFlag(Integer code){
            this.code=code;
        }
        public Integer getCode() {
            return code;
        }
    }

    /**
     * 是否发布（0：不允许修改，1：允许修改）',
     */
    public enum allowFlag {
        /**1：允许修改*/
        allow(1),
        /**0：不允许修改*/
        not_allow(0);
        private final Integer code;
        allowFlag(Integer code){
            this.code=code;
        }
        public Integer getCode() {
            return code;
        }
    }
}
