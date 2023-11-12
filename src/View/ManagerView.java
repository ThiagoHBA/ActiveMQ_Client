package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ManagerView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane topicPane;
	private JTextPane queuePane;
	private JTextField createTopicTextField;
	private JTextField createQueueTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		topicLabel.setBounds(107, 33, 77, 14);
		contentPane.add(topicLabel);
		
		JLabel queueLabel = new JLabel("Filas");
		queueLabel.setBounds(428, 33, 46, 14);
		contentPane.add(queueLabel);
		
		topicPane = new JTextPane();
		topicPane.setBounds(22, 48, 246, 195);
		contentPane.add(topicPane);
		
		queuePane = new JTextPane();
		queuePane.setBounds(311, 48, 246, 195);
		contentPane.add(queuePane);
		
		JLabel createTopicLabel = new JLabel("Criar tópico");
		createTopicLabel.setBounds(22, 254, 64, 14);
		contentPane.add(createTopicLabel);
		
		createTopicTextField = new JTextField();
		createTopicTextField.setBounds(32, 271, 136, 20);
		contentPane.add(createTopicTextField);
		createTopicTextField.setColumns(10);
		
		JButton createTopicButton = new JButton("Criar");
		createTopicButton.setBounds(178, 268, 90, 23);
		contentPane.add(createTopicButton);
		
		JLabel createQueueLabel = new JLabel("Criar Fila");
		createQueueLabel.setBounds(311, 254, 64, 14);
		contentPane.add(createQueueLabel);
		
		createQueueTextField = new JTextField();
		createQueueTextField.setColumns(10);
		createQueueTextField.setBounds(311, 269, 163, 20);
		contentPane.add(createQueueTextField);
		
		JButton createQueueButton = new JButton("Criar");
		createQueueButton.setBounds(482, 268, 75, 23);
		contentPane.add(createQueueButton);
		
		JLabel createClientLabel = new JLabel("Instanciar Cliente");
		createClientLabel.setBounds(241, 338, 134, 14);
		contentPane.add(createClientLabel);
		
		JTextField clientIdentifierTextField = new JTextField();
		clientIdentifierTextField.setBounds(174, 361, 150, 20);
		contentPane.add(clientIdentifierTextField);
		clientIdentifierTextField.setColumns(10);
		
		JButton createClientButton = new JButton("Instanciar");
		createClientButton.setBounds(334, 360, 107, 23);
		contentPane.add(createClientButton);
	}
}
