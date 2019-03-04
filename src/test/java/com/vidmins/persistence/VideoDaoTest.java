package com.vidmins.persistence;

import com.vidmins.entity.Video;
import com.vidmins.entity.Directory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Video dao test.
 */
class VideoDaoTest {

    GenericDao<Video> dao;
    Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Video.class);
//        try {
//            ProcessBuilder process = new ProcessBuilder("~/IdeaProjects/vidmins/src/test/resources/reset_test_db.sh");
//            process.start();
//            logger.debug("ProcessBuilder started UserDaoTest");
//        } catch (IOException ioException) {
//            logger.debug("ProcessBuilder failed to run in UserDaoTest");
//        }
        // would have to reenter my databases content into arrays
//        CleanTestDB cleanTestDB = new CleanTestDB();
//        cleanTestDB.testDbClean();

        com.vidmins.test.util.Database database = com.vidmins.test.util.Database.getInstance();
        database.runSQL("reset_db.sql");
    }

    /**
     * Verifies gets all orders successfully.
     */
    @Test
    void getAllVideosSuccess() {
        List<Video> videos = dao.getAll();
        assertEquals(3, videos.size());
    }

    /**
     * Verifies a order is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Video video = dao.getById(1);
        assertNotNull(video);
    }

    /**
     * Verifies a order is returned correctly based on id search
     */
    @Test
    void getByIdVerifyVideoSuccess() {
        Video video = dao.getById(2);
        assertNotNull(video);
        assertEquals("superhero movie", video.getTitle());
    }


    /**
     * Verify successful delete of order
     */
    @Test
    void deleteSuccess() {
        int deleteId = 2;

        // make this video
        Video video = dao.getById(deleteId);
        assertNotNull(video);
        // disappear
        dao.delete(video);
        assertNull(dao.getById(deleteId));
    }

    /**
     * Verify successful update of order
     */
    @Test
    void updateSuccess() {
        int updateId = 1;
        String newVideoTitle = "NEW VIDEO TITLE";
        Video videoToUpdate = dao.getById(updateId);
        videoToUpdate.setTitle(newVideoTitle);
        dao.saveOrUpdate(videoToUpdate);

        Video retrievedVideo = dao.getById(updateId);
        assertEquals(newVideoTitle, retrievedVideo.getTitle());
    }

    /**
     * Verify successful insert
     */
    @Test
    void insertSuccess() {

        String videoTitle = "TestVideoInsert";
        Video newVideo = new Video();
        newVideo.setTitle(videoTitle);
        GenericDao<Directory> directoryDao = new GenericDao<>(Directory.class);
        newVideo.setDirectory(directoryDao.getById(3));

        int insertId = dao.insert(newVideo);
        assertNotEquals(0, insertId);

        Video insertedVideo = dao.getById(insertId);
        assertNotNull(insertedVideo);
        assertEquals(videoTitle, insertedVideo.getTitle());

        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/videoguide/html_single/Hibernate_Video_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        // TODO make for Video
        List<Video> videos = dao.findByPropertyEqual("duration", "2400");
        assertEquals(1, videos.size());
        assertEquals(3, videos.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertiesEqualSuccess() {
        Map<String, Object> searchVideo = new HashMap<>();
        searchVideo.put("title", "comedy");

        List<Video> videos = dao.findByPropertyEqual(searchVideo);

        assertEquals(1, videos.size());
    }
}
