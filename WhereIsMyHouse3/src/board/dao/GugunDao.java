package board.dao;

import java.util.List;

import board.dto.GugunDto;

public interface GugunDao {
	
	// sido 코드를 받아와서 구군 리스트 출력
	List<GugunDto> gugunList(String code); 
	
}
