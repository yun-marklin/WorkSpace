package marklin.user.web;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import marklin.user.awt.VerifyCodeDemo;
/*
 *  ������֤��
 */
public class VerifyServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VerifyCodeDemo vcd=new VerifyCodeDemo();
		//���������
		BufferedImage graph = vcd.graph();
		//��ȡ��֤���ı���Ϣ
		String vcd_code = vcd.getText();
		//���浽session����
		HttpSession session = request.getSession();
		session.setAttribute("vcd_code",vcd_code);
		
		vcd.output(graph, response.getOutputStream());
	}
}
