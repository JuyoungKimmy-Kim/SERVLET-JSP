package board.service;

import java.util.List;

import board.dto.GugunDto;

public interface GugunService {
	List<GugunDto> gugunList(String code);
}
