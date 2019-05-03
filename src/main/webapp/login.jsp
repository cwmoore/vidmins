<c:import url="head.jsp" />
    <div class="container-fluid aquapanel">
        <div class="row-fullwidth">
            <div class="aquapanel">
                <form action="j_security_check" method="post">
                    <input type="text" name="j_username" placeholder="username"/>
                    <input type="password" name="j_password" placeholder="password"/>
                    <input type="submit" value="Log in"/>
                </form>
            </div>
        </div>
    </div>
<%@ include file="footer.jsp" %>