package marklin.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.commons.beanutils.BeanUtils;

import marklin.user.domain.User;
import marklin.user.service.UserService;
import marklin.user.service.Exception.UserException;

public class RegistServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		//���ñ����ʽ
		request.setCharacterEncoding("utf-8");
		//������Ӧҳ���ʽ
		response.setContentType("text/html;charset=utf-8");
		//��ȡ�û��ύ��Ϣ���û�����
		Map<?,?> parameterMap = request.getParameterMap();
		//����������User����
		
		User user=new User();
		UserService us=new UserService();
		try {
			BeanUtils.populate(user, parameterMap);
			
			Map<String,String> errors = new HashMap<String, String>();
			
			String username=user.getUsername();
			if(username==null){
				errors.put("username", "�û���������Ϊ��!");
			}else if(username.length()<2||username.length()>=20){
				errors.put("username", "�û�����������ΧΪ2~20");
			}
			String password=user.getPassword();
			if(password==null){
				errors.put("password", "���벻����Ϊ��!");
			}
			//��ȡ������ɵ���֤����û��ύ����֤��
			String s = (String) request.getSession().getAttribute("vcd_code");
			String verifyCode = user.getVerifyCode();
			//�ж���֤���Ƿ���ȷ
			if(verifyCode==null){
				errors.put("verifyCode", "��������֤��!");
			}else if(!s.equals(verifyCode)){
				errors.put("verifyCode", "��֤�����!,������������֤��");
			}
			
			if(errors!=null&&errors.size()>0){
				request.setAttribute("error", errors);
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("/marklin/a.jsp").forward(request, response);
				return;
			}
			//����UserService���ע�᷽��
			us.regist(user);
			
			PrintWriter writer = response.getWriter();
			writer.print("��ϲ��ע��ɹ�<a href='"+request.getContextPath()+"/marklin/b.jsp"+"'>��������½ҳ��</a>");
		}catch(UserException e){
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", user);
			request.getRequestDispatcher("/marklin/a.jsp").forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
