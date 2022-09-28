package board.service;

import java.util.List;

import board.dto.CodeDto;

public interface CodeService {
	List<CodeDto> getCodeList (String groupCode);
}
