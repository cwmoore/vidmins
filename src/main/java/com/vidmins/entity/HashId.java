package com.vidmins.entity;

import com.vidmins.entity.HashIdAble;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 *
 *
 * Workflow:
 * Before creating an update or delete form (or any action other that changes the database representation of an entity)
 * and to create random layer over public facing database identifiers (a hash mapped to an id, instead of integer id)
 * HashId maps the hash id from the public (html forms) facing side to the object/id on the server.
 * This prevents URL parameter 'attacks' or unsupported usage from reaching site data.
 * May be extended to create a token based access system for verifying new user email.
 */

@Entity(name="HashId")
@Table(name = "hash_ids")
public class HashId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String hashId;
    private int objectId;
    private String objectType;

    public HashId() {}

    public HashId(int objectId, String objectType) {
        this();
        this.objectId = objectId;
        this.objectType = objectType;
    }

    public HashId(String hashId, int objectId, String objectType) {
        this();
        this.hashId = hashId;
        this.objectId = objectId;
        this.objectType = objectType;
    }

    public HashId(HashIdAble obj) {
        this();
        this.hashId = obj.getHashId();
        this.objectId = obj.getId();
        this.objectType = obj.getHashIdObjectType();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashId hashId1 = (HashId) o;

        if (id != hashId1.id) return false;
        if (objectId != hashId1.objectId) return false;
        if (hashId != null ? !hashId.equals(hashId1.hashId) : hashId1.hashId != null) return false;
        return objectType != null ? objectType.equals(hashId1.objectType) : hashId1.objectType == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (hashId != null ? hashId.hashCode() : 0);
        result = 31 * result + objectId;
        result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HashId{" +
                "id=" + id +
                ", hashId='" + hashId + '\'' +
                ", objectId=" + objectId +
                ", objectType='" + objectType + '\'' +
                '}';
    }
}
