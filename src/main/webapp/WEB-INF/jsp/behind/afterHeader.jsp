<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2021/3/6
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar-inverse">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

            <a class="navbar-brand" href="${pageContext.request.contextPath}/video/list">视频管理系统</a>
        </div>

        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-9">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/afterVideo/fuzzy">视频管理</a></li>
                <li><a href="${pageContext.request.contextPath}/afterSpeaker/fuzzy">主讲人管理</a></li>
                <li><a href="${pageContext.request.contextPath}/showCourseList">课程管理</a></li>


            </ul>
             <p class="navbar-text navbar-right">
                 <span>${sessionScope.admin.username}</span> <i class="glyphicon glyphicon-log-in"
                                                          aria-hidden="true"></i>&nbsp;&nbsp;<a
                     href="${pageContext.request.contextPath}/afterAdmin/loginOut"
                     class="navbar-link">退出</a>
             </p>
        </div>
        <!-- /.navbar-collapse -->


    </div>
    <!-- /.container-fluid -->
</nav>
</body>
</html>
