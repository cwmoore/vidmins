package com.vidmins.persistence;

import com.vidmins.entity.Directory;
import com.vidmins.entity.Note;
import com.vidmins.persistence.GenericDao;
import com.vidmins.entity.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VideoDaoTest {

    GenericDao<Video> dao;

    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Video.class);
    }

    @Test
    void getAllSuccess() {
        List<Video> videos = dao.getAll();

        assertNotNull(videos);
        assertEquals(149, videos.size());
    }

    @Test
    void getByIdSuccess() {
        Video video = dao.getById(4);
        assertNotNull(video);
        assertEquals("Welcome", video.getTitle());
    }

    @Test
    void delete() {
    }

    @Test
    void insert() {
        GenericDao<Directory> dirDao = new GenericDao<>(Directory.class);
        Directory directory = dirDao.getById(2);
        Video newVideo = new Video("2uf4n74", "Title", LocalDateTime.now(), -1, directory);
        directory.getVideos().add(newVideo);

        int id = dao.insert(newVideo);

        assertNotEquals(0,id);
        Video insertedVideo = dao.getById(id);
        assertEquals("Title", insertedVideo.getTitle());
        assertNotNull(insertedVideo.getDirectory());
        assertEquals("default", insertedVideo.getDirectory().getName());
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void findByPropertyEqual() {
    }

    @Test
    void findByPropertyEqual1() {
    }
}