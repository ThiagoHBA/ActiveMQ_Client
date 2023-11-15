package Core;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTopic;

public class CustomTopicPublisher{
	
 	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	ConnectionFactory connectionFactory;
	Connection connection;
	Session session;
	
	public void initialize() throws JMSException {
		connectionFactory = new ActiveMQConnectionFactory(url);
		connection = connectionFactory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	
	public void deinitialize() throws JMSException {
	      session.close();
	      connection.close();
	}
	
	public void createTopic(String topicName) throws JMSException {
		Destination dest = session.createTopic(topicName);
		MessageProducer publisher = session.createProducer(dest);
		publisher.close();
	}
	
	public void produceMessage(String topicName, String textMessage) throws JMSException { 
		Destination dest = session.createTopic(topicName);
		MessageProducer publisher = session.createProducer(dest);
		TextMessage message = session.createTextMessage(textMessage);

		publisher.send(message);
		publisher.close();
	}

	public List<String> loadTopicNames() throws JMSException {
		Set<ActiveMQTopic> topics = ((ActiveMQConnection) connection).getDestinationSource().getTopics();
		List<String> topicNames = new ArrayList<>();
		
		for (ActiveMQTopic topic: topics) { 
			topicNames.add(topic.getPhysicalName());
		}
		
		return topicNames;
	}
	
	public void deleteTopic(String topicName) throws JMSException {
		Destination destination = session.createTopic(topicName);
		((ActiveMQConnection) connection).destroyDestination((ActiveMQDestination) destination);
	}
}
