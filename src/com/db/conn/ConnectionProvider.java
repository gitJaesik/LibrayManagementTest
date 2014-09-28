package com.db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	
	//일반 커넥션
	public static Connection getConnection() throws SQLException{
		
		Connection con=null;

	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");

		con=DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE", 
				"scott", 
				"tiger");
		    
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
	    }catch(SQLException se){
		    se.printStackTrace();
	    }
		
		return con;
	}	
	
	//커넥션 풀을 사용하는 경우
	public static Connection getConnectionPool() throws SQLException{
		
		Connection con=null;
		DataSource ds=null;

	try{
			Context initCtx = new InitialContext();
			Context envCtx=(Context)initCtx.lookup("java:comp/env");
			
			ds = (DataSource)envCtx.lookup("jdbc/webDB");
		    con=ds.getConnection();
		    
		    System.out.println("연결되었습니다.");
		    
		}catch(NamingException ne){
		    ne.printStackTrace();
	    }catch(SQLException se){
		    se.printStackTrace();
	    }
		
		return con;
	}
}
