package JD.server.getid;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import JD.Threads.server.ServerThreadAllocatingGetUrlWork;
import windows.Server;

public class SeverAllocatingGetIDWork
{
	
	private int port;
	
	public SeverAllocatingGetIDWork(int port)
	{
		super();
		this.port = port;
	}

	public static void dofilter1() throws ClientProtocolException, IOException
	{
		HttpClient client = HttpClients.createDefault();
		//所有分类页面
		HttpGet get = new HttpGet("https://www.jd.com/allSort.aspx");
		
		CloseableHttpResponse response = (CloseableHttpResponse) client.execute(get);

		// 得到String
		
		HttpEntity enity = response.getEntity();
		String body = EntityUtils.toString(enity, "UTF-8");

		//System.out.println(body);

		// 字符串处理 及入队
		String regex = "<a href=\"//.{0,200}\" t";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(body);

		//判断是否开始入队
		boolean flag=false;
		while (m.find())
		{
			// System.out.println(m.group());
			// System.out.println(m.start()+"->"+m.end());
			
		
			String s = m.group();
			//System.out.println(s);
			if(s.equals("<a href=\"//e.jd.com/ebook.html\" t"))
				flag=true;
			
			//System.out.println(flag);
			if(flag==true)
			
			JD.Static.Static.filter1.add(s.substring(11,s.length()-3));
			
			if(s.equals("<a href=\"//list.jd.com/list.html?cat=12379,13302,13313\" t"))
				break;
		}

		get.releaseConnection();
		response.close();
		EntityUtils.consume(enity);

	}
	
	public static void dofilter2(String url) throws ClientProtocolException, IOException
	{
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://"+url);
		CloseableHttpResponse response = (CloseableHttpResponse) client.execute(get);

		// 得到String
		HttpEntity enity = response.getEntity();
		String body = EntityUtils.toString(enity, "UTF-8");
		
        
		//System.out.println(body);
		
		String regex = "<a href=\"//list.{0,200}\"\\b";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(body);

		while (m.find())
		{
			// System.out.println(m.group());
			// System.out.println(m.start()+"->"+m.end());
			
		
			String s = m.group();
			//System.out.println(s);
			String nurl=s.substring(11);
			
			String[] rnurl=nurl.split(" ");
			//System.out.println(rnurl[0].substring(0, rnurl[0].length()-1));
			
			JD.Static.Static.filter1.add(rnurl[0]);
		}
		
		get.releaseConnection();
		response.close();
		EntityUtils.consume(enity);
		
	}
	
	public static void getGoodsUrl()
	{
		
		try
		{
			dofilter1();
			
		} catch (ClientProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//int l=JD.Static.Static.GoodsUrl.size();
		
		//遍历过程中不运行add hash 和set
		for(String url:JD.Static.Static.filter1)
		{

		
			//System.out.println(url);
			
			if(url.startsWith("list")==false)
			{
				try
				{
					dofilter2(url);
				} catch (ClientProtocolException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}

		}
		//getList("http://mvd.jd.com/education.html");
		
		for(String url:JD.Static.Static.filter1)
		{
			if(url.startsWith("list")==true)
			{
			JD.Static.Static.filter2.add(url);
			}
		}
		System.out.println("共"+JD.Static.Static.filter2.size()+"个分类");
		Server.allocating_info.insert("共"+JD.Static.Static.filter2.size()+"个分类\n",Server.allocating_info.getText().length());
		
		String rurl ="";
		for(String url:JD.Static.Static.filter2)
		{
			System.out.println(url);
			
			 rurl ="";
			Pattern p = Pattern.compile("[0-9]+");
			Matcher m = p.matcher(url);
			
			int i=0;
			while (m.find())
			{
				
				//System.out.print(m.group() + ",");
				rurl=rurl+m.group()+",";
				i++;
				if(i==3)
					break;
			}
			//System.out.print(rurl.substring(0, rurl.length()-1));
		    JD.Static.Static.GoodsUrl.add(rurl.substring(0, rurl.length()-1));
			
		}
		for(String url: JD.Static.Static.GoodsUrl)
		{
			JD.Static.Static.Pageurl.add(url);
		}
		
		
		System.out.println("得到pageurl,共:"+JD.Static.Static.Pageurl.size()+"个");
		Server.allocating_info.insert("得到pageurl,共:"+JD.Static.Static.Pageurl.size()+"个\n",Server.allocating_info.getText().length());

		
		//清理工作
		JD.Static.Static.filter1.clear();
		JD.Static.Static.filter2.clear();
		JD.Static.Static.GoodsUrl.clear();
		
	}
	
	public static String getIDCount() throws SQLException
	{
		String sql="SELECT COUNT(*) FROM jdgoodsid";
		PreparedStatement ptmt=JD.Static.Static.conn.prepareStatement(sql);
		
		ResultSet rs=ptmt.executeQuery();
		if(rs.next())
		{
			return rs.getString(1);
		}
			return null;
	}
	public  void start() throws InterruptedException, ClientProtocolException, IOException, SQLException
	{
		System.out.println("分发任务服务器初始化中.......");
		Server.allocating_info.insert("分发任务服务器初始化中.......\n",Server.allocating_info.getText().length());

		
		getGoodsUrl();
		int len=JD.Static.Static.Pageurl.size();
		// TODO
//		for(int i=0;i<1000;i++)
//		{
//		String s=JD.Static.Static.Pageurl.poll();
//		}
		
		ServerSocket server = new ServerSocket(port);
	
		int i=0;
		long t1=System.currentTimeMillis();
		
		Server.ID_count.setText(getIDCount()+"个");
		System.out.println("分发任务服务器开启成功......");
		Server.allocating_info.insert("分发任务服务器开启成功!,占用端口:"+Server.port1+"\n",Server.allocating_info.getText().length());
      
//		for(int a=0;a<2173;a++)
//		{
//			JD.Static.Static.Pageurl.poll();
//		}
        while(true){  
        	
        	
        	 //关闭工作
            
        	Socket client  = server.accept();  
            System.out.println("aaa:"+JD.Static.Static.Pageurl.size());
            if(JD.Static.Static.Pageurl.size()==0)
            {
            	new Thread(new ServerThreadAllocatingGetUrlWork(client,"close")).start();
            	System.out.println("任务完成.......");
            	Server.allocating_info.insert("任务完成.......\n",Server.allocating_info.getText().length());
            	break;
            }
            
            System.out.println("与客户端连接成功!共爬取页面"+i+"个,共需爬取页面"+len+"个");  
            Server.ID_count.setText(getIDCount()+"个");
            Server.allocating_info.insert("与客户端连接成功!  共爬取页面"+i+"个,共需爬取页面"+len+"个\n",Server.allocating_info.getText().length());
            Server.allocating_info.setCaretPosition(Server.allocating_info.getText().length());
            i++;
            new Thread(new ServerThreadAllocatingGetUrlWork(client,JD.Static.Static.Pageurl.poll())).start();    
           
           
        }  
        server.close();
        long t2=System.currentTimeMillis();
        System.out.println("爬取完所有url共花费"+(t2-t1)/1000+"秒");
        Server.allocating_info.insert("爬取完所有url共花费\n",Server.allocating_info.getText().length());

        
	}
}
		
		



