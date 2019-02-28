package com.vidmins.test.util;

public class TestDbReset {

    String sqlFilePath;
    String sql;
    Database database;

    public TestDbReset() {
        sqlFilePath = "reset_db.sql";

        database = Database.getInstance();
        sql = getSql();

    }

    private String getSql() {
        return "";
    }
}
