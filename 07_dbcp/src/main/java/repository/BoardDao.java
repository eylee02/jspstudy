package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	// �Խñ� ���� ��ȯ �޼ҵ�
	public int getBoardCount() {
		
		// �Խñ� ����
		int count = 0;
		
		try {
			
			con = dataSource.getConnection();
			String sql = "SELECT COUNT(*) FROM BOARD_T";  // COUNT(*)
			                                              // ---------
			                                              //   120 			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);   // count = rs.getInt("COUNT(*)")�� ������
			}		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		// �Խñ� ���� ��ȯ
		return count;
		
		
	}
	
	// �Խñ� ��� ��ȯ �޼ҵ�
	public List<BoardDto> getBoardList(Map<String, Object> map){
		
		// �Խñ� ��� ���� List
		List<BoardDto> list = new ArrayList<BoardDto>();
		
		try {
			
			con = dataSource.getConnection();
			String sql = "SELECT A.BOARD_NO, A.TITLE, A.CONTENT, A.MODIFIED_AT, A.CREATED_AT"
					+ "     FROM (SELECT ROW_NUMBER() OVER (ORDER BY BOARD_NO DESC) AS RN, BOARD_NO, TITLE, CONTENT, MODIFIED_AT, CREATED_AT"
					+ "             FROM BOARD_T) A"
					+ "    WHERE A.RN BETWEEN ? AND ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int)map.get("begin"));
			ps.setInt(2, (int)map.get("end"));
			rs = ps.executeQuery();
			while(rs.next()) {
				// rs -> BoardDto
				BoardDto dto = BoardDto.builder()
						      .board_no(rs.getInt(1))
						      .title(rs.getString(2))
						      .content(rs.getString(3))
						      .modified_at(rs.getDate(4))
						      .created_at(rs.getDate(5))
						      .build();
				// BoardDto -> list �߰�
				list.add(dto);				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		// �Խñ� ��� ��ȯ
		return list;
	}
	
	// �Խñ� ��ȯ �޼ҵ�
	public BoardDto getBoardByNo(int board_no) {
		
		// �Խñ� 
		BoardDto dto = null;
		
		try {
			
			con = dataSource.getConnection();
			String sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_AT, CREATED_AT"
					  +  "  FROM BOARD_T"
					  +  " WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = BoardDto.builder()
					 .board_no(rs.getInt(1))
					 .title(rs.getString(2))
					 .content(rs.getString(3))
					 .modified_at(rs.getDate(4))
					 .created_at(rs.getDate(5))
					 .build(); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		// �Խñ� ��ȯ
		return dto;
		
	}
	
	// �Խñ� ���� �޼ҵ�
	public int modify(BoardDto dto) {
		
		// ���� ���
		int modifyResult = 0;
		
		
		try {
			
			con = dataSource.getConnection();
			String sql = "UPDATE BOARD_T"
					   + "   SET TITLE = ?, CONTENT = ?, MODIFIED_AT = SYSDATE"
					   + " WHERE BOARD_NO = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setInt(3, dto.getBoard_no());
			modifyResult = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		// ���� ��� ��ȯ
		return modifyResult;
		
		
	}
	
	// �Խñ� ���� �޼ҵ�
	public int delete(int board_no) {
		
		// ���� ���
		int deleteResult = 0;
		
		
		try {
			
			con = dataSource.getConnection();
			String sql = "DELETE FROM BOARD_T WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			deleteResult = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		// ���� ��� ��ȯ
		return deleteResult;
		
	}
	

}
