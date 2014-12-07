package com.kic.hrm.server.businesslogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReportServiceImpl {

	public static boolean sendReportDailyToEmail(String email) {
		// TODO Auto-generated method stub
		SendEmailServiceImpl sender = new SendEmailServiceImpl();
		String emailTo = email;
		String emailForm = "noppon.w@vr.camt.info";
		String subject = "Send Report Daliy";

		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"email/emailBody.html"));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch (IOException e) {
		}
		String content = contentBuilder.toString();
		System.out.println("content : " + content);
		String htmlBody = content;
		// Files.toString(new File("/path/to/file", Charsets.UTF_8);
		boolean isSuccessSend = sender.sendMail(emailTo, emailForm, subject,
				htmlBody);
		return isSuccessSend;
	}

}
