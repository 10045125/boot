<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav role="navigation" class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="navbar-brand">&lt;&nbsp;PULPO&nbsp;/&gt;</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home</a></li>
                <li><a href="${contextPath}/mypage">My Page</a></li>
                <li><a href="/team">Team</a></li>
                <li><a href="/projects">Projects</a></li>
                <li><a href="/timeCard">TimeCard</a></li>
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">View <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-header">Nav header</li>
                        <li><a href="/?view=Timeline">Time Line</a></li>
                        <li><a href="/calendar" id="calendar">Calendar</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Default</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%--<li><a href="../navbar/">Time Line</a></li>--%>
                <form action="/work/attend" method="post" id="attend_form"></form>
                <li title="Report Bag"><a href="./">Report Bag</a></li>
                <li><a href="../navbar-fixed-top/">Logout</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
