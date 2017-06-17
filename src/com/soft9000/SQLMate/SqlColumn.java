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
public class SqlColumn extends SqlObject implements ISchemaValidator {

    private String ColumnName = "";
    private EColumnType sqlType = EColumnType.Text;

    private boolean AllowNull = true;
    private boolean PrimaryKey = false;
    private boolean SqlSelectable = false;
    private boolean SqlUpdatable = false;
    private boolean SqlDeleteable = false;
    private String utn = "";
    private boolean bNotNull = false;
    private boolean bKey = false;
    private boolean bAuto = false;

    public SqlColumn(String name, EColumnType eColumnType) {
        this.ColumnName = name;
        this.sqlType = eColumnType;
        if (name.toUpperCase().equals("ID")) {
            this.PrimaryKey = true;
        }
    }

    // TODO: Default Value.
    // boolean SqlOptional = false;
    @Override
    public void validate() throws ExInvalid {
        if (getColumnName().isEmpty()) {
            throw new ExInvalid("Empty column name detected.");
        }
    }

    /**
     * @return the ColumnName
     */
    public String getColumnName() {
        return ColumnName;
    }

    /**
     * @param ColumnName the ColumnName to set
     */
    public void setColumnName(String ColumnName) {
        this.ColumnName = ColumnName;
    }

    /**
     * @return the sqlType
     */
    public EColumnType getSqlType() {
        return sqlType;
    }

    /**
     * @param sqlType the sqlType to set
     */
    public void setSqlType(EColumnType sqlType) {
        this.sqlType = sqlType;
    }

    /**
     * @return the AllowNull
     */
    public boolean isAllowNull() {
        return AllowNull;
    }

    /**
     * @param AllowNull the AllowNull to set
     */
    public void setAllowNull(boolean AllowNull) {
        this.AllowNull = AllowNull;
    }

    /**
     * @return the PrimaryKey
     */
    public boolean isPrimaryKey() {
        return PrimaryKey;
    }

    /**
     * @param PrimaryKey the PrimaryKey to set
     */
    public void setPrimaryKey(boolean PrimaryKey) {
        this.PrimaryKey = PrimaryKey;
    }

    /**
     * @return the SqlSelectable
     */
    public boolean isSqlSelectable() {
        return SqlSelectable;
    }

    /**
     * @param SqlSelectable the SqlSelectable to set
     */
    public void setSqlSelectable(boolean SqlSelectable) {
        this.SqlSelectable = SqlSelectable;
    }

    /**
     * @return the SqlUpdatable
     */
    public boolean isSqlUpdatable() {
        return SqlUpdatable;
    }

    /**
     * @param SqlUpdatable the SqlUpdatable to set
     */
    public void setSqlUpdatable(boolean SqlUpdatable) {
        this.SqlUpdatable = SqlUpdatable;
    }

    /**
     * @return the SqlDeleteable
     */
    public boolean isSqlDeleteable() {
        return SqlDeleteable;
    }

    /**
     * @param SqlDeleteable the SqlDeleteable to set
     */
    public void setSqlDeleteable(boolean SqlDeleteable) {
        this.SqlDeleteable = SqlDeleteable;
    }

    public void setUserDbTypeName(String type) {
        if (type == null || type.isEmpty()) {
            return;
        }
        this.utn = type;
    }

    public void setNotNull() {
        this.bNotNull = true;
    }

    public void setKey() {
        this.bKey = true;
    }

    public void setAutoincrement() {
        this.bAuto = true;
    }

    /**
     * Check to see if only the column type & name are the same.
     *
     * @param field Field to check.
     * @return True of both column name & column type are the saem.
     */
    public boolean matchColumn(final SqlColumn field) {
        if (this.sqlType != field.sqlType) {
            return false;
        }
        if (this.ColumnName.equals(field.ColumnName) == false) {
            return false;
        }
        return true;
    }

}
