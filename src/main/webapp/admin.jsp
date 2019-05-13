<%@include file="head.jsp"%>
<div class="container-fluid aquapanel">
    <div class="row-fullwidth">
        <div class="aquapanel">
    <%-- do admin only stuff --%>
    <h1>Admin</h1>
    <ul>
        <li>Remove user</li>
        <li>Remove directory</li>
        <li>Remove video</li>
        <li>Remove note</li>
    </ul>
    <p>Can access when logged in as admin.</p>
    <a href="loadClient">Load Client</a><br />
    <a href="logout">Log Out</a>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>