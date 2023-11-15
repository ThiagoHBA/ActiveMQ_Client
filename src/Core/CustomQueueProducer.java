package Core;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;


public class CustomQueueProducer {
	ConnectionFactory connectionFactory;
	Connection connection;
	Session session;
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public void initialize() throws JMSException {
	      this.connectionFactory = new ActiveMQConnectionFactory(url);
	      this.connection = connectionFactory.createConnection();
	      connection.start();
	
	      this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	
	public void deinitialize() throws JMSException {
	      session.close();
	      connection.close();
	}
	
	public void createQueue(String queueName) throws JMSException {	
	      Destination destination = session.createQueue(queueName);
	      MessageProducer producer = session.createProducer(destination);
	      producer.close();
	}
	
	public void produceMessage(String queueName, String messageText) throws JMSException {  
		  Destination destination = session.createQueue(queueName);
		  MessageProducer producer = session.createProducer(destination);
	      TextMessage message = session.createTextMessage(messageText);
	
	      producer.send(message);
	 }
	
	public List<String> loadQueueNames() throws JMSException {
		Set<ActiveMQQueue> queues = ((ActiveMQConnection) connection).getDestinationSource().getQueues();
		List<String> queueNames = new ArrayList<>();
		
		for (ActiveMQQueue queue: queues) { 
			queueNames.add(queue.getPhysicalName());
		}
		
		return queueNames;
	}
	
	public void deleteQueue(String queueName) throws JMSException {
		Destination destination = session.createQueue(queueName);
		((ActiveMQConnection) connection).destroyDestination((ActiveMQDestination) destination);
	}
	
	public int countMessagesInQueue(String queueName) throws JMSException {
		ActiveMQQueue queue = (ActiveMQQueue) session.createQueue(queueName);
		QueueBrowser browser = session.createBrowser((ActiveMQQueue) queue);
		Enumeration<?> enumeration = browser.getEnumeration();
		int count = 0;
		while (enumeration.hasMoreElements()) {
			enumeration.nextElement();
			count++;
		}
		return count;
	}
}