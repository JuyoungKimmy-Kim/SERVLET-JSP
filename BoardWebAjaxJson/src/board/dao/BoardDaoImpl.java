package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import board.common.DBManager;
import board.dto.BoardDto;

public class BoardDaoImpl implements BoardDao{

	private static BoardDaoImpl instance=new BoardDaoImpl();
	private BoardDaoImpl() {}
	
	public static BoardDaoImpl getInstance () {
		return instance;
	}
	
	@Override
	public int boardInsert(BoardDto boardDto) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int ret=-1;
		
		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append("INSERT INTO BOARD (USER_SEQ, TITLE, CONTENT, REG_DT)")
			.append("VALUES (?,?,?, now())");
			
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, boardDto.getUserSeq());
			pstmt.setString(2, boardDto.getTitle());
			pstmt.setString(3, boardDto.getContent());
	
			ret=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return ret;
	}

}
