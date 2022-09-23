package board.dao;

import java.util.List;

import board.dto.HouseInfoDto;

public interface HouseInfoDao {
	List<HouseInfoDto> houseInfoListByDong(String dongName);
	List<HouseInfoDto> houseInfoListByName(String aptName);
}
