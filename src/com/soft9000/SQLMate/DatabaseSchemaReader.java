/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft9000.SQLMate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author profnagy
 */
public class DatabaseSchemaReader {

    public static List<SqlTable> ParseDatabase(Connection conn) throws SQLException, ExInvalid {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT SQL FROM sqlite_master;");
        StringBuilder sb = new StringBuilder();
        while(rs.next()) {
            sb.append(rs.getString(1));
            sb.append(" ");
        }
        return ParseSqliteTables(sb.toString().trim());
    }

    public static List<SqlTable> ParseSqliteTables(String schema) throws ExInvalid {
        schema = schema.replaceAll("\t", " ");
        schema = schema.replaceAll("\n", " ");
        schema = schema.replaceAll("\r", " ");
        int iPos = schema.indexOf("  ");
        while (iPos != -1) {
            schema = schema.replaceAll("  ", " ");
            iPos = schema.indexOf("  ");
        }
        String upper = schema.toUpperCase();
        ArrayList<String> tables = new ArrayList<String>();
        int iStart = -1;
        while (true) {
            int iEnd = upper.indexOf("CREATE TABLE ", iStart + 1);
            if (iEnd == -1) {
                tables.add(schema.substring(iStart));
                break;
            }
            if (iStart < 0) {
                iStart = 0;
            }
            String tblx = schema.substring(iStart, iEnd);
            tables.add(tblx);
            iStart = iEnd;
        }
        ArrayList<SqlTable> result = new ArrayList<SqlTable>();
        for (String ref : tables) {
            SqlTable tbl = ParseSqliteTable(ref);
            if (tbl != null) {
                result.add(tbl);
            }
        }
        return result;
    }

    public static SqlTable ParseSqliteTable(String tbl) throws ExInvalid {
        // OBSERVED - "NOT EXISTS" not preserved ... no worries!
        int iStart = tbl.indexOf("(");
        if (iStart == -1) {
            return null;
        }
        int iEnd = tbl.lastIndexOf(")");
        if (iEnd == -1) {
            return null;
        }
        String[] creAry;
        creAry = tbl.substring(0, iStart).trim().split(" ");
        if (creAry.length != 3) {
            return null;
        }
        SqlTable result = new SqlTable();
        result.setTableName(creAry[2].trim());
        String[] body = tbl.substring(iStart + 1, iEnd).split(",");
        ArrayList<SqlColumn> array = new ArrayList<SqlColumn>();
        for (String scol : body) {
            SqlColumn col = ParseSqliteColumn(scol);
            if (col != null) {
                array.add(col);
            }
        }
        result.setColumns(array);
        return result;
    }

    private static SqlColumn ParseSqliteColumn(String line) throws ExInvalid {
        line = line.trim();
        String[] words = line.split(" ");
        if (words.length < 2) {
            return null;
        }
        String fName = words[0];
        String type = words[1];
        EColumnType zType = EColumnType.FindType(type);
        if (zType == null) {
            return null;
        }
        SqlColumn result = new SqlColumn(fName, zType);
        result.setUserDbTypeName(type);
        for (int ss = 2; ss < words.length; ss++) {
            String str = words[ss].toLowerCase().trim();
            if (str.equals("not")) {
                result.setNotNull();
                continue;
            }
            if (str.equals("key")) {
                result.setKey();
                continue;
            }
            if (str.equals("autoincrement")) {
                result.setAutoincrement();
                continue;
            }
            if (str.equals("default")) {
                throw new ExInvalid("Column: default values not supported.");
            }
            if (str.equals("collate")) {
                throw new ExInvalid("Column: collate not supported.");
            }
            if (str.equals("check")) {
                throw new ExInvalid("Column: check not supported.");
            }
            if (str.equals("unique")) {
                throw new ExInvalid("Column: unique not supported.");
            }
        }
        return result;
    }

    static String zSchema = "table|dayevent2|dayevent2|2|CREATE TABLE dayevent2 (\n"
            + "    'ID' NUMBER(10),\n"
            + "    'DATESTATUS' VARCHAR,\n"
            + "    'DAY' NUMBER(5),\n"
            + "    'DESCRIPTION' VARCHAR,\n"
            + "    'EDITINGSTATUS' VARCHAR,\n"
            + "    'EVENTTYPECODE' VARCHAR,\n"
            + "    'JAVACLASSNAME' VARCHAR,\n"
            + "    'MONTH' NUMBER(5),\n"
            + "    'PWCLAC' VARCHAR,\n"
            + "    'SOURCECODE' VARCHAR,\n"
            + "    'WEIGHT' NUMBER(10),\n"
            + "    'ZYEAR' NUMBER(5)\n"
            + ")\n"
            + "table|biblia|biblia|3|CREATE TABLE biblia (\n"
            + "    'ID' NUMBER(10),\n"
            + "    'BOOKNAME' VARCHAR,\n"
            + "    'CHAPTERNUM' NUMBER(10),\n"
            + "    'QUOTE' CLOB,\n"
            + "    'VERSENUM' NUMBER(10),\n"
            + "    'WEIGHT' NUMBER(10)\n"
            + ")\n"
            + "table|asciiart|asciiart|4|CREATE TABLE asciiart (\n"
            + "    'ID' NUMBER(10),\n"
            + "    'ARTWORK' CLOB,\n"
            + "    'CATEGORY' VARCHAR,\n"
            + "    'CITATION' CLOB,\n"
            + "    'FORMATCODE' CHAR,\n"
            + "    'SOURCE' CLOB,\n"
            + "    'WEIGHT' NUMBER(10)\n"
            + ")\n"
            + "table|words1|words1|5|CREATE TABLE words1 (\n"
            + "    'ID' NUMBER(10),\n"
            + "    'DESCRIPTION' CLOB,\n"
            + "    'SOUNDEX' CLOB,\n"
            + "    'SOURCE' CLOB,\n"
            + "    'WEIGHT' NUMBER(10),\n"
            + "    'WORD' CLOB\n"
            + ")\n"
            + "table|NojQuote4|NojQuote4|7|CREATE TABLE NojQuote4(ID       INTEGER PRIMARY KEY AUTOINCREMENT, GBUCODE  TEXT, UCANHC   LONG, QUOTE    TEXT NOT NULL, AUTHOR   TEXT, CITATION TEXT, SOURCE   TEXT, WEIGHT   TEXT )\n"
            + "table|sqlite_sequence|sqlite_sequence|9|CREATE TABLE sqlite_sequence(name,seq)";

    public static void main(String... atrs) throws ExInvalid {
        List<SqlTable> data = ParseSqliteTables(zSchema);
    }

}
