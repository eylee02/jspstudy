package repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BookDto;

public class BookDao {
	
	// mybatis�� SqlSession�� ���� �� �ִ� SqlSessionFactory ����
	private SqlSessionFactory factory;
	
	// Singleton Pattern
	private static BookDao dao = new BookDao();
	private BookDao() {
		// SqlSessionFactory ����
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static BookDao getDao() {
		return dao;
	}
	
	// ������ namespace
	private final String NS = "mybatis.mapper.book.";
	
	// ��ü ���� ��ȯ �޼ҵ� (��� ���� 1��)
	public int bookCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(NS + "bookCount");
		ss.close();
		return count;
	}
	
	// ��� ��ȯ �޼ҵ� (��� ���� ���� ��)
	public List<BookDto> bookList(Map<String, Object> map) {
		SqlSession ss = factory.openSession();
		List<BookDto> list =  ss.selectList(NS + "bookList", map);
		ss.close();
		return list;
	}
	
	// �� ��ȯ �޼ҵ�
	public BookDto bookDetail(int bookNo) {
		SqlSession ss = factory.openSession();
		BookDto dto = ss.selectOne(NS + "bookDetail", bookNo);
		ss.close();
		return dto;
	}	
	
	// ��� �޼ҵ�
	public int bookAdd(BookDto dto) {
		SqlSession ss = factory.openSession(false);  // ���� Ŀ���Ѵ�.(outocommit ��������ʴ´�.)
		int addResult = ss.insert(NS + "bookAdd", dto);
		if(addResult == 1) {
			ss.commit();
		}
		ss.close();
		return addResult;
	}
	
	// ���� �޼ҵ�
	public int bookModify(BookDto dto) {
		SqlSession ss = factory.openSession(false);
		int modifyResult = ss.update(NS + "bookModify", dto);
		if(modifyResult == 1) {
			ss.commit();
		}
		ss.close();
		return modifyResult;		
	}
	
	// ���� �޼ҵ�
	public int bookDelete(int bookNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "bookDelete", bookNo);
		if(deleteResult == 1) {
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}

}
