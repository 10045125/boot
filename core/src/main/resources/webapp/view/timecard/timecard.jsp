<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html>
<head lang="ja">
    <meta charset="UTF-8">
    <title>Pulpo TimeCard</title>
    <%@include file="../_css.jsp"%>
</head>
<body>
<%@ include file="../_header.jsp" %>
<div class="container">
    <div class="row-fluid">
        <div class="col-md-2"></div>
        <div class="col-md-10">
            <div>
                <div class="row-fluid">
                    <div class="col-md-7">
                        <h2>${user.name}</h2>
                    </div>
                    <div class="col-md-5 text-right">
                        <c:if test="${onWork.editable}">
                            <form role="form" action="${contextPath}/timeCard/${onWork.state.method}" method="POST">
                                <input type="submit" value="${onWork.state.method}" class="btn btn-lg btn-primary" />
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>begin work</th>
                    <th>end work</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="timeCard" varStatus="i">
                    <tr>
                        <td>${timeCard.onWork}</td>
                        <td>${timeCard.leave}</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <th>begin work</th>
                    <th>end work</th>
                </tr>
                </tfoot>
            </table>

        </div>
    </div>
</div>
<%@ include file="../_footer.jsp" %>
<%@include file="../_js.jsp"%>
</body>
</html>