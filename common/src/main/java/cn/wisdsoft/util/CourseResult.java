package cn.wisdsoft.util;

import java.util.List;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-15 12:36
 * @ Description：
 */
public class CourseResult<T> {
    private Integer status;
    private long count;
    private String msg;
    private boolean optionFlag;
    private List<T> data;

    public CourseResult(){
        this.status = 200;
        this.count = 0;
        this.msg = "OK";
        this.data = null;
    }

    public CourseResult(long count,boolean optionFlag,List<T> list){
        this.status = 200;
        this.count = count;
        this.msg = "OK";
        this.data = list;
        this.optionFlag = optionFlag;
    }

    public CourseResult(Integer status,String msg){
        this.status = status;
        this.msg = msg;
        this.count = 0;
        this.data = null;
    }

    public static <T> CourseResult<T> ok(long count,boolean optionFlag,List<T> list){
        return new CourseResult<>(count,optionFlag,list);
    }

    public static <T> CourseResult<T> build(Integer status,String msg){
        return new CourseResult<>(status,msg);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isOptionFlag() {
        return optionFlag;
    }

    public void setOptionFlag(boolean optionFlag) {
        this.optionFlag = optionFlag;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> list) {
        this.data = list;
    }
}
