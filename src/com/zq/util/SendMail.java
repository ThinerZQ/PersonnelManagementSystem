package com.zq.util;

import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {

	public void sendMail(String sendUser,String toUser,String subject,String massage){
		
		
		
		//之后可以把msg交给transport发送
		
		try {
			//properties对象定义session中的一些属性
			Properties props = new Properties();
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "smtp.qq.com");
			
			//可以，在session得到实力的时候，传一个验证信息返回器对象
			//创建message对象
			Session session = Session.getInstance(props,
					new Authenticator(){
						protected PasswordAuthentication getPasswordAuthentication(){
							return new PasswordAuthentication("601097836","blue,193746.");
						}
				}
			);
			session.setDebug(true);
			
			
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sendUser));
			msg.setSubject(subject);
			msg.setRecipients(RecipientType.TO, InternetAddress.parse(toUser));
			//调用这个静态方法
			
			msg.setContent(massage,"text/html;charset=gbk");
			
			Transport.send(msg);
		
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
