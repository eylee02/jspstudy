package ex06_ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.XML;

/**
 * Servlet implementation class XMLServlet
 */
@WebServlet("/getXML")
public class XMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XMLServlet() {
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
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		int age = 0;
		if(strAge != null && !strAge.isEmpty()) {
			age = Integer.parseInt(strAge);
		}
		
		// 3. XML �����
		/*
		 * {"person: {"name": "alice", "age": 30}}�� ������ �� XML �±� �������� ��ȯ
		 * <XML version="1.0" encoding="UTF-8">
		 * <person>
		 * 	<name>alice</name>
		 * 	<age>30</age>
		 * </person>
		 */
			
		JSONObject person = new JSONObject();
		person.put("name", name);
		person.put("age", age);
		
		JSONObject resJSON = new JSONObject();
		resJSON.put("person", person);
		
		String responseXML = XML.toString(resJSON);
		
		// 4. ���� ������ Ÿ�԰� ���ڵ�
		response.setContentType("application/xml; charset=UTF-8");
		
		// 5. ���佺Ʈ�� �����
		PrintWriter out = response.getWriter();
		
		// 6. ����
		out.println(responseXML);  // $.ajax({success: function(resData){}...)
								   // function�� �Ű����� resData�� responseXML�� ���޵ȴ�.
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
