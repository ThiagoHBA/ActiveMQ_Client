package Core;
import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class CustomQueueConsumer {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public TextMessage consume(String queueName) throws JMSException {
	      ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	      Connection connection = connectionFactory.createConnection();
	      connection.start();
	
	
	      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
	      Destination destination = session.createQueue(queueName);
	
	      MessageConsumer consumer = session.createConsumer(destination);
	
	      Message message = consumer.receive();
	
	      consumer.close();
	      session.close();
	      connection.close();
	      
          if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                return textMessage;
          }    
          
          return null;
	}
}