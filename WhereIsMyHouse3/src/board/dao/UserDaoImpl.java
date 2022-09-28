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
public class UserDaoImpl implements UserDao {

	private static UserDaoImpl instance=new UserDaoImpl();
	private UserDaoImpl () {}
	
	public static UserDaoImpl getInstance() {
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
				userDto.setCode(rs.getString("code"));	
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			// Connection 객체 반납
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return userDto;
	}
	

	@Override
	public int userRegister(UserDto userDto) {
		
		System.out.println("============ User Dao Impl - Regist ============");
		System.out.println(userDto.toString());
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int ret=-1;
		
		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append("INSERT INTO MY_HOUSE.USER (id, password, name, address, phone, code)\n")
			.append("VALUES (?,?,?,?,?,?)");
			
			pstmt=con.prepareStatement(sb.toString());
			
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, userDto.getPassword());
			pstmt.setString(3, userDto.getName());
			pstmt.setString(4, userDto.getAddress());
			pstmt.setString(5, userDto.getPhone());
			pstmt.setString(6, userDto.getCode());
			
				
			ret=pstmt.executeUpdate(); //영향받은 row수 return
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return ret;
	}

	@Override
	public int modifyPw(String id,String pw) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int ret=-1;
		
		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append("update MY_HOUSE.USER set password=? \n")
			.append("where id=?");
			
			pstmt=con.prepareStatement(sb.toString());
			
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			
			
			ret=pstmt.executeUpdate(); //영향받은 row수 return
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return ret;
	}

	@Override
	public int modify(UserDto userDto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int ret=-1;
		
		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append("update MY_HOUSE.USER set name =? , address = ? , phone = ? \n")
			.append("where id=?");
			
			pstmt=con.prepareStatement(sb.toString());
			
			pstmt.setString(1, userDto.getName());
			pstmt.setString(2, userDto.getAddress());
			pstmt.setString(3, userDto.getPhone());
			pstmt.setString(4, userDto.getId());
			
			
			
			ret=pstmt.executeUpdate(); //영향받은 row수 return
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return ret;
	}

	@Override
	public int delete(String userId) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int ret=-1;
		
		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append("delete from MY_HOUSE.USER where id = ?");
			
			pstmt=con.prepareStatement(sb.toString());
			
			pstmt.setString(1, userId);
			
					
			ret=pstmt.executeUpdate(); //영향받은 row수 return
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return ret;

	}
	
}
