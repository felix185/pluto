package dhbw.pluto.laundry;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.mail.*;
import javax.mail.internet.*;


public class LaundryAlert extends ScheduledThreadPoolExecutor {
	
	private String emailAdress;
	private Date time;
	
	public LaundryAlert(String emailAdress, String time){
		super(1);
		this.time = new Date();
		setTime(time);
		this.emailAdress = emailAdress;
		
		

	}
	
	private void setTime(String time) {
		this.time.setHours(Integer.parseInt(time.substring(0, 2)));
		this.time.setMinutes(Integer.parseInt(time.substring(3, 5)));
		this.time.setSeconds(0);
		System.out.println(this.time.toString());
	}

	public void initializeAlert(){
		
		Date now = new Date();
		System.out.println(now.toString());
		long duration = (this.time.getTime()-now.getTime())/1000;
		System.out.println(duration);
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
		this.schedule(alert, duration, TimeUnit.SECONDS);
	}
	

}
