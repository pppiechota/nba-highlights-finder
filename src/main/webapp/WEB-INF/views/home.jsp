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
            <h1 class="mt-5">Highlights from ${date}</h1>
            <p class="lead">Click on the game from above list</p>
            <div id="nbalogo">
                <img src="/img/nbalogo.png">
            </div>
            <p class="welcome">I<span style="color: red">LOVE</span>THIS<span style="color: darkblue">GAME</span>!</p>
        </div>
    </div>
</div>

<script src="vendor/jquery/jquery.slim.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
