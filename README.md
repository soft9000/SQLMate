# SQLMate
**MISSION:** Rapidly generate a Data-Access-Object (D.A.O) for embedding SQLite into any Java Aplication. ***Database Server not required!***

I tossed this together to speed-up the creation of a lot of SQLite3 code recently, I thought that I would share this effort with you. 

These classes will generate the C.R.U.D operations for a DAO (DML.) Schema / SQL Table creation & drop (DDL.) The implementation is designed for super-fast growth. 

Full support for all SQLite (TEXT, Integer, Real, & BLOB/CLOB) built-in data-types, as well. 

<image src="https://github.com/soft9000/SQLMate/blob/gh-pages/_images/SqlMate01b.png">

Project: https://github.com/soft9000/SQLMate

Demonstration on how to use the package is in [GenTestTable.java](https://github.com/soft9000/SQLMate-for-Java/blob/gh-pages/src/com/soft9000/TestDb/GenTestTable.java). 

Output class from same is in [SqlTestTable.java](https://github.com/soft9000/SQLMate-for-Java/blob/gh-pages/src/com/soft9000/TestDb/SqlTestTable.java).


NEW: 2024 - We're now able to:
1. [Dynamically SELECT Columns](https://github.com/soft9000/SQLMate-for-Java/blob/gh-pages/src/com/soft9000b/SqlDynamics/Dynamic.java).
2. Dynamically detect aliases, as well as to:
3. Use [a key / value pair](https://github.com/soft9000/SQLMate-for-Java/blob/bfb230f6ba11fd397c995e52cb4188f950e0ef50/src/com/soft9000/TestDb/SqlTestTable.java#L20) to populate [any generated](https://github.com/soft9000/SQLMate-for-Java/blob/bfb230f6ba11fd397c995e52cb4188f950e0ef50/src/com/soft9000/TestDb/SqlTestTable.java#L208) Java Class.


Feel free to join us on [Facebook](https://www.facebook.com/randall.nagy).


Cheers,


Randall Nagy
-- [Soft9000.com](http://soft9000.com)


If you're into Python, you might also enjoy [PyDAO](https://github.com/soft9000/PyDAO).

## zSupport?
If you want to support the effort, I seek no donations. Instead, simply feel free to purchase one of [my educational](https://www.udemy.com/user/randallnagy2/) or [printed](https://www.amazon.com/Randall-Nagy/e/B08ZJLH1VN?ref=sr_ntt_srch_lnk_1&qid=1660050704&sr=8-1) productions?

