package board.service;

import java.util.List;


import board.dao.SidoDao;
import board.dao.SidoDaoImpl;
import board.dto.SidoDto;

public class SidoServiceImpl implements SidoService{

	private static SidoServiceImpl instance = new SidoServiceImpl();
	private SidoServiceImpl() {}
	
	public static SidoServiceImpl getInstance() {
		return instance;
	}
	
	SidoDao sidoDao=SidoDaoImpl.getInstance();
	
	@Override
	public List<SidoDto> sidoList() {
		return sidoDao.sidoList();
	}

}
