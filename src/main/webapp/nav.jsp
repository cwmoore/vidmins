<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <nav class="navbar navbar-expand-sm navbar-expand-md navbar-expand-lg navbar-expand-xl navbar-light bg-light">
            <a class="navbar-brand" href="/">VidMins</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link<c:if test="${videos == null}"> disabled</c:if>" href="#">Videos</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <c:if test="${user == null}">User</c:if>
                            <c:if test="${user != null}">${user.firstName}</c:if>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item<c:if test="${user == null}"> disabled</c:if>" href="#">Profile</a>
                            <a class="dropdown-item<c:if test="${user == null}"> disabled</c:if>" href="#">Settings</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item<c:if test="${user == null}"> disabled</c:if>" href="#">Data</a>
                            <c:if test="${user != null}"><div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout">Sign Out</a></c:if>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">About</a>
                    </li>
                </ul>
                <c:if test="${user == null}">
                    <form class="form-inline my-2 my-lg-0" action="login" method="POST">
                        <input type="text" name="username" placeholder="username" class="form-control mr-sm-2" />
                        <input type="password" name="password" placeholder="password" class="form-control mr-sm-2" />
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
                    </form>
                </c:if>
                <c:if test="${user != null}">
                    <form class="form-inline my-2 my-lg-0" action="loadClient" method="GET">
                        <input type="search" name="search" placeholder="Search query" class="form-control mr-sm-2" />
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </c:if>
            </div>
        </nav>