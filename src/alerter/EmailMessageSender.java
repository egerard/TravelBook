package alerter;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailMessageSender {

	public EmailMessageSender(){
		
	}
	
	public void sendMessage(String recipient, String messageBody){
	    String host = "smtp.gmail.com";
	    String from = "egerard23@gmail.com";
	    String pass = "e8ghtn11)";
	    Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", "true"); // added this line
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    
	    Authenticator authentication = new MyAuthenticator(from, pass);
	     
	    Session session = Session.getDefaultInstance(props, authentication);
	    MimeMessage message = new MimeMessage(session);
	    try {
	      message.addRecipient(
	        Message.RecipientType.TO, new InternetAddress(recipient)
	      );
	      message.setSubject("Price Alert");
	      message.setText(messageBody);
	      Transport.send(message);
	      System.out.println("Email sent");
	    }
	    catch (MessagingException ex){
	      System.err.println("Cannot send email. " + ex);
	    }
	}
	
	public static void main(String[] args){
		EmailMessageSender sender = new EmailMessageSender();
		sender.sendMessage("cweitzn1@gmail.com", "hello cheryl - I am a computer");
	}
	
    private class MyAuthenticator extends javax.mail.Authenticator {
        String User;
        String Password;
        public MyAuthenticator (String user, String password) {
            User = user;
            Password = password;
        }
         
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication(User, Password);
        }
    }
     
	
}
	
