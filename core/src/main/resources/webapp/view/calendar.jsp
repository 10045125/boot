<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Pulpo</title>
    <%@ include file="_css.jsp"%>
    <link rel="stylesheet" href="${contextPath}/dist/css/calendar.css" />
</head>
<body>
<%@ include file="_header.jsp" %>
<div class="container">
    <div class="row-fluid">
        <div class="col-md-2">
            <div class="content">
                <ul>
                    <li>
                        <div>
                            <span>開発1課</span>
                            <ul>
                                <li>A Team</li>
                                <li>B Team</li>
                                <li>C Team</li>
                                <li>D Team</li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <div>
                            <span>開発2課</span>
                            <ul>
                                <li>A Team</li>
                                <li>B Team</li>
                                <li>C Team</li>
                                <li>D Team</li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <div>
                            <span>開発3課</span>
                            <ul>
                                <li>A Team</li>
                                <li>B Team</li>
                                <li>C Team</li>
                                <li>D Team</li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
            <div id="report"></div>
        </div>
        <div class="col-md-10">
            <div class="content" id="calendar_content"></div>
        </div>
    </div>
</div>
<%@ include file="_footer.jsp" %>
<%@ include file="_js.jsp"%>
<script src="${contextPath}/dist/calendar.js"></script>
<script>
    new Calendar().showTable(document.getElementById('calendar_content'))
    $.ajax('/timeCard/report',{
        success : function(data, status, xhr){
            var attendances = data.attendanceList
            $.each(attendances, function(){
                var onWork = new Date(this.onWork);
                var attendNode = $('<span>').text(this.account.name + "/" + this.workingHours);
                $("td[year='" + onWork.getFullYear() + "']" + "[month='"+ (onWork.getMonth() + 1) + "'][date='" + onWork.getDate() + "']").append(attendNode);
                console.log(new Date(this.onWork));
            });
        }
    });
</script>
</body>
</html>
