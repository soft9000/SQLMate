/* THIS CLASS WAS GENERATED BY SQLMate .07b
   The targeted SQL technology is SQLite3.
   Test case main.java documents tested level of support.
   Your project will need to include sqlite-jdbc.jar:
   -We used sqlite-jdbc-3.8.11.2.jar
*/

package com.soft9000.TestDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import com.soft9000.SQLMate.EColumnType;
import com.soft9000.SQLMate.SqlColumn;
import com.soft9000b.tv.TagPair;

public class SqlTestTable {
   public static final int NO_ID = -1;

   public static String CONN_STRING = "jdbc:sqlite:.//TestTable.sqlt3";

   public static Connection Connect() throws ClassNotFoundException, SQLException {
      return Connect(CONN_STRING);
   }

   public static Connection Connect(String fileName) throws ClassNotFoundException, SQLException {
      if (fileName == null) {
         throw new SQLException("File name cannot be NULL.");
      }

      Class.forName("org.sqlite.JDBC");
      Connection conn = DriverManager.getConnection(fileName);
      return conn;
   }

   public static void Cleanup(Connection conn) throws SQLException {
      if (conn == null) {
         throw new SQLException("Connection Object Reference is NULL.");
      }

      Statement ref = conn.createStatement();
      ref.execute("VACUUM");
   }

   int ID = NO_ID;
   String TextField = "";
   int IntField = 0;
   double DoubleField = 0.0;
   long LongField = 0L;
   byte[] BlobField = new byte[0];
   public int getID() {
      return this.ID;
   }

   public String getTextField() {
      return this.TextField;
   }

   public int getIntField() {
      return this.IntField;
   }

   public double getDoubleField() {
      return this.DoubleField;
   }

   public long getLongField() {
      return this.LongField;
   }

   public byte[] getBlobField() {
      return this.BlobField;
   }

   public boolean setID(int  param) {
      this.ID = param;
      return true;
   }

   public boolean setTextField(String  param) {
      if(param == null)
      return false;
      this.TextField = param;
      return true;
   }

   public boolean setIntField(int  param) {
      this.IntField = param;
      return true;
   }

   public boolean setDoubleField(double  param) {
      this.DoubleField = param;
      return true;
   }

   public boolean setLongField(long  param) {
      this.LongField = param;
      return true;
   }

   public boolean setBlobField(byte[]  param) {
      if(param == null)
      return false;
      this.BlobField = param;
      return true;
   }

   public boolean insert(Connection conn) throws SQLException {
      if (conn == null) {
         throw new SQLException("Connection Object Reference is NULL.");
      }

      PreparedStatement ref = conn.prepareStatement("INSERT INTO TestTable (TextField, IntField, DoubleField, LongField, BlobField ) VALUES (?, ?, ?, ?, ?) ;");
      ref.setString(1, this.getTextField());
      ref.setInt(2, this.getIntField());
      ref.setDouble(3, this.getDoubleField());
      ref.setLong(4, this.getLongField());
      ref.setBytes(5, this.getBlobField());
      int result = ref.executeUpdate();
      return (result == 1);
   }

   public int lastInsert(Connection conn) throws SQLException {
      Statement smt = conn.createStatement();
      ResultSet set = smt.executeQuery("SELECT ID From " + sTableName + " DESC LIMIT 1;");
      int result = NO_ID;
      while (set.next()) {
         result = set.getInt("ID");
      }
      return result;
   }

   public List<SqlTestTable> select(Connection conn, String sqlSelect) throws SQLException {
      if (conn == null) {
         throw new SQLException("Connection Object Reference is NULL.");
      }

      Statement smt = conn.createStatement();
      ResultSet set = smt.executeQuery(sqlSelect);
      ArrayList<SqlTestTable> result = new ArrayList<SqlTestTable>();
      while (set.next()) {
         SqlTestTable ref = new SqlTestTable();
         ref.setID(set.getInt("ID"));
         ref.setTextField(set.getString("TextField"));
         ref.setIntField(set.getInt("IntField"));
         ref.setDoubleField(set.getDouble("DoubleField"));
         ref.setLongField(set.getLong("LongField"));
         ref.setBlobField(set.getBytes("BlobField"));
         result.add(ref);	}

      return result;
   }

   public boolean update(Connection conn) throws SQLException {
      if (conn == null) {
         throw new SQLException("Connection Object Reference is NULL.");
      }

      PreparedStatement ref = conn.prepareStatement("UPDATE TestTable SET TextField = ?, IntField = ?, DoubleField = ?, LongField = ?, BlobField = ? WHERE ID = ? ;");
      ref.setString(1, this.getTextField());
      ref.setInt(2, this.getIntField());
      ref.setDouble(3, this.getDoubleField());
      ref.setLong(4, this.getLongField());
      ref.setBytes(5, this.getBlobField());
      ref.setInt(6, this.getID());
      int result = ref.executeUpdate();
      return (result == 1);
   }

   public boolean delete(Connection conn) throws SQLException {
      if (conn == null) {
         throw new SQLException("Connection Object Reference is NULL.");
      }

      if (this.getID() == NO_ID) {
         throw new SQLException("Object ID is NULL.");
      }

      Statement ref = conn.createStatement();
      int result = ref.executeUpdate("DELETE FROM TestTable WHERE ID = " + this.getID() + " ;");
      return (result == 1);
   }

   public void createTable(Connection conn) throws SQLException {
      if (conn == null) {
         throw new SQLException("Connection Object Reference is NULL.");
      }

      Statement ref = conn.createStatement();	String sql = "CREATE TABLE IF NOT EXISTS TestTable (\nID Integer PRIMARY KEY AUTOINCREMENT,\nTextField Text,\nIntField Integer,\nDoubleField Real,\nLongField Long,\nBlobField Blob\n);";
      ref.execute(sql);
   }

   public void deleteTable(Connection conn) throws SQLException {
      if (conn == null) {
         throw new SQLException("Connection Object Reference is NULL.");
      }

      Statement ref = conn.createStatement();	String sql = "DROP TABLE IF EXISTS TestTable ;";
      ref.execute(sql);
   }

   public boolean set(TagPair pair) {
      if(pair == null || pair.isNull()) return false;
      try {switch(pair.getTag().toLowerCase()) {
            case "id": {
               return setID(Integer.parseInt(pair.getValue()));
            }
            case "textfield": {
               return setTextField(pair.getValue());
            }
            case "intfield": {
               return setIntField(Integer.parseInt(pair.getValue()));
            }
            case "doublefield": {
               return setDoubleField(Double.parseDouble(pair.getValue()));
            }
            case "longfield": {
               return setLongField(Long.parseLong(pair.getValue()));
            }
            case "blobfield": {
               return setBlobField(pair.getValue().getBytes());
            }
         }
      } catch(Exception ex) {}
      return false;
   }


   final String sTableName = "TestTable";

   public static List<SqlColumn> GetFieldInfo() {
      List<SqlColumn> result = new ArrayList<SqlColumn>();
      result.add(new SqlColumn("ID", EColumnType.Integer));
      result.add(new SqlColumn("TextField", EColumnType.Text));
      result.add(new SqlColumn("IntField", EColumnType.Integer));
      result.add(new SqlColumn("DoubleField", EColumnType.Real));
      result.add(new SqlColumn("LongField", EColumnType.Long));
      result.add(new SqlColumn("BlobField", EColumnType.Blob));
      return result;
   }



   public String getTableName() {
      return sTableName;
   }

   public static void main(String ... args) throws ClassNotFoundException, SQLException {
      Connection conn = Connect("jdbc:sqlite:~test.tmp");
      SqlTestTable ref = new SqlTestTable();

// TEST TABLE OPS:
      ref.deleteTable(conn);
      ref.createTable(conn);

// TEST INSERT:
      ref.setID(1);
      ref.setTextField("data2");
      ref.setIntField(3);
      ref.setDoubleField(4.4f);
      ref.setLongField(5);
      ref.setBlobField(new byte[0]);
      if(ref.getID() != 1) {
         throw new SQLException("Test Case 'checker 1.1' Error");
      }

      if(ref.getTextField().equals("data2") == false) {
         throw new SQLException("Test Case 'checker 1.2' Error");
      }

      if(ref.getIntField() != 3) {
         throw new SQLException("Test Case 'checker 1.3' Error");
      }

      if(ref.getDoubleField() != 4.4f) {
         throw new SQLException("Test Case 'checker 1.4' Error");
      }

      if(ref.getLongField() != 5) {
         throw new SQLException("Test Case 'checker 1.5' Error");
      }


// BLOB: Run-time okay - test case scheduled for another release.

      if (ref.insert(conn) == false) {
         throw new SQLException("Test Case 'insert' Error");
      }


// TEST INSERT:
      ref.setID(1);
      ref.setTextField("data2");
      ref.setIntField(3);
      ref.setDoubleField(4.4f);
      ref.setLongField(5);
      ref.setBlobField(new byte[0]);
      if(ref.getID() != 1) {
         throw new SQLException("Test Case 'checker 1.1' Error");
      }

      if(ref.getTextField().equals("data2") == false) {
         throw new SQLException("Test Case 'checker 1.2' Error");
      }

      if(ref.getIntField() != 3) {
         throw new SQLException("Test Case 'checker 1.3' Error");
      }

      if(ref.getDoubleField() != 4.4f) {
         throw new SQLException("Test Case 'checker 1.4' Error");
      }

      if(ref.getLongField() != 5) {
         throw new SQLException("Test Case 'checker 1.5' Error");
      }


// BLOB: Run-time okay - test case scheduled for another release.

      if (ref.insert(conn) == false) {
         throw new SQLException("Test Case 'insert' Error");
      }


// TEST GET LAST INSERT:

      if(ref.lastInsert(conn) == NO_ID) {
         throw new SQLException("Test Case 'lastInsert' Error");
      }


// TEST UPDATE:
      ref.setTextField("data12");
      ref.setIntField(13);
      ref.setDoubleField(14.14f);
      ref.setLongField(15);
      ref.setBlobField(new byte[0]);
      if(ref.update(conn) == false) {
         throw new SQLException("Test Case 'update 1' Error");
      }

      if(ref.getTextField().equals("data12") == false) {
         throw new SQLException("Test Case 'checker 1.12' Error");
      }

      if(ref.getIntField() != 13) {
         throw new SQLException("Test Case 'checker 1.13' Error");
      }

      if(ref.getDoubleField() != 14.14f) {
         throw new SQLException("Test Case 'checker 1.14' Error");
      }

      if(ref.getLongField() != 15) {
         throw new SQLException("Test Case 'checker 1.15' Error");
      }


// BLOB: Run-time okay - test case scheduled for another release.

// TEST DELETE:
      if(ref.delete(conn) == false) {
         throw new SQLException("Test Case 'delete' Error");
      }

      ref.deleteTable(conn);}


}

