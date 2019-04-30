<%
    String url = "/vidmins/loadClient";
    response.setStatus(response.SC_TEMPORARY_REDIRECT);
    //response.setHeader("Location", url);
    response.sendRedirect(url);
    return;
%>