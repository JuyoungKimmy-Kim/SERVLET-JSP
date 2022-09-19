package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import board.common.DBManager;
import board.dto.UserDto;

public class UserDaoImpl implements UserDao{

	private static UserDaoImpl instance=new UserDaoImpl();
	private UserDaoImpl () {}
	
	public static UserDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public int userRegister(UserDto userDto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append("INSERT INTO BOARD.USERS (USER_SEQ, USER_NAME, USER_PASSWORD, USER_EMAIL, USER_PROFILE_IMAGE_URL, USER_REGISTER_DATE)\n")
			.append("VALUES (?,?,?,?,?,?)");
			
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, userDto.getUserSeq());
			pstmt.setString(2, userDto.getUserName());
			pstmt.setString(3, userDto.getUserPassword());
			pstmt.setString(4, userDto.getUserEmail());
			pstmt.setString(5, userDto.getUserProfileImageUrl());
			//pstmt.setDate(6, userDto.getUserRegisterDate());
							
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return 0;
	}
	
}
