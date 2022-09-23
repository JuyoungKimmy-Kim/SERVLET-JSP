package board.service;

import java.util.List;

import board.dao.GugunDao;
import board.dao.GugunDaoImpl;
import board.dto.GugunDto;

public class GugunServiceImpl implements GugunService{

	private static GugunServiceImpl instance = new GugunServiceImpl();
	private GugunServiceImpl() {}
	
	public static GugunServiceImpl getInstance() {
		return instance;
	}
	
	GugunDao gugunDao=GugunDaoImpl.getInstance();
	
	@Override
	public List<GugunDto> gugunList(String code) {
		// TODO Auto-generated method stub
		return gugunDao.gugunList(code);
	}


}
