package com.kic.hrm.server;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailServiceImpl {
	
	public static void SentEmail() 
	{
		System.out.println("Server ask SentEmail");
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "...";

		try {
		    Message msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress("noppon.w@vr.camt.info", "Example.com Admin"));
		    msg.addRecipient(Message.RecipientType.TO,
		     new InternetAddress("noppon.w@vr.camt.info", "Mr. User"));
		    msg.setSubject("Your Example.com account has been activated");
		    msg.setText(msgBody);
		    Transport.send(msg);
		    
		    System.out.println("Server ask SentEmail end");
		    
		} catch (AddressException e) {
			System.out.println("AddreesException : " + e);
		    // ...
		} catch (MessagingException e) {
			System.out.println("MessagingException : " + e);
		    // ...
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("UnsupportedEncodingException : " + e);
			e.printStackTrace();
		}
	}
}
