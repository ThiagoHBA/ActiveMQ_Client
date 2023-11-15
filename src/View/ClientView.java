package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Core.CustomQueueConsumer;
import Core.CustomQueueProducer;
import Core.CustomTopicPublisher;
import Core.CustomTopicSubscriber;
import Interfaces.SubscriberListener;

import javax.jms.JMSException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;

public class ClientView extends JFrame implements SubscriberListener {
	private String clientIdentifier; 

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField assignTopicTextField;
	private JTextField sendMessageToTopicTextField;
	private JTextField queueIdentifier;
	private JTextPane topicMessagesPane;
	private CustomQueueConsumer consumer = new CustomQueueConsumer();
	private CustomQueueProducer producer = new CustomQueueProducer();
	private JTextPane directMessagesPanel;
	private JTextField directMessageTextField;
	
	public static void render(String clientIdentifier) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientView frame = new ClientView(clientIdentifier);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientView(String clientIdentifier) {
		this.clientIdentifier = clientIdentifier;
		setupView();
	}
	
	public void setupView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 446);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tópicos");
		lblNewLabel.setBounds(88, 33, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mensagem Direta");
		lblNewLabel_1.setBounds(394, 33, 126, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Assinar Tópico");
		lblNewLabel_2.setBounds(13, 78, 185, 14);
		contentPane.add(lblNewLabel_2);
		
		assignTopicTextField = new JTextField();
		assignTopicTextField.setBounds(13, 99, 86, 20);
		assignTopicTextField.setToolTipText("Nome do Tópico");
		contentPane.add(assignTopicTextField);
		assignTopicTextField.setColumns(10);
		
		JButton assignTopicButton = new JButton("Assinar");
		assignTopicButton.setBounds(109, 98, 89, 23);
		assignTopicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { assignToTopicTapped(); }
		});
		contentPane.add(assignTopicButton);
		
		JLabel lblNewLabel_3 = new JLabel("Mensagens do Tópico");
		lblNewLabel_3.setBounds(13, 130, 263, 14);
		contentPane.add(lblNewLabel_3);
		
		topicMessagesPane = new JTextPane();
		topicMessagesPane.setBounds(13, 155, 263, 147);
		topicMessagesPane.setEditable(false);
		topicMessagesPane.setBackground(new Color(232, 232, 232));
		contentPane.add(topicMessagesPane);
		
		sendMessageToTopicTextField = new JTextField();
		sendMessageToTopicTextField.setBounds(10, 346, 176, 20);
		contentPane.add(sendMessageToTopicTextField);
		sendMessageToTopicTextField.setColumns(10);
		
		JButton sendTopicMessageButton = new JButton("Enviar");
		sendTopicMessageButton.setBounds(196, 345, 78, 23);
		sendTopicMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { sendMessageToTopicTapped(); }
		});
		contentPane.add(sendTopicMessageButton);
		
		JLabel lblNewLabel_4 = new JLabel("Enviar Mensagem para o tópico");
		lblNewLabel_4.setBounds(13, 321, 263, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Identificador");
		lblNewLabel_5.setBounds(303, 78, 78, 14);
		contentPane.add(lblNewLabel_5);
		
		queueIdentifier = new JTextField();
		queueIdentifier.setBounds(303, 99, 163, 20);
		contentPane.add(queueIdentifier);
		queueIdentifier.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Mensagem");
		lblNewLabel_6.setBounds(303, 130, 78, 14);
		contentPane.add(lblNewLabel_6);
		
		directMessageTextField = new JTextField();
		directMessageTextField.setBounds(303, 154, 145, 20);
		contentPane.add(directMessageTextField);
		directMessageTextField.setColumns(10);
		
		JButton sendDirectMessageButton = new JButton("Enviar");
		sendDirectMessageButton.setBounds(458, 153, 80, 23);
		sendDirectMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendDirectMessageTapped();
			}
		});
		contentPane.add(sendDirectMessageButton);
		
		directMessagesPanel = new JTextPane();
		directMessagesPanel.setEditable(false);
		directMessagesPanel.setBounds(303, 185, 235, 147);
		directMessagesPanel.setBackground(new Color(232, 232, 232));
		contentPane.add(directMessagesPanel);
		
		JButton updateMessagesButton = new JButton("Atualizar Mensagens");
		updateMessagesButton.setBounds(338, 345, 176, 23);
		updateMessagesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateReceivedMessages();
			}
		});
		contentPane.add(updateMessagesButton);
		
		JLabel clientIdentifierLabel = new JLabel("Identificador");
		clientIdentifierLabel.setBounds(254, 11, 260, 14);
		contentPane.add(clientIdentifierLabel);
		clientIdentifierLabel.setText(clientIdentifierLabel.getText() + ": " + this.clientIdentifier);
		
		try {
			producer.initialize();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void assignToTopicTapped() {
		String topicName = assignTopicTextField.getText();
		if (topicName.isBlank()) { return; }
		
		CustomTopicSubscriber subscriber = new CustomTopicSubscriber(topicName, this);
		subscriber.Go();
	}
	
	public void sendMessageToTopicTapped() {
		String topicName = assignTopicTextField.getText();
		String message = sendMessageToTopicTextField.getText();
		
		if (topicName.isBlank()) { return; }
		if (message.isBlank()) { return; }
		
		CustomTopicPublisher publisher = new CustomTopicPublisher();

		try {
			publisher.initialize();
			publisher.produceMessage(topicName, message);
			System.out.println("Enviando mensagem: " + message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void sendDirectMessageTapped() {
		String queueIdentifierText = queueIdentifier.getText();
		String messageText = directMessageTextField.getText();
		
		if (queueIdentifierText.isBlank()) { return; }
		if (messageText.isBlank()) { return; }
		
		try {
			producer.produceMessage(queueIdentifierText, messageText);
			System.out.println("Enviando mensagem: " + messageText + "para o usuário: " + queueIdentifierText);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void updateReceivedMessages() {
		try {
			String message = consumer.consume(this.clientIdentifier).getText();
			
			if (message != null) {
				String oldMessages = directMessagesPanel.getText();
				directMessagesPanel.setText(oldMessages + "\n" + message);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void didReceiveTopicMessage(String message) {
		String oldMessage = topicMessagesPane.getText();
		topicMessagesPane.setText(oldMessage + "\n" + message);
	}

}
