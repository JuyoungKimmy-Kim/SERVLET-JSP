package board.service;

import java.util.List;

import board.dao.HouseInfoDao;
import board.dao.HouseInfoDaoImpl;
import board.dto.HouseInfoDto;


public class HouseInfoServiceImpl implements HouseInfoService{

	private static HouseInfoServiceImpl instance=new HouseInfoServiceImpl();
	private HouseInfoServiceImpl() {}
	
	public static HouseInfoServiceImpl getInstance() {
		return instance;
	}
	
	HouseInfoDao houseInfoDao=HouseInfoDaoImpl.getInstance();
	@Override
	public List<HouseInfoDto> houseInfoListByDong(String dongName) {
		return houseInfoDao.houseInfoListByDong(dongName);
	}

	@Override
	public List<HouseInfoDto> houseInfoListByName(String aptName) {
		return houseInfoDao.houseInfoListByName(aptName);
	}

}
