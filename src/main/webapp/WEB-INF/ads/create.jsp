<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Post a New Ad" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h1>Create a new Ad</h1>
        <jsp:include page="/WEB-INF/partials/ad-form.jsp">
            <jsp:param name="action" value="/ads/create" />
        </jsp:include>
    </div>
    <jsp:include page="/WEB-INF/partials/bootstrap-scripts.jsp" />
</body>
</html>
