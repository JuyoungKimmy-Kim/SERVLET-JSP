package mvc;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDto;

// 목록 : /mvc/list
// 등록 : /mvc/insert
// 수정 : /mvc/update

@WebServlet("/mvc/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);

	}
	
	// Controller 역할의 서블릿
	private void process (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		// 인코딩 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); // jsp로 forward할 경우 필요x
		
		// 업무 구분 처리
		String contextPath=request.getContextPath();
		System.out.println(contextPath);
		String path=request.getRequestURI().substring((contextPath.length()));
		System.out.println(path);
		
		switch (path) {
		case "/mvc/list" : list(request, response); break;
		case "/mvc/insert" : insert (request, response);break;
		//....
		}
	}
	
	private void list (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	    ArrayList<UserDto> list = new ArrayList<>();
	    list.add(new UserDto(1111, "홍길동", "hong@mail.com"));
	    list.add(new UserDto(2222, "이길동", "lee@mail.com"));
	    list.add(new UserDto(3333, "삼길동", "sam@mail.com"));
	
	    System.out.println("===");
	    // JSP로 Model (data)전달, forward
	    request.setAttribute("list", list);
	    RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/mvc/userList.jsp");
	    dispatcher.forward(request, response);
	}
	
	private void insert (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("mvc-insert 들어옴");
		
		String username = request.getParameter("username");
		System.out.println(username);
		
		String password = request.getParameter("password");
		String[] hobby=request.getParameterValues("hobby");
		
		StringBuilder sb=new StringBuilder();
		sb.append("username : "+username).append(" password: "+password);
		for (String str : hobby) {
			System.out.println(str);
			sb.append(" hobby : "+str);
		}
		
		UserDto userDto=new UserDto(111, username, "hong@hong.com");
		request.setAttribute("userDto", userDto);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/mvc/userInsertResult.jsp");
		dispatcher.forward(request, response);
	}
}
