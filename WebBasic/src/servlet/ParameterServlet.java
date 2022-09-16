package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ParameterServlet")
public class ParameterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request 한글 처리
		//request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		System.out.println(username);
		
		// response 한글 처리
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().append("username : "+username);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		
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
		
		response.getWriter().append(sb);
	}

}
