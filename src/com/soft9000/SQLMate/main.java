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
public class main {

    public static void main(String... args) throws ExInvalid {
        SqlCodeJavaJdbc test = new SqlCodeJavaJdbc();
        test.getTable().setTableName("TestTable");
        test.getTable().getColumns().add(new SqlColumn("ID", EColumnType.Integer));
        test.getTable().getColumns().add(new SqlColumn("Name", EColumnType.Text));
        test.getTable().getColumns().add(new SqlColumn("Big", EColumnType.Blob));
        test.getConn().setDbFileName("TestDb");
        test.getConn().setDbFilePath("com/soft9000");
        StringBuilder sb = new StringBuilder();
        test.write(sb);

        // Still a tad ugly - needs some work - feel free to omit / manually format output in your IDE;
        System.out.println(SourceCodeFormatter.FormatCode(sb.toString()));
    }
}
