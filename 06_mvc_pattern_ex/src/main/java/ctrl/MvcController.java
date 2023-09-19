package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.MvcService;
import service.MvcServiceImpl;

/**
 * Servlet implementation class MvcController
 */
@WebServlet("*.do")
public class MvcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MvcController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	// ��û ���ڵ� + ���� Ÿ�԰� ���ڵ�
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");
		
		// ��û Ȯ��
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// ���� ��ü ����
		MvcService mvcService = new MvcServiceImpl();
		
		// ActionForward ��ü ���� (������ ���� ����� ������ ��ü)
		ActionForward af = null;
		
		// ��û�� ���� ���� ���ð� ����
		switch(urlMapping) {
		case "/getAge.do":
		  af = mvcService.getAge(request);
		  break;
		}
		
		// �̵�
		if(af != null) {
			if(af.isRedirect()) {
			  response.sendRedirect(af.getPath());
			} else {
			  request.getRequestDispatcher(af.getPath()).forward(request, response);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
