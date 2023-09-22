package util;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PageVo {
	
	// ������ ��� �����
	private int page;
	private int total;
	private int display;        // �� �������� ǥ���� �׸��� ����(��û �Ķ���ͷ� �޴´�.)
	private int begin;          // �� �������� ǥ�õǴ� �׸��� ���� ��ȣ(����Ѵ�.)
	private int end;            // �� �������� ǥ�õǴ� �׸��� ���� ��ȣ(����Ѵ�.)
	
	private int totalPage;          // ��ü �������� ����(����Ѵ�.)
	private int pagePerBlock = 2;   // �� ��Ͽ� ǥ�õǴ� �������� ����(���Ƿ� ���Ѵ�)
	private int beginPage;          // �� ��Ͽ� ǥ�õǴ� �������� ���� ��ȣ(����Ѵ�.)
	private int endPage;            // �� ��Ͽ� ǥ�õǴ� �������� ���� ��ȣ(����Ѵ�.)
	
	public void setPaging(int page, int total, int display) {
		
		/* �� �������� ��Ÿ�� �� �ʿ��� ���� */
		
		// ���� ���� ����
		this.page = page;
		this.total = total;
		this.display = display;
		
		// ����� ���� ����
		begin = (page - 1) * display + 1;
		end = begin + display - 1;
		
		if(end > total) {
			end = total;
		}
		
		/* ��ü �������� ��Ÿ�� �� �ʿ��� ���� */
		
		// ��ü ������ ���
		totalPage = (int)Math.ceil((double)total / display);
		
		// �� ����� ���� �������� ���� ������ ���
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}

		
	}
	
	public String getPaging(String url) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div>");
		
		// ���� ���
		if(beginPage == 1) {
			sb.append("<span>����</span>");
		} else {
			sb.append("<a href=\""+ url + "?page=" + (beginPage - 1) + "\">����</a>");
		}
		
		// ������ ��ȣ
		for(int p = beginPage; p <= endPage; p++) {
			// ���� ������ Ŭ������
			if(p == page) {
				sb.append("<span>" + p + "</span>");
			} else {
		  sb.append("<a href=\""+ url + "?page=" + p + "\">" + p + "</a>");
			}
		}
	
		// ���� ���
		if(endPage == totalPage) {
			sb.append("<span>����</span>");
		} else {
			sb.append("<a href=\""+ url + "?page=" + (endPage + 1) + "\">����</a>");
		}
		
		sb.append("</div>");
		
		return sb.toString();

	}
	
	

}
