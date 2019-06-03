package com.sssri.test.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyCode {
	private int width=100;
	private int height=40;
	
	private Random r=new Random();
	private String[] fontNames={"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
	private String codes="23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ"; //没有1和l，数字0和o
	private Color baColor=new Color(255,255,255);
	private String text;
	
	public String getText(){
		return text;
	}
	
	private Color randomColor(){
		int red=r.nextInt(150);
		int green=r.nextInt(150);
		int blue=r.nextInt(150);
		return new Color(red,green,blue);
	}
	
	private Font randomFont(){
		int index=r.nextInt(fontNames.length);
		String fontName=fontNames[index];
		int style=r.nextInt(4);
		int size=r.nextInt(5)+24;
		return new Font(fontName,style,size);
	}
	
	private void drawLine(BufferedImage image){
		int num=3;
		Graphics2D g=(Graphics2D)image.getGraphics();
		for(int i=0;i<num;i++){
			int x1=r.nextInt(width);
			int y1=r.nextInt(height);
			int x2=r.nextInt(width);
			int y2=r.nextInt(height);
			g.setStroke(new BasicStroke(1.5F));
			g.setColor(Color.blue);
			g.drawLine(x1, y1, x2, y2);
		}
	}
	
	private char randomChar(){
		int index=r.nextInt(codes.length());
		return codes.charAt(index);
	}
	
	private BufferedImage createImage(){
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D=(Graphics2D)image.getGraphics();
		g2D.setColor(this.baColor);
		g2D.fillRect(0, 0, width, height);
		return image;
	}
	
	public BufferedImage getImage(){
		BufferedImage image=createImage();
		Graphics2D g2D=(Graphics2D)image.getGraphics();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<4;i++){
			String s=randomChar()+"";
			sb.append(s);
			float x=i*1.0F*width/4;
			g2D.setFont(randomFont());
			g2D.setColor(randomColor());
			g2D.drawString(s, x, height-6);
		}
		this.text=sb.toString();
		drawLine(image);
		return image;
	}
	
	public static void output(BufferedImage image, OutputStream out) 
			throws IOException{
		ImageIO.write(image, "JPEG", out);
	}

}
