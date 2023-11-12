package Core;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class CustomQueueProducer {

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public void produceMessage(String queueName, String messageText) throws JMSException {  
	      ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	      Connection connection = connectionFactory.createConnection();
	      connection.start();
	
	      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
	      Destination destination = session.createQueue(queueName);
	
	      MessageProducer producer = session.createProducer(destination);
	      TextMessage message = session.createTextMessage(messageText);
	
	      producer.send(message);
	
	      System.out.println("Messagem: '" + message.getText() + ", Enviada para a Fila");
	
	      producer.close();
	      session.close();
	      connection.close();
	 }
}