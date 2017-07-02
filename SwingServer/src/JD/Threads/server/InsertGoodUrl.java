package JD.Threads.server;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import JD.Static.Static;



public class InsertGoodUrl  extends Thread
{
	


	 
	 
	
	 
	 @Override
	public void run()
	{
	
		while(true)
		{
        try
		{
			String id=JD.Static.Static.GoodID.take();
		
			if(id.equals("close"))
				break;
			long  goods_id = 0;
			if(id!=null&&id!="")
			{
				try
				{
			     goods_id =Long.parseLong(id);
				}catch(Exception e)
				{
					
					continue;
				}
			
			}
		
			//insertID(id);
			 String sql = "insert into jdgoodsid(id) values(?)";

				
			  PreparedStatement ptmt = null;
			try
			{
				ptmt = (PreparedStatement) Static.conn.prepareStatement(sql);
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  try
			{
				ptmt.setLong(1, goods_id);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  try
			{
				ptmt.execute();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				continue;
			}finally
			{
				try
				{
					ptmt.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			  
			//id=null;
			
		} catch (InterruptedException e)
		{
			
			e.printStackTrace();
		}
	    }
	
	}

}