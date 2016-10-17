package marklin.user.service;

import marklin.user.DAO.UserDAO;
import marklin.user.domain.User;
import marklin.user.service.Exception.UserException;

public class UserService {
	private UserDAO dao=new UserDAO();
	public void regist(User user) throws UserException{
		User username = dao.findUserByName(user.getUsername());
		//User password = dao.findUserByName(user.getPassword());
		//User verifyCode=dao.findUserByName(user.getVerifyCode());
		if(username!=null){
			throw new UserException("用户已经存在！");
		}
		addUser(user);
	}
	public void addUser(User user){
		dao.addUser(user);
	}
	
	public User login(User user) throws UserException{
		User UserByName = dao.findUserByName(user.getUsername());
		if(UserByName!=null){
			//该用户名对应的数据中的password和获取的表单中的password数据一样则 用户信息正确
			boolean b = user.getPassword().equals(UserByName.getPassword());
			if(b){
				return user;
			}else{
				throw new UserException("用户密码错误");
			}
		}else{
			throw new UserException("用户不存在");
		}
	}
}
