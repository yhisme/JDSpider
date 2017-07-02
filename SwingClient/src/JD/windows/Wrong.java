package JD.windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class Wrong extends JFrame
{

	private JPanel contentPane;

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
					Wrong frame = new Wrong();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Wrong()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 112);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblD = new JLabel("\u5BA2\u6237\u7AEF\u51FA\u73B0\u5F02\u5E38\uFF01\u8BF7\u68C0\u67E5\u914D\u7F6E\u662F\u5426\u6B63\u786E");
		lblD.setFont(new Font("·½ÕýÀ¼Í¤³¬Ï¸ºÚ¼òÌå", Font.PLAIN, 20));
		contentPane.add(lblD, BorderLayout.CENTER);
	}

}
