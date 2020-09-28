<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="link_input" class="aquapanel">
    <form name="link_input_form" method="get" action="#">

        <label>Link Text</label><br />
        <input id="linkText" type="text" name="userLinkText" /><br />

        <label>YouTube Video URL:</label><br />
        <input id="videoLink" type="url" name="userVideoUrl" /><br />

        <input type="hidden" name="timeStampPrompt" />
        <label>Prompt time:</label> <span id="link_time_stamp_prompt"></span>s<br />

        <label>Your link</label>
        <div id="finished_link"></div>

        <br />
        <button type="button" class="btn btn-primary" onclick="makeLink()">Link with time</button>
    </form>
</div>


<%--
<div id="survey_input"  class="aquapanel" method="get" action="#" onsubmit="processInput(); return false;">
    <form name="survey_input_form">

        <label>Question:</label><br />
        <textarea name="question"></textarea><br />

        <input type="hidden" name="timeStampPrompt" />
        <label>Prompt time:</label> <span id="time_stamp_prompt"></span><br />

        <br />
        <input type="submit" value="Update Survey" />
    </form>
</div>

<div id="comment_input"  class="aquapanel" method="get" action="#" onsubmit="processInput(); return false;">
    <form name="comment_input_form">

        <label>Comment:</label><br />
        <textarea name="comment"></textarea><br />

        <input type="hidden" name="timeStampComment" />
        <label>Comment time:</label> <span id="time_stamp_comment"></span><br />

        <br />
        <input type="submit" class="btn btn-primary" value="Give Feedback" />
    </form>
</div>


<div id="ask_input"  class="aquapanel" method="get" action="#" onsubmit="processInput(); return false;">
    <form name="question_input_form">

        <label>Ask Question:</label><br />
        <textarea name="askQuestion"></textarea><br />

        <input type="hidden" name="timeStampAsk" />
        <label>Related time:</label> <span id="time_stamp_ask"></span><br />

        <br />
        <input type="submit" class="btn btn-primary" value="Ask" />
    </form>
</div>
--%>

<%--div id="player_input"  class="aquapanel" method="get" action="#" onsubmit="processInput(); return false;">
</div--%>