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
<jsp:include page="resultsScrollbar.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h2 class="mt-4">${game.homeTeam} - ${game.visitorTeam}</h2>
            <p>${game.date}</p>
            <div class="player">
                <div class="videoWrapper">
                    <iframe src="https://www.youtube.com/embed/${video.get(0)}" frameborder="0"
                            allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                </div>
            </div>
        </div>
    </div>
</div>

<script src='<spring:url value="/vendor/jquery/jquery.slim.min.js"/>'></script>
<script src='<spring:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
<script src='<spring:url value="/js/app.js"/>'></script>
</body>
</html>