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
			throw new UserException("�û��Ѿ����ڣ�");
		}
		addUser(user);
	}
	public void addUser(User user){
		dao.addUser(user);
	}
	
	public User login(User user) throws UserException{
		User UserByName = dao.findUserByName(user.getUsername());
		if(UserByName!=null){
			//���û�����Ӧ�������е�password�ͻ�ȡ�ı��е�password����һ���� �û���Ϣ��ȷ
			boolean b = user.getPassword().equals(UserByName.getPassword());
			if(b){
				return user;
			}else{
				throw new UserException("�û��������");
			}
		}else{
			throw new UserException("�û�������");
		}
	}
}
