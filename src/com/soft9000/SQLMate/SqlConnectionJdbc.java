/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft9000.SQLMate;

/**
 *
 * @author profnagy
 */
public class SqlConnectionJdbc extends SqlObject implements ISchemaValidator {

    private String dbFilePath = "/sqlite";
    private String dbFileName = "default.sqlt3";
    //String dbDatabasePassword = "";

    @Override
    public void validate() throws ExInvalid {
        if (this.getDbFileName().isEmpty()) {
            throw new ExInvalid("Database File Name Is Empty");
        }
    }

    public void writeConnectionString(StringBuilder sb) throws ExInvalid {
        validate();
        sb.append("jdbc:sqlite:");
        sb.append(getDbFilePath());
        sb.append("/");
        sb.append(getDbFileName());
    }

    public void getJavaPackageName(StringBuilder sb) throws ExInvalid {
        StringBuilder pkg = new StringBuilder();
        pkg.append(getDbFilePath());
        pkg.append("/");
        pkg.append(getDbFileName());
        String str = pkg.toString();
        str = str.replaceAll("/", ".");
        sb.append(str);
    }

    /**
     * @return the dbFilePath
     */
    public String getDbFilePath() {
        return dbFilePath;
    }

    /**
     * @param dbFilePath the dbFilePath to set
     */
    public void setDbFilePath(String dbFilePath) {
        this.dbFilePath = dbFilePath;
    }

    /**
     * @return the dbFileName
     */
    public String getDbFileName() {
        return dbFileName;
    }

    /**
     * @param dbFileName the dbFileName to set
     */
    public void setDbFileName(String dbFileName) {
        this.dbFileName = dbFileName;
    }

}
