<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section id="gamesList">
    <div class="static-top">
        <div class="container">
            <div class="scrollmenu">
                <c:forEach items="${schedule}" var="game" varStatus="gamelist">
                    <a href='<spring:url value="/game?id=${gamelist.index}"/>'>
                            ${game.homeTeam} vs ${game.visitorTeam}
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
</body>
</html>
