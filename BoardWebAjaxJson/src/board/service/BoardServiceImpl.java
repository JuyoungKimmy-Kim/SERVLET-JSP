package board.service;

import board.dao.BoardDao;
import board.dao.BoardDaoImpl;
import board.dto.BoardDto;

public class BoardServiceImpl implements BoardService{

	private static BoardServiceImpl instance=new BoardServiceImpl();
	private BoardServiceImpl() {}
	
	public static BoardServiceImpl getInstance () {
		return instance;
	}
	
	BoardDao boardDao=BoardDaoImpl.getInstance();
	
	@Override
	public int boardInsert(BoardDto boardDto) {
		return boardDao.boardInsert(boardDto);
	}

}
