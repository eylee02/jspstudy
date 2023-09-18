package ex06_session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ��û ���ڵ�
		request.setCharacterEncoding("UTF-8");
		
		// ��û �Ķ����
		String item = request.getParameter("item");
		int ea = Integer.parseInt(request.getParameter("ea"));	
		
		// item + ea -> Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("item", item);
		map.put("ea", ea);
		
		// ��ٱ��� ó�� ��� : session�� cart �Ӽ��� �����ϰ� cart�� [item + ea -> Map] ����
		HttpSession session = request.getSession();
		List<Map<String, Object>> cart = (List<Map<String, Object>>)session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<Map<String,Object>>();
			session.setAttribute("cart", cart);
		}
		
		// cart�� Map �����ϱ�
		cart.add(map);
		
		// ���� Ÿ�԰� ���ڵ�
		response.setContentType("text/html; charset=UTF-8");
		
		// ���� ��� ��Ʈ��
		PrintWriter out = response.getWriter();
		
		// �����ϱ�
		out.println("<script>");
		out.println("if(confirm('" + item + "�� ��ٱ��Ͽ� ��ҽ��ϴ�.��ٱ��Ϸ� ������ \"Ȯ��\" ��� �����Ϸ��� \"���\" ��ư�� ��������')){");
		out.println("location.href='" + request.getContextPath() + "/ex06_session/cart.jsp'");
		out.println("}else{");
		out.println("location.href='" + request.getContextPath() + "/ex06_session/main.jsp'");
		out.println("}");
		out.println("</script>");
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
