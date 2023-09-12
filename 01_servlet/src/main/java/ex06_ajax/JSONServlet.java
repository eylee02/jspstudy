package ex06_ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class JSONServlet
 */
@WebServlet("/getJSON")
public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSONServlet() {
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
		String title = request.getParameter("title");
		String writer  = request.getParameter("writer");
		
		// 3. JSON �����
		// {"title": "��������", "writer": "������"}
		JSONObject responseJSON = new JSONObject();
		responseJSON.put("title", title);
		responseJSON.put("writer", writer);
		
		// 4. ���� ������ Ÿ�԰� ���ڵ�
		response.setContentType("application/json; charset=UTF-8");
		
		// 5. ���� ��Ʈ�� ����
		PrintWriter out = response.getWriter();
		
		// 6. ����
		out.println(responseJSON);
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