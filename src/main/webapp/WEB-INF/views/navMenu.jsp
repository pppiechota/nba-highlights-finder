<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
        <a class="navbar-brand" href='<spring:url value="/"/>'>
            nba spoiler free highlights for hard working polish people
        </a>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href='<spring:url value="/calendar"/>'>Pick a date</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href='<spring:url value="/teams"/>'>Pick a team</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href='<spring:url value="/about"/>'>About</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
