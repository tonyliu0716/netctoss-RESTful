package org.tarena.netctoss.controller.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class GetImageController {
	@RequestMapping(value="/getCode/{dateTime}",method=RequestMethod.GET)
	//不需要返回值，因为是一个图片，我们利用的是response输出
	public void execute(@PathVariable("dateTime") String date, HttpServletResponse response, HttpServletRequest request) throws IOException{
		//1.准备一张空白的有尺寸的图片：
		BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
		//2.获取图片的画笔对象
		Graphics g = image.getGraphics();
		//3.设置画笔颜色
		Random ran = new Random();
		g.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
		//4.绘制一个矩形的背景,从（0，0）开始画出一个背景 100为宽，30为高
		g.fillRect(0, 0, 100, 30);
		//5.调用自定义方法，获取长度为5的字母数字组合的字符串
		String number = getNumber(5);
		HttpSession session = request.getSession();
		session.setAttribute("checkcode", number);
		//6.更换画笔颜色
		g.setColor(new Color(0, 0, 0));
		//7.设置字体
		g.setFont(new Font(null, Font.BOLD, 24));
		//8.画出字符串及干扰线
		g.drawString(number, 5, 25);
		for(int i = 0; i < 8; i++){
			g.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
			g.drawLine(ran.nextInt(100), ran.nextInt(30), ran.nextInt(100), ran.nextInt(30));
		}
		//9.设置相应流的数据格式
		response.setContentType("image/jpeg");
		//10.获取输出流
		OutputStream ops = response.getOutputStream();
		//11.存储图片到流中
		ImageIO.write(image, "jpeg", ops);
		ops.close();
	}
	
	//获取字符串的方法：
		public String getNumber(int length){
			String[] array = {"1","2","3","4","5","6","7","8","9","0","A","B","C","D","E","F","G"
					,"H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
			String str = "";
			Random ran = new Random();
			for(int i = 0; i < length; i++){
				str += array[ ran.nextInt(array.length) ];
			}
			return str;
		}
}
