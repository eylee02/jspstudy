package ex09_up_down;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ��û ���ڵ�
		request.setCharacterEncoding("UTF-8");
		
		// ��û �Ķ���� (�ٿ�ε� �� ���ϸ�)
		String path = URLDecoder.decode(request.getParameter("path"), "UTF-8");
		
		// �ٿ�ε� �� ������ File ��ü ����
		File file = new File(path);   // new File("C:\storage\404.jpg")
		
		// �ٿ�ε� �� ������ �о� ���� ����Ʈ ��� �Է� ��Ʈ�� ����
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		// �ٿ�ε�� ������ ������� ���� ��� "Content-Disposition"�� "attachment" ���� �����ؾ� �Ѵ�.
		response.setHeader("Content-Disposition", "attachment");  // �ٿ�ε��� �� ���� ���� ��ȭ���ڰ� ��Ÿ����.
		response.setHeader("Content-Disposition", "attachment; filename=" + path.substring(path.lastIndexOf("\\") + 1));  // �ٿ�ε��� �� �ۼ��� filename���� ��ٷ� ����ȴ�.
		
		// ����Ʈ ��� ���� ��� ��Ʈ�� ����
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());   // ���信�� ��½�Ʈ��
		
		// ÷�� ���� �б� -> byte[] -> ���� ��� ��Ʈ��
		//      in                         out
		byte[] b = new byte[1024];               // 1024����Ʈ ������ �б�
		int readByte = 0;                        // ������ ���� ����Ʈ ��
		while((readByte = in.read(b)) != -1) {   // ��� �о �� �̻� ���� ������ ������ -1�� ��ȯ
			out.write(b, 0, readByte);           // ���� ����Ʈ��ŭ�� ��������
		}
		
		// ����� ��Ʈ�� �ݱ�
		out.close();
		in.close();		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
