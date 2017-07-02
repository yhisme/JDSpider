package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class ServerDB extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;

	/**
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
					ServerDB frame = new ServerDB();
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
	 * Create the frame.
	 */
	public ServerDB()
	{
		setResizable(false);
		setTitle("\u6570\u636E\u5E93\u914D\u7F6E");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 220);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u4E0B\u4E00\u6B65");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				EventQueue.invokeLater(new Runnable()
				{
					public void run()
					{
						String u=textField.getText();
						String p=textField_1.getText();
						
						boolean flag=false;
						
						try{
							getConn(u, p);
							flag=true;
						}catch(Exception e)
						{
							
							new WrongDb().setVisible(true);
							dispose();
						}
						
						if (flag == true)
						{
							try
							{
								ServerConfig frame = new ServerConfig(u,p);
								frame.setVisible(true);
								dispose();
							} catch (Exception e)
							{
								e.printStackTrace();
							}
						}
					}
				});
			}
		});
		
		JLabel label_1 = new JLabel("\u6570\u636E\u5E93\u5E10\u53F7\uFF1A");
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("华文彩云", Font.PLAIN, 18));
		label_1.setBackground(Color.GRAY);
		
		JLabel label = new JLabel("\u6570\u636E\u5E93\u5BC6\u7801\uFF1A");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("华文彩云", Font.PLAIN, 18));
		label.setBackground(Color.GRAY);
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
							.addGap(0))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(18))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button)
					.addContainerGap(32, Short.MAX_VALUE))
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
