/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft9000b.SqlDynamics;

import com.soft9000.SQLMate.SqlColumn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dynamic {

    public static ArrayList<ArrayList<DynamicPair>> SelectFields(Connection conn, String sqlSelect, List<SqlColumn> fields) {
        try {
            if (conn == null) {
                throw new Exception("Connection Object Reference is NULL.");
            }

            Statement smt = conn.createStatement();
            ResultSet ref = smt.executeQuery(sqlSelect);
            ArrayList<ArrayList<DynamicPair>> results = new ArrayList<>();
            while (ref.next()) {
                ArrayList<DynamicPair> row = new ArrayList();
                for (SqlColumn field : fields) {
                    addIff(row, ref, field.getColumnName());
                    results.add(row);
                }
            }
            return results;
        } catch (Exception ex) {
            Logger.getLogger(Dynamic.class.getName()
            ).log(Level.SEVERE, ex.toString());
            return null;
        }
    }

    static void addIff(ArrayList<DynamicPair> results, ResultSet ref, String ColumnName) {
        DynamicPair tv = new DynamicPair();
        if (addIff(tv, ref, ColumnName)) {
            results.add(tv);
        }
    }

    static boolean addIff(DynamicPair result, ResultSet ref, String ColumnName) {
        try {
            int pos = ref.findColumn(ColumnName);
            result.setTag(ColumnName);
            result.setValue(ref.getString(pos));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
