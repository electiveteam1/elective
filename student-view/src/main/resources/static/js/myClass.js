import {Tools} from "./util/tools.js";

let tool = new Tools();
let userInfoString = sessionStorage.getItem("user");
let userInfo = JSON.parse(userInfoString);

$(".header").load("header.html");
let myData = {
    token: userInfo.token,
    courseFlag: 'all'
};
show(myData).then(res => {
    console.log(res);
    let content = ``;
    $(".list").empty();
    if (res.data === null) {

    } else {
        res.data.forEach(item => {
            let json = JSON.parse(item.timeAndPlace);
            content += `<div class="list1">
                                <img src="${item.remark === "" ? 'img/default.jpg' : item.remark}"/>
                                <div class="intro">
                                    <h3>${item.courseLibraryName}</h3>
                                    <ul>
                                        <li>${item.teacherName}</li>
                                        <li>学时：${item.classHour} &nbsp;&nbsp;&nbsp;&nbsp;学分：${item.credit}</li>
                                        <li>上课时间和地点： ${json[0].week + "-" + json[0].lou + "-" + json[0].room + "-" + json[0].times}</li>
                                        <li style="color: red;">更多时间和地点请查看详情</li>
                                    </ul>

                                </div>
                                <div class="xuan tui" data-tui="${item.electiveCourseId}" style="display:${item.termStatus < 3 && item.termStatus >1 && item.courseFlag=== 'learning' ? "block" : "none"}">
                                    <a><img src="img/backClass.png"/>
                                        <span>退选课程</span>
                                    </a>
                                </div>
                                <div class="xuan">
                                    <a href="detaile.html?tid=${item.teacherId}&courseId=${item.electiveCourseId}&flag=true"><img src="img/xiangqing.png"/>
                                        <span>详情</span>
                                    </a>
                                </div>
                            </div>`;
        });
        $(".list").append(content);
    }
},res => {
    console.log(res);
});

//选项卡切换
$("#tab li").click(function () {
    $(this).siblings().removeClass("current");
    // console.log($(this).siblings().removeClass("current"));
    $(this).addClass("current");
    // console.log($(this).context.dataset.item);
    let myData = {
        token: userInfo.token,
        courseFlag: $(this).context.dataset.item
    };
    show(myData).then(res => {
        console.log(res);
        let content = ``;
        $(".list").empty();
        if (res.data === null) {

        } else {
            res.data.forEach(item => {
                let json = JSON.parse(item.timeAndPlace);
                content += `<div class="list1">
                                <img src="${item.remark === "" ? 'img/default.jpg' : item.remark}"/>
                                <div class="intro">
                                    <h3>${item.courseLibraryName}</h3>
                                    <ul>
                                        <li>${item.teacherName}</li>
                                        <li>学时：${item.classHour} &nbsp;&nbsp;&nbsp;&nbsp;学分：${item.credit}</li>
                                        <li>上课时间和地点： ${json[0].week + "-" + json[0].lou + "-" + json[0].room + "-" + json[0].times}</li>
                                        <li style="color: red;">更多时间和地点请查看详情</li>
                                    </ul>

                                </div>
                                <div class="xuan tui" data-tui="${item.electiveCourseId}" style="display:${item.termStatus < 3 && item.termStatus >1 && item.courseFlag=== 'learning' ? "block" : "none"}">
                                    <a><img src="img/backClass.png"/>
                                        <span>退选课程</span>
                                    </a>
                                </div>
                                <div class="xuan">
                                    <a href="detaile.html?tid=${item.teacherId}&courseId=${item.electiveCourseId}&flag=true"><img src="img/xiangqing.png"/>
                                        <span>详情</span>
                                    </a>
                                </div>
                            </div>`;
            });
            $(".list").append(content);
        }
    },res => {
        console.log(res);
    });

});
function show(data) {
    return tool.get("/student/view/mycourse",data,false);
}

$(document).on('click','.tui',function (e) {
    console.log(e.currentTarget.dataset.tui);
    let data = {
        token: userInfo.token,
        electiveCourseId: e.currentTarget.dataset.tui
    };
    tool.post("/student/view/dropcourse",data,true)
        .then(res => {
            if (res.status === 200) {
                alert("退课成功");
            } else {
                alert(res.msg);
            }
        },res => {
            console.log(res);
        })
})