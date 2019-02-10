<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp" />

<body<%-- onload="showPanel('help');"--%>>
<div class="container-fluid">
    <div class="row-fullwidth">
        <nav class="navbar navbar-expand-sm navbar-expand-md navbar-expand-lg navbar-expand-xl navbar-light bg-light">
            <a class="navbar-brand" href="/">VidMins</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link<c:if test="${videos == null}"> disabled</c:if>" href="#">Videos</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <c:if test="${user == null}">User</c:if>
                            <c:if test="${user != null}">${user.firstName}</c:if>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item<c:if test="${user == null}"> disabled</c:if>" href="#">Profile</a>
                            <a class="dropdown-item<c:if test="${user == null}"> disabled</c:if>" href="#">Settings</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item<c:if test="${user == null}"> disabled</c:if>" href="#">Data</a>
                            <c:if test="${user != null}"><div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="logout">Sign Out</a></c:if>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">About</a>
                    </li>
                </ul>
                <c:if test="${user == null}">
                    <form class="form-inline my-2 my-lg-0" action="login" method="POST">
                        <input type="text" name="username" placeholder="username" class="form-control mr-sm-2" />
                        <input type="password" name="password" placeholder="password" class="form-control mr-sm-2" />
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
                    </form>
                </c:if>
                <c:if test="${user != null}">
                    <form class="form-inline my-2 my-lg-0" action="loadClient" method="GET">
                        <input type="search" name="search" placeholder="Search query" class="form-control mr-sm-2" />
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </c:if>
            </div>
        </nav>
    </div>
    <div class="row">
        <div id="watcher" class="col-lg-5">
            <h1>Video Minutes</h1>
            <div class="menu">
                <button id="help_button" name="help" class="btn btn-info" onclick="showPanel('help');">?</button>
                <button id="note_button" name="show-time" class="btn btn-info" onclick="showPanel('note');makeNote();">Note</button>
                <button id="link_button" name="make-link" class="btn btn-info" onclick="showPanel('link');makeLink();">Link</button>
                <%-- <button id="survey_button" name="survey" class="btn btn-info" onclick="showPanel('survey');makeSurveyQuestion();">Survey</button> --%>
                <button id="comment_button" name="feedback" class="btn btn-info" onclick="showPanel('comment');makeComment();">Comment</button>
                <button id="ask_button" name="ask-question" class="btn btn-info" onclick="showPanel('ask');makeAskQuestion();">Ask</button>
            </div>
            <div id="notes">
                <!-- TODO: use bootstrap navigation -->

                <div id="note_input" class="aquapanel">
                    <form id="note_input_form" accept-charset="utf-8" method="post" action="http://localhost:8080/new-note"<%-- onsubmit="processInput(); return false;" --%>>

                        <label>Label:</label><br />
                        <input type="text" name="label" /><br />

                        <label>Text:</label><br />
                        <textarea name="note_text"></textarea><br />

                        <label>Tags:</label><br />
                        <input type="text" name="tag" />

                        <button name="add-tag" onclick="addTag()">Add Tag</button><br />
                        <span id="tags"></span>

                        <input type="hidden" name="timeStampStart" />
                        <label>Start:</label> <span id="time_stamp_start"></span><br />

                        <input type="hidden" name="timeStampEnd" />
                        <label>End:</label> <span id="time_stamp_end"></span><br />

                        <input type="hidden" name="userId" value="${userId}" />
                        <input type="hidden" name="videoId" value="${videoId}" />

                        <br />
                        <input type="submit" class="btn btn-primary" value="Store Annotation" />
                    </form>
                </div>
                <%-- <div id="notes">
                    <ul class="notes"></ul>
                </div>
                <div id="videos">
                    <ul class="videos"></ul>
                </div>
--%>

                <div id="link_input" class="aquapanel" method="get" action="#" onsubmit="processInput(); return false;">
                    <form name="link_input_form">

                        <label>YouTube Video URL:</label><br />
                        <input id="videoLink" type="url" name="userVideoUrl" /><br />

                        <input type="hidden" name="timeStampPrompt" />
                        <label>Prompt time:</label> <span id="link_time_stamp_prompt"></span>s<br />

                        <br />
                        <input type="submit" class="btn btn-primary" value="Link with time" />
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
--%>
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
                <div id="help_input" class="aquapanel">
                    <h3>App use cases:</h3>
                    <h4>One might:</h4>
                    <ul>
                        <li>pause a video</li>
                        <li>obtain a blank card</li>
                        <li>write down an idea</li>
                        <li>note the timestamp related to the concept</li>
                        <li>continue the video</li>
                    </ul>
                    <h4>Or just as likely one might:</h4>
                    <ul>
                        <li>encounter a term</li>
                        <li>search annotations for that and related terms</li>
                        <li>obtain a list of timestamp links to multiple videos</li>
                        <li>view the provided video material</li>
                    </ul>
                    <h4>A presenter might:</h4>
                    <ul>
                        <li>prepare a video with timely annotations</li>
                        <li>pause the video at each point to explain</li>
                        <li>include additional linked content</li>
                    </ul>
                    <h4>An online class might:</h4>
                    <ul>
                        <li>watch video</li>
                        <li>at each point the video pauses</li>
                        <li>answer quiz or survey question related to the video</li>
                    </ul>
                    <h4>Likely target users:</h4>
                    <ul>
                        <li>A film afficionado</li>
                        <li>An online college student</li>
                        <li>An online college</li>
                        <li>A music lover adding song titles/start times to a full album.</li>
                        <li>A video content creator looking for viewer response</li>
                        <li>Political commentary on news coverage</li>
                        <li>Investigator analyzing event footage</li>
                        <li>Content owners defending copyright</li>
                        <li>Trend/topic exploration</li>
                    </ul>
                </div>

            </div>
        </div>
        <div class="col-lg-7">
            <c:if test="${ currentVideo == null }">
                <h3><i>Video will load here</i></h3>
            </c:if>
            <c:if test="${ currentVideo != null }">
                <!-- begin code from YouTube Dev -->
                <!-- 1. The <iframe> (and video player) will replace this <div> tag. -->
                <div class="player-frame">
                    <div id="player"></div>
                    <div class="video-title">
                        <h4><a href="https://www.youtube.com/watch?v=${currentVideo.youTubeId}">${currentVideo.title}<%--<br />https://www.youtube.com/watch?v=${currentVideo.youTubeId}--%></a></h4>
                    </div>
                </div>

                <c:if test="${notes != null}">
                    <div class="row">
                        <h2>Notes: </h2>
                        <table id="note_table" class="table table-compact table-striped">
                            <tr>
                                <th>Label</th>
                                <th>Text</th>
                                <th>Start</th>
                                <th>End</th>
                                <th>Created</th>
                                <th>videoId</th>
                            </tr>
                            <c:forEach items="${notes}" var="note">
                                <tr class="">
                                    <td>${note.label}</td>
                                    <td>${note.text}</td>
                                    <td>${note.start}</td>
                                    <td>${note.end}</td>
                                    <td>${note.createDatetime}</td>
                                    <td>${note.videoId}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>

                <script>
                    // 2. This code loads the IFrame Player API code asynchronously.
                    var tag = document.createElement('script');

                    tag.src = "https://www.youtube.com/iframe_api";
                    var firstScriptTag = document.getElementsByTagName('script')[0];
                    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

                    // 3. This function creates an <iframe> (and YouTube player)
                    //    after the API code downloads.
                    var player;
                    var youTubeId = '${currentVideo.youTubeId}';
                    function onYouTubeIframeAPIReady() {
                        player = new YT.Player('player', {
                            height: '450',
                            width: '800',
                            videoId: youTubeId,
                            events: {
                                'onReady': onPlayerReady,
                                'onStateChange': onPlayerStateChange
                            },
                            playerVars: {
                                'autoplay': 0,
                                'controls': 1,
                                'autohide': 1,
                                'wmode': 'opaque',
                                'origin': 'http://localhost:8080/'
                            }
                        });
                    }

                    // 4. The API will call this function when the video player is ready.
                    function onPlayerReady(event) {
                        //event.target.playVideo();
                    }

                    // 5. The API calls this function when the player's state changes.
                    //    The function indicates that when playing a video (state=1),
                    //    the player should play for six seconds and then stop.
                    var done = false;
                    function onPlayerStateChange(event) {
                        // if (event.data == YT.PlayerState.PLAYING && !done) {
                        //   setTimeout(stopVideo, 6000);
                        //   done = true;
                        // }
                    }
                    function stopVideo() {
                        player.stopVideo();
                    }
                </script>
                <!-- end code from YouTube Dev -->
            </c:if>
        </div>
    </div>


        <c:if test="${videos != null}">
            <div class="row">
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
            </div>
        </c:if>
        <c:if test="${user != null}">
            <div class="row">
            <h2>User Info: </h2>
            <table class="table table-striped">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>User Name</th>
                    <th>Age</th>
                    <th>Password</th>
                </tr>
                <tr class="">
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.userName}</td>
                    <td>${user.age}</td>
                    <td>${user.password}</td>
                </tr>
            </table>
            </div>
        </c:if>
</div>
<%@ include file="footer.jsp" %>