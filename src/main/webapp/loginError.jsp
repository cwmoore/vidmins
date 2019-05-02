<%
    //String url = "/vidmins/";
    String url = "/vidmins/index.jsp";
    response.setStatus(response.SC_UNAUTHORIZED);
    //response.setHeader("Location", url);
    response.sendRedirect(url);
    return;
%>