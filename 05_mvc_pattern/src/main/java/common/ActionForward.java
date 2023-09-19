package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class ActionForward {
	
	private String path;         // ���� �� ���ΰ�
	private boolean isRedirect;  // redirect�� true, forward�� false
	
}
