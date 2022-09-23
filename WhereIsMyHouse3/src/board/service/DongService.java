package board.service;

import java.util.List;

import board.dto.DongDto;

public interface DongService {
	List<DongDto> dongList(String gugunCode);
}
