package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Core.CustomQueueProducer;
import Core.CustomTopicPublisher;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.jms.JMSException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ManagerView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane topicPane;
	private JTextPane queuePane;
	private JTextField createTopicTextField;
	private JTextField createQueueTextField;
	private JTextField clientIdentifierTextField;
	
	private CustomQueueProducer queueProducer = new CustomQueueProducer();
	private CustomTopicPublisher topicPublisher = new CustomTopicPublisher();
	
	private JTextField removeQueueTextField;
	private JTextField removeTopicTextField;

	public void render() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerView frame = new ManagerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManagerView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel managerLabel = new JLabel("Gerenciador");
		managerLabel.setBounds(264, 11, 97, 14);
		contentPane.add(managerLabel);
		
		JLabel topicLabel = new JLabel("Topicos");
		topicLabel.setBounds(117, 33, 77, 14);
		contentPane.add(topicLabel);
		
		JLabel queueLabel = new JLabel("Filas");
		queueLabel.setBounds(413, 33, 46, 14);
		contentPane.add(queueLabel);
		
		topicPane = new JTextPane();
		topicPane.setBackground(new Color(232, 232, 232));
		topicPane.setForeground(new Color(64, 0, 64));
		topicPane.setEditable(false);
		topicPane.setBounds(22, 48, 246, 195);
		contentPane.add(topicPane);
		
		queuePane = new JTextPane();
		queuePane.setEditable(false);
		queuePane.setForeground(new Color(0, 0, 0));
		queuePane.setBackground(new Color(232, 232, 232));
		queuePane.setBounds(299, 48, 258, 195);
		contentPane.add(queuePane);
		
		JLabel createTopicLabel = new JLabel("Criar tópico");
		createTopicLabel.setBounds(22, 254, 246, 14);
		contentPane.add(createTopicLabel);
		
		createTopicTextField = new JTextField();
		createTopicTextField.setBounds(22, 271, 146, 20);
		contentPane.add(createTopicTextField);
		createTopicTextField.setColumns(10);
		
		JButton createTopicButton = new JButton("Criar");
		createTopicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTopicTapped();
			}
		});
		createTopicButton.setBounds(178, 268, 90, 23);
		contentPane.add(createTopicButton);
		
		JLabel createQueueLabel = new JLabel("Criar Fila");
		createQueueLabel.setBounds(299, 254, 64, 14);
		contentPane.add(createQueueLabel);
		
		createQueueTextField = new JTextField();
		createQueueTextField.setColumns(10);
		createQueueTextField.setBounds(299, 269, 163, 20);
		contentPane.add(createQueueTextField);
		
		JButton createQueueButton = new JButton("Criar");
		createQueueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createQueueTapped();
			}
		});
		createQueueButton.setBounds(472, 268, 85, 23);
		contentPane.add(createQueueButton);
		
		JLabel createClientLabel = new JLabel("Instanciar Cliente");
		createClientLabel.setBounds(245, 351, 134, 14);
		contentPane.add(createClientLabel);
		
		clientIdentifierTextField = new JTextField();
		clientIdentifierTextField.setBounds(178, 374, 150, 20);
		contentPane.add(clientIdentifierTextField);
		clientIdentifierTextField.setColumns(10);
		
		JButton createClientButton = new JButton("Instanciar");
		createClientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instantiateClientTapped();
			}
		});
		createClientButton.setBounds(338, 373, 107, 23);
		contentPane.add(createClientButton);
		
		removeQueueTextField = new JTextField();
		removeQueueTextField.setColumns(10);
		removeQueueTextField.setBounds(299, 315, 163, 20);
		contentPane.add(removeQueueTextField);
		
		JLabel removeQueueLabel = new JLabel("Remover Fila");
		removeQueueLabel.setBounds(299, 300, 163, 14);
		contentPane.add(removeQueueLabel);
		
		JButton removeQueueButton = new JButton("Remover");
		removeQueueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { removeQueueTapped(); }
		});
		removeQueueButton.setBounds(472, 314, 85, 23);
		contentPane.add(removeQueueButton);
		
		JLabel removeTopicLabel = new JLabel("Remover Tópico");
		removeTopicLabel.setBounds(22, 298, 163, 14);
		contentPane.add(removeTopicLabel);
		
		removeTopicTextField = new JTextField();
		removeTopicTextField.setColumns(10);
		removeTopicTextField.setBounds(22, 313, 146, 20);
		contentPane.add(removeTopicTextField);
		
		JButton removeTopicButton = new JButton("Remover");
		removeTopicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeTopicTapped();
			}
		});
		removeTopicButton.setBounds(178, 312, 90, 23);
		contentPane.add(removeTopicButton);
		
		
		try {
			queueProducer.initialize();
			topicPublisher.initialize();
			updateQueues();	
			updateTopics();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateQueues() {
		try {
			List<String> queueNames = queueProducer.loadQueueNames();
			queuePane.setText("");
			int index = 0;
			
			for (String queueName: queueNames) { 
				int pendindMessagesCount = queueProducer.countMessagesInQueue(queueName);
				String oldPaneText = queuePane.getText();
				queuePane.setText(oldPaneText + "\n" + (index + 1) + ": " + queueName + " | ("+ pendindMessagesCount + ") Mensagens não lidas"); 
				index++;
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTopics() {
		try {
			List<String> topicNames = topicPublisher.loadTopicNames();
			topicPane.setText("");
			int index = 0;
			
			for (String topicName: topicNames) { 
				String oldPaneText = topicPane.getText();
				topicPane.setText(oldPaneText + "\n" + (index + 1) + ": " + topicName); 
				index++;
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void instantiateClientTapped() {
		String clientName = clientIdentifierTextField.getText();
		if (clientName.isBlank()) { return; }
		
		try { 
			queueProducer.createQueue(clientName);
			updateQueues();
			
			System.out.println("Displaying Client View");
			ClientView.render(clientName);
			
		} catch (JMSException e) {
			e.printStackTrace();
		} 
	}
	
	public void createTopicTapped() {
		String topicName = createTopicTextField.getText();
		if (topicName.isBlank()) return;
		
		try { 
			topicPublisher.createTopic(topicName);			
			System.out.println("Tópico de nome: " + topicName +" criado com sucesso");
			updateTopics();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void createQueueTapped() {
		String queueName = createQueueTextField.getText();
		if (queueName.isBlank()) return;

		try { 
			queueProducer.createQueue(queueName);
			System.out.println("Fila de nome: " + queueName +" criada com sucesso");
			updateQueues();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void removeQueueTapped() {
		String queueName = removeQueueTextField.getText();
		if (queueName.isBlank()) { return; }
		
		try {
			 queueProducer.deleteQueue(queueName);
			 updateQueues();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void removeTopicTapped() {
		String topicName = removeTopicTextField.getText();
		if (topicName.isBlank()) { return; }
		
		try {
			 topicPublisher.deleteTopic(topicName);
			 updateTopics();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
