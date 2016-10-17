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
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		//设置响应页面格式
		response.setContentType("text/html;charset=utf-8");
		//获取用户提交信息的用户集合
		Map<?,?> parameterMap = request.getParameterMap();
		//创建基本类User对象
		
		User user=new User();
		UserService us=new UserService();
		try {
			BeanUtils.populate(user, parameterMap);
			
			Map<String,String> errors = new HashMap<String, String>();
			
			String username=user.getUsername();
			if(username==null){
				errors.put("username", "用户名不允许为空!");
			}else if(username.length()<2||username.length()>=20){
				errors.put("username", "用户名长度允许范围为2~20");
			}
			String password=user.getPassword();
			if(password==null){
				errors.put("password", "密码不允许为空!");
			}
			//提取随机生成的验证码和用户提交的验证码
			String s = (String) request.getSession().getAttribute("vcd_code");
			String verifyCode = user.getVerifyCode();
			//判断验证码是否正确
			if(verifyCode==null){
				errors.put("verifyCode", "请输入验证码!");
			}else if(!s.equals(verifyCode)){
				errors.put("verifyCode", "验证码错误!,请重新输入验证码");
			}
			
			if(errors!=null&&errors.size()>0){
				request.setAttribute("error", errors);
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("/marklin/a.jsp").forward(request, response);
				return;
			}
			//调用UserService类的注册方法
			us.regist(user);
			
			PrintWriter writer = response.getWriter();
			writer.print("恭喜您注册成功<a href='"+request.getContextPath()+"/marklin/b.jsp"+"'>点击进入登陆页面</a>");
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
