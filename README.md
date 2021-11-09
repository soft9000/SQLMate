# SQLMate
Rapidly generate a DAO for SQLite

Date: 2016/06/07
Project: https://github.com/soft9000/SQLMate

Tossed this together to speed-up the creation of a lot of SQLite3 for Java / JDBC recently. These classes will generate the basic C.R.U.D 
operations for a DAO. Schema creation & drop as well.

Demonstration on how to use the package is in main.java.
   Sample output file from same is in SqlTestTable.java.
   The implementation is designed for super-fast growth. 

Thought I would share the package. 

Feel free to join us on [Facebook](https://www.facebook.com/groups/javatraining9000).


Cheers,


Randall Nagy
-- Soft9000.com


P.S: Note that the DAO closes the connection after each operation. Feel free to remove until we upload the next release.
