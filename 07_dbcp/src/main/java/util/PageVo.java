package util;

public class PageVo {

	
	// ������ ��� �����
	private int page;           // ���� ������ ��ȣ(��û �Ķ���ͷ� �޴´�.)
	private int total;          // ��ü �׸��� ����(DB���� ���� �� �޴´�.)
	private int display;        // �� �������� ǥ���� �׸��� ����(��û �Ķ���ͷ� �޴´�.)
	private int begin;          // �� �������� ǥ�õǴ� �׸��� ���� ��ȣ(����Ѵ�.)
	private int end;            // �� �������� ǥ�õǴ� �׸��� ���� ��ȣ(����Ѵ�.)
	
	public void setPage(int page, int total, int display) {
		
		// ���� ���� ����
		this.page = page;
		this.total = total;
		this.display = display;
		
		// ����� ���� ����
		begin = (page - 1) * display + 1;
		end = begin + display - 1;
		
	}
	
	
}
