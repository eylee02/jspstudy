package ex09_up_down;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * ���� ÷�� ���� �۾�
		 * 1. http://servlets.com ����
		 * 2. COS File Upload Library - cos-22.05.zip �ٿ�ε�
		 * 3. zip ���� ���� �� lib/cos.jar ���̺귯�� -> ������Ʈ webapp/WEB-INF/lib/cos.jar �� �����̵�
		 */
		
		/*
		 * cos.jar
		 * 1. ���ε� ���� ���̺귯���̴�.
		 * 2. ���� ÷�� �� ����� enctype="multipart/form-data"�� �����ϴµ� �̷��ԵǸ� HttpServletRequest�� ����� �� ����.
		 * 3. HttpServletRequest ��� ����� MultipartRequest Ŭ������ �����ϴ� ���̺귯���̴�.
		 */
		
		// ÷�� ������ ��� (C����̺� storage����)
		String path = "C:/storage";
		
		
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		// MultipartRequest ��ü ���� (���� ���ε尡 ����Ǵ� �ڵ�)
		MultipartRequest mr = new MultipartRequest(
									request                          // ���� ����ϴ� request
								  , path                             // ÷�� ���
								  , 1024 * 1024 * 50                 // ÷�� ������ �ִ� ũ�� (50MB) 1024*1024=1MB
								  , "UTF-8"                          // ���ڵ�
								  , new DefaultFileRenamePolicy());  // ÷�� ���ϸ��� �ߺ��Ǵ� ��� ÷�� ������ �̸��� �ٲٴ� ��å(���ϸ� �ڿ� ��ȣ�� ���δ�.)		
	
		// ��û �Ķ���� (HttpServletRequest ��� MultipartRequest me �� �̿��Ѵ�.)
		String uploader = mr.getParameter("uploader");
		
		// ÷�� ����
		String originName = mr.getOriginalFileName("attach");    // ÷�� ���ϸ�
		String filesystemName = mr.getFilesystemName("attach");  // ����� ÷�� ���ϸ�
		
		File attach = mr.getFile("attach");
		String name = attach.getName();      // ÷�� ���ϸ�
		String parent = attach.getParent();  // ÷�� ���� ���� ���͸�
		           // attach.getPath();      // ÷�� ���� ���� ���͸� + ÷�� ���ϸ�
		String lastModified = new SimpleDateFormat("yyy-MM-dd").format(attach.lastModified());
		String size = new DecimalFormat("#,##0").format(attach.length() / 1024 + (attach.length() % 1024 == 0 ? 0 : 1));       
		
		// ����
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<ul>");
		out.println("<li>���δ�:" + uploader + "</li>");
		out.println("<li>÷�� ���ϸ�:" + originName + "</li>");
		out.println("<li>����� ���ϸ�:" + filesystemName + "</li>");
		out.println("<li>÷�� ���ϸ�:" + name + "</li>");
		out.println("<li>���� ���͸�:" + parent + "</li>");
		out.println("<li>���� ������:" + lastModified + "</li>");
		out.println("<li>���� ũ��:" + size + "</li>");
		out.println("</ul>");
		
		// ��� ÷�� ���� ���
		File[] attaches = dir.listFiles();
		for(int i = 0; i < attaches.length; i++) {
			out.println("<div><a href=\"/servlet/download?path=" + URLEncoder.encode(attaches[i].getPath(), "UTF-8") + "\">" + attaches[i].getName() + "</a></div>");
		}
		
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
