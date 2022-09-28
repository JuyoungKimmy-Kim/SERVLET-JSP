package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import board.dto.UserDto;
import board.service.UserService;
import board.service.UserServiceImpl;


@WebServlet({"/login", "/register", "/user"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService service = UserServiceImpl.getInstance();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		String act = request.getParameter("act");
		if(act.equals("logout")) {
			logout(request, response);
			
		}
		else {
		session.invalidate(); // 무조건!! remove아님
		//"result" : "success"
		Gson gson=new Gson();
		JsonObject jsonObject=new JsonObject();
		jsonObject.addProperty("result", "success");
		
		String jsonStr=gson.toJson(jsonObject);
		response.getWriter().write(jsonStr);
		}
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("로그인 하러 들어옴!");
		
		
		// #1. Client로부터 email, password 입력 받음
		String userId=request.getParameter("userId");
		String userPassword=request.getParameter("userPassword");

		System.out.println(userId + " " + userPassword);
		
		// #2. 로그인
		UserDto userDto=service.login(userId, userPassword);
		
		System.out.println(userDto);
		
		// #3. 성공, 실패에 대한 처리
		// #3-2. 성공 : session에 사용자 정보 UserDto를 저장
		// #3-3. 실패 : 로그인 실패 결과를 client에 return (json)
		
		if (userDto!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("userDto", userDto);
			
			//"result" : "success"
			Gson gson=new Gson();
			JsonObject jsonObject=new JsonObject();
			jsonObject.addProperty("result", "success");
			String jsonStr=gson.toJson(jsonObject);
			response.getWriter().write(jsonStr);
			
			System.out.println("LoginServlet - login success");
		} else {
			//"result" : "success"
			Gson gson=new Gson();
			JsonObject jsonObject=new JsonObject();
			jsonObject.addProperty("result", "fail");
			
			String jsonStr=gson.toJson(jsonObject);
			response.getWriter().write(jsonStr);
			
			System.out.println("LoginServlet - login fail");
		}

	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Client로부터 정보 입력 받음
		
		String userId=request.getParameter("userId");
		String userPhone=request.getParameter("userPhone");		
		String userName=request.getParameter("userName");
		String userPassword=request.getParameter("userPassword");
		String userEmail=request.getParameter("userEmail");
		String code=request.getParameter("code");

		System.out.println("============User Servlet : Register ==============");
		System.out.println("code : "+code);
		
		// userDto에 정보 등록
		UserDto userDto=new UserDto();
		userDto.setId(userId);
		userDto.setPhone(userPhone);
		userDto.setName(userName);
		userDto.setPassword(userPassword);
		userDto.setAddress(userEmail);
		userDto.setCode(code);
		
		// Service로 보내서 등록
		int ret=service.userRegister(userDto);
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

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		HttpSession session=request.getSession();
		session.invalidate(); // 무조건!! remove아님
		response.sendRedirect(request.getContextPath()+"/");
	}
	
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session=request.getSession();
		

		String userId=request.getParameter("userId");
		String userPhone=request.getParameter("userPhone");		
		String userName=request.getParameter("userName");
		String userEmail=request.getParameter("userEmail");
		// permission?
		
		
		// userDto에 정보 등록
		UserDto userDto=new UserDto();
		userDto.setId(userId);
		userDto.setPhone(userPhone);
		userDto.setName(userName);
		userDto.setAddress(userEmail);
		userDto.setCode(((UserDto)session.getAttribute("userDto")).getCode());
						
		
		int ret=service.modify(userDto);
		Gson gson=new Gson();
		JsonObject jsonObject=new JsonObject();
		
		if (ret==1) {
			//성공
			userDto.setPassword(((UserDto) session.getAttribute("userDto")).getPassword());
			session.setAttribute("userDto", userDto);
			jsonObject.addProperty("result", "success");
		} else {
			//실패
			jsonObject.addProperty("result", "fail");
		}
		String jsonStr=gson.toJson(jsonObject);
		response.getWriter().write(jsonStr);
		
	}
	private void modifyPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession();

		Gson gson=new Gson();
		JsonObject jsonObject=new JsonObject();
		String userId=request.getParameter("userId");
		String id = ((UserDto) session.getAttribute("userDto")).getId();
		if(id.equals(userId)) {
		String pw=request.getParameter("userPassword");
		session.invalidate(); // 무조건!! remove아님
		
		int ret=service.modifyPw(id, pw);
		
		if (ret==1) {
			//성공
			jsonObject.addProperty("result", "success");
		} else {
			//실패
			jsonObject.addProperty("result", "fail");
		}
		
		}
		
		else {

			session.invalidate();
			jsonObject.addProperty("result", "fail");
		}
		String jsonStr=gson.toJson(jsonObject);
		response.getWriter().write(jsonStr);
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
		
	String userId=request.getParameter("userId");
	String id = ((UserDto) session.getAttribute("userDto")).getId();

	Gson gson=new Gson();
	JsonObject jsonObject=new JsonObject();
	if(id.equals(userId)) {
		
		int ret=service.delete(id);
		
		if (ret==1) {
			
			//성공

			session.invalidate();
			jsonObject.addProperty("result", "success");
		} else {
			//실패
			jsonObject.addProperty("result", "fail");
		}
	}else {
		session.invalidate();
		jsonObject.addProperty("result", "fail");
	}
		String jsonStr=gson.toJson(jsonObject);
		response.getWriter().write(jsonStr);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		switch(act) {
		case "login":
			login(request,response);
			break;
		case "register":
			register(request, response);
			break;
		case "modifyPw":
			modifyPw(request, response);
			break;
		case "modify":
			modify(request, response);
			break;
		case "del":
			delete(request, response);
			break;
		}
	
	}

}
