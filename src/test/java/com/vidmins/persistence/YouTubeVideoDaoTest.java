package com.vidmins.persistence;

import com.vidmins.entity.YouTubeVideo;
import com.vidmins.entity.Directory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type YouTubeVideo dao test.
 */
@TestInstance(Lifecycle.PER_METHOD)
class YouTubeVideoDaoTest {

    static GenericDao<YouTubeVideo> dao;
    Logger logger = LogManager.getLogger(this.getClass());
    static com.vidmins.test.util.Database database;

    /**
     * Creating the dao.
     */
    @BeforeAll
    static void setUp() {
        System.out.println("YouTubeVideoDaoTest.setUp()");
        dao = new GenericDao<>(YouTubeVideo.class);
       
        database = com.vidmins.test.util.Database.getInstance();
    }

    /**
     * Resetting the DB.
     */
    @BeforeEach
    void resetDb() {
        System.out.println("YouTubeVideoDaoTest.resetDb()");
        database.runSQL("reset_db.sql");
    }

    /**
     * Verifies gets all youTubeVideos successfully.
     */
    @Test
    void getAllYouTubeVideosSuccess() {
        List<YouTubeVideo> youTubeVideos = dao.getAll();
        assertEquals(3, youTubeVideos.size());
    }
//
//    /**
//     * Verifies a youTubeVideo is returned correctly based on id search
//     */
//    @Test
//    void getByIdSuccess() {
//        YouTubeVideo youTubeVideo = dao.getById(2);
//        assertNotNull(youTubeVideo);
//    }
//
//    /**
//     * Verifies a youTubeVideo is returned correctly based on id search
//     */
//    @Test
//    void getByIdVerifyYouTubeVideoSuccess() {
//        YouTubeVideo youTubeVideo = dao.getById(2);
//        assertNotNull(youTubeVideo);
//        assertEquals("Week1Act5", youTubeVideo.getTitle());
//    }
//
//
//    /**
//     * Verify successful delete of youTubeVideo
//     */
//    @Test
//    void deleteSuccess() {
//        int deleteId = 2;
//
//        // make this youTubeVideo
//        YouTubeVideo youTubeVideo = dao.getById(deleteId);
//        assertNotNull(youTubeVideo);
//        // disappear
//        dao.delete(youTubeVideo);
//        assertNull(dao.getById(deleteId));
//    }
//
//    /**
//     * Verify successful update of youTubeVideo
//     */
//    @Test
//    void updateSuccess() {
//        int updateId = 3;
//        String newYouTubeVideoTitle = "NEW VIDEO TITLE";
//        YouTubeVideo youTubeVideoToUpdate = dao.getById(updateId);
//        youTubeVideoToUpdate.setTitle(newYouTubeVideoTitle);
//        dao.saveOrUpdate(youTubeVideoToUpdate);
//
//        YouTubeVideo retrievedYouTubeVideo = dao.getById(updateId);
//        assertEquals(newYouTubeVideoTitle, retrievedYouTubeVideo.getTitle());
//    }
//
//    /**
//     * Verify successful insert
//     */
//    @Test
//    void insertSuccess() {
//
//        String youTubeVideoTitle = "TestYouTubeVideoInsert";
//        YouTubeVideo newYouTubeVideo = new YouTubeVideo();
//        newYouTubeVideo.setTitle(youTubeVideoTitle);
//        GenericDao<Directory> directoryDao = new GenericDao<>(Directory.class);
//        newYouTubeVideo.setDirectory(directoryDao.getById(3));
//        newYouTubeVideo.setYouTubeId("dF0NWtxRXsg");
//
//        int insertId = dao.insert(newYouTubeVideo);
//        assertNotEquals(0, insertId);
//
//        YouTubeVideo insertedYouTubeVideo = dao.getById(insertId);
//        assertNotNull(insertedYouTubeVideo);
//        assertEquals(youTubeVideoTitle, insertedYouTubeVideo.getTitle());
//
//        // Could continue comparing all values, but
//        // it may make sense to use .equals()
//        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/youTubeVideoguide/html_single/Hibernate_YouTubeVideo_Guide.html#mapping-model-pojo-equalshashcode
//    }
//
//    /**
//     * Verify successful get by property (equal match)
//     */
//    @Test
//    void getByPropertyEqualSuccess() {
//        // TODO make for YouTubeVideo
//        List<YouTubeVideo> youTubeVideos = dao.findByPropertyEqual("duration", 2400);
//        assertEquals(1, youTubeVideos.size());
//        assertEquals(2, youTubeVideos.get(0).getId());
//    }
//
//    /**
//     * Verify successful get by property (like match)
//     */
//    @Test
//    void getByPropertiesEqualSuccess() {
//        Map<String, Object> searchYouTubeVideo = new HashMap<>();
//        searchYouTubeVideo.put("title", "Welcome");
//
//        List<YouTubeVideo> youTubeVideos = dao.findByPropertyEqual(searchYouTubeVideo);
//
//        assertEquals(1, youTubeVideos.size());
//    }
}
