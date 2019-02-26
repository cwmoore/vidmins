package com.vidmins.persistence;

import com.vidmins.persistence.GenericDao;
import com.vidmins.entity.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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