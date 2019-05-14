$("#logout").click(function () {
    sessionStorage.clear();
    location.href = "login.html";
});