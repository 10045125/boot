<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="ja">
    <meta charset="UTF-8">
    <title>Pulpo Projects</title>
    <%@include file="../_css.jsp"%>
</head>
<body>
<%@ include file="../_header.jsp" %>
<div class="container">
    <div class="row-fluid">
        <div class="col-md-2"></div>
        <div class="col-md-10">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>name</th>
                    <th>description</th>
                    <th>customer</th>
                    <th>phase</th>
                    <th>start</th>
                    <th>end</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="prj" items="${projects}">
                    <tr>
                        <td><a href="/project/show?id=${prj.id}" rel="contents">${prj.name}</a></td>
                        <td>${prj.description}</td>
                        <td>${prj.customer}</td>
                        <td>${prj.phase}</td>
                        <td>${prj.start}</td>
                        <td>${prj.end}</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <th>name</th>
                    <th>description</th>
                    <th>customer</th>
                    <th>phase</th>
                    <th>start</th>
                    <th>end</th>
                </tr>
                </tfoot>
            </table>

        </div>
    </div>
</div>
<%@ include file="../_footer.jsp" %>
</body>
</html>