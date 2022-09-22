package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

			sb.append("INSERT INTO BOARD (USER_SEQ, TITLE, CONTENT, REG_DT, READ_COUNT)")
			.append("VALUES (?,?,?, now(), 0)");
			
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

	@Override
	public List<BoardDto> boardList(int limit, int offset) {
		
		List<BoardDto> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT B.BOARD_ID, B.USER_SEQ, U.USER_NAME, U.USER_PROFILE_IMAGE_URL, B.TITLE, B.CONTENT, B.REG_DT, B.READ_COUNT ")
			.append(" FROM BOARD B, USERS U ")
			.append(" WHERE B.USER_SEQ=U.USER_SEQ ")
			.append(" ORDER BY REG_DT DESC ")
			.append(" LIMIT ? OFFSET ? ");
			
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, limit);
			pstmt.setInt(2, offset);
			
			rs=pstmt.executeQuery(); 
			while (rs.next()) {
				BoardDto boardDto=new BoardDto();
				boardDto.setBoardId(rs.getInt("board_id"));
				boardDto.setUserSeq(rs.getInt("user_seq"));
				boardDto.setUserName(rs.getString("user_name"));
				boardDto.setUserProfileImageUrl(rs.getString("user_profile_image_url"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setRegDt(rs.getTimestamp("reg_dt").toLocalDateTime());
				boardDto.setReadCount(rs.getInt("read_count"));	
				
				list.add(boardDto);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		System.out.println("====BoardDaoImple====\n"+list);

		return list;
	}

	@Override
	public List<BoardDto> boardListSearchWord(int limit, int offset, String searchWord) {
		
		List<BoardDto> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT B.BOARD_ID, B.USER_SEQ, U.USER_NAME, U.USER_PROFILE_IMAGE_URL, B.TITLE, B.CONTENT, B.REG_DT, B.READ_COUNT ")
			.append(" FROM BOARD B, USERS U ")
			.append(" WHERE B.USER_SEQ=U.USER_SEQ ")
			.append(" AND B.TITLE LIKE ? ")	//주의! -> like에 ? 처리
			.append(" ORDER BY REG_DT DESC ")
			.append(" LIMIT ? OFFSET ? ");
			
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, "%"+searchWord+"%");
			pstmt.setInt(2, limit);
			pstmt.setInt(3, offset);
			
			rs=pstmt.executeQuery(); 
			while (rs.next()) {
				BoardDto boardDto=new BoardDto();
				boardDto.setBoardId(rs.getInt("board_id"));
				boardDto.setUserSeq(rs.getInt("user_seq"));
				boardDto.setUserName(rs.getString("user_name"));
				boardDto.setUserProfileImageUrl(rs.getString("user_profile_image_url"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setRegDt(rs.getTimestamp("reg_dt").toLocalDateTime());
				boardDto.setReadCount(rs.getInt("read_count"));	
				
				list.add(boardDto);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return list;
	}

	@Override
	public int boardListTotalCnt() {
		
		int totalCnt=-1;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT COUNT(*) cnt ")
			.append(" FROM BOARD ");

			pstmt=con.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				totalCnt=rs.getInt("cnt");
				//== totalCnt=rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return totalCnt;
	}

	@Override
	public int boardListSearchWordTotalCnt(String searchWord) {
		
		int totalCnt=-1;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT COUNT(*) cnt ")
			.append(" FROM BOARD ")
			.append(" WHERE TITLE LIKE ? ");

			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, "%"+searchWord+"%");
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				totalCnt=rs.getInt("cnt");
				//== totalCnt=rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return totalCnt;
	}

	@Override
	public BoardDto boardDetail(int boardId) {
		
		BoardDto boardDto=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT B.BOARD_ID, B.USER_SEQ, U.USER_NAME, U.USER_PROFILE_IMAGE_URL, B.TITLE, B.CONTENT, B.REG_DT, B.READ_COUNT ")
			.append(" FROM BOARD B, USERS U ")
			.append(" WHERE B.USER_SEQ=U.USER_SEQ ")
			.append(" AND B.BOARD_ID=? ");
			
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, boardId);
			
			rs=pstmt.executeQuery(); 
			if (rs.next()) {
				
				System.out.println("Board 받으러 들어왔어요..? 제발요");
				boardDto=new BoardDto();
				boardDto.setBoardId(rs.getInt("board_id"));
				boardDto.setUserSeq(rs.getInt("user_seq"));
				boardDto.setUserName(rs.getString("user_name"));
				boardDto.setUserProfileImageUrl(rs.getString("user_profile_image_url"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setRegDt(rs.getTimestamp("reg_dt").toLocalDateTime());
				boardDto.setReadCount(rs.getInt("read_count"));	
				
				System.out.println("BoardDto 받아오세요 ? ");
				System.out.println(boardDto);
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return boardDto;
	}
	//=========================추가=========================

	@Override
	public int boardDelete(int boardId) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int ret=-1;

		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" DELETE FROM BOARD WHERE BOARD_ID=? ");

			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, boardId);
			
			ret=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return ret;
	}
	
	@Override
	public int boardUpdate(BoardDto boardDto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int ret=-1;

		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();		
			sb.append(" UPDATE BOARD SET TITLE=?, CONTENT=? WHERE BOARD_ID=? ");

			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, boardDto.getTitle());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setInt(3, boardDto.getBoardId());
			
			ret=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return ret;
	}
	
	//=========================추가=========================




}
