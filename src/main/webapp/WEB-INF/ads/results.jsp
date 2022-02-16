<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="${param.search} Search" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Search results for:</h1>
    <p><c:out value="${param.search}" /></p>

    <jsp:include page="/WEB-INF/partials/ads-display.jsp" />
</div>
<jsp:include page="/WEB-INF/partials/bootstrap-scripts.jsp" />
</body>
</html>
