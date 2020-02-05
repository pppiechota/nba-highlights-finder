<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>nba highlights</title>

    <link href='<spring:url value="/vendor/bootstrap/css/bootstrap.min.css"/>' rel="stylesheet" />
    <link href='<spring:url value="/css/simple-sidebar.css"/>' rel="stylesheet" />
</head>
<body>

<div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
        <div class="sidebar-heading">nba spoilerfree<br/> highlights<br/> for hardworking<br/> polish people</div>
        <div class="list-group list-group-flush">
            <a href='<spring:url value="/get-games/1"/>' class="list-group-item list-group-item-action bg-light">ATL</a>
            <a href='<spring:url value="/get-games/2"/>' class="list-group-item list-group-item-action bg-light">BOS</a>
            <a href='<spring:url value="/get-games/3"/>' class="list-group-item list-group-item-action bg-light">BKN</a>
            <a href='<spring:url value="/get-games/4"/>' class="list-group-item list-group-item-action bg-light">CHA</a>
            <a href='<spring:url value="/get-games/5"/>' class="list-group-item list-group-item-action bg-light">CHI</a>
            <a href='<spring:url value="/get-games/6"/>' class="list-group-item list-group-item-action bg-light">CLE</a>
            <a href='<spring:url value="/get-games/7"/>' class="list-group-item list-group-item-action bg-light">DAL</a>
            <a href='<spring:url value="/get-games/8"/>' class="list-group-item list-group-item-action bg-light">DEN</a>
            <a href='<spring:url value="/get-games/9"/>' class="list-group-item list-group-item-action bg-light">DET</a>
            <a href='<spring:url value="/get-games/10"/>' class="list-group-item list-group-item-action bg-light">GSW</a>
            <a href='<spring:url value="/get-games/11"/>' class="list-group-item list-group-item-action bg-light">HOU</a>
            <a href='<spring:url value="/get-games/12"/>' class="list-group-item list-group-item-action bg-light">IND</a>
            <a href='<spring:url value="/get-games/13"/>' class="list-group-item list-group-item-action bg-light">LAC</a>
            <a href='<spring:url value="/get-games/14"/>' class="list-group-item list-group-item-action bg-light">LAL</a>
            <a href='<spring:url value="/get-games/15"/>' class="list-group-item list-group-item-action bg-light">MEM</a>
            <a href='<spring:url value="/get-games/16"/>' class="list-group-item list-group-item-action bg-light">MIA</a>
            <a href='<spring:url value="/get-games/17"/>' class="list-group-item list-group-item-action bg-light">MIL</a>
            <a href='<spring:url value="/get-games/18"/>' class="list-group-item list-group-item-action bg-light">MIN</a>
            <a href='<spring:url value="/get-games/19"/>' class="list-group-item list-group-item-action bg-light">NOP</a>
            <a href='<spring:url value="/get-games/20"/>' class="list-group-item list-group-item-action bg-light">NYK</a>
            <a href='<spring:url value="/get-games/21"/>' class="list-group-item list-group-item-action bg-light">OKC</a>
            <a href='<spring:url value="/get-games/22"/>' class="list-group-item list-group-item-action bg-light">ORL</a>
            <a href='<spring:url value="/get-games/23"/>' class="list-group-item list-group-item-action bg-light">PHI</a>
            <a href='<spring:url value="/get-games/24"/>' class="list-group-item list-group-item-action bg-light">PHX</a>
            <a href='<spring:url value="/get-games/25"/>' class="list-group-item list-group-item-action bg-light">POR</a>
            <a href='<spring:url value="/get-games/26"/>' class="list-group-item list-group-item-action bg-light">SAC</a>
            <a href='<spring:url value="/get-games/27"/>' class="list-group-item list-group-item-action bg-light">SAS</a>
            <a href='<spring:url value="/get-games/28"/>' class="list-group-item list-group-item-action bg-light">TOR</a>
            <a href='<spring:url value="/get-games/29"/>' class="list-group-item list-group-item-action bg-light">UTA</a>
            <a href='<spring:url value="/get-games/30"/>' class="list-group-item list-group-item-action bg-light">WAS</a>
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

        <div class="container-fluid">
            <h2 class="mt-4">${game.homeTeam} - ${game.visitorTeam}</h2>
            <p>${game.date}</p>
            <iframe width="560" height="315" src="https://www.youtube.com/embed/${video}" frameborder="0"
                    allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

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
