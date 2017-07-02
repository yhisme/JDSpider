package JD.windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JD.client.ClientGetGoodsID;
import JD.client.ClientGetGoodsInfo;
import javafx.scene.control.TextArea;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JScrollPane;
import java.awt.Font;

public class Client extends JFrame
{

	private JPanel contentPane;
	private JTextField txtAsdasd;
	public static JTextField textField_1;
	public static JTextArea client_info;
	public String ip;
	public int port1;
	public int port2;
	public int threadCount;
	public static  AtomicInteger IDcount=new AtomicInteger(0);
	public static  AtomicInteger Infocount=new AtomicInteger(0);
	public static JTextField ID_count;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Client(String ip,int port1, int port2 ,int threadCount)
	{
		setResizable(false);
		txtAsdasd = new JTextField();
		txtAsdasd.setColumns(10);
		Client.ID_count=txtAsdasd;
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton button = new JButton("\u5F00\u59CB\u722C\u53D6");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				button.setEnabled(false);
				Thread t= new Thread(new Runnable()
				{
					
					@Override
					public void run()
					{
						
						
						ClientGetGoodsID cID=new ClientGetGoodsID(ip, port1, port2, threadCount);
						ClientGetGoodsInfo cInfo=new ClientGetGoodsInfo(ip, port1, port2, threadCount);
						try
						{
							cID.start();
							System.out.println("Cinfo................");
							try
							{
								Thread.sleep(1000*60);
							} catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							cInfo.start();
						} catch (IOException e1)
						{
							
							new Wrong().setVisible(true);
							dispose();
						}
						
					}
				});
				t.start();
					}
				});
		JLabel label = new JLabel("");
		
		JLabel lblid = new JLabel("\u672C\u673A\u5DF2\u722C\u5546\u54C1ID\uFF1A");
		
		
		
		JLabel label_1 = new JLabel("\u672C\u673A\u5DF2\u722C\u5546\u54C1\u4FE1\u606F\uFF1A");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(button)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(lblid))
					.addGap(6)
					.addComponent(txtAsdasd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addComponent(label_1)
					.addGap(6)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(button)
					.addGap(7)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addGap(3)
							.addComponent(lblid))
						.addComponent(txtAsdasd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		
		JTextArea txtrDsa = new JTextArea();
		txtrDsa.setFont(new Font("»ªÎÄÏ¸ºÚ", Font.PLAIN, 14));
		client_info=txtrDsa;
		scrollPane.setViewportView(txtrDsa);
		contentPane.setLayout(gl_contentPane);
	}
}
