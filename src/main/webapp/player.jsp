<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${ currentVideo != null }">
        <!-- begin code from YouTube Dev -->
        <!-- 1. The <iframe> (and video player) will replace this <div> tag. -->
        <div class="player-frame aquapanel">

            <form name="player_input_form">
                <table class="player-table"><tr>
                    <td>
                        <button type="button" id="skip_back_biggest" name="sbbst" class="player-button button-biggest" onclick="stepReverse(player, 60);">&#x21b6;</button>
                    </td>
                    <td>
                        <button type="button" id="skip_back_big" name="sbb" class="player-button button-big" onclick="stepReverse(player, 30);">&#x21b6;</button>
                    </td>
                    <td>
                        <button type="button" id="skip_back" name="sb" class="player-button"  onclick="stepReverse(player);">&#x21b6;</button>
                    </td>
                    <td>
                        <button type="button" id="pause_play" name="pp" class="player-button"  onclick="togglePausePlay(player);">&#x25ba;</button>
                    </td>
                    <td>
                        <button type="button" id="skip_ahead" name="sa" class="player-button"  onclick="stepForward(player);">&#x21b7;</button>
                    </td>
                    <td>
                        <button type="button" id="skip_ahead_big" name="sab" class="player-button button-big" onclick="stepForward(player, 30);">&#x21b7;</button>
                    </td>
                    <td>
                        <button type="button" id="skip_ahead_biggest" name="sabst" class="player-button button-biggest" onclick="stepForward(player, 60);">&#x21b7;</button>
                    </td>

                </tr></table>
            </form>

            <div id="player"></div>
            <div class="video-title">
                <h4><a href="https://www.youtube.com/watch?v=${currentVideo.youTubeVideo.youTubeId}">${currentVideo.title}</a></h4>
            </div>
        </div>

        <script>
            // 2. This code loads the IFrame Player API code asynchronously.
            var tag = document.createElement('script');

            tag.src = "https://www.youtube.com/iframe_api";
            var firstScriptTag = document.getElementsByTagName('script')[0];
            firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

            // 3. This function creates an <iframe> (and YouTube player)
            //    after the API code downloads.
            var player;
            var youTubeId = '${currentVideo.youTubeVideo.youTubeId}';
            var viewWidth = '800';//'1120';//this.parentNode.parentNode.clientWidth;
            var viewHeight = '450';//'630';//(viewWidth / 16) * 9;
            // console.log(this.parentNode);
            // console.log(this.parentNode.parentNode);
            // this.parentNode.width = viewWidth + 8; // 2 * width of padding and borders

            function onYouTubeIframeAPIReady() {
                player = new YT.Player('player', {
                    height: viewHeight,//'450',
                    width: viewWidth,//'800',
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
                        'origin': 'http://3.18.76.195:8080/VidMins'
                    }
                });
            }

            // 4. The API will call this function when the video player is ready.
            function onPlayerReady(event) {
                //event.target.playVideo();
                <c:choose>
                <c:when test="${param.startTime > 0}">
                    event.target.seekTo(<c:out value="${param.startTime}" />, true);
                </c:when>
                <c:when test="${ note != null }">
                    <c:if test="${ note.start > 0 }">
                        event.target.seekTo(<c:out value="${note.start}" />, false);
                    </c:if>
                </c:when>
                <c:otherwise></c:otherwise>
                </c:choose>
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
    </c:when>
</c:choose>
