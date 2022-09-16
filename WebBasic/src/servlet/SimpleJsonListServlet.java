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
		// �񵿱� ��û�� ���� ó��
		// response : html x
		// response : data only : xml, json...
		
		
		// request : client�� ��û
		// response : client���� ����
		
		
		// client�� ���� ������ Ȯ��
		String userId = request.getParameter("userId");
		String msg= request.getParameter("msg");
		
		//client�� ������ ���� �ƴϰ� tomcat console �� ���
		System.out.println("userId : "+userId + " / msg : "+msg);
	
		//client���� ������ ������ �غ�, ����
		ArrayList<UserDto> userList=new ArrayList<>();
		userList.add(new UserDto (11111, "ȫ�浿", "hong@mail.com"));
		userList.add(new UserDto (22222, "�̱浿", "lee@mail.com"));
		userList.add(new UserDto (33333, "��浿", "sam@mail.com"));
		
		// java object => json 
		// gson libraray jar�� tomcat/lib �� �߰�
		Gson gson=new Gson();
		String jsonStr=gson.toJson(userList);					// jsonStr : userList�� ǥ���� JSON ���ڿ�
		
		// vs code lib serve ������ �ϴ� ��
		// vsCode libe cors
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		
		System.out.println(jsonStr);
		
		response.setContentType("text/html; charset=utf-8"); 	// client
		response.getWriter().write(jsonStr);					// client���� ���ڿ� ����
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
