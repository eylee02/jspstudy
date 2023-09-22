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
		
		// 등록할 제목과 저자,가격
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		
		// 제목 + 내용 + 가격 -> BookDto 객체
		BookDto dto = BookDto.builder()
				         .title(title)
				         .author(author)
				         .price(price)
				         .build();
		
		// BookDao의 resiger 메소드 호출
		int addResult = dao.bookAdd(dto);
		
		// 등록 성공, 등록 실패
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
		
		// 전달된 페이지 번호(페이지전달이없으면 1페이지를연다
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int total = dao.bookCount();
		
		int display = 10;
		
		// pageVo의 모든 정보 계산하기
		pageVo.setPaging(page, total, display);
		
		// 게시글 목록을 가져올 때 사용할 변수들을 Map으로 만듦
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageVo.getBegin());
		map.put("end", pageVo.getEnd());
		
		
		// DB로부터 게시글 목록 가져오기
		List<BookDto> bookList = dao.bookList(map);
		
		// 게시글 목록과 paging을 /book/list.jsp로 전달하기위해 request에 저장한뒤 forward
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
