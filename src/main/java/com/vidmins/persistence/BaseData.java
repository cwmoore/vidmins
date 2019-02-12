package com.vidmins.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseData {
    Logger logger;

    public BaseData() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting " + this.getClass().getName());
    }
}
