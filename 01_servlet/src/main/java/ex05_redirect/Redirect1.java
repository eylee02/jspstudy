package ex05_redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Redirect1
 */
@WebServlet("/redirect1")
public class Redirect1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Redirect1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * redirect
		 * 1. �ٸ� ��η� �̵��ϴ� ��� �� �ϳ��̴�.
		 * 2. ������ �ٸ� ��θ� �����ϸ� Ŭ���̾�Ʈ�� �ش� ��η� ���� �̵��ϴ� ����̴�.
		 * 3. ��θ� �ۼ��� �� ContextPath�� URLMapping�� ��� �ۼ��Ѵ�.
		 */
		
		// redirect�� ��θ� ������
		response.sendRedirect("/servlet/redirect2");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
