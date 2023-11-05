package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Interfaces.SubscriberListener;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ContentView extends JFrame implements SubscriberListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField sendMessageToTopicTextField;
	private JTextField queueIdentifier;
	private JTextField directMessageTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContentView frame = new ContentView();
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
	public ContentView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 374);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tópicos");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mensagem Direta");
		lblNewLabel_1.setBounds(446, 11, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Assinar Tópico");
		lblNewLabel_2.setBounds(10, 36, 79, 14);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setToolTipText("Nome do Tópico");
		textField.setBounds(10, 57, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton assignTopicButton = new JButton("Assinar");
		assignTopicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { assignToTopicTapped(); }
		});
		assignTopicButton.setBounds(106, 56, 89, 23);
		contentPane.add(assignTopicButton);
		
		JLabel lblNewLabel_3 = new JLabel("Mensagens do Tópico");
		lblNewLabel_3.setBounds(10, 88, 121, 14);
		contentPane.add(lblNewLabel_3);
		
		JTextPane topicMessagesPane = new JTextPane();
		topicMessagesPane.setBackground(new Color(232, 232, 232));
		topicMessagesPane.setBounds(10, 113, 263, 147);
		contentPane.add(topicMessagesPane);
		
		sendMessageToTopicTextField = new JTextField();
		sendMessageToTopicTextField.setBounds(7, 304, 188, 20);
		contentPane.add(sendMessageToTopicTextField);
		sendMessageToTopicTextField.setColumns(10);
		
		JButton sendTopicMessageButton = new JButton("Enviar");
		sendTopicMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  }
		});
		sendTopicMessageButton.setBounds(211, 303, 62, 23);
		contentPane.add(sendTopicMessageButton);
		
		JLabel lblNewLabel_4 = new JLabel("Enviar Mensagem para o tópico");
		lblNewLabel_4.setBounds(10, 279, 185, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Usuário");
		lblNewLabel_5.setBounds(300, 36, 36, 14);
		contentPane.add(lblNewLabel_5);
		
		queueIdentifier = new JTextField();
		queueIdentifier.setBounds(300, 57, 163, 20);
		contentPane.add(queueIdentifier);
		queueIdentifier.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Mensagem");
		lblNewLabel_6.setBounds(300, 88, 78, 14);
		contentPane.add(lblNewLabel_6);
		
		directMessageTextField = new JTextField();
		directMessageTextField.setBounds(300, 112, 163, 20);
		contentPane.add(directMessageTextField);
		directMessageTextField.setColumns(10);
		
		JButton sendDirectMessageButton = new JButton("Enviar");
		sendDirectMessageButton.setBounds(473, 113, 62, 23);
		contentPane.add(sendDirectMessageButton);
		
		JTextPane directMessagesPanel = new JTextPane();
		directMessagesPanel.setBackground(new Color(232, 232, 232));
		directMessagesPanel.setBounds(300, 143, 235, 181);
		contentPane.add(directMessagesPanel);
	}
	
	public void assignToTopicTapped() {
		
	}

	@Override
	public void didReceiveTopicMessage(String message) {
		
		
	}
}
