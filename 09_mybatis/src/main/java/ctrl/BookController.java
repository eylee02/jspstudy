package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BookService;
import service.BookServiceImpl;

/**
 * Servlet implementation class BookController
 */
@WebServlet("*.do")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private BookService bookService = new BookServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 응답 타입과 인코딩
		response.setContentType("text/html charset=UTF-8");
		
		// 요청 주소 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// ActionForward 객체
		ActionForward af = null;
		
		// BoardService 객체 생성
		BookService bookService = new BookServiceImpl();
		
		// 요청에 따른 처리
		switch(urlMapping) {
		
		case "/book/write.do":
			af = new ActionForward("/book/write.jsp", false);
			break;
		case "/index.do":
			af = new ActionForward("/index.jsp", false);
			break;
		case "/book/add.do":
		      af = bookService.bookAdd(request);
		      break;
		case "/book/list.do":
		      af = bookService.bookList(request);
		      break;
		case "/book/detail.do":
		      af = bookService.bookDetail(request);
		      break;
		case "/book/edit.do":
		      af = bookService.bookEdit(request);
		      break;
		case "/book/modify.do":
		      af = bookService.bookModify(request);
		      break;
		case "/book/delete.do":
		      af = bookService.bookDelete(request);
		      break;
		}
		
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
