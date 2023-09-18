package ex06_session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ��û ���ڵ�
		request.setCharacterEncoding("UTF-8");
		
		// ��û �Ķ����
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// �α��� ���� ��Ģ : id�� pw�� �����ϸ� �α��� �������� �����ϰ� Ǯ��
		if(id.equals(pw)) {
			// �α��� ó�� : session�� id�� ������ �α�
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
		}
		
		// �α��� ȭ������ �ǵ��ư���
		response.sendRedirect(request.getContextPath() + "/ex06_session/login.jsp");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
