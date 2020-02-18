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
            <h1 class="mt-5">Highlights for ${team.fullName}</h1>

            <form action='<spring:url value="/teams/${team.id}"/>' method="post">
                <label for="clubMonth">Select month:</label>
                <input type="month" id="clubMonth" name="month"
                       max="${maxDate}" min="${minDate}" value="2020-02">
                <input type="submit" value="OK">
            </form>
        </div>
    </div>
</div>

<script src='<spring:url value="/vendor/jquery/jquery.slim.min.js"/>'></script>
<script src='<spring:url value="/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
<script src='<spring:url value="/js/app.js"/>'></script>
</body>
</html>