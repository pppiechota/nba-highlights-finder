<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Schedule</title>
    <link href='<spring:url value="/css/additional-style.css"/>' rel="stylesheet" />
</head>
<body>
<div class="scrollmenu">
    <c:forEach items="${schedule}" var="game" varStatus="gamelist">
        <a href='<spring:url value="/schedule/game?gameId=${gamelist.index}"/>'>${game.homeTeam} vs ${game.visitorTeam}</a>
    </c:forEach>
</div>

<%--<h3>Last night's schedule</h3>--%>
<%--<c:forEach items="${schedule}" var="game">--%>
<%--    ${game.homeTeam} vs ${game.visitorTeam} (${game.date}) <br/>--%>
<%--</c:forEach>--%>
</body>
</html>
