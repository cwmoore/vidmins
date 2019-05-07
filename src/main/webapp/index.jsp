<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />

<body>
<div class="container-fluid">
    <div class="row-fullwidth">
        <c:import url="nav.jsp" />
    </div>
    <div class="row">
        <div id="watcher" class="col-lg-4">
            <c:import url="watcher.jsp" />
            <c:import url="notes.jsp" />
            <c:import url="directory.jsp" />
        </div>
        <div class="col-lg-8">
            <c:import url="signup.jsp" />
            <c:import url="player.jsp" />
            <c:import url="video.jsp" />
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>