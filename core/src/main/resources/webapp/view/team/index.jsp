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
        <div class="col-md-2">
            <dl>
                <c:forEach items="${list}" var="team" varStatus="i">
                    <dt>name</dt>
                    <dd>${team.name}</dd>
                    <dt>members</dt>
                    <dd>${team.memberNames}</dd>
                </c:forEach>
            </dl>
        </div>
        <div class="col-md-8">
            <h3>Joined Teams</h3>
            <div class="table-bordered">
            <dl>
                <dt>name</dt>
                <dd></dd>
                <dt>members</dt>
                <dd></dd>
            </dl>
                <div>
                    <label for="message">message for members</label>
                    <textarea class="form-control" rows="2" name="message" id="message"></textarea>
                </div>
                <div>
                    <h4>Add Members</h4>
                    <div>${joined[0].memberNames}</div>
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <h3>Team List</h3>
            <c:forEach items="${teams}" var="team" varStatus="i">
                <div>
                    <dl>
                        <dt>name</dt>
                        <dd>${team.name}</dd>
                        <dt>members</dt>
                        <dd>${team.memberNames}</dd>
                    </dl>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@ include file="../_footer.jsp" %>
<%@ include file="../_js.jsp" %>
</body>
</html>