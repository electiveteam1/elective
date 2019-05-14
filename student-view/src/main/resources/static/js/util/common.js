class Common {
    constructor(){
        this.category = [{key:"all",value: "全部"},{key:"school",value: "校选"},{key:"college",value: "院选"},{key:"standby",value: "备选"}];
        this.capacity = [{key:"all",value: "全部"},{key:"have",value: "有"},{key:"nothave",value: "无"}];
        this.weeks = [
            {key:"all",value: "全部"},
            {key:"星期一",value: "星期一"},
            {key:"星期二",value: "星期二"},
            {key:"星期三",value: "星期三"},
            {key:"星期四",value: "星期四"},
            {key:"星期五",value: "星期五"},
            {key:"星期六",value: "星期六"},
            {key:"星期日",value: "星期日"}
            ];
        this.times = [
            {key:"all",value: "全部"},
            {key:"第一节",value: "第一节"},
            {key:"第二节",value: "第二节"},
            {key:"第三节",value: "第三节"},
            {key:"第四节",value: "第四节"},
            {key:"第五节",value: "第五节"},
            {key:"第六节",value: "第六节"},
            {key:"第七节",value: "第七节"},
            {key:"第八节",value: "第八节"},
            {key:"第九节",value: "第九节"},
            {key:"第十节",value: "第十节"},
            {key:"十一节",value: "十一节"},
            {key:"十二节",value: "十二节"}
            ];
    }

    getCategory(){
        return this.category;
    }
    getCapacity(){
        return this.capacity;
    }
    getWeeks(){
        return this.weeks;
    }
    getTimes(){
        return this.times;
    }
}
export {Common}