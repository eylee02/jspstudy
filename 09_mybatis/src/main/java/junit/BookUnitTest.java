package junit;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import domain.BookDto;
import repository.BookDao;


/*
 * 1.JUnit ���� �׽�Ʈ ���� ���
 * 	1) JUnit Test Case ���� �߰�
 * 	2) @Test �ֳ����̼��� �߰��� �׽�Ʈ �޼ҵ� �ۼ�
 * 	3) [Run As] - [JUnit Test]
 * 
 * 2. JUnit4 ���� �׽�Ʈ �ֿ� �ֳ����̼�
 * 	1) @Test      : ���� �׽�Ʈ ����
 * 	2) @Before    : @Test ������ ����
 * 	3) @BeforeAll : JUnit Test Case(BookUnitTest.java) ���� ����, static �ʼ�
 * 	4) @After     : @Test ���Ŀ� ����
 * 	5) @AfterAll  : JUnit Test Case(BookUnitTest.java) ���� ����, static �ʼ�
 * 
 * 3. JUnit4 ���� �׽�Ʈ ���ǻ���
 * 	1) ���� ����(Dao)�� �׽�Ʈ �Ѵ�.
 * 	2) WAS(Tomcat)�� ������ �����Ƿ� WAS�� �ʿ��� �ڵ�� �׽�Ʈ �Ұ��ϴ�.
 * 	3) �޼ҵ� �̸��� �ѱ۷� �ۼ��ص� �������.
 */

public class BookUnitTest {

	// Dao
	private BookDao dao = BookDao.getDao();
	
	
	// @Test
	public void å_���_�׽�Ʈ() {
		
		// ����� BookDto ����
		BookDto dto = BookDto.builder()
				        .title("�׽�Ʈ����")
				        .author("�׽�Ʈ����")
				        .price(99999)
				        .build();
		
		// BookDto ���
		int addResult = dao.bookAdd(dto);
		
		// ��� ��� Ȯ��
		assertEquals(1, addResult);
		
	}
	
	// @Test
	public void å_��ȸ_�׽�Ʈ() {
		
		// ��ȸ�� å ��ȣ
		int bookNo = 1;
		
		// ��ȸ
		BookDto dto = dao.bookDetail(bookNo);
		
		// ��ȸ ��� Ȯ��
		assertNotNull(dto);

	}
	
	// @Test
	public void å_���_�׽�Ʈ() {
		
		// begin, end�� ���� Map ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", 1);
		map.put("end", 20);
		
		// ��� ��������
		List<BookDto> list = dao.bookList(map);
		
		// ��� Ȯ��
		assertEquals(1, list.size());
		
	}
	
	// @Test
	public void å_����_�׽�Ʈ() {
		
		// ������ BookDto ����
		BookDto dto = BookDto.builder()
						.bookNo(1)
						.title("[����]����")
						.author("[����]����")
						.price(100000)
						.build();
		
		// ����
		int modifyResult = dao.bookModify(dto);
				
		// ��� Ȯ��
		assertEquals(1, modifyResult);
		
	}
	
	// @Test
	public void å_����_�׽�Ʈ() {
		
		// ������ å ��ȣ
		int bookNo = 1;
		
		// ����
		int deleteResult = dao.bookDelete(bookNo);
		
		// ��� Ȯ��
		assertEquals(1, deleteResult);	
		
	}

}
