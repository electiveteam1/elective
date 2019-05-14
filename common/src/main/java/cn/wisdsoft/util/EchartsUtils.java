package cn.wisdsoft.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: SongJunWei
 * @Date: 2019/4/28 09:38
 * @Description:
 */
public class EchartsUtils implements Serializable {

    //横坐标数据
    private String xAxisData;
    //顶部显示（例如多柱形Echarts图）
    private String legendDate;
    //图表数据名称（例如多柱形Echarts图）
    private String seriesName;
    //图表数据
    private String seriesData;

    public String getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(String xAxisData) {
        this.xAxisData = xAxisData;
    }

    public String getLegendDate() {
        return legendDate;
    }

    public void setLegendDate(String legendDate) {
        this.legendDate = legendDate;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(String seriesData) {
        this.seriesData = seriesData;
    }

//    public List<String> XAxisData(){
//        String[] getxAxisData = this.getxAxisData().split(",");
//        List<String> strings = Arrays.asList(getxAxisData);
//        return strings;
//    }
//    public List<String> SeriesData(){
//        String[] split = this.getSeriesData().split(",");
//        List<String> strings = Arrays.asList(split);
//        return strings;
//    }

}
