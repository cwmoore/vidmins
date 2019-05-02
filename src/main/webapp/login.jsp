<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />
<%-- TODO style this to look like vidmins --%>
<body>
<div class="container-fluid">
    <div class="row-fullwidth">
        <form action="j_security_check" method="post">
            <input type="text" name="j_username" placeholder="username"/>
            <input type="password" name="j_password" placeholder="password"/>
            <input type="submit" value="Log in"/>
        </form>
    </div>
</div>
<%@ include file="footer.jsp" %>