<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Welcome, <c:out value="${sessionScope.user.username}" />!</h1>
    </div>
    <a href="/updateprofile">Update profile</a>
    <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
            <a class="nav-link active" id="about-tab" data-toggle="tab" href="#nav-about" role="tab"
               aria-controls="nav-about" aria-selected="true">About</a>
            <a class="nav-link" id="ads-tab" data-toggle="tab" href="#nav-ads" role="tab" aria-controls="nav-ads"
               aria-selected="false">Ads</a>
            <a class="nav-link" id="create-tab" data-toggle="tab" href="#nav-create" role="tab" aria-controls="nav-create-ads"
               aria-selected="false">Create</a>
        </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade show active" id="nav-about" role="tabpanel" aria-labelledby="about-tab">
            <h5 class="card-title">Profile Information</h5>
            <p class="card-text">Email: <c:out value="${sessionScope.user.email}"/></p>
            <div class="card-text">
                <label>First Name</label>
                <p><c:out value="${profile.firstName}" /> </p>
            </div>

            <div class="card-text">
            <label>Last Name</label>
            <p> <c:out value="${profile.lastName}" /> </p>
        </div>
            <div class="card-text">
                <label>City</label>
                <p> <c:out value="${profile.city}" /> </p>
            </div>

        </div>
        <div class="tab-pane fade" id="nav-ads" role="tabpanel" aria-labelledby="ads-tab">
            <div class="container">
                <h1>Here Are all the ads!</h1>
                <jsp:include page="/WEB-INF/partials/ads-display.jsp" />
            </div>
        </div>
            <div class="tab-pane fade" id="nav-create" role="tabpanel" aria-labelledby="create-tab">
                <div class="container">
                    <jsp:include page="/WEB-INF/ads/create.jsp" />
                </div>
            </div>
        </div>
    <jsp:include page="/WEB-INF/partials/bootstrap-scripts.jsp" />
</body>
</html>
