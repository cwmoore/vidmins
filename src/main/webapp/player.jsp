<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${ currentVideo == null }">
    <h3><i>Video will load here</i></h3>
</c:if>
<c:if test="${ currentVideo != null }">
    <!-- begin code from YouTube Dev -->
    <!-- 1. The <iframe> (and video player) will replace this <div> tag. -->
    <div class="player-frame aquapanel">
        <div id="player"></div>
        <div class="video-title">
            <h4><a href="https://www.youtube.com/watch?v=${currentVideo.youTubeId}">${currentVideo.title}</a></h4>
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
            <c:if test="${param.startTime > 0}">
                event.target.seekTo(<c:out value="${param.startTime}" />, true);
                //event.target.playVideo();
            </c:if>
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