package com.vidmins.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public abstract class BaseData {
    // String tableName;
    Logger logger;

    public BaseData() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting " + this.getClass().getName());

       // this.tableName = "Base";
    }
/*
    public BaseData fromId(int id) {
        BaseEntity baseEntity = new BaseEntity();
        return baseEntity.findById(id);
    }

    public BaseData fromCriterion(String fieldName, String value) {
        String sql = "SELECT * FROM ${this.tableName} WHERE ${fieldName} = '${value}'";
    }

    public BaseData fromCriteria(Map<String, String> criteria) {

    }

    public BaseData fromDataObject(BaseData dataObj) {
        // given an object of another type, return related rows of this type
    }
*/}
