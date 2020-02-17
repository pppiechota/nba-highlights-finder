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

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <p class="lead">Search for highlights from another day</p>
            <form action='<spring:url value="/calendar"/>' method="post">
                <label for="anotherDay">Date:</label>
                <input type="date" id="anotherDay" name="another" max="${maxDate}" min="${minDate}">
                <input type="submit" value="Choose">
            </form>
            <div id="nbalogo">
                <img src="/img/nbalogo.png">
            </div>
        </div>
    </div>
</div>

<script src="vendor/jquery/jquery.slim.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>