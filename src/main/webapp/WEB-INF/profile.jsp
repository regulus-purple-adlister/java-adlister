<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="ml-1 mt-3 mb-5">
    <h1>Welcome, <c:out value="${sessionScope.user.username}"/>!</h1>
</div>
<%--<div>--%>
<%--    <a href="/updateprofile">Update profile</a>--%>
<%--</div>--%>
<div class="container">
    <nav>
        <div class="nav nav-pills nav-fill mb-3" id="nav-tab" role="tablist">
            <a class="nav-link active" id="about-tab" data-toggle="tab" href="#nav-about" role="tab"
               aria-controls="nav-about" aria-selected="true">About</a>
            <a class="nav-link" id="ads-tab" data-toggle="tab" href="#nav-ads" role="tab" aria-controls="nav-ads"
               aria-selected="false">Ads</a>
            <a class="nav-link" id="create-tab" data-toggle="tab" href="#nav-create" role="tab"
               aria-controls="nav-create-ads"
               aria-selected="false">Create</a>
            <a class="nav-link" id="update-tab" data-toggle="tab" href="#nav-update" role="tab"
               aria-controls="nav-update"
               aria-selected="false">Update Profile</a>
        </div>
    </nav>
    <div class="tab-content mt-3 mb-3" id="nav-tabContent">
        <div class="tab-pane fade show active" id="nav-about" role="tabpanel" aria-labelledby="about-tab">
            <h3 class="card-title mt-3 mb-5">Profile Information</h3>
            <h4><label>Email:</label> </h4>
            <p><c:out value="${sessionScope.user.email}"/></p>
            <div class="card-text">
                <h5><label>First Name:</label></h5>
                <p><c:out value="${profile.firstName}"/></p>
            </div>

            <div class="card-text">
                <h5><label>Last Name:</label></h5>
                <p><c:out value="${profile.lastName}"/></p>
            </div>
            <div class="card-text">
                <h5><label>City:</label></h5>
                <p><c:out value="${profile.city}"/></p>
            </div>

        </div>
        <div class="tab-pane fade" id="nav-ads" role="tabpanel" aria-labelledby="ads-tab">
            <div class="container mt-4">
                <h1>Here Are all the ads!</h1>
                <jsp:include page="/WEB-INF/partials/ads-display.jsp"/>
            </div>
        </div>
        <div class="tab-pane fade" id="nav-create" role="tabpanel" aria-labelledby="create-tab">
            <div class="container">
                <jsp:include page="/WEB-INF/ads/create.jsp"/>
            </div>
        </div>
        <div class="tab-pane fade" id="nav-update" role="tabpanel" aria-labelledby="update-tab">
            <div class="container">
                <jsp:include page="/WEB-INF/updateprofile.jsp"/>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/partials/bootstrap-scripts.jsp"/>
</body>
</html>
