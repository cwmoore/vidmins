<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${user == null}">
    <div id="access_form_container" class="aquapanel">

        <form class="signin" name="signup" method="post" action="">

            <label for="t_first_name">First Name</label>      <input type="text" name="firstName" id="t_first_name"/> <br />
            <label for="t_last_name">Last Name</label>        <input type="text" name="lastName" id="t_last_name"/> <br />
            <label for="t_organization">Organization</label>  <input type="text" name="organization" id="t_organization"/> <br />
            <label for="t_introduction">Introduction</label>  <input type="text" name="introduction" id="t_introduction"/> <br />
            <label for="t_username">Username</label>          <input type="text" name="username" id="t_username"/> <br />
            <label for="t_email">Email</label>                <input type="email" name="email" id="t_email"/> <br />
            <label for="t_password0">Password</label>         <input type="password" name="password0" id="t_password0"/> <br />
            <label for="t_password1">Password</label>         <input type="password" name="password1" id="t_password1"/> <br />

            <button type="button">Sign Up</button>

        </form>

    </div>
</c:if>