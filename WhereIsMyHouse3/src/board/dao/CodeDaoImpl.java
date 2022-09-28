package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.common.DBManager;
import board.dto.CodeDto;

public class CodeDaoImpl implements CodeDao {

	private CodeDaoImpl() {}
	private static CodeDaoImpl instance=new CodeDaoImpl ();
	
	public static CodeDaoImpl getInstance () {
		return instance;
	}
	
	@Override
	public List<CodeDto> getCodeList(String groupCode) {
		List<CodeDto> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT CODE, CODE_NAME FROM MY_HOUSE.CODE ")
			.append( " WHERE GROUP_CODE=? ");
			pstmt=con.prepareStatement(sb.toString());
			
			pstmt.setString(1, groupCode);
			rs=pstmt.executeQuery(); 
				
			while (rs.next()) {
				CodeDto codeDto=new CodeDto ();
				codeDto.setCode(rs.getString("code"));
				codeDto.setCodeName(rs.getString("code_name"));
				
				list.add(codeDto);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		return list;
	}

	@Override
	public String getCodeName(String groupCode, String code) {
		String codeName=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int ret=-1;

		try {
			con=DBManager.getConnection();
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT CODE_NAME FROM MY_HOUSE.CODE ")
			.append( " WHERE GROUP_CODE=? AND CODE=?");
			pstmt=con.prepareStatement(sb.toString());
			
			pstmt.setString(1, groupCode);
			pstmt.setString(2, code);
			rs=pstmt.executeQuery();

			while (rs.next()) {
				codeName=rs.getString("code_name");
			}

				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		return codeName;
	}

}
