        <div class="container-fluid">
            <div id="footer" class="row-fullwidth">
                <h4>Vidmins.com - &copy 2019</h4>
            </div>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <c:choose>
            <c:when test="${user == null}"><script>const loadCallback = () => { console.log("show nothing special"); }</script></c:when>

            <c:when test="${param.directoryId != null}"><script>const loadCallback = () => { showDirectory(); }</script></c:when>
            <c:when test="${param.videoId != null}"><script>const loadCallback = () => { showVideo(); }</script></c:when>
            <c:when test="${param.noteId != null}"><script>const loadCallback = () => { showNote(); }</script></c:when>

            <c:when test="${currentDirectory == null}"><script>const loadCallback = () => { showDirectory(); }</script></c:when>
            <c:when test="${currentVideo == null}"><script>const loadCallback = () => { showVideo(); }</script></c:when>
            <c:when test="${currentNote == null}"><script>const loadCallback = () => { showNote(); }</script></c:when>

            <c:otherwise><script>const loadCallback = () => { console.log("show nothing special"); }</script></c:otherwise>
        </c:choose>
        <script src="js/vidmins.js"></script>
    </body>
</html>