package JD.Threads.server;


import java.io.PrintStream;  
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  
  
/** 
 * 该类为多线程类，用于服务端 
 */  
public class ServerThreadAllocatingGetInfoWork implements Runnable {  
  
	private String rmsg;
    private Socket client = null;  
    public ServerThreadAllocatingGetInfoWork(Socket client){  
        this.client = client;  
  
        rmsg="";
		for(int index=0;index<100 ;index++)
		{
		  rmsg=rmsg+JD.Static.Static.GoodsID.poll()+" ";
		}
    }  
      
    public static String getInfoCount() throws SQLException
	{
		String sql="SELECT COUNT(*) FROM goods";
		PreparedStatement ptmt=JD.Static.Static.conn.prepareStatement(sql);
		
		ResultSet rs=ptmt.executeQuery();
		if(rs.next())
		{
			return rs.getString(1);
		}
			return null;
	}
    @Override  
	public void run()
	{
    	try
		{
			windows.Server.textField_1.setText(getInfoCount());
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			// 获取Socket的输出流，用来向客户端发送数据
			PrintStream out = new PrintStream(client.getOutputStream());
			// 获取Socket的输入流，用来接收从客户端发送过来的数据
			//BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));

			// 接收从客户端发送过来的数据
			//String str = buf.readLine();
			//System.out.println(str);
			if(windows.Server.allocating_info.getText().length()>100000)
    		{
				windows.Server.allocating_info.setText(windows.Server.allocating_info.getText().substring(50000, 100000));
    		}

			out.println(rmsg);
			out.close();
			client.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
  
}  