package ex08_Cookie;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cookie2
 */
@WebServlet("/cookie2")
public class Cookie2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cookie2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ��û�� ���� ��� ��Ű �������� (��� ������ �� ����.)
		Cookie[] cookies = request.getCookies();
		
		// ��Ű Ȯ���ϱ�
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				// ��Ű �̸�
				String name = cookies[i].getName();
				// ��Ű ��
				String value = URLDecoder.decode(cookies[i].getValue(), "UTF-8") ;
				// ��Ű ���
				String path = cookies[i].getPath();
				// ��Ű ���� �ð�
				int expire = cookies[i].getMaxAge();
				System.out.println(name + "," + value + "," + path + "," + expire);
			}
		}
		
		// ��Ű �����ϱ� (���� �̸�(name)�� ��Ű�� ���� �� ��Ű �����ð��� 0���� �����ϰ� �����Ѵ�.(������Ѵ�))
		Cookie cookie = new Cookie("name", "�����ƹ��ǹ̰�����.");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
