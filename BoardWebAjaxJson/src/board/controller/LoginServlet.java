package board.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.common.DBManager;
import board.dto.UserDto;
import board.service.LoginService;
import board.service.LoginServiceImpl;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Connection con=DBManager.getConnection();
//		System.out.println(con);
//		DBManager.releaseConnection(null, null, con);
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// #1. Client로부터 email, password 입력 받음
		String userEmail=request.getParameter("userEmail");
		String userPassword=request.getParameter("userPassword");

		System.out.println(userEmail + " " + userPassword);
		
		// #2. 로그인
		LoginService service = LoginServiceImpl.getInstance();
		UserDto userDto=service.login(userEmail, userPassword);
		
		System.out.println(userDto);
	
	}

}
