package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.common.DBManager;
import board.dto.GugunDto;
import board.dto.SidoDto;

public class GugunDaoImpl implements GugunDao{

	private static GugunDaoImpl instance=new GugunDaoImpl();
	private GugunDaoImpl() {}
	
	public static GugunDaoImpl getInstance() {
		return instance;
	}
	@Override
	public List<GugunDto> gugunList(String code) {
		List<GugunDto> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT * FROM MY_HOUSE.GUGUN_CODE ")
			.append( " WHERE SIDO_CODE=? ");
			pstmt=con.prepareStatement(sb.toString());
			
			pstmt.setString(1, code);
			rs=pstmt.executeQuery(); 
			
			
			while (rs.next()) {
				GugunDto gugunDto;
				gugunDto=new GugunDto();
				gugunDto.setCode(rs.getString("code"));
				gugunDto.setName(rs.getString("name"));
				gugunDto.setSidoCode(rs.getString("sido_code"));

				list.add(gugunDto);
				
				System.out.println("==========" +list);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		return list;
	}
	
}
