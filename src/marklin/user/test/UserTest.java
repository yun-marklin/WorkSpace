package marklin.user.test;

import marklin.user.DAO.UserDAO;
import marklin.user.domain.User;
import marklin.user.service.UserService;
import marklin.user.service.Exception.UserException;

import org.junit.Test;

public class UserTest {
	
	@Test
	public void findUserTest(){
		UserDAO user=new UserDAO();
		User byName = user.findUserByName("marklin");
		System.out.println(byName);
	}
//	@Test
//	public void addUserTest(){
//		UserDAO user=new UserDAO();
//		User u=new User();
//		u.setUsername("海神");
//		u.setPassword("456789");
//		user.addUser(u);
//	}
	@Test
	public void registTest(){
		UserService us=new UserService();
		User user=new User();
		user.setUsername("海神");
		//user.setPassword("456789");
		try {
			us.regist(user);
		} catch (UserException e) {
			System.out.println("用户已经存在！");
		}
	}
}
