package board.service;

import java.util.List;

import board.dao.CodeDao;
import board.dao.CodeDaoImpl;
import board.dto.CodeDto;

public class CodeServiceImpl implements CodeService{

	private CodeServiceImpl() {}
	private static CodeServiceImpl instance= new CodeServiceImpl ();
	
	public static CodeServiceImpl getInstance () {
		return instance;
	}
	
	CodeDao codeDao=CodeDaoImpl.getInstance();

	@Override
	public List<CodeDto> getCodeList(String groupCode) {
		return codeDao.getCodeList(groupCode);
	}
}
