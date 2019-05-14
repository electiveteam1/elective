package cn.wisdsoft.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.Date;

/**
 * @ Author     ：高伟萌.
 * @ Date       ：Created in 2019-04-25 14:45
 * @ Description：
 */
public class InformationVo implements Serializable {
    //消息ID
    private Long informationId;
    //时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;
    //消息
    private String tip;

    public InformationVo(Long informationId, Date time, String tip) {
        this.informationId = informationId;
        this.time = time;
        this.tip = tip;
    }

    public Date getTime() {
        return time;
    }

    public InformationVo setTime(Date time) {
        this.time = time;
        return this;
    }

    public String getTip() {
        return tip;
    }

    public InformationVo setTip(String tip) {
        this.tip = tip;
        return this;
    }

    public Long getInformationId() {
        return informationId;
    }

    public InformationVo setInformationId(Long informationId) {
        this.informationId = informationId;
        return this;
    }
}
