<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Schedule</title>
    <link href='<spring:url value="/css/additional-style.css"/>' rel="stylesheet" />
    <link href='<spring:url value="/vendor/bootstrap/css/bootstrap.min.css"/>' rel="stylesheet" />
    <link href='<spring:url value="/css/simple-sidebar.css"/>' rel="stylesheet" />
</head>
<body>
<div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
        <div class="sidebar-heading">nba spoilerfree<br/> highlights<br/> for hardworking<br/> polish people</div>
        <div class="list-group list-group-flush">
            <a href='#' class="list-group-item list-group-item-action bg-light">Option 1</a>
            <a href='#' class="list-group-item list-group-item-action bg-light">Option 2</a>
            <a href='#' class="list-group-item list-group-item-action bg-light">Option 3</a>
            <a href='#' class="list-group-item list-group-item-action bg-light">Option 4</a>
        </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <button class="btn btn-primary" id="menu-toggle">Toggle List</button>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Login</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="scrollmenu">
            <c:forEach items="${schedule}" var="game" varStatus="gamelist">
                <a href='<spring:url value="/schedule/game?gameId=${gamelist.index}"/>'>${game.homeTeam} vs ${game.visitorTeam}</a>
            </c:forEach>
        </div>
        <div class="container-fluid">
            <h2 class="mt-4">Welcome</h2>
            <p></p>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->


<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>
</body>
</html>
