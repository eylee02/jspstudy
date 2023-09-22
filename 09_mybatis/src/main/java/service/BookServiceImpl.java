package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.BookDto;
import repository.BookDao;
import util.PageVo;

public class BookServiceImpl implements BookService {
	
	private BookDao dao = BookDao.getDao();
	private PageVo pageVo = new PageVo();
	
	
	@Override
	public ActionForward bookAdd(HttpServletRequest request) {
		
		// ����� ����� ����,����
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		
		// ���� + ���� + ���� -> BookDto ��ü
		BookDto dto = BookDto.builder()
				         .title(title)
				         .author(author)
				         .price(price)
				         .build();
		
		// BookDao�� resiger �޼ҵ� ȣ��
		int addResult = dao.bookAdd(dto);
		
		// ��� ����, ��� ����
		String path = null;
		if(addResult == 1) {
			path = request.getContextPath() + "/book/list.jsp";
		} else if(addResult == 0) {
			path = request.getContextPath() + "/index.do";
		}	
		
		return new ActionForward(path, true);
	}
	
	@Override
	public ActionForward bookList(HttpServletRequest request) {
		
		// ���޵� ������ ��ȣ(�����������̾����� 1������������
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int total = dao.bookCount();
		
		int display = 10;
		
		// pageVo�� ��� ���� ����ϱ�
		pageVo.setPaging(page, total, display);
		
		// �Խñ� ����� ������ �� ����� �������� Map���� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageVo.getBegin());
		map.put("end", pageVo.getEnd());
		
		
		// DB�κ��� �Խñ� ��� ��������
		List<BookDto> bookList = dao.bookList(map);
		
		// �Խñ� ��ϰ� paging�� /book/list.jsp�� �����ϱ����� request�� �����ѵ� forward
		request.setAttribute("bookList", bookList);
		request.setAttribute("paging", pageVo.getPaging(request.getContextPath() + "/book/list.do"));
		return new ActionForward("/book/list.jsp", false);
	}
	
	@Override
	public ActionForward bookDetail(HttpServletRequest request) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("bookNo"));
		int bookNo = Integer.parseInt(opt.orElse("0"));
		
		BookDto book = dao.bookDetail(bookNo);
		
		request.setAttribute("book", book);	
		return new ActionForward("/book/detail.jsp", false);
	}
	
	@Override
	public ActionForward bookEdit(HttpServletRequest request) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("bookNo"));
		int bookNo = Integer.parseInt(opt.orElse("0"));
		
		BookDto book = dao.bookDetail(bookNo);
		
		request.setAttribute("book", book);
		return new ActionForward("/book/edit.jsp", false);
	}
	
	@Override
	public ActionForward bookModify(HttpServletRequest request) {
		
		int bookNo = Integer.parseInt(request.getParameter("bookNo"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		
		BookDto dto = BookDto.builder()
					 .bookNo(bookNo)
					 .title(title)
					 .author(author)
					 .price(price)
					 .build();
		
		int modifyResult = dao.bookModify(dto);
		
		String path = null;
		if(modifyResult == 1) {
			path = request.getContextPath() + "/book/detail.do?bookNo=" + bookNo;
		} else {
			path = request.getContextPath() + "/index.do";
		}		
		
		return new ActionForward(path, true);
	}
	
	@Override
	public ActionForward bookDelete(HttpServletRequest request) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("bookNo"));
		int bookNo = Integer.parseInt(opt.orElse("0"));
		
		int deleteResult = dao.bookDelete(bookNo);
		
		String path = null;
		if(deleteResult == 1) {
			path = request.getContextPath() + "/book/list.do";
		} else if(deleteResult == 0) {
			path = request.getContextPath() + "/index.do";
		}				
		
		return new ActionForward(path, true);
	}

}
