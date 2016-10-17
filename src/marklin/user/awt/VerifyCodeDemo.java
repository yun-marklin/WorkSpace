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
 * 	1.����һ�� ģ�����ó��Ϳ�
 * 	2.��������ַ����ַ���Χ
 * 	3.��������ĸ��ַ�������������ַ�����������
 * 	4.�����ɵ�������Ƶ�ģ����
 */
public class VerifyCodeDemo {
	private int weight=70;
	private int height=25;
	private Random r=new Random();
	//��װ�ɱ���������
	private String [] fontNames={"����","���Ŀ���","С׭","����","΢���ź�","����_GB2312"};
	//��װҪ���ɵ��ַ��������ַ���
	private String s="123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTYVWXYZ";
	//������ɫ
	private Color bgcolor=new Color(255,255,255);
	//�����ı���ʽ
	private String text; 
	//�������һ���ַ�
	private char  octet(){
		int nextInt = r.nextInt(s.length());
		char c = s.charAt(nextInt);	
		return c;
	}
	//�������������ɫ
	private Color randomColor(){
		int red=r.nextInt(150);
		int green=r.nextInt(150);
		int blue=r.nextInt(150);
		return new Color(red,green,blue);
	}
	//���������������
	private Font randomfont(){
		int nextInt = r.nextInt(fontNames.length);
		ArrayList<String> al=new ArrayList<String>();
		for(String Str:fontNames){
			al.add(Str);
		}
		//���������������
		String font = al.get(nextInt);
		//�����������ʽ, 0(����ʽ), 1(����), 2(б��), 3(����+б��)
		int nextInt2 = r.nextInt(4);
		//��������ֺ�
		int nextInt3=r.nextInt(5)+24;
		return new Font(font, nextInt2, nextInt3);
	}
	//���Ƹ�����
	private void drawLine(BufferedImage image){
		int num=3;
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		for(int x=0;x<num;x++){
			int x1=r.nextInt(weight);
			int x2=r.nextInt(weight);
			int y1=r.nextInt(height);
			int y2=r.nextInt(height);
			//ָ���ߵĿ��
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
	//���������֤��
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
