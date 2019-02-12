<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${videos != null}">
        <h2>Videos: </h2>
        <table id="video_table" class="table table-striped">
            <tr>
                <th>ID</th>
                <th>YouTubeId</th>
                <th>Title</th>
                <th>Duration</th>
                <th># Notes</th>
                <th>Add Date</th>
            </tr>
            <c:forEach items="${videos}" var="video">
                <tr class="">
                    <td>${video.id}</td>
                    <td>${video.youTubeId}</td>
                    <td>${video.title}</td>
                    <td>${video.duration}</td>
                    <td><a href="http://localhost:8080/loadClient?videoId=${video.id}"># Notes</a></td>
                    <td>${video.addDate}</td>
                </tr>
            </c:forEach>
        </table>
</c:if>