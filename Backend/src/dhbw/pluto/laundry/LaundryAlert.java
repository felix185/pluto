package dhbw.pluto.laundry;

import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.mail.*;
import javax.mail.internet.*;

import dhbw.pluto.activities.ActivityController;
import dhbw.pluto.activities.ActivityCreationException;
import dhbw.pluto.activities.LaundryNotificationActivity;
import dhbw.pluto.activities.LaundryReminderCreationActivity;



public class LaundryAlert extends ScheduledThreadPoolExecutor {
	
	private String emailAdress;
	private String time;
	
	public LaundryAlert(String emailAdress, String timestamp){
		super(1);
		this.time = timestamp;
		this.emailAdress = emailAdress;
		
	}
	

	public void initializeAlert(){
		Calendar alertTime = Calendar.getInstance();
		alertTime.set(Calendar.MINUTE, Integer.parseInt(time.substring(3,5)));
		alertTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0,2)));
		alertTime.set(Calendar.SECOND, 0);
		alertTime.set(Calendar.MILLISECOND, 0);
		System.out.println(alertTime.getTimeInMillis());
		System.out.println(System.currentTimeMillis());
		System.out.println(alertTime.getTimeInMillis() - System.currentTimeMillis());
		
		long duration = alertTime.getTimeInMillis() - System.currentTimeMillis();
	
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
				try {
					ActivityController.writeActivity(new LaundryNotificationActivity(System.currentTimeMillis(), to));
				} catch (ActivityCreationException e) {
					e.printStackTrace();
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


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "LaundryAlert [emailAdress=" + emailAdress + ", time=" + time + "]";
	}
	

}
