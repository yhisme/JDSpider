package JD.server.getid;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import JD.Threads.server.InsertGoodUrl;

import JD.Threads.server.ServerThreadGetGoodsUrlFromClient;
import windows.Server;



public class ServerGetGoodsIDFromClient
{
	private int  port;
	public static boolean down=false;

	
	

	public ServerGetGoodsIDFromClient(int port)
	{
		super();
		this.port = port;
	}



	public void start() throws IOException
	{
		
		InsertGoodUrl insert= new InsertGoodUrl();
		Thread tin= new Thread(insert);
     	tin.start();

		 int i = 0;

			
		  ServerSocket server = new ServerSocket(port);		
		  System.out.println("收集ID服务器开启......");
		  Server.get_info.insert("收集ID服务器成功开启!,占用端口:"+Server.port2, Server.get_info.getText().length());
	        while(true){ 
	        	
	        	if(ServerGetGoodsIDFromClient.down==true)
	        	{
	        		System.out.println("close.............");
	        		server.close();
	        		break;
	        	}
	        	
	        	// TODO
	        	Socket client = server.accept();  
	            System.out.println("收集ID服务器与客户端连接成功！共连接"+i+"次"); 
	            Server.get_info.insert("收集ID服务器与客户端连接成功！共连接"+i+"次\n", Server.get_info.getText().length());
	            Server.get_info.setCaretPosition(Server.get_info.getText().length());
	            i++;
	            

              
	            new Thread(new ServerThreadGetGoodsUrlFromClient(client,"ok")).start();  
	          
               
	        }  
	        
	        System.out.println("任务完成收集ID服务器关闭.....................");
			Server.get_info.insert("任务完成收集ID服务器关闭.....................", Server.get_info.getText().length());


		}

}
