package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JD.server.getid.ServerGetGoodsIDFromClient;
import JD.server.getid.SeverAllocatingGetIDWork;
import JD.server.getinfo.ServerAllocatingGetGoodsInfoWork;
import JD.server.getinfo.SeverGetGoodInfoFromClient;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JScrollPane;

public class Server extends JFrame
{

	private JPanel contentPane;
	public static JTextArea allocating_info;
	public static JTextArea get_info;
	public static JTextField ID_count;
	public static JTextField textField_1;
	public static String user;
	public static String password;
	public static String port1;
	public static String port2;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Server(String user, String password, String port1,String port2)
	{
		setResizable(false);
		Server.user=user;
		Server.password=password;
		Server.port1=port1;
		Server.port2=port2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 926, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("\u5F00\u542F\u5206\u53D1\u4EFB\u52A1\u670D\u52A1\u5668");
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			//开启服务器
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnNewButton.setEnabled(false);
				
				
				Thread t = new Thread(
						new Runnable(){
				    public void run(){
								try
								{
									//分发ID
									int p1 = Integer.valueOf(port1);
									SeverAllocatingGetIDWork sID = new SeverAllocatingGetIDWork(p1);
									sID.start();
									
									//分发商品信息
									
									ServerAllocatingGetGoodsInfoWork sInfo=new ServerAllocatingGetGoodsInfoWork(p1);
									System.out.println("分发商品信息开启------------------------------------------------------");
									sInfo.start();
									
								} catch (Exception e)
								{
									new WrongPort().setVisible(true);
									e.printStackTrace();
									dispose();
								}
							}
				});
				t.start();
				
				
			}
		});
		btnNewButton.setFont(new Font("华文中宋", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton button = new JButton("\u5F00\u542F\u6536\u96C6\u4FE1\u606F\u670D\u52A1\u5668");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int p2=Integer.valueOf(port2);
				button.setEnabled(false);

				Thread t = new Thread(new Runnable()
				{
					public void run()
					{
						ServerGetGoodsIDFromClient sgid = new ServerGetGoodsIDFromClient(p2);
						SeverGetGoodInfoFromClient sginof=new SeverGetGoodInfoFromClient(p2);

						try
						{
							sgid.start();
							System.out.println("2-----------------------");
							sginof.start();
						} catch (IOException e)
						{
							new WrongPort().setVisible(true);
							e.printStackTrace();
							dispose();
						}
						
						
					}
				});
				t.start();

				
			}
		});
		button.setFont(new Font("华文中宋", Font.PLAIN, 15));
		
		JLabel lblid_1 = new JLabel("\u722C\u866B\u5148\u722C\u53D6\u5546\u54C1ID\u540E\u722C\u53D6\u5546\u54C1\u4FE1\u606F");
		lblid_1.setFont(new Font("幼圆", Font.PLAIN, 17));
		
		JLabel lblid = new JLabel("\u6570\u636E\u5E93\u5546\u54C1ID\u6570\u91CF\uFF1A");
		lblid.setFont(new Font("华文细黑", Font.PLAIN, 15));
		
		JLabel label_1 = new JLabel("\u6570\u636E\u5E93\u5546\u54C1\u4FE1\u606F\u6570\u91CF\uFF1A");
		label_1.setFont(new Font("华文细黑", Font.PLAIN, 15));
		
		ID_count = new JTextField();
		ID_count.setEditable(false);
		ID_count.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6CE8\uFF1A\u4E24\u4E2A\u670D\u52A1\u5668\u9700\u540C\u65F6\u5F00\u542F,");
		label_2.setFont(new Font("幼圆", Font.PLAIN, 17));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblid)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(ID_count, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblid_1)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblid)
						.addComponent(ID_count, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblid_1)
							.addContainerGap())))
		);
		
		JTextArea txtrAsd = new JTextArea();
		txtrAsd.setFont(new Font("华文细黑", Font.PLAIN, 15));
		allocating_info=txtrAsd;
		scrollPane.setViewportView(txtrAsd);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("华文细黑", Font.PLAIN, 15));
		scrollPane_1.setViewportView(textArea);
		get_info=textArea;
		contentPane.setLayout(gl_contentPane);
	}
}
