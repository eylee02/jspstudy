package ex03_response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseServlet
 */
@WebServlet("/response")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. ��û ���ڵ�
		request.setCharacterEncoding("UTF-8");
		
		// 2. ��û �Ķ����
		String gender = request.getParameter("gender");
		String domain = request.getParameter("domain");
		String content = request.getParameter("content");
		String region = request.getParameter("region");
		
		/*
		 * ���� (Response)
		 * 1. ������ Ŭ���̾�Ʈ���� �����͸� ������ ���� �ǹ��Ѵ�.  (���� -> Ŭ���̾�Ʈ)
		 * 2. HttpServletResponse Ŭ������ ������ ó���Ѵ�.
		 * 3. ���� �������� Ÿ��(MIME TYPE)�� �����ϰ� �����Ѵ�.
		 * 	1) text/html        : �±׸� ���� ��ȯ�ϴ� ��� 
		 * 	2) application/xml  : XML�� ��ȯ�ϴ� ���
		 * 	3) application/json : JSON�� ��ȯ�ϴ� ���
		 */
		
		// 3. ������ �������� Ÿ�԰� ���ڵ� ����
		response.setContentType("text/html; charset=UTF-8");
		
		// 4. ���� ��Ʈ�� ���� (���� ��� ��� ��Ʈ���� Writer ����)
		//    IOException ���� ó���� �ʿ������� doGet() �޼ҵ�� IOException ó���� �̹� �ϰ��ִ�.
		PrintWriter out = response.getWriter();
		
		// 5. �����ϱ� (������ �� ������)
		out.println("<DOCTYPE html>");
		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>����������</title>");
		out.println("<style>");
		out.println(" .wrap { ");
		out.println("  background-color: #a0a0a0;");
		out.println("}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"wrap\">");
		out.println("<h1>��û �Ķ����</h1>");
		out.println("<ul>");
		out.println("<li>����: " + gender + "</il>");
		out.println("<li>������: " + domain + "</il>");
		out.println("<li><pre>" + content + "</pre></il>");
		out.println("<li>����: " + region + "</il>");
		out.println("</ul>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
