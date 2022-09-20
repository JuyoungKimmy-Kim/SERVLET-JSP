package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import board.dto.UserDto;
import board.service.UserService;
import board.service.UserServiceImpl;

@WebServlet("/register")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserService userService=UserServiceImpl.getInstance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// Client로부터 정보 입력 받음
		String userName=request.getParameter("userName");
		String userPassword=request.getParameter("userPassword");
		String userEmail=request.getParameter("userEmail");		
		
		// userDto에 정보 등록
		UserDto userDto=new UserDto();
		userDto.setUserName(userName);
		userDto.setUserPassword(userPassword);
		userDto.setUserEmail(userEmail);

		// Service로 보내서 등록
		int ret=userService.userRegister(userDto);
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
