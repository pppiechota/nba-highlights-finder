<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Schedule</title>
</head>
<body>
<h3>Last night's schedule</h3>
<c:forEach items="${schedule}" var="game">
    ${game.homeTeam} vs ${game.visitorTeam} (${game.date}) <br/>
</c:forEach>
</body>
</html>
