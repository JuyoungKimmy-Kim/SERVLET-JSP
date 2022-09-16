package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CookieRemoveServlet")
public class CookieRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie c1=new Cookie ("c1", "v1");
		c1.setPath("/");
		c1.setMaxAge(0);	// remove없음 -> setMaxAge!
		response.addCookie(c1);
		
		System.out.println("Cookie 삭제!");
		response.getWriter().append("Check Cookie !!");
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
