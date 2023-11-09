package Core;
import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class Consumidor{
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	private static String queueName = "1";
	
	public static void main(String[] args) throws JMSException {
	      ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	      Connection connection = connectionFactory.createConnection();
	      connection.start();
	
	
	      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
	      Destination destination = session.createQueue(queueName);
	
	      MessageConsumer consumer = session.createConsumer(destination);
	
	      Message message = consumer.receive();
	
	
	        if (message instanceof TextMessage) { 
	                    TextMessage textMessage = (TextMessage) message;
	                    String text = textMessage.getText();
	                    System.out.println("Recebido: " + text);
	                } else {
	                    System.out.println("Recebido: " + message);
	                }
	
	        consumer.close();
	        session.close();
	        connection.close();
	    }
}