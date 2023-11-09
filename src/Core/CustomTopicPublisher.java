package Core;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import Interfaces.SubscriberListener;

public class CustomTopicPublisher{
	
 	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	private String topicName;
	
	public CustomTopicPublisher(String topicName) {
   	 	this.topicName = topicName;
    }
	
	public void produceMessage(String textMessage) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
 
		Destination dest = session.createTopic(topicName);

		MessageProducer publisher = session.createProducer(dest);

		TextMessage message = session.createTextMessage();
		message.setText(textMessage);

		publisher.send(message);
       
		publisher.close();
		session.close();
		connection.close();
	}

}
