<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <%@include file="_css.jsp"%>
</head>
<body>
<%@ include file="_header.jsp" %>
<div class="container">
    <div class="row-fluid">
        <div class="col-md-2">
            <div class="content">${e.name}</div>
        </div>
        <div class="col-md-8">
            <div class="content">content2</div>
        </div>
        <div class="col-md-2">
            <div class="content">content2</div>
        </div>
    </div>
</div>
<%@include file="_footer.jsp"%>
<%@include file="_js.jsp"%>
</body>
</html>