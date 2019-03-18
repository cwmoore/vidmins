<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${videos != null}">
    <div class="aquapanel">
        <h2>Videos: </h2>
        <table id="video_table" class="table table-striped">
            <tr>
                <%--th>ID</th>
                <th>YouTubeId</th--%>
                <th>Title</th>
                <th>Duration</th>
                <%--th># Notes</th--%>
                <th>Add Date</th>
            </tr>
            <c:forEach items="${videos}" var="video">
                <tr class="">
                    <%--td>${video.id}</td>
                    <td>${video.youTubeId}</td--%>
                    <td><a href="loadClient?videoId=${video.id}">${video.title}</a></td>
                    <td>${video.youTubeVideo.duration}</td>
                    <%--td--># Notes</td--%>
                    <td>${video.addDate}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </c:when>
    <c:otherwise>
        <%--form id="add_video_form" action="new-video" method="get">
            <label for="youtube_url">YouTube Video URL</label>
            <input type="text" id="youtube_url" placeholder="https://youtu.be/R4nd0mCh4r5"/>
            <button type="button" class="btn btn-primary" id="add_video_button">Add Video</button>
            TODO figure out when this shows and make it work
        </form--%>
    </c:otherwise>
</c:choose>