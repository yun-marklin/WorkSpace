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
 *  生成验证码
 */
public class VerifyServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VerifyCodeDemo vcd=new VerifyCodeDemo();
		//生成随机数
		BufferedImage graph = vcd.graph();
		//获取验证码文本信息
		String vcd_code = vcd.getText();
		//保存到session域中
		HttpSession session = request.getSession();
		session.setAttribute("vcd_code",vcd_code);
		
		vcd.output(graph, response.getOutputStream());
	}
}
