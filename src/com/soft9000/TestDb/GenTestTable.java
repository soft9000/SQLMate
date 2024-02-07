/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft9000.TestDb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.soft9000.SQLMate.EColumnType;
import com.soft9000.SQLMate.ExInvalid;
import com.soft9000.SQLMate.SqlCodeJavaJdbc;
import com.soft9000.SQLMate.SqlColumn;
import com.soft9000.SQLMate.SourceCodeFormatter;
import com.soft9000b.file.TextLineWriter;
import java.io.File;

/**
 * How to create a DAO - basic training.
 * 
 * @author profnagy
 */
public class GenTestTable {

    public static void main(String... args) throws ExInvalid {
        SqlCodeJavaJdbc test = new SqlCodeJavaJdbc();
        test.getTable().setTableName("TestTable");
        test.setPackageName("com.soft9000.TestDb"); // NEW
        // NEW: test.setWantMainTest(false);

        test.getTable().getColumns().add(new SqlColumn("ID", EColumnType.Integer));
        test.getTable().getColumns().add(new SqlColumn("TextField", EColumnType.Text));
        test.getTable().getColumns().add(new SqlColumn("IntField", EColumnType.Integer));
        test.getTable().getColumns().add(new SqlColumn("DoubleField", EColumnType.Real));
        test.getTable().getColumns().add(new SqlColumn("LongField", EColumnType.Long));
        test.getTable().getColumns().add(new SqlColumn("BlobField", EColumnType.Blob));

        test.getConn().setDbFileName("TestTable.sqlt3");
        test.getConn().setDbFilePath("./");
        StringBuilder sb = new StringBuilder();
        test.write(sb);

        // Still a tad ugly - needs some work - feel free to omit / manually format output in your IDE;
        String code = SourceCodeFormatter.FormatCode(sb.toString());
        TextLineWriter writer = new TextLineWriter(new File("./src/com/soft9000/TestDB/SqlTestTable.java"));
        if (writer.open()) {
            writer.write(code);
        }
        writer.close();
    }
}
