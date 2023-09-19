package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import domain.BoardDto;

public class BoardDao {
	
	// ��� �޼ҵ尡 �������� ����� ��ü ����
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	// Connection Pool ���� DataSource ��ü ����
	private DataSource dataSource;
	
	// Singleton Pattern���� BoardDao ��ü ����
	private static BoardDao dao = new BoardDao();
	private BoardDao() {
	  // META-INF/context.xml�� �ִ� <Resource name="jdbc/oraclexe" /> �±� ������ �о DataSource ��ü �����ϱ�
	  try {
		  Context context = new InitialContext();
		  Context env = (Context)context.lookup("java:comp/env");
		  dataSource = (DataSource)env.lookup("jdbc/oraclexe");
	  } catch(Exception e) {
		  e.printStackTrace();
	  }
	}
	public static BoardDao getDao() {
		return dao;
	}
	
	// �ڿ� �ݳ� �޼ҵ�
	public void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// �Խñ� ��� �޼ҵ�
	public int register(BoardDto dto) {
		
		// ��� ��� ����(insert ���� ����� ���Ե� ���� �����̴�.)
		int insertResult = 0;
		
		try {
			
			// Connection Pool���� Connection�� �ϳ� �޾ƿ´�.
			// Connection Pool ������ DataSource ��ü�� ����ȴ�.
			
			con = dataSource.getConnection();
			
			// ������ �ۼ�
			String sql = "INSERT INTO BOARD_T(BOARD_NO, TITLE, CONTENT, MODIFIED_AT, CREATED_AT) VALUES (BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, SYSDATE)";
			
			// ps ��ü ���� (������ ������ ����ϴ� ��ü)
		    ps = con.prepareStatement(sql);
			
			// �������� ����(?�� ó���� �κ�)�� ���� ����
			ps.setString(1, dto.getTitle());  // 1��° ����ǥ(?)�� dto.getTitle() �����ϱ�
			ps.setString(2, dto.getContent()); // 2��° ����ǥ(?)�� dto.getContent() �����ϱ�
			
			// �������� ����
			insertResult = ps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			close();
		}	
		// ��� ��� ��ȯ
		return insertResult;
		
		
	}
	
	/*
	 * SELECT A.BOARD_NO, A.TITLE, A.CONTENT, A.MODIFIED_AT, A.CREATED_AT
         FROM (SELECT ROW_NUMBER() OVER (ORDER BY BOARD_NO DESC) AS RN, BOARD_NO, TITLE, CONTENT, MODIFIED_AT, CREATED_AT
                 FROM BOARD_T) A
        WHERE A.RN BETWEEN 1 AND 2;
	 */
	
	
	
	
	
	
	

}
