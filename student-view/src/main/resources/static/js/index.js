import {Tools} from './util/tools.js';
let tool = new Tools();

const colors = ["#40b88b","#4a8bc4","#915fb9"];

let getCourse = function (electiveCourseId) {
    alert(electiveCourseId);
};

$(".header").load("header.html");
let userInfoString = sessionStorage.getItem("user");
let userInfo = JSON.parse(userInfoString);
let allCourseData = {
    token: userInfo.token
};
//获取课程总数
tool.get("/student/view/statistic/course", {},false)
    .then(res => {
        $("#allNum").text(res.data + "门");
    }, (res => {
        console.log(res);
    }));
//获取可选课程数量
tool.get("/student/view/statistic/select", allCourseData,false)
    .then(res => {
        $("#selNum").text(res.data + "门");
    }, (res => {
        console.log(res);
    }));
//推荐课程
let topData = {
    token: userInfo.token,
    size: 4
};

tool.get("/student/view/top",topData,false).then(res => {
    let content = ``;
    if (res.status === 200) {
        res.data.forEach((item,index) => {
            console.log(item,0);
            content += `<div class="swiper-slide">
                    <div class="my" onclick="(function () {
                        location.href = 'detaile.html?tid=${item.teacherId}&courseId='+${item.electiveCourseId};
                    }())" style="background: ${colors[index%3]}"><span>${item.courseLibraryName}</span></div>
                </div>`;

        });
        $("#loop").prepend(content);
        var swiper = new Swiper('.swiper-container', {
            slidesPerView: 3,
            spaceBetween: 30,
            slidesPerGroup: 1,
            loop: true,
            loopFillGroupWithBlank: true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            speed: 1000,
            autoplay: {
                delay: 1500,//1秒切换一次
            },
        });
    } else {
        if(res.msg === "token失效！"){
            alert(res.msg);
            location.href = "login.html";
        }
    }

}, (res => {
    console.log(res);
}));