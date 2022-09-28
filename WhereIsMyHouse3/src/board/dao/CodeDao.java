package board.dao;

import java.util.List;

import board.dto.CodeDto;

public interface CodeDao {
	List<CodeDto> getCodeList (String groupCode);	
	String getCodeName (String groupCode, String code);
}
