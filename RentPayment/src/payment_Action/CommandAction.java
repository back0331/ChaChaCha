package payment_Action;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
public interface CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
//��û �Ķ���ͷ� ��ɾ �����ϴ� ����� ���� �������̽�
//���ǿ� �����Ϸ��� request.getsession(); �޼ҵ带 �����ϸ� �ȴ�.