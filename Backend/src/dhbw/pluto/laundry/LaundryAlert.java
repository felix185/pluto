package dhbw.pluto.laundry;

import java.util.Properties;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.mail.*;
import javax.mail.internet.*;

public class LaundryAlert extends ScheduledThreadPoolExecutor {
	
	private String emailAdress;
	private long time;
	
	public LaundryAlert(String emailAdress, long timestamp){
		super(1);
		this.time = timestamp;
		this.emailAdress = emailAdress;
		
	}
	

	public void initializeAlert(){
		
		long duration = time - System.currentTimeMillis();
		this.toString();
		Runnable alert = new Runnable() {
			
			@Override
			public void run() {
				sendMail("localhost");
			}
			public void sendMail(String host){
				String to = emailAdress;
				String from = "info@waeschealarm.de";
				Properties properties = System.getProperties();
				
				properties.setProperty("localhost", host);
				
				Session session = Session.getDefaultInstance(properties);
				
				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(from));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject("Wäschealarm");
					message.setText("Deine Wäsche ist fertig!");
					Transport.send(message);
					
				}catch (MessagingException mex) {
					mex.printStackTrace();
				}
			}
		};
		this.schedule(alert, duration, TimeUnit.MILLISECONDS);
		System.out.println("Alarm wurde erstellt");
	}


	public String getEmailAdress() {
		return emailAdress;
	}


	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}


	public long getTime() {
		return time;
	}


	public void setTime(long time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "LaundryAlert [emailAdress=" + emailAdress + ", time=" + time + "]";
	}
	

}
