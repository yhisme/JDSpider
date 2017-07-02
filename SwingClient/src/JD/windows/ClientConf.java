package JD.windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JD.client.ClientGetGoodsID;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JRadioButton;

public class ClientConf extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_ip;
	private JTextField textField_port1;
	private JTextField textField_port2;
	private JTextField textField_thread;

	/**1272
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ClientConf frame = new ClientConf();
					frame.setVisible(true);
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	 public static Connection getConn(String username,String password) throws ClassNotFoundException, SQLException 
		{

	    	
			 String driver = "com.mysql.jdbc.Driver";
			    //显示中文要字符集
			    String url = "jdbc:mysql://localhost:3306/jd?useUnicode=true&characterEncoding=utf8";
			  
			    Connection conn = null;
			    
			        Class.forName(driver); //classLoader,加载对应驱动
			        conn = (Connection) DriverManager.getConnection(url, username, password);
			  
			    return conn;
		}

	/**
	 * Create the frame.0
	 */
	public ClientConf()
	{
		setResizable(false);
		setTitle("\u5BA2\u6237\u7AEF\u914D\u7F6E");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 325);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField_ip = new JTextField();
		textField_ip.setColumns(10);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				String ip=null;
				int port1 = 0;
				int port2 = 0;
				int threadCount = 0;
				try{
					 ip=textField_ip.getText();
				     port1=Integer.valueOf(textField_port1.getText());
				     port2=Integer.valueOf(textField_port2.getText());
				     threadCount=Integer.valueOf(textField_thread.getText());
				    new Client(ip, port1, port2, threadCount).setVisible(true);
				}catch(Exception e)
				{
					dispose();
					new Wrong().setVisible(true);
					e.printStackTrace();
				}
				
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		JLabel lblip = new JLabel("\u670D\u52A1\u5668\u5C40\u57DF\u7F51ip\uFF1A");
		lblip.setVerticalAlignment(SwingConstants.BOTTOM);
		lblip.setForeground(Color.GRAY);
		lblip.setFont(new Font("华文彩云", Font.PLAIN, 16));
		lblip.setBackground(Color.GRAY);
		
		JLabel label = new JLabel("\u5206\u53D1\u4EFB\u52A1\u670D\u52A1\u5668\u7AEF\u53E3\uFF1A");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("华文彩云", Font.PLAIN, 16));
		label.setBackground(Color.GRAY);
		
		textField_port1 = new JTextField();
		textField_port1.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6536\u96C6\u4FE1\u606F\u670D\u52A1\u5668\u7AEF\u53E3\uFF1A");
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("华文彩云", Font.PLAIN, 16));
		label_1.setBackground(Color.GRAY);
		
		textField_port2 = new JTextField();
		textField_port2.setColumns(10);
		
		JLabel label_2 = new JLabel("\u672C\u673A\u722C\u53D6\u7EBF\u7A0B\u6570\uFF1A");
		label_2.setVerticalAlignment(SwingConstants.BOTTOM);
		label_2.setForeground(Color.GRAY);
		label_2.setFont(new Font("华文彩云", Font.PLAIN, 16));
		label_2.setBackground(Color.GRAY);
		
		textField_thread = new JTextField();
		textField_thread.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(button)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField_port2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField_thread, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(label)
									.addComponent(lblip))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_ip, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_port1, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblip, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_ip, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_port1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_port2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_thread, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(button)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
