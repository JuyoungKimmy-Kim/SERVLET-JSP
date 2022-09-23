package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.common.DBManager;
import board.dto.HouseInfoDto;

public class HouseInfoDaoImpl implements HouseInfoDao{

	private static HouseInfoDaoImpl instance=new HouseInfoDaoImpl();
	private HouseInfoDaoImpl () {}
	
	public static HouseInfoDaoImpl getInstance () {
		return instance;
	}
	@Override
	public List<HouseInfoDto> houseInfoListByDong(String dongName) {
		
		List<HouseInfoDto> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT * FROM MY_HOUSE.HOUSEINFO ")
			.append( " WHERE DONG=? ");
			pstmt=con.prepareStatement(sb.toString());
			
			pstmt.setString(1, dongName);
			rs=pstmt.executeQuery(); 
				
			while (rs.next()) {
				HouseInfoDto houseInfoDto;
				houseInfoDto=new HouseInfoDto();
				
				houseInfoDto.setNo(rs.getInt("no"));
				houseInfoDto.setDong(rs.getString("dong"));
				houseInfoDto.setAptName(rs.getString("AptName"));
				houseInfoDto.setCode(rs.getString("code"));
				houseInfoDto.setBuildYear(rs.getString("buildYear"));
				houseInfoDto.setJibun(rs.getString("jibun"));
				houseInfoDto.setLat(rs.getString("lat"));
				houseInfoDto.setLng(rs.getString("lng"));
				
				list.add(houseInfoDto);
				
				System.out.println(list);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		return list;
	}

	@Override
	public List<HouseInfoDto> houseInfoListByName(String aptName) {
		
		List<HouseInfoDto> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT * FROM MY_HOUSE.HOUSEINFO ")
			.append( " WHERE APTNAME LIKE ? ");
			pstmt=con.prepareStatement(sb.toString());
			
			pstmt.setString(1, "%"+aptName+"%");
			rs=pstmt.executeQuery(); 
				
			System.out.println(pstmt.toString());
			
			
			while (rs.next()) {
				HouseInfoDto houseInfoDto;
				houseInfoDto=new HouseInfoDto();
				
				houseInfoDto.setNo(rs.getInt("no"));
				houseInfoDto.setDong(rs.getString("dong"));
				houseInfoDto.setAptName(rs.getString("AptName"));
				houseInfoDto.setCode(rs.getString("code"));
				houseInfoDto.setBuildYear(rs.getString("buildYear"));
				houseInfoDto.setJibun(rs.getString("jibun"));
				houseInfoDto.setLat(rs.getString("lat"));
				houseInfoDto.setLng(rs.getString("lng"));
				
				list.add(houseInfoDto);
				
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
