package board.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.UserDto;
import board.service.UserService;
import board.service.UserServiceImpl;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// Client로부터 정보 입력 받음
		int userSeq=Integer.parseInt(request.getParameter("userSeq"));
		String userName=request.getParameter("userName");
		String userPassword=request.getParameter("userPassword");
		String userEmail=request.getParameter("userEmail");
		String userProfileImageUrl=request.getParameter("userProfileImageUrl");		
		Date userRegisterDate=null; //=request.getParameter("userRegisterDate");
		
		// userDto에 정보 등록
		UserDto userDto=new UserDto();
		userDto.setUserSeq(userSeq);
		userDto.setUserPassword(userPassword);
		userDto.setUserEmail(userEmail);
		userDto.setUserProfileImageUrl(userProfileImageUrl);
		userDto.setUserRegisterDate(userRegisterDate);
		
		// Service로 보내서 등록
		UserService service=UserServiceImpl.getInstance();
		service.userRegister(userDto);
	
		/*
		 * 성공, 실패 결과
		 * 
		 */
	}
}
