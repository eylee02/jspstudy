package ex08_Cookie;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cookie1
 */
@WebServlet("/cookie1")
public class Cookie1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cookie1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * ��Ű
		 * 1. Ŭ���̾�Ʈ���� ����Ǵ� ������ �ǹ��Ѵ�.
		 * 2. ���ȿ��� ����ϹǷ� ���� ���� ���� �ΰ� ������ �������� �ʴ´�.
		 */
		
		// ��Ű �����
		Cookie cookie1 = new Cookie("name", "ȫ�浿");
		Cookie cookie2 = new Cookie("age", "20");   // ��� ��Ű ���� String Ÿ������ �����Ѵ�.
		Cookie cookie3 = new Cookie("address",  URLEncoder.encode("����Ư���� ���α�", "UTF-8"));  // ������ ��ȿ���� ���� ���ڿ��̹Ƿ� ���ڵ��� �ʿ��ϴ�
		
		// ��Ű�� ����� ��� �����ϱ� (�����ϸ� ���ؽ�Ʈ�н� ��ο� ����ȴ�.)
		cookie1.setPath("/servlet");          // ���ؽ�Ʈ�н� : request.getContextPath()�� �˾Ƴ��� ����
		cookie2.setPath("/servlet/cookie1");  // �������   : request.getRequestURI()
		                                      // cookie3�� ��� ������ ���������Ƿ� ���ؽ�Ʈ�н��� ����ȴ�.
		
		// ��Ű�� �����Ǵ� �ð� �����ϱ� (�����ϸ� ������Ű�� �ȴ�. �������� ������ ��������. �ð��� 0���� �����ϰ� ����� ��Ű��������)
		cookie1.setMaxAge(60 * 60);           // 1�ð� = 60�� * 60��
		cookie2.setMaxAge(60 * 60 * 24 * 7);  // 7��
											  // cookie3�� �ð� ������ ���������Ƿ� ������Ű�� �ȴ�.
		
		// ��Ű�� �������� �����ϱ� (�������� ó���Ѵ�.)
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);		
		
		// Cookie2 �������� redirect �̵�
		response.sendRedirect("/servlet/cookie2");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
