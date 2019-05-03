<%
    String url = "/vidmins/";
    //String url = "login.jsp";
    response.setStatus(response.SC_UNAUTHORIZED);
    //response.setHeader("Location", url);
    response.sendRedirect(url);
    return;
%>