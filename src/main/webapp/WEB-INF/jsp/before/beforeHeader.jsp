<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">

</head>

<body>
<div class="container">
    <span>欢迎来到Y先生教育！</span>

    <!-- 此处这个标志用于修复一个小Bug -->
    <input type="hidden" id="isLogin" value="0">

        <div id="regBlock" style="display:none;float:right">
            <a href="javascript:;" id="reg_open"><img src="${pageContext.request.contextPath}/img/we.png">注册</a>
            <a href="javascript:;" id="login_open"><img src="${pageContext.request.contextPath}/img/we.png">登录</a>
        </div>



        <div id="userBlock" style="display:none;float:right">

            <a href="javascript:;" id="loginout">退出</a>
            <a href="${pageContext.request.contextPath}/user/showMyProfile" id="account">${sessionScope.user.email}</a>
        </div>



    <a onclick="JavaScript:addFavorite2()"><img src="${pageContext.request.contextPath}/img/sc.png"
                                                draggable="false">加入收藏</a>
    <a onclick="pyRegisterCvt()" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2580094677&site=qq&menu=yes"><img
            src="${pageContext.request.contextPath}/img/we.png" draggable="false">联系我们</a>
    <a class="color_e4"><img src="${pageContext.request.contextPath}/img/phone.png" draggable="false"> 0375-2089092&#x3000;&#x3000;0375-208-9051</a>

</div>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath}/js/gVerify.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
<script>



    function addFavorite2() {
        var url = window.location;
        var title = document.title;
        var ua = navigator.userAgent.toLowerCase();
        if (ua.indexOf("360se") > -1) {
            alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
        } else if (ua.indexOf("msie 8") > -1) {
            window.external.AddToFavoritesBar(url, title); //IE8
        } else if (document.all) {
            try {
                window.external.addFavorite(url, title);
            } catch (e) {
                alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
            }
        } else if (window.sidebar) {
            window.sidebar.addPanel(title, url, "");
        } else {
            alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
        }
    }
</script>


</body>
</html>
