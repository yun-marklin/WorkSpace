package marklin.user.awt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;



/*
 * 	1.创建一个 模板设置长和宽
 * 	2.创建随机字符的字符范围
 * 	3.随机生成四个字符，并随机生成字符的字体类型
 * 	4.将生成的字体绘制到模板中
 */
public class VerifyCodeDemo {
	private int weight=70;
	private int height=25;
	private Random r=new Random();
	//封装可变字体类型
	private String [] fontNames={"宋体","华文楷体","小篆","黑体","微软雅黑","楷体_GB2312"};
	//封装要生成的字符的所有字符集
	private String s="123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTYVWXYZ";
	//背景颜色
	private Color bgcolor=new Color(255,255,255);
	//设置文本格式
	private String text; 
	//随机生成一个字符
	private char  octet(){
		int nextInt = r.nextInt(s.length());
		char c = s.charAt(nextInt);	
		return c;
	}
	//生成随机背景颜色
	private Color randomColor(){
		int red=r.nextInt(150);
		int green=r.nextInt(150);
		int blue=r.nextInt(150);
		return new Color(red,green,blue);
	}
	//随机生成字体类型
	private Font randomfont(){
		int nextInt = r.nextInt(fontNames.length);
		ArrayList<String> al=new ArrayList<String>();
		for(String Str:fontNames){
			al.add(Str);
		}
		//生成随机字体名称
		String font = al.get(nextInt);
		//生成随机的样式, 0(无样式), 1(粗体), 2(斜体), 3(粗体+斜体)
		int nextInt2 = r.nextInt(4);
		//生成随机字号
		int nextInt3=r.nextInt(5)+24;
		return new Font(font, nextInt2, nextInt3);
	}
	//绘制干扰线
	private void drawLine(BufferedImage image){
		int num=3;
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		for(int x=0;x<num;x++){
			int x1=r.nextInt(weight);
			int x2=r.nextInt(weight);
			int y1=r.nextInt(height);
			int y2=r.nextInt(height);
			//指定线的宽度
			graphics.setStroke(new BasicStroke());
			graphics.setColor(Color.green);
			graphics.drawLine(x1, y1, x2, y2);
		}
		
	}
	private BufferedImage createImage(){
		BufferedImage image=new BufferedImage(weight,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(bgcolor);
		graphics.fillRect(0, 0, weight, height);
		return image;
	}
	//生成随机验证码
	public BufferedImage graph(){
		BufferedImage bi=createImage();
		Graphics2D gr = (Graphics2D) bi.getGraphics();
		StringBuilder sb=new StringBuilder();
		for(int x=0;x<4;x++){
			String write=octet()+"";
			sb.append(write);
			float f=x*1.0F*weight/4;
			gr.setFont(randomfont());
			gr.setColor(randomColor());
			gr.drawString(write, f, height-5);
		}
		this.text = sb.toString();
		drawLine(bi);
		return bi;
	}
	public String getText(){
		return text;
	}
	public void output(BufferedImage bi,OutputStream out) throws IOException{
		ImageIO.write(bi,"JPEG",out);
	}
}
