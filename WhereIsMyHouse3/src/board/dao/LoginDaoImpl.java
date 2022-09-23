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
	public UserDto login(String userId) {
		UserDto userDto=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//#1. Connection 객체 획득
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			//#2. SQL 쿼리문 작성
			sb.append("SELECT *")
			.append("FROM MY_HOUSE.USER WHERE ID=?");
			
			//#2-2. DB에서 해당하는 객체를 얻어 옴
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1,userId);
			
			//#3. 얻어온 객체를 UserDto에 담음
			System.out.println(sb.toString());
			rs=pstmt.executeQuery();
			if (rs.next()) {
				userDto=new UserDto();
				
				userDto.setId(rs.getString("id"));
				userDto.setPassword(rs.getString("password"));
				userDto.setName(rs.getString("name"));
				userDto.setAddress(rs.getString("address"));
				userDto.setPhone(rs.getString("phone"));
				userDto.setPermission(rs.getInt("password"));
				
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
