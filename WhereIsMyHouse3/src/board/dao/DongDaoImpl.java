package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.common.DBManager;
import board.dto.DongDto;

public class DongDaoImpl implements DongDao{
	
	private static DongDaoImpl instance=new DongDaoImpl();
	private DongDaoImpl() {}
	
	public static DongDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<DongDto> dongList(String gugunCode) {
		
		List<DongDto> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT CODE, NAME, CITY_NAME FROM MY_HOUSE.DONG_CODE ")
			.append( " WHERE GUGUN_CODE=? ");
			pstmt=con.prepareStatement(sb.toString());
			
			pstmt.setString(1, gugunCode);
						rs=pstmt.executeQuery(); 
				
			
			while (rs.next()) {
							
				DongDto dongDto;
				dongDto=new DongDto();
				dongDto.setCode(rs.getString("code"));
				dongDto.setName(rs.getString("name"));
				dongDto.setCityName(rs.getString("city_name"));
				
				list.add(dongDto);
				
				System.out.println(list);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		return list;
	}

}
