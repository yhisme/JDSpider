package JD.Threads.server;

import windows.Server;

public class HandleGoodsurlMsg extends Thread
{
	private String msg;
	
	
	
	 public HandleGoodsurlMsg(String msg)
	{
		super();
		this.msg = msg;
	}



	@Override
	public void run()
	{
		
		 String[] id=msg.split(",");
			
		 int count=0;
			for (String singal : id)
			{
				String[] url = new String[]{};
				url=singal.split(" ");
				for (int ii=0;ii<url.length;ii++)
				{
					
					//这里必须用put 不然不会notify
					try
					{
						JD.Static.Static.GoodID.put(url[ii]);
						count++;
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				System.out.println("收集ID服务器收集到商品ID:"+count+"个ID");
	            Server.get_info.insert("收集ID服务器收集到商品ID:"+count+"个ID\n", Server.get_info.getText().length());

				
			}
	
	}

}
