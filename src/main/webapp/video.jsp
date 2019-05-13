<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${videos != null}">
    <div class="aquapanel">
        <h3>${currentDirectory.name}</h3>
        <p>${currentDirectory.description}</p>

        <h3>Videos</h3>
        <table id="video_table" class="table table-striped">
            <tr>
                <%--th>ID</th--%>
                <th>Title</th>
                <th>YouTubeId</th>
                <th>YouTube Title</th>
                <th>Duration</th>
                <%--th># Notes</th--%>
                <th>Add Date</th>
                <th>Controls</th>
            </tr>
            <c:forEach items="${videos}" var="video">
                <tr class="">
                    <%--td>${video.id}</td--%>
                    <td><a href="loadClient?videoId=${video.id}">${video.title}</a></td>
                    <td>${video.youTubeVideo.youTubeId}</td>
                        <td><a href="loadClient?videoId=${video.id}">${video.youTubeVideo.title}</a></td>
                    <td>${video.youTubeVideo.duration}</td>
                    <%--td--># Notes</td--%>
                    <td>${video.addDate}</td>
                    <td>
                        <a href="edit-video?videoId=${video.id}">Edit</a>
                       <%-- <a href="move-video?id=${video.id}">Move</a> --%>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </c:when>
</c:choose>