package board.service;

import java.util.List;

import board.dto.HouseInfoDto;

public interface HouseInfoService {
	List<HouseInfoDto> houseInfoListByDong(String dongName);
	List<HouseInfoDto> houseInfoListByName(String aptName);
}
