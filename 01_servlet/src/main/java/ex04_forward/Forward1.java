package ex04_forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Forward1
 */
@WebServlet("/forward1")
public class Forward1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forward1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * forward
		 * 1. �ٸ� ��η� �̵��ϴ� ��� �� �ϳ��̴�.
		 * 2. �ٸ� ��η� ���� ��û �Ķ���͸� �̵���Ų��.
		 * 3. ��θ� �ۼ��� �� URLMapping�� �ۼ��Ѵ�. (ContextPath�� �ۼ����� �ʴ´�.)
		 */
		
		// ��û�� ���� ��ü ����
		RequestDispatcher dispatcher = request.getRequestDispatcher("/forward2");
		
		// ���� (��û�� ������ ��� ������)
		dispatcher.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
