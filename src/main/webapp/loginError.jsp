<%
    String url = "/VidMins/";
    response.setStatus(response.SC_UNAUTHORIZED);
    //response.setHeader("Location", url);
    response.sendRedirect(url);
    return;
%>