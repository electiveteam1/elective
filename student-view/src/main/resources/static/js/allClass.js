import {Tools} from "./util/tools.js";
import {Common} from "./util/common.js";
$(".header").load("header.html");
let tool = new Tools();
let common = new Common();
let userInfoString = sessionStorage.getItem("user");
let userInfo = JSON.parse(userInfoString);

let category = common.getCategory();
let capacity = common.getCapacity();
let weeks = common.getWeeks();
let times = common.getTimes();

let categoryText = `<span class="tabTitle">课程类别</span><br>
                    <div class="tabContent">`;
let capacityText = `<span class="tabTitle">有无容量</span><br>
                    <div class="tabContent">`;
let weeksText = `<span class="tabTitle">选择星期</span><br>
                    <div class="tabContent">`;
let timesText = `<span class="tabTitle">上课时间</span><br>
                    <div class="tabContent">`;
category.forEach(item => {
    categoryText += `<label>
                            <input type="radio" name="category" value="${item.key}" ${item.value === "全部" ? "checked" : ''}>${item.value}
                        </label>`;
});
capacity.forEach(Item => {
    capacityText += `<label>
                            <input type="radio" name="capacity" value="${Item.key}" ${Item.value === '全部' ? "checked" : ''}>${Item.value}
                        </label>`;
});
weeks.forEach(item => {
    weeksText += `<label>
                            <input type="radio" name="week" value="${item.key}" ${item.value === '全部' ? "checked" : ''}>${item.value}
                        </label>`;
});
times.forEach(item => {
    timesText += `<label>
                            <input type="radio" name="times" value="${item.key}" ${item.value === '全部' ? "checked" : ''}>${item.value}
                        </label>`;
});
categoryText += `</div>`;
capacityText += `</div>`;
weeksText += `</div>`;
timesText += `</div>`;
$(".mycategory").append(categoryText);
$(".mycapacity").append(capacityText);
$(".myweek").append(weeksText);
$(".mytimes").append(timesText);
let myData = {
    token: userInfo.token,
    nature: $("input[name='category']:checked").val(),
    volume: $("input[name='capacity']:checked").val(),
    locations: $("input[name='times']:checked").val(),
    time: $("input[name='week']:checked").val(),
    page: 1,
    limit: 3
};
//分页
show(myData).then(res => {
    $("#page").paging({
        pageNum: 1, // 当前页面
        totalNum: res.count === 0 ? 0 : Math.ceil(res.count/3), // 总页码
        totalList: res.count, // 记录总数量
        callback: function(num) { //回调函数
            let data = {
                token: userInfo.token,
                nature: $("input[name='category']:checked").val(),
                volume: $("input[name='capacity']:checked").val(),
                locations: $("input[name='times']:checked").val(),
                time: $("input[name='week']:checked").val(),
                page: num,
                limit: 3
            };
            show(data).then(res => {
                let content = ``;
                let data = res.data;
                if (res.status === 200) {
                    data.forEach(item => {
                        let json = JSON.parse(item.timeAndPlace);
                        content += `<div class="list1">
                                <img src="${item.remark === "" ? 'img/default.jpg' : item.remark}"/>
                                <div class="intro">
                                    <h3>${item.courseLibraryName}</h3>
                                    <ul>
                                        <li>任课教师：${item.teacherName}</li>
                                        <li>学时：${item.classHour} &nbsp;&nbsp;&nbsp;&nbsp;学分：${item.credit}</li>
                                        <li>容量：${item.currentNumber}/${item.maxNumber}</li>
                                        <li>上课时间和地点： ${json[0].week + "-" + json[0].lou + "-" + json[0].room + "-" + json[0].times}</li>
                                        <li style="color: red;">更多时间和地点请查看详情</li>
                                    </ul>

                                </div>
                                <div class="xuan" style="display:${res.optionFlag === true ? "block" : "none"}" >
                                    <a><img src="img/xuanze (1).png"/>
                                        <span>选择课程</span>
                                    </a>
                                </div>
                                <div class="xuan">
                                    <a href="detaile.html?tid=${item.teacherId}&courseId=${item.electiveCourseId}"><img src="img/xiangqing.png"/>
                                        <span>详情</span>
                                    </a>
                                </div>
                            </div>`;
                    });
                    $(".list").empty();
                    $(".list").append(content);
                }
            })
        }
    });
    let cont = ``;
    let data = res.data;
    if (res.status === 200) {
        data.forEach(item => {
            let json = JSON.parse(item.timeAndPlace);
            cont += `<div class="list1">
                                <img src="${item.remark === "" ? 'img/default.jpg' : item.remark}"/>
                                <div class="intro">
                                    <h3>${item.courseLibraryName}</h3>
                                    <ul>
                                        <li>任课教师：${item.teacherName}</li>
                                        <li>学时：${item.classHour} &nbsp;&nbsp;&nbsp;&nbsp;学分：${item.credit}</li>
                                        <li>容量：${item.currentNumber}/${item.maxNumber}</li>
                                        <li>上课时间和地点： ${json[0].week + "-" + json[0].lou + "-" + json[0].room + "-" + json[0].times}</li>
                                        <li style="color: red;">更多时间和地点请查看详情</li>
                                    </ul>

                                </div>
                                <div class="xuan" style="display:${res.optionFlag === true ? "block" : "none"}">
                                    <a><img src="img/xuanze (1).png"/>
                                        <span>选择课程</span>
                                    </a>
                                </div>
                                <div class="xuan">
                                    <a href="detaile.html?tid=${item.teacherId}&courseId=${item.electiveCourseId}"><img src="img/xiangqing.png"/>
                                        <span>详情</span>
                                    </a>
                                </div>
                            </div>`;
        });
        $(".list").empty();
        $(".list").append(cont);
    }
});

$(".filter").click(function () {
    let myData = {
        token: userInfo.token,
        nature: $("input[name='category']:checked").val(),
        volume: $("input[name='capacity']:checked").val(),
        locations: $("input[name='times']:checked").val(),
        time: $("input[name='week']:checked").val(),
        page: 1,
        limit: 3
    };
    show(myData).then(res => {
        $("#page").paging({
            pageNum: 1, // 当前页面
            totalNum: res.count === 0 ? 0 : Math.ceil(res.count/3), // 总页码
            totalList: res.count, // 记录总数量
            callback: function(num) { //回调函数
                console.log(num);
                let data = {
                    token: userInfo.token,
                    nature: $("input[name='category']:checked").val(),
                    volume: $("input[name='capacity']:checked").val(),
                    locations: $("input[name='times']:checked").val(),
                    time: $("input[name='week']:checked").val(),
                    page: num,
                    limit: 3
                };
                show(data).then(res => {
                    let content = ``;
                    let data = res.data;
                    if (res.status === 200) {
                        data.forEach(item => {
                            let json = JSON.parse(item.timeAndPlace);
                            content += `<div class="list1">
                                <img src="${item.remark === "" ? 'img/default.jpg' : item.remark}"/>
                                <div class="intro">
                                    <h3>${item.courseLibraryName}</h3>
                                    <ul>
                                        <li>任课教师：${item.teacherName}</li>
                                        <li>学时：${item.classHour} &nbsp;&nbsp;&nbsp;&nbsp;学分：${item.credit}</li>
                                        <li>容量：${item.currentNumber}/${item.maxNumber}</li>
                                        <li>上课时间和地点： ${json[0].week + "-" + json[0].lou + "-" + json[0].room + "-" + json[0].times}</li>
                                        <li style="color: red;">更多时间和地点请查看详情</li>
                                    </ul>

                                </div>
                                <div class="xuan" style="display:${res.optionFlag === true ? "block" : "none"}" >
                                    <a><img src="img/xuanze (1).png"/>
                                        <span>选择课程</span>
                                    </a>
                                </div>
                                <div class="xuan">
                                    <a href="detaile.html?tid=${item.teacherId}&courseId=${item.electiveCourseId}"><img src="img/xiangqing.png"/>
                                        <span>详情</span>
                                    </a>
                                </div>
                            </div>`;
                        });
                        $(".list").empty();
                        $(".list").append(content);
                    }
                })
            }
        });
        let cont = ``;
        let data = res.data;
        if (res.status === 200) {
            data.forEach(item => {
                let json = JSON.parse(item.timeAndPlace);
                cont += `<div class="list1">
                                <img src="${item.remark === "" ? 'img/default.jpg' : item.remark}"/>
                                <div class="intro">
                                    <h3>${item.courseLibraryName}</h3>
                                    <ul>
                                        <li>任课教师：${item.teacherName}</li>
                                        <li>学时：${item.classHour} &nbsp;&nbsp;&nbsp;&nbsp;学分：${item.credit}</li>
                                        <li>容量：${item.currentNumber}/${item.maxNumber}</li>
                                        <li>上课时间和地点： ${json[0].week + "-" + json[0].lou + "-" + json[0].room + "-" + json[0].times}</li>
                                        <li style="color: red;">更多时间和地点请查看详情</li>
                                    </ul>

                                </div>
                                <div class="xuan" style="display:${res.optionFlag === true ? "block" : "none"}">
                                    <a><img src="img/xuanze (1).png"/>
                                        <span>选择课程</span>
                                    </a>
                                </div>
                                <div class="xuan">
                                    <a href="detaile.html?tid=${item.teacherId}&courseId=${item.electiveCourseId}"><img src="img/xiangqing.png"/>
                                        <span>详情</span>
                                    </a>
                                </div>
                            </div>`;
            });
            $(".list").empty();
            $(".list").append(cont);
        }
    });
});
function show(data) {
    return tool.get("/student/view/allcourse",data,false);
}
//分页