package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.UserDto;

@WebServlet("/SimpleJsonListServlet")
public class SimpleJsonListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비동기 요청에 대한 처리
		// response : html x
		// response : data only : xml, json...
		
		
		// request : client의 요청
		// response : client에게 응답
		
		
		// client가 보낸 데이터 확인
		String userId = request.getParameter("userId");
		String msg= request.getParameter("msg");
		
		//client에 보내는 것이 아니고 tomcat console 에 출력
		System.out.println("userId : "+userId + " / msg : "+msg);
	
		//client에게 보내는 데이터 준비, 전송
		ArrayList<UserDto> userList=new ArrayList<>();
		userList.add(new UserDto (11111, "홍길동", "hong@mail.com"));
		userList.add(new UserDto (22222, "이길동", "lee@mail.com"));
		userList.add(new UserDto (33333, "삼길동", "sam@mail.com"));
		
		// java object => json 
		// gson libraray jar를 tomcat/lib 에 추가
		Gson gson=new Gson();
		String jsonStr=gson.toJson(userList);					// jsonStr : userList를 표현한 JSON 문자열
		
		// vs code lib serve 때문에 하는 것
		// vsCode libe cors
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		
		System.out.println(jsonStr);
		
		response.setContentType("text/html; charset=utf-8"); 	// client
		response.getWriter().write(jsonStr);					// client에게 문자열 전송
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
