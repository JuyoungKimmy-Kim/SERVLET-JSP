package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import board.common.DBManager;
import board.dto.UserDto;


/*
 * Dao의 역할은 Email을 받으면 Email이 있는 정보를 return --> 
 * Service의 역할은 Dao에서 받은 정보를 통해 Email & Password 확인 
 * --> Service쪽에서 Business Logic 처리
 * 
 */

// --Singleton Design Pattern
public class LoginDaoImpl implements LoginDao {

	private static LoginDaoImpl instance=new LoginDaoImpl();
	private LoginDaoImpl () {}
	
	public static LoginDaoImpl getInstance() {
		return instance;
	}

	@Override
	public UserDto login(String userEmail) {
		UserDto userDto=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//#1. Connection 객체 획득
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			//#2. SQL 쿼리문 작성
			sb.append("SELECT USER_SEQ,USER_NAME,USER_PASSWORD,USER_EMAIL,USER_PROFILE_IMAGE_URL,USER_REGISTER_DATE\n")
			.append("FROM USERS WHERE USER_EMAIL=?");
			
			//#2-2. DB에서 해당하는 객체를 얻어 옴
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1,userEmail);
			
			//#3. 얻어온 객체를 UserDto에 담음
			System.out.println(sb.toString());
			rs=pstmt.executeQuery();
			if (rs.next()) {
				userDto=new UserDto();
				userDto.setUserSeq(rs.getInt("USER_SEQ"));
				userDto.setUserName(rs.getString("USER_NAME"));
				userDto.setUserPassword(rs.getString("USER_PASSWORD"));
				userDto.setUserEmail(rs.getString("USER_EMAIL"));
				userDto.setUserProfileImageUrl(rs.getString("USER_PROFILE_IMAGE_URL"));
				userDto.setUserRegisterDate(rs.getDate("USER_REGISTER_DATE"));
				
				System.out.println("여기"+userDto);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			// Connection 객체 반납
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return userDto;
	}
	


}
