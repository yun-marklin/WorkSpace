package marklin.user.DAO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import marklin.user.domain.User;

/*
 * �û����ݲ�����
 */
public class UserDAO {
	private String path="E://users.xml";
	@Test
	public User findUserByName(String username){
		SAXReader reader=new SAXReader();
		Document read=null;
		try {
			read = reader.read(path);
			Element el=(Element) read.selectSingleNode("//user[@username='"+username+"']");
			if(el==null){
				return null;
			}
			User user =new User();
			String Valuename = el.attributeValue("username");
			String Valuepwd = el.attributeValue("password");
			user.setUsername(Valuename);
			user.setPassword(Valuepwd);
			return user;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void addUser(User user){
		SAXReader reader = new SAXReader();
		Document read=null;
		try {
			read = reader.read(path);
			//��ȡRootElement����
			Element element = read.getRootElement();
			Element userEle = element.addElement("user");
			userEle.addAttribute("username", user.getUsername());
			userEle.addAttribute("password", user.getPassword());
			//���������
			OutputFormat format = new OutputFormat("\t",true);
			//����ԭ���������ͻ���
			format.setTrimText(true);
			
			XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path),"utf-8"),format);
			writer.write(read);
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
