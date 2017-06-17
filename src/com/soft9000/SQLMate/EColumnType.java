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
public enum EColumnType {

    Integer("int", true),
    Long("long", true),
    Text("String", false),
    Real("float", true),
    Blob("byte[]", false);

    private String javaType;
    private boolean primitiveJavaType;

    private EColumnType(String javaType, boolean primitiveJavaType) {
        this.javaType = javaType;
        this.primitiveJavaType = primitiveJavaType;
    }

    public String getJavaType() {
        return this.javaType;
    }

    public boolean isJavaPrimitive() {
        return primitiveJavaType;
    }

    public static EColumnType FindType(String utn) {
        if (utn == null || utn.isEmpty()) {
            return null;
        }
        utn = utn.toLowerCase().trim();
        if (utn.indexOf("long") != -1) {
            return EColumnType.Long;
        }
        if (utn.indexOf("int") != -1) {
            return EColumnType.Integer;
        }
        if (utn.indexOf("short") != -1) {
            return EColumnType.Integer;
        }
        if (utn.indexOf("num") != -1) {
            return EColumnType.Integer;
        }

        if (utn.indexOf("real") != -1) {
            return EColumnType.Real;
        }
        if (utn.indexOf("float") != -1) {
            return EColumnType.Real;
        }
        if (utn.indexOf("doub") != -1) {
            return EColumnType.Real;
        }
        if (utn.indexOf("money") != -1) {
            return EColumnType.Real;
        }

        if (utn.indexOf("blob") != -1) {
            return EColumnType.Blob;
        }

        if (utn.indexOf("clob") != -1) {
            return EColumnType.Text;
        }

        return EColumnType.Text;
    }

    public String mkJavaParam(int ss) throws ExInvalid {
        switch (this) {
            case Long:
            case Integer:
                return "" + ss;
            case Text:
                return "\"data" + ss + "\"";
            case Real:
                return "" + ss + "." + ss + "f";
            case Blob:
                return "new byte[0]";
            default:
                throw new ExInvalid("Unsupported type: " + this.name());
        }
    }

    public static String GetDefaultJavaValue(final SqlColumn ref) throws ExInvalid {
        if (ref == null) {
            return "void";
        }
        switch (ref.getSqlType()) {
            case Long:
                if (ref.getColumnName().equals("ID")) {
                    return "NO_ID";
                }
                return "0L";
            case Integer:
                if (ref.getColumnName().equals("ID")) {
                    return "NO_ID";
                }
                return "0";
            case Text:
                return "\"\"";
            case Real:
                return "0.0";
            case Blob:
                return "new byte[0]";
            default:
                throw new ExInvalid("Invalid Type: '" + ref.getSqlType().name() + "' !");
        }
    }
}
