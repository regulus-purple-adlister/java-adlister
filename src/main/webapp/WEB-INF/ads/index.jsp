<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="All Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <div class="container-fluid d-flex px-0">
        <h1 class="ml-0">All Ads</h1>
        <a class="ml-auto mr-0" href="/ads/create" >Post an ad</a>
    </div>
    <jsp:include page="/WEB-INF/partials/ads-display.jsp" />
</div>
<jsp:include page="/WEB-INF/partials/bootstrap-scripts.jsp" />
</body>
</html>
