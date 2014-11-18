package internet;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public final class Mailer {
   
	// DOC https://javamail.java.net/nonav/docs/api/com/sun/mail/smtp/package-summary.html
	
	private final String mailerName;
	
    private Properties properties = System.getProperties();
    private SmtpHost smtpHost;
    
    private static Logger LOGGER = Logger.getLogger(Mailer.class);
    
    public Mailer(final String name, final SmtpHost sHost) {
    	this.mailerName = name;
    	this.smtpHost = sHost;
//      properties.setProperty("mail.user", "test");
//      properties.setProperty("mail.password", "mypwd");
    	properties.setProperty("mail.smtp.host", sHost.getHost());
    	properties.setProperty("mail.smtp.port", sHost.getPort());
    }
    
    public void sendMail(List<String> recipients, String subject, String text) {    	
    	LOGGER.info("email ->  " + recipients.get(0) + ", subject:" + subject);
//    	Session session = Session.getDefaultInstance(properties);
//    	try {
//    		for (String recipient : recipients) {	    		 
//    			MimeMessage message = new MimeMessage(session);
//    			message.setFrom(new InternetAddress(this.mailerName));        
//    			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//    			message.setSubject(subject);
//    			String emailtext = "Dear " + getFirstName(recipient) + ",\n" + text + "\nRegards";
//    			message.setText(emailtext);
//    			Transport.send(message);
//    			LOGGER.info("email ->  " + recipient + ", subject:" + subject);
//    		}
//    	} catch (MessagingException mex) {
//    		LOGGER.error("unable to send message.", mex);	    	
//    	}
	}
	
    private String getFirstName(String emailRecipient) {
    	// assume firstname.lastname@
    	return emailRecipient.split("\\.")[0];
    }
}
