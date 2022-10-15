package org.alkemy.challengedisney.util;

import java.io.IOException;

import org.alkemy.challengedisney.exception.GenericException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Component
public class CustomMail {
	
	@Value("${sendgrid.api-key}")
	private String apiKey;
	
	@Value("${sendgrid.from-mail}")
	private String fromMail;

	public void send(String toEmail,String emailSubject, String emailContent) {
		Email from = new Email(this.fromMail);
		String subject = emailSubject;
		Email to = new Email(toEmail);
		Content content = new Content("text/plain", emailContent);
		Mail mail = new Mail(from, subject, to, content);
		SendGrid sg = new SendGrid(this.apiKey);
		Request request = new Request();
		try {
		  request.setMethod(Method.POST);
		  request.setEndpoint("mail/send");
		  request.setBody(mail.build());
		  Response response = sg.api(request);
		  if(response.getStatusCode() != 202) {
			  throw new GenericException(response.getBody());
		  }
		} catch (IOException ex) {
		  throw new GenericException(ex.getMessage());
		}
	}
}
