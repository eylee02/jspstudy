package service;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.BoardDto;
import repository.BoardDao;


public class BoardServiceImpl implements BoardService {
	
  // ��� ���񽺰� �������� ����ϴ� BoardDao ��ü ��������
  private BoardDao dao = BoardDao.getDao();
	
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
		path = "/board/list.jsp";
	} else if(registerResult == 0) {
		path = "/index.jsp";
	}

	// ���� ��� �̵��ϴ��� ��ȯ (insert ���� �Ŀ��� �ݵ�� redirect �̵��Ѵ�.)
	return new ActionForward(path, true);
	}
  
  
  
  
  

}
