/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft9000b.SqlDynamics;

import com.soft9000.SQLMate.SqlColumn;
import com.soft9000b.tv.TagPair;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Smart & sparce SQL Query results. Can dynamically detect "as" fields.
 *
 * @author Randall Nagy
 */
public class Dynamic {

    /**
     * Detect & select results from an SQL Select.
     *
     * @param conn An active database connection.
     * @param sqlSelect The SQL Select statement. Can detect "as" fields.
     * @param fields Dynamic list of field names therein.
     * @return All rows, as detected.
     */
    public static List<ArrayList<TagPair>> SelectFields(Connection conn, String sqlSelect, List<SqlColumn> fields) {
        try {
            if (conn == null) {
                throw new Exception("Connection Object Reference is NULL.");
            }

            List<String> asIf = getDynamicFields(sqlSelect);

            Statement smt = conn.createStatement();
            ResultSet ref = smt.executeQuery(sqlSelect);
            ArrayList<ArrayList<TagPair>> results = new ArrayList<>();
            while (ref.next()) {
                ArrayList<TagPair> row = new ArrayList();
                for (String dy : asIf) {
                    TagPair tv = new TagPair();
                    if (getColumn(tv, ref, dy)) {
                        row.add(tv);
                    }
                }
                for (SqlColumn field : fields) {
                    TagPair tv = new TagPair();
                    if (getColumn(tv, ref, field.getColumnName())) {
                        row.add(tv);
                    }
                }
                results.add(row);
            }
            return results;
        } catch (Exception ex) {
            Logger.getLogger(Dynamic.class.getName()
            ).log(Level.SEVERE, ex.toString());
            return null;
        }
    }

    /**
     * Populate TagPair if, and only if, the column-name is present.
     *
     * @param result A column name + value pair, if present.
     * @param ref The SQL query response.
     * @param ColumnName The field / column name to check for.
     * @return True when the field name is found in the result set.
     */
    static boolean getColumn(TagPair result, ResultSet ref, String ColumnName) {
        try {
            int pos = ref.findColumn(ColumnName);
            result.setTag(ColumnName);
            result.setValue(ref.getString(pos));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Detect any dynamic fields.
     *
     * @param sqlSelect
     * @return The effective database field definition.
     */
    public static List<String> getDynamicFields(String sqlSelect) {
        ArrayList<String> results = new ArrayList<>();
        String[] cols = sqlSelect.replaceAll(",", "").replaceAll(";", "").toLowerCase().split(" as ");
        boolean bskip = true;
        for (String col : cols) {
            if (!bskip) {
                String[] asif = col.split(" ");
                if (asif.length >= 1) {
                    results.add(asif[0]);
                }
            }
            bskip = false;
        }
        return results;
    }

    public static void main(String[] args) {
        for (String val : getDynamicFields("select foo as bar, select bar as net;")) {
            System.out.println(val);
        }
    }

}
