<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib prefix="p" uri="http://yanzhenwei.com/common/" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Y先生教育</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/confirm.js"></script>
    <script type="text/javascript">
        function showAddPage() {
            location.href = "${pageContext.request.contextPath}/afterSpeaker/toAddOrEdit";
        }

        function delSpeakerById(Obj, id, name) {

            Confirm.show('温馨提示：', '确定要删除' + name + '么？', {
                'Delete': {
                    'primary': true,
                    'callback': function () {
                        var param = {"id": id};
                        $.post("${pageContext.request.contextPath}/afterSpeaker/delete", param, function (data) {
                            if (data == 'success') {
                                Confirm.show('温馨提示：', '删除成功');
                                $(Obj).parent().parent().remove();
                            } else {
                                Confirm.show('温馨提示：', '操作失败');
                            }
                        });
                    }
                }
            });
        }
    </script>
    <style type="text/css">
        th {
            text-align: center;
        }
    </style>
</head>

<body>

<jsp:include page="afterHeader.jsp"></jsp:include>

<div class="jumbotron" style="padding-top: 15px;padding-bottom: 15px;">
    <div class="container">
        <h2>主讲人管理</h2>
    </div>
</div>


<div class="container">


    <button onclick="showAddPage()" type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown"
            aria-haspopup="true" aria-expanded="false">
        添加
    </button>

</div>

<div class="container" style="margin-top: 20px;">

    <table class="table table-bordered table-hover"
           style="text-align: center;table-layout:fixed;">
        <thead>
        <tr class="active">
            <th>序号</th>
            <th>名称</th>
            <th>职位</th>
            <th style="width:60%;">简介</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="speaker" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${speaker.speakerName}</td>

                <td>${speaker.speakerJob}</td>
                <td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${speaker.speakerDesc}</td>
                <td><a href="${pageContext.request.contextPath}/afterSpeaker/toAddOrEdit?id=${speaker.id}"><span
                        class="glyphicon glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
                <td><a href="#" onclick="return delSpeakerById(this,'${speaker.id}','${speaker.speakerName}')"><span
                        class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>
            </tr>

        </c:forEach>


        </tbody>
    </table>
</div>
<div class="container">
    <div class="navbar-right" style="padding-right: 17px">
       <%-- <p:page url="${pageContext.request.contextPath}/speaker/showSpeakerList"></p:page>--%>

           <nav aria-label="Page navigation">
               <ul class="pagination">
                   <c:if test="${page.pageNum<=1}">
                       <li class="disabled">
                           <a href="#" aria-label="Previous" >
                               <span aria-hidden="true">&laquo;</span>
                           </a>
                       </li>
                   </c:if>
                   <c:if test="${page.pageNum>1}">
                       <li>
                           <a href="${pageContext.request.contextPath}/afterSpeaker/fuzzy?pageNum=${page.prePage}" aria-label="Previous" >
                               <span aria-hidden="true">&laquo;</span>
                           </a>
                       </li>
                   </c:if>

                   <c:forEach begin="1" end="${page.pages}" varStatus="i">
                       <c:if test="${page.pageNum==i.count}">
                           <li class="active"><a href="#" >${i.count}</a></li>
                       </c:if>
                       <c:if test="${page.pageNum!=i.count}">
                           <li><a href="${pageContext.request.contextPath}/afterSpeaker/fuzzy?pageNum=${i.count}">${i.count}</a></li>
                       </c:if>

                   </c:forEach>

                   <c:if test="${page.pageNum>=page.pages}">
                       <li class="disabled">
                           <a href="#" aria-label="Next">
                               <span aria-hidden="true">&raquo;</span>
                           </a>
                       </li>
                   </c:if>
                   <c:if test="${page.pageNum<page.pages}">
                       <li>
                           <a href="${pageContext.request.contextPath}/afterSpeaker/fuzzy?pageNum=${page.nextPage}" aria-label="Next" >
                               <span aria-hidden="true">&raquo;</span>
                           </a>
                       </li>
                   </c:if>

               </ul>
           </nav>
    </div>
</div>
</body>

</html>