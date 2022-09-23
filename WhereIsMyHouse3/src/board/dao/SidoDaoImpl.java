package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.common.DBManager;
import board.dto.SidoDto;

public class SidoDaoImpl implements SidoDao {

	private static SidoDaoImpl instance=new SidoDaoImpl();
	private SidoDaoImpl() {}
	
	public static SidoDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<SidoDto> sidoList() {
		List<SidoDto> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT * FROM MY_HOUSE.SIDO_CODE ");
			pstmt=con.prepareStatement(sb.toString());

			
			rs=pstmt.executeQuery(); 
			while (rs.next()) {
				SidoDto sidoDto;
				sidoDto=new SidoDto();
				sidoDto.setCode(rs.getString("code"));
				sidoDto.setName(rs.getString("name"));

				list.add(sidoDto);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}


		return list;
	}

}
