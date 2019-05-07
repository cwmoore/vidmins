<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${videos != null}">
    <div class="aquapanel">
        <h2>${currentDirectory.name}</h2>
        <p>${currentDirectory.description}</p>

        <h3>Videos: </h3>
        <table id="video_table" class="table table-striped">
            <tr>
                <%--th>ID</th--%>
                <th>YouTubeId</th>
                <th>Title</th>
                <th>Duration</th>
                <%--th># Notes</th--%>
                <th>Add Date</th>
            </tr>
            <c:forEach items="${videos}" var="video">
                <tr class="">
                    <%--td>${video.id}</td--%>
                    <td>${video.youTubeVideo.youTubeId}</td>
                    <td><a href="loadClient?videoId=${video.id}">${video.title}</a></td>
                    <td>${video.youTubeVideo.duration}</td>
                    <%--td--># Notes</td--%>
                    <td>${video.addDate}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </c:when>
</c:choose>