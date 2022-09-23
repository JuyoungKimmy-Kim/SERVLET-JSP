package board.dao;

import java.util.List;

import board.dto.DongDto;

public interface DongDao {
	List<DongDto> dongList(String gugunCode);
}
