package users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user/*")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// user/registerForm : 회원가입 페이지 요청 (get)
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// CodeService => CodeDao
	private void userRegister (HttpServletRequest request, HttpServletResponse response) {
		//CodeDapImpl codeDao=CodeDaoImpl.getInstance();
		List<CodeDto> codeList=codeDato.getCodeList ("001") // group_code에서 회원 구분에 대한 값 001
				
		request.setAttribute("codeList", codeList);
	}
}
