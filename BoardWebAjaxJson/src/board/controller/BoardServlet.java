package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.dto.UserDto;

// login.jsp에서 sucess 되었을 때, /board/boardMain을 호출하니 여기로 오고 -> boardMain으로 감
@WebServlet("/board/*")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// LoginFilter로 대체
		//HttpSession session=request.getSession();
		//UserDto userDto=(UserDto) session.getAttribute("userDto");
//		
//		if (userDto!=null) System.out.println("로그인 사용자 -> 세션 유효");
//		else System.err.println("미로그인 사용자 -> 세션 유효하지 않음");
		
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
		
		System.out.println("들어옴");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		System.out.println(title+" "+content);
	}
	
}
