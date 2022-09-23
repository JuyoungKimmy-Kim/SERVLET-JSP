package board.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	
	//#1. Connection 객체를 얻어서 리턴
	public static Connection getConnection() {
		Connection con=null;
		try {
			//#1-2. context.xml에 접근
			Context context=(Context)new InitialContext().lookup("java:comp/env/");
			
			// #1-3. Resource중 jdbc/board 에 접근
			DataSource dataSource=(DataSource)context.lookup("jdbc/board");
			// --> DataSource == Connection pool
			con=dataSource.getConnection();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//#2. Connection 객체를 반납받는 
	public static void releaseConnection (ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs!=null) rs.close();
			if (pstmt!=null) pstmt.close();
			if (con!=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
