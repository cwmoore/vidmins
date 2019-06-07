<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 id="brand-header"><span>Vid</span>eo <span>Min</span>ute<span>s</span></h1>

    <div class="menu">
        <%-- these buttons show or hide the corresponding panels below --%>
        <button id="help_button" name="help" class="btn btn-info" onclick="showPanel('help');">?</button>
        <button id="directory_button" name="new-directory" class="btn<c:choose><c:when test="${ user != null }"> btn-info" onclick="showDirectory();"</c:when><c:otherwise>"</c:otherwise></c:choose>>Directory</button>
        <button id="video_button" name="new-video" class="btn<c:choose><c:when test="${ currentDirectory != null }"> btn-info" onclick="showVideo();"</c:when><c:otherwise>"</c:otherwise></c:choose>>Video</button>
            <button id="note_button" name="show-time" class="btn<c:choose><c:when test="${ currentVideo != null }"> btn-info" onclick="showNote();"</c:when><c:otherwise>"</c:otherwise></c:choose>>Note</button>
        <%-- <button id="survey_button" name="survey" class="btn btn-info" onclick="showPanel('survey');makeSurveyQuestion();">Survey</button>
        <button id="comment_button" name="feedback" class="btn btn-info" onclick="makeComment();">Comment</button>
        <button id="ask_button" name="ask-question" class="btn btn-info" onclick="makeAskQuestion();">Ask</button>
        <button id="player_button" name="player-command" class="btn btn-info" onclick="makePlayerCommand();">Play</button>
        <button id="link_button" name="make-link" class="btn btn-info" onclick="makeLink();">Link</button>
                --%>
    </div>



    <div id="watcher">

        <c:import url="input_directory.jsp"/>
        <c:import url="input_video.jsp"/>
        <c:import url="input_note.jsp"/>
        <c:import url="help.jsp"/>
    </div>