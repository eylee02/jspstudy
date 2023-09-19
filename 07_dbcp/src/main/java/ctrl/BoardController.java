package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// BoardFilter ���� �� Controller ����
		
		// ��û ���ڵ�(BoardFilter�� ������) + ���� Ÿ�԰� ���ڵ�
		// request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");
		
		// ��û �ּ� Ȯ��
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// ���� ��� �̵��� ������ �˰� �ִ� ActionForward ��ü
		ActionForward af = null;
		
		// BoardService ��ü ����
		BoardService boardService = new BoardServiceImpl();
		
		// ��û�� ���� ó��
		switch(urlMapping) {
		// �ܼ� �̵� (forward ó��)
		case "/board/list.do":
			af = new ActionForward("/board/list.jsp", false);
			break;
		case "/board/write.do":
			af = new ActionForward("/board/write.jsp", false);
			break;
			// ���� ó��
		case "/board/register.do":
			af = boardService.register(request);
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
