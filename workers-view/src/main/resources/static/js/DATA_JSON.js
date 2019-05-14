class Common {
    constructor() {
        this.weeks = [
            {key: "星期一", value: "星期一"},
            {key: "星期二", value: "星期二"},
            {key: "星期三", value: "星期三"},
            {key: "星期四", value: "星期四"},
            {key: "星期五", value: "星期五"},
            {key: "星期六", value: "星期六"},
            {key: "星期日", value: "星期日"}
        ];
        this.times = [
            {key: "第一节", value: "第一节"},
            {key: "第二节", value: "第二节"},
            {key: "第三节", value: "第三节"},
            {key: "第四节", value: "第四节"},
            {key: "第五节", value: "第五节"},
            {key: "第六节", value: "第六节"},
            {key: "第七节", value: "第七节"},
            {key: "第八节", value: "第八节"},
            {key: "第九节", value: "第九节"},
            {key: "第十节", value: "第十节"},
            {key: "十一节", value: "十一节"},
            {key: "十二节", value: "十二节"}
        ];
        this.restrictedGrade = [
            {key: "大一", value: "大一"},
            {key: "大二", value: "大二"},
            {key: "大三", value: "大三"},
            {key: "大四", value: "大四"},
        ];
        this.collegeName=[
            {key: "经济管理学院", value: "经济管理学院"},
            {key: "软件学院", value: "软件学院"},
            {key: "土木工程学院", value: "土木工程学院"},
            {key: "建筑学院", value: "建筑学院"},
            {key: "人文学院", value: "人文学院"},
            {key: "信息技术学院", value: "信息技术学院"},
            {key: "会计学院", value: "会计学院"},
            {key: "工程管理学院", value: "工程管理学院"},
            {key: "校选", value: "校选"},
        ];
    }

    getWeeks() {
        return this.weeks;
    };

    getTimes() {
        return this.times;
    }

    getRestrictedGrade(){
        return this.restrictedGrade;
    }

    getCollegeName(){
        return this.collegeName;
    }
}

export {Common}