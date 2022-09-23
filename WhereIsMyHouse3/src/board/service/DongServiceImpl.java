package board.service;

import java.util.List;

import board.dao.DongDao;
import board.dao.DongDaoImpl;
import board.dto.DongDto;

public class DongServiceImpl implements DongService{

	private static DongServiceImpl instance=new DongServiceImpl();
	private DongServiceImpl() {}
	
	public static DongServiceImpl getInstance() {
		return instance;
	}
	
	DongDao dongDao=DongDaoImpl.getInstance();
	
	@Override
	public List<DongDto> dongList(String gugunCode) {	
		return dongDao.dongList(gugunCode);
	}
}
