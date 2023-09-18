package ex07_cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RememberMe
 */
@WebServlet("/rememberMe")
public class RememberMe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RememberMe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ��û ���ڵ�
		request.setCharacterEncoding("UTF-8");
		
		// ��û �Ķ���� (id, remember_me)
		String id = request.getParameter("id");                    // �Է� ������ �Է��� ������ �� ���ڿ�("")
		String remember_me = request.getParameter("remember_me");  // checkbox�� üũ�� ������ null
		
		// ���̵� ����ϱ� üũ�ߴٸ� ��Ű�� id�� �����Ѵ�.
		Cookie cookie = null;
		if(remember_me != null) {   // if(remember_me.equals("on")){ �� ������
		 cookie = new Cookie("remember_me", id);
		 cookie.setMaxAge(60 * 60 * 24 * 15);   // 15�ϰ� ��Ű ����
		} else {
		 cookie = new Cookie("remember_me", "");
		 cookie.setMaxAge(0);                  // ��Ű ������ ���� 0�ʰ� ��Ű ����
		}
		
		// ��Ű�� Ŭ���̾�Ʈ���� �����Ѵ�. ��Ű�� Ŭ���̾�Ʈ�� �����Ѵ�.
		response.addCookie(cookie);
		
		// main ȭ������ ���ư���
		response.sendRedirect(request.getContextPath() + "/ex07_cookie/main.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
