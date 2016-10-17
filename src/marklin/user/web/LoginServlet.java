package marklin.user.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import marklin.user.domain.User;
import marklin.user.service.UserService;
import marklin.user.service.Exception.UserException;
/*
 * 登陆成功项
 *  调用service里的login()方法
 */
public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取请求参数的map集合
		Map map = request.getParameterMap();
		User form = new User();
		
			try {
				BeanUtils.populate(form, map);
				UserService s=new UserService();
				User user = s.login(form);
				//将获取的user对象放入到Session域中
				request.getSession().setAttribute("user",user);
				response.sendRedirect(request.getContextPath()+"/marklin/c.jsp");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserException e) {
				request.setAttribute("msg",e.getMessage());
				request.setAttribute("user", form);
				request.getRequestDispatcher("/marklin/b.jsp").forward(request, response);
			}	
		//获取UserService 类的对象
	}
}
