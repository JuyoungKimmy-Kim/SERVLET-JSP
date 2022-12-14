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
	
	// 멤버 변수가 하나도 없음 -> thread safe
	
	@Override
	public int userRegister(UserDto userDto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int ret=-1;
		
		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append("INSERT INTO USERS (USER_NAME, USER_PASSWORD, USER_EMAIL, USER_PROFILE_IMAGE_URL, USER_REGISTER_DATE)\n")
			.append("VALUES (?,?,?,'',now())");
			
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, userDto.getUserName());
			pstmt.setString(2, userDto.getUserPassword());
			pstmt.setString(3, userDto.getUserEmail());
					
			ret=pstmt.executeUpdate(); //영향받은 row수 return
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return ret;
	}
	
}
