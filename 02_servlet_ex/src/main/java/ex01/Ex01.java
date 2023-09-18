package ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex01
 */
@WebServlet("/ex01")
public class Ex01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ex01() {
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
	    String id = request.getParameter("id");
	    String pw = request.getParameter("pw");
	    String name = request.getParameter("name");
	    String year = request.getParameter("year");
	    String month = request.getParameter("month");
	    String day = request.getParameter("day");
	    String gender = request.getParameter("gender");
	    String email = request.getParameter("email");
	    String country = request.getParameter("country");
	    String tel = request.getParameter("tel");
	    
	    // ���� Ÿ�� �� ���ڵ�
	    response.setContentType("text/html; charset=UTF-8");
	    
	    // ���� ��Ʈ��
	    PrintWriter out = response.getWriter();
	    
	    // ����
	    out.println("<!DOCTYPE html>");
	    out.println("<html lang=\"ko\">");
	    out.println("<head>");
	    out.println("<meta charset=\"UTF-8\">");
	    out.println("<title>ȸ����������</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<ul>");
	    out.println("<li>���̵�: " + id + "</li>");
	    out.println("<li>��й�ȣ: " + pw + "</li>");
	    out.println("<li>�̸�: " + name + "</li>");
	    int m = month.isEmpty() ? 0 : Integer.parseInt(month);
	    int d = day.isEmpty() ? 0 : Integer.parseInt(day);
	    out.println("<li>�������: " + year + "�� " + String.format("%02d", m) + "�� " + String.format("%02d", d) + "��" + "</li>");
	    out.println("<li>����: " + (gender.equals("male") ? "����" : gender.equals("female") ? "����" : "���þ���") + "</li>");
	    out.println("<li>�̸���: " + (email.isEmpty() ? "����" : email) + "</li>");
	    out.println("<li>�޴���ȭ: " + country + " " + tel.substring(1) + "</li>");
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
