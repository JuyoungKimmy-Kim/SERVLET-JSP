package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import board.dto.BoardDto;
import board.dto.UserDto;
import board.service.BoardService;
import board.service.BoardServiceImpl;

// login.jsp에서 sucess 되었을 때, /board/boardMain을 호출하니 여기로 오고 -> boardMain으로 감
@WebServlet("/board/*")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService boardService=BoardServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String contextPath=request.getContextPath();
		String path=request.getRequestURI().substring(contextPath.length());
		System.out.println(path);
		
		switch (path) {
		case "/board/boardMain" : boardMain(request, response); break;
		case "/board/boardInsert" : boardInsert (request, response); break;
		}
	}
	
	private void boardMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/board/boardMain.jsp");
		dispatcher.forward(request, response);
	}
	
	private void boardInsert(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("================Enter BoardServlet - Insert ====================");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		BoardDto boardDto=new BoardDto();
		boardDto.setTitle(title);
		boardDto.setContent(content);
		
		HttpSession session=request.getSession();
		UserDto userDto=(UserDto) session.getAttribute("userDto");
		
		boardDto.setUserSeq(userDto.getUserSeq());
		// + user seq 추가
		
		
		System.out.println(boardDto);
		
		// Service로 보내서 등록
		int ret=boardService.boardInsert(boardDto);
		Gson gson=new Gson();
		JsonObject jsonObject=new JsonObject();
		
		if (ret==1) {
			//성공
			jsonObject.addProperty("result", "success");
		} else {
			//실패
			jsonObject.addProperty("result", "fail");
		}
		String jsonStr=gson.toJson(jsonObject);
		response.getWriter().write(jsonStr);
	}
	
}
