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
            <div class="about">
                <h1 class="mt-5">What is it?</h1>
            </div>
            <p class="lead">.</p>
            <p class="lead">.</p>
            <p class="lead">.</p>
            <p class="lead">League pass ain't cheap, but you can (almost) always count on the good people on youtube uploading game highlights as soon as they ends.
                This site will check the schedule and search YT to give you highlights from the game you want, without spoiling it.
                If the search doesn't seem to work properly or you have any ideas about improving the site, contact me at example@example.pl</p>
            <p>
                &copy; Piotr Piechota, 2020
            </p>
        </div>
    </div>
</div>

<script src='<spring:url value="/vendor/jquery/jquery.slim.min.js"/>'></script>
<script src='<spring:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
<script src='<spring:url value="/js/app.js"/>'></script>
</body>
</html>
