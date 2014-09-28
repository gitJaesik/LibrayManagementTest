package com.sql;

public class Sql {
	public static final String BOOK_INSERT = "INSERT INTO Book VALUES (?, ?, ?, ?)";
	public static final String BOOK_SELECT_BY_NO = "SELECT * FROM BOOK WHERE NO=?";
	public static final String BOOK_SELECT_ALL = "SELECT * FROM BOOK ORDER BY NO";
	public static final String BOOK_SELECT_BY_NAME = "SELECT * FROM BOOK WHERE NAME LIKE ?";
	public static final String BOOK_UPDATE = "UPDATE BOOK SET NAME=?, AUTHOR=?, PUBLISHER=? WHERE NO=?";
	public static final String BOOK_DELETE = "DELETE FROM BOOK WHERE NO=?";
}
