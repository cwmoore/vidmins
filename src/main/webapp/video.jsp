<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
    <c:when test="${currentDirectory != null}">
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
            <c:forEach items="${currentDirectory.videos}" var="video">
                <tr class="video-row">
                    <%--td>${video.id}</td--%>
                    <c:choose>
                        <c:when test="${video.id == currentVideo.id || video.id == editVideo.id }">
                            <td><c:out value="${video.title}"/></td>
                            <td>${video.youTubeVideo.youTubeId}</td>
                            <td><c:out value="${video.youTubeVideo.title}"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="loadClient?videoId=${video.id}"><c:out value="${video.title}"/></a></td>
                            <td>${video.youTubeVideo.youTubeId}</td>
                            <td><a href="loadClient?videoId=${video.id}"><c:out value="${video.youTubeVideo.title}"/></a></td>
                        </c:otherwise>
                    </c:choose>
                    <td>${video.youTubeVideo.duration}</td>
                    <%--td--># Notes</td--%>
                    <td>${video.addDate}</td>
                    <td>
                        <a href="edit-video?videoId=${video.id}">Edit</a>
                       <%-- <a href="move-video?id=${video.id}">Move</a> --%>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${fn:length(currentDirectory.videos) == 0}">
                <p>There are no videos in this directory.</p>
            </c:if>
        </table>
    </div>
    </c:when>
</c:choose>