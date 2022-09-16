package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/SimplePostTestServlet")
public class SimplePostTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// client가 보낸 데이터 확인
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		System.out.println("userId : " + userId + " / userPwd : " + userPwd);

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();

		// response : 성공, 실패
		if ("ssafy".equals(userId) && "1234".equals(userPwd)) {
			jsonObject.addProperty("result", "success");
		} else {
			jsonObject.addProperty("result", "fail");
		}

		String jsonStr = gson.toJson(jsonObject);
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		System.out.println(jsonStr);
		response.setContentType("application/json; charset=utf-8"); 	
		response.getWriter().write(jsonStr);					
	}

}
