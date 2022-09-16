package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionMakeServlet")
public class SessionMakeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.setAttribute("userId", "kimmy");
		
		int[] intArray= {1,2,3,4,5};
		session.setAttribute("intArray", intArray);	//배열
		
		String[] strArray= {"Ju", "young", "Kim", "JJang"};
		List<String> strList=Arrays.asList(strArray);
		session.setAttribute("strList", strList);
		
		System.out.println("Session에 객체 저장 완료!");
		response.getWriter().append("Session Added!");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
