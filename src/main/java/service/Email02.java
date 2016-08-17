package service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email02 {

	public Email02(String email, String account, String password) {
		// 01.寄發郵件(收件人、信件主旨、信件內容)
		final MailStatus status = this.sendMail(email, "訂餐系統通知信件",
				"您好,您的訂餐系統密碼已更改\n\r帳號:" + account + "\n\r密碼:" + password
						+ "\n\r請馬上快速登入系統使用服務喔~~\n\r系統管理員 敬上!!");

		// 02.傳送結果
		System.out.println(status.getMsg());
	}

	/**
	 * 郵件處理狀態
	 */
	public enum MailStatus {
		Success("信件發送成功!"), //
		Fail("信件發送失敗!");

		final String msg;

		private MailStatus(final String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}
	}

	/**
	 * Send Mail via Gmail
	 * 
	 * @param toUser
	 * @param subject
	 * @param content
	 * @return MailStatus
	 */
	private MailStatus sendMail(final String toUser, final String subject,
			final String content) {
		final String loginUser = "abcd20160608@gmail.com"; // Gmail 帳號<必輸入>
		final String password = "20160608"; // Gmail 密碼<必輸入>

		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(loginUser, password);
			}
		});
		try {
			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(loginUser));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toUser));
			message.setSubject(subject);
			message.setText(content);
			Transport.send(message);
		} catch (MessagingException e) {
			System.out.println(e);
			return MailStatus.Fail;
		}

		return MailStatus.Success;
	}

}