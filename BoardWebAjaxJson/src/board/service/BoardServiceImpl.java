package board.service;

import java.util.List;

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

	@Override
	public List<BoardDto> boardList(int limit, int offset) {
		return boardDao.boardList(limit, offset);
	}

	@Override
	public List<BoardDto> boardListSearchWord(int limit, int offset, String searchWord) {
		return boardDao.boardListSearchWord(limit, offset, searchWord);
	}

	@Override
	public int boardListTotalCnt() {
		return boardDao.boardListTotalCnt();
	}

	@Override
	public int boardListSearchWordTotalCnt(String searchWord) {
		return boardDao.boardListSearchWordTotalCnt(searchWord);
	}

	@Override
	public BoardDto boardDetail(int boardId, int userSeq) { //현재 boardId 게시물을 클릭한 사용자의 userseq
		BoardDto boardDto= boardDao.boardDetail(boardId); //게시글 정보 , 이곳의 userSeq는 글 작성자
		
		// 두 사용자가 같은지에 대한 sameUser 처리
		
		if (boardDto.getUserSeq()==userSeq ) {
			boardDto.setSameUser(true);
			
		}else boardDto.setSameUser(false);
		
		return boardDto;
		
	}

}
