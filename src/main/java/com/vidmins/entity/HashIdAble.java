package com.vidmins.entity;

import com.vidmins.persistence.GenericDao;

import java.util.UUID;

public abstract class HashIdAble {

    private int id;

    public String getHashId() {
        return UUID.randomUUID().toString();
    }

    public String getHashIdObjectType() {
        return this.getClass().getName();
    }

    public abstract int getId();

}
