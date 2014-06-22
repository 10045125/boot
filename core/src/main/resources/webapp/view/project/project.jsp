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
            <h3>${project.name}</h3>
            <dl class="dl-horizontal">
                <dt>description</dt>
                <dd>${project.description}</dd>
                <dt>customer</dt>
                <dd>${project.customer}</dd>
                <dt>phase</dt>
                <dd>${project.phase}</dd>
                <dt>start</dt>
                <dd>${project.start}</dd>
                <dt>end</dt>
                <dd>${project.end}</dd>
                <dt>members</dt>
                <dd>...members...</dd>
            </dl>
        </div>
    </div>
</div>
<%@ include file="../_footer.jsp" %>
<%@include file="../_js.jsp"%>
</body>
</html>