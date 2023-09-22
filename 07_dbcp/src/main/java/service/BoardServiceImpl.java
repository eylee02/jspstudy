package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.BoardDto;
import repository.BoardDao;
import util.PageVo;


public class BoardServiceImpl implements BoardService {
	
  // ��� ���񽺰� �������� ����ϴ� BoardDao, PageVo ��ü ��������
  private BoardDao dao = BoardDao.getDao();
  private PageVo pageVo = new PageVo();
  
  @Override
  public ActionForward register(HttpServletRequest request) {
		
	// ����� ����� ����
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	// ���� + ���� -> BoardDto ��ü
	BoardDto dto = BoardDto.builder()
				      .title(title)
				      .content(content)
				      .build();
	
	// BoardDao�� register �޼ҵ� ȣ��
	int registerResult = dao.register(dto);
	  
	// ��� ���� (registerResult == 1), ��� ����(registerResult == 0)
	String path = null;
	if(registerResult == 1) {
		path = request.getContextPath() + "/board/list.jsp";
	} else if(registerResult == 0) {
		path = request.getContextPath() + "/index.do";
	}

	// ���� ��� �̵��ϴ��� ��ȯ (insert ���� �Ŀ��� �ݵ�� redirect �̵��Ѵ�.)
	return new ActionForward(path, true);
	}
  
  
  @Override
  public ActionForward getBoardList(HttpServletRequest request) {
	  
	 /* page, total, display ������ �־�� ����� ������ �� �ִ�. */
		
	 // ���޵� ������ ��ȣ (������ ��ȣ�� ������ ������ 1 �������� ����.)
	 Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
	 int page = Integer.parseInt(opt.orElse("1"));
	 
	 int total = dao.getBoardCount();  // dao �ʿ���
	  
	 int display = 5;  // ���� �� ���(���ϸ��Ķ���ͷ� �޾� ���� ������ ���浵 ������)
	 
	 // PageVo�� ��� ���� ����ϱ�
	 pageVo.setPaging(page, total, display);
	 
	 // �Խñ� ����� ������ �� ����� �������� Map���� ����
	 Map<String, Object> map = new HashMap<String, Object>();
	 map.put("begin", pageVo.getBegin());
	 map.put("end", pageVo.getEnd());
	 
	 // DB�κ��� �Խñ� ��� ��������
	 List<BoardDto> boardList = dao.getBoardList(map);
	 
	 // �Խñ� ��ϰ� paging�� /board/list.jsp�� �����ϱ� ���Ͽ� request�� ������ �� forward�Ѵ�.
	 request.setAttribute("boardList", boardList);	 
	 request.setAttribute("paging", pageVo.getPaging(request.getContextPath() + "/board/list.do"));
	 return new ActionForward("/board/list.jsp", false);
	 
	}
	

  @Override
	public ActionForward getBoardByNo(HttpServletRequest request) {
		
	  // ����ȸ�� �Խñ� ��ȣ
	  Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
	  int board_no = Integer.parseInt(opt.orElse("0"));
	  
	  // DB�κ��� �Խñ� ��������
	  BoardDto board = dao.getBoardByNo(board_no);
	  
	  // �Խñ��� /board/detail.jsp�� �����ϱ� ���ؼ� forward ó��
	  request.setAttribute("board", board);
	  return new ActionForward("/board/detail.jsp", false);
	}
  
  
  @Override
  public ActionForward edit(HttpServletRequest request) {
	  
	  // ������ �Խñ� ��ȣ
	  Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
	  int board_no = Integer.parseInt(opt.orElse("0"));
	  
	  // DB�κ��� �Խñ� ��������
	  BoardDto board = dao.getBoardByNo(board_no);
	  
	  // �Խñ��� /board/edit.jsp�� �����ϱ� ���ؼ� forward ó��
	  request.setAttribute("board", board);
	  return new ActionForward("/board/edit.jsp", false);
	  
	 
  }
  
  @Override
	public ActionForward modify(HttpServletRequest request) {
		
	  // ������ �Խñ� ����
	  String title = request.getParameter("title");
	  String content = request.getParameter("content");
	  int board_no = Integer.parseInt(request.getParameter("board_no"));
	  
	  // ������ �Խñ� ������ BoardDto ��ü�� ����
	  BoardDto dto = BoardDto.builder()
			        .title(title)
			        .content(content)
			        .board_no(board_no)
			        .build();
	 
	  // �����ϱ�
	  int modifyResult = dao.modify(dto);
	  
	  // ���� ����(modifyResult == 1), ���� ����(modifyResult == 0)
	  String path = null;
	  if(modifyResult == 1) {
		  path = request.getContextPath() + "/board/detail.do?board_no=" + board_no;
	  } else {
		  path = request.getContextPath() + "/index.do";
	  }
	  
	  
	  // update ���Ŀ��� redirect �Ѵ�.
	  return new ActionForward(path, true);
	}
  
  
  @Override
	public ActionForward delete(HttpServletRequest request) {
		
	 // ������ �Խñ� ��ȣ
	 Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
	 int board_no = Integer.parseInt(opt.orElse("0"));

	 // �����ϱ�
	 int deleteResult = dao.delete(board_no);
	 
	 // ���� ����(deleteResult == 1), ���� ����(deleteResult == 0)
	 String path = null;
	 if(deleteResult == 1) {
		 path = request.getContextPath() + "/board/list.do";
	 } else if(deleteResult == 0) {
		 path = request.getContextPath() + "/.index.do";
	 }
	
	 // delete ���Ŀ��� redirect �Ѵ�
		return new ActionForward(path, true);
	}

}

