<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>NSFHFHWPP</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Piotr Piechota">
    <link href='<spring:url value="/vendor/bootstrap/css/bootstrap.css"/>' rel="stylesheet"/>
    <link href='<spring:url value="/css/additional-style.css"/>' rel="stylesheet"/>
</head>
<body>
<jsp:include page="navMenu.jsp"/>
<section id="gamesList">
    <div class="navbar navbar-expand-lg navbar-dark static-top">
        <div class="container">
            <div class="scrollmenu">
                <c:forEach items="${schedule}" var="game" varStatus="gamelist">
                    <a href='<spring:url value="/game?id=${gamelist.index}"/>'>${game.homeTeam}
                        vs ${game.visitorTeam}</a>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1 class="mt-5">Highlights for ${team.fullName}</h1>
            <ul style="list-style-type:none;">
                <c:forEach items="${teamGamesList}" var="game">
                    <li><a href='<spring:url value="/teams/game?id=${team.id}"/>'>
                            ${game.homeTeam} vs ${game.visitorTeam} (${game.date})
                    </a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<script src="vendor/jquery/jquery.slim.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>