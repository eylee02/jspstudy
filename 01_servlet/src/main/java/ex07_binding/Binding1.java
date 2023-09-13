package ex07_binding;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Binding1
 */
@WebServlet("/binding1")
public class Binding1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Binding1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * binding
		 * 
		 * 1. ���� ������ �� �ִ� ������ �Ӽ�(Attribute)�� ���·� ���� �����ϴ� ���� �ǹ��Ѵ�. (�±��� �Ӽ��� �ٸ�)
		 * 2. HTML �±��� �Ӽ�(Attribute)���� ��� ���� �����̴�.
		 * 3. ������ ���� ������ ������ �����Ѵ�.
		 * 4. ���� ����
		 * 	1) ServletContext     : ���ؽ�Ʈ(������Ʈ, ���ø����̼�) ���� ������ ���� ������ �� �ִ�. 
		 *                          ���ø����̼��� �������̶�� ��𼭵� ���� ������ �� �ִ�.
		 *  2) HttpSession        : ���� ���� ������ ���� ������ �� �ִ�. �� �������� �������̶�� ��𼭵� ���� ������ �� �ִ�.(�ð� ���� ����)
		 *  3) HttpServletRequest : ��û ���� ������ ���� ������ �� �ִ�. ���� ���̶�� ���� ������ �� �ִ�. (1ȸ��)
		 * 5. �Ӽ�(Attribute) ó�� �޼ҵ�
		 * 	1) setAttribute("�Ӽ�", ��) : Object Ÿ������ ���� �����Ѵ�.
		 * 	2) getAttribute("�Ӽ�")     : Object Ÿ���� ���� ��ȯ�Ѵ�. ĳ������ �ʿ��� �� �ִ�.
		 * 	3) removeAttribute("�Ӽ�")  : ���� �����Ѵ�.
		 */
		
		
		// ServletContext ������ ���� �����ϱ�
		ServletContext application = request.getServletContext();		
		application.setAttribute("msg", "ServletContext");
		
		// HttpSession ������ ���� �����ϱ�
		HttpSession session = request.getSession();
		session.setAttribute("msg", "HttpSession");
		
		// HttpServletRequest ������ ���� �����ϱ� (�̹� �Ű������� ����Ǿ� �ִ�.)
		request.setAttribute("msg", "HttpServletRequest");
		
		// Binding2 �������� �̵�(HttpServletRequest�� ������ ������ forward�� �̿�)
		request.getRequestDispatcher("/binding2").forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
