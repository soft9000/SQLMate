# SQLMate for Java
Rapidly generate a DAO for SQLite.

Project: https://github.com/soft9000/SQLMate

Tossed this together to speed-up the creation of a lot of SQLite3 for Java / JDBC recently. These classes will generate the basic C.R.U.D operations for a DAO. Schema creation & drop as well.

The implementation is designed for super-fast growth. 

Thought I would share the package. 

Demonstration on how to use the package is in [GenTestTable.java](https://github.com/soft9000/SQLMate-for-Java/blob/gh-pages/src/com/soft9000/TestDb/GenTestTable.java). Output class from same is in [SqlTestTable.java](https://github.com/soft9000/SQLMate-for-Java/blob/gh-pages/src/com/soft9000/TestDb/SqlTestTable.java).


NEW: 2024 - We're now able to:
1. [Dynamically SELECT Columns](https://github.com/soft9000/SQLMate-for-Java/blob/gh-pages/src/com/soft9000b/SqlDynamics/Dynamic.java).
2. Dynamically detect aliases, as well as to
3. Use [a key / value pair](https://github.com/soft9000/SQLMate-for-Java/blob/bfb230f6ba11fd397c995e52cb4188f950e0ef50/src/com/soft9000/TestDb/SqlTestTable.java#L20) to [any generated](https://github.com/soft9000/SQLMate-for-Java/blob/bfb230f6ba11fd397c995e52cb4188f950e0ef50/src/com/soft9000/TestDb/SqlTestTable.java#L208) DAO Column.

Feel free to join us on [Facebook](https://www.facebook.com/randall.nagy).


Cheers,


Randall Nagy
-- [Soft9000.com](http://soft9000.com)


If you're into Python, you might also enjoy [PyDAO](https://github.com/soft9000/PyDAO).

## zSupport?
If you want to support the effort, I seek no donations. Instead, simply feel free to purchase one of [my educational](https://www.udemy.com/user/randallnagy2/) or [printed](https://www.amazon.com/Randall-Nagy/e/B08ZJLH1VN?ref=sr_ntt_srch_lnk_1&qid=1660050704&sr=8-1) productions?

