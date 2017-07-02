package JD.client;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintStream;  
import java.net.Socket;  
import java.net.SocketTimeoutException;  
  
public class Client2
{
	private String ip;
	private int port;
	
	public Client2(String ip, int port)
	{
		super();
		this.ip = ip;
		this.port = port;
	}

	public String sendAndGet(String msg) throws IOException
	{
		// 客户端请求与本机在20006端口建立TCP连接
		Socket client = new Socket(ip, port);
		client.setSoTimeout(10000);

		PrintStream out = new PrintStream(client.getOutputStream());
		// 获取Socket的输入流，用来接收从服务端发送过来的数据
		BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));	
		out.println(msg);

		try
		{
			// 从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
			String smsg = buf.readLine();
			return smsg;
		} catch (SocketTimeoutException e)
		{
			System.out.println("Time out, No response");
		}finally
		{
			
			if (client != null)
			{
				System.out.println("client close");
				// 如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭
				client.close(); // 只关闭socket，其关联的输入输出流也会被关闭
			}
			
		}
		
		return null;

		
	}
}