package JD.Threads.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  
import java.io.PrintStream;  
import java.net.Socket;

import JD.server.getid.ServerGetGoodsIDFromClient;  
  
/** 
 * 该类为多线程类，用于服务端 
 */  
public class ServerThreadGetGoodsUrlFromClient implements Runnable {  
  
	private String rmsg;
    private Socket client = null;  
    public ServerThreadGetGoodsUrlFromClient(Socket client,String rmsg){  
        this.client = client;  
        this.rmsg=rmsg;
    }  
      
    @Override  
	public void run()
	{
		try
		{
			// 获取Socket的输出流，用来向客户端发送数据
			PrintStream out = new PrintStream(client.getOutputStream());
			// 获取Socket的输入流，用来接收从客户端发送过来的数据
			BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));

			// 接收从客户端发送过来的数据
			String str = buf.readLine();
			
			//System.out.println("服务器读取到数据"+str);
			
			if(str.equals("close"))
			{
				ServerGetGoodsIDFromClient.down=true;
			}
			
			 Thread HandleMsg=new Thread(new HandleGoodsurlMsg(str));
			    
			 HandleMsg.start();
			

			out.println(rmsg);
			
			
			
			client.close();
			
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
  
}  