/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft9000.SQLMate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author profnagy
 */
public class SqlTable extends SqlObject implements ISchemaValidator {

    private String TableName = "";

    private List<SqlColumn> Columns = new ArrayList<SqlColumn>();

    @Override
    public void validate() throws ExInvalid {
        if (getTableName().isEmpty()) {
            throw new ExInvalid("Table name is not defined.");
        }
        boolean bHasID = false;
        for (SqlColumn ref : this.getColumns()) {
            ref.validate();
            if (ref.getColumnName().equals("ID")) {
                bHasID = true;
            }
        }
        if (bHasID == false) {
            throw new ExInvalid("ID field is missing. Please add an integral field named 'ID'.");
        }
    }

    public String writeTableCreate() throws ExInvalid {
        validate();
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append(this.getTableName());
        sb.append(" (");
        for (int ss = 0; ss < this.getColumns().size(); ss++) {
            if (ss != 0) {
                sb.append(", ");
            }
            SqlColumn ref = this.getColumns().get(ss);
            sb.append(ref.getColumnName());
            sb.append(" ");
            sb.append(ref.getSqlType().name());
            if (ref.isPrimaryKey()) {
                sb.append(" PRIMARY KEY AUTOINCREMENT");
            }
        }
        sb.append(") ;");
        return sb.toString();
    }

    public String writeTableDrop() throws ExInvalid {
        validate();
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE IF EXISTS ");
        sb.append(this.getTableName());
        sb.append(" ;");
        return sb.toString();
    }

    public String writeColumnInfo() throws ExInvalid {
        validate();
        StringBuilder sb = new StringBuilder();
        sb.append("\n\npublic static List<SqlColumn> GetFieldInfo() {\n");
        sb.append("\tList<SqlColumn> result = new ArrayList<SqlColumn>();\n");
        for (SqlColumn ref : this.Columns) {
            sb.append("\tresult.add(new SqlColumn(\"" + ref.getColumnName() + "\", EColumnType." + ref.getSqlType().toString() + "));\n");
        }
        sb.append("\treturn result;\n}\n\n");
        return sb.toString();
    }

    /**
     * @return the TableName
     */
    public String getTableName() {
        return TableName;
    }

    /**
     * @param TableName the TableName to set
     */
    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    /**
     * @return the Columns
     */
    public List<SqlColumn> getColumns() {
        return Columns;
    }

    /**
     * @param Columns the Columns to set
     */
    public void setColumns(List<SqlColumn> Columns) {
        this.Columns = Columns;
    }
}
