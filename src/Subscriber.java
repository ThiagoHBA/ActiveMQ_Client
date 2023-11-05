import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import Interfaces.SubscriberListener;

 
public class Subscriber implements MessageListener {	
 	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private String topicName;
	private SubscriberListener listener;
     
     public Subscriber(String topicName, SubscriberListener listener) {
    	 this.topicName = topicName;
    	 this.listener = listener;
     }

     public void Go(){
         try {
				ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
				Connection connection = connectionFactory.createConnection();
				connection.start();
			
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
				Destination dest = session.createTopic(topicName);
				
				MessageConsumer subscriber = session.createConsumer(dest);
			
				subscriber.setMessageListener(this);
         			 
         } catch(Exception e){
             e.printStackTrace();
         }       
     }   

     public void onMessage(Message message){
         if(message instanceof TextMessage){
             try {
            	 listener.didReceiveTopicMessage(message.toString());
             } catch(Exception e){
            	 System.out.println("Error: " + e.toString());
             }
         }
     }  
 }
