package JD.client;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import JD.Threads.client.GetGoodsInfo;
import JD.Threads.client.SendGoodInfoToServer;

public class ClientGetGoodsInfo
{
	public static String ip;
	public static int port;
	public static int port2;
	public static int ThreadCount;
	public static StringBuffer rmsg = new StringBuffer("");

	public static JSONArray jsonArray = new JSONArray();

	public ClientGetGoodsInfo(String i, int p, int p2, int tc)
	{
		ip = i;
		port = p;
		port2 = p2;
		if (ThreadCount > 15)
			ThreadCount = 15;

		ThreadCount = tc;

	}

	public void start()
	{

		while (true)
		{

			Client c = new Client(ip, port);
			String msg = null;
			try
			{
				msg = c.sendAndGet("giveme100");
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String[] id = msg.split(" ");
			System.out.println("客户端收到:" + id.length);
			JD.windows.Client.client_info.append("收集信息客户端开启!.................................\n");
			JD.windows.Client.client_info.append("客户端收到:" + id.length);

			ExecutorService fixedThreadPool = Executors.newFixedThreadPool(ThreadCount);
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(200);// 设置最大连接数
			cm.setDefaultMaxPerRoute(200);// 对每个指定连接的服务器（指定的ip）可以创建并发20
											// socket进行访问
			// 上面的设置是我本机对服务器最大的连接数

			CloseableHttpClient httpClient = HttpClients.custom().setRetryHandler(new DefaultHttpRequestRetryHandler())// 设置请求超时后重试次数默认3次
					.setConnectionManager(cm).build();

			int len = id.length;
			for (int i = 0; i < len; i++)
			{

				HttpGet httpget = new HttpGet("http://item.jd.com/" + id[i] + ".html");
				System.out.println("创建连接:");
				JD.windows.Client.client_info.append("创建连接:" + httpget.getURI() + "\n");
				System.out.println(httpget.getURI());
				fixedThreadPool.execute(new GetGoodsInfo(httpClient, httpget, id[i]));
				httpget.releaseConnection();
			}

			fixedThreadPool.shutdown();
			while (true)
			{

				if (fixedThreadPool.isTerminated())
				{
					try
					{
						httpClient.close();
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("所有的子线程都结束了");
					JD.windows.Client.client_info.append("所有的子线程都结束了\n");

					break;

				}
				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try
			{

				// System.out.println("jsonlen"+jsonArray.length());

				// 价格
				String url = "";
				for (int i = 0; i < len; i++)
					url = url + "J_" + id[i] + "%2c";

				// System.out.println(url);
				CloseableHttpClient client = HttpClients.createDefault();
				HttpGet get4 = new HttpGet("https://p.3.cn/prices/mgets?skuIds=" + url
						+ "&pdbp=0&pdtk=&pdpin=&pduid=131242956&source=list_pc_front&_=1494422447061");
				// System.out.println(get4.getURI());
				CloseableHttpResponse response4 = (CloseableHttpResponse) client.execute(get4);
				HttpEntity entity4 = response4.getEntity();
				String body4 = EntityUtils.toString(entity4, "UTF-8");
				// System.out.println(body4);

				JSONArray ja = new JSONArray(body4);

				int size = ja.length();
				int asize = jsonArray.length();
				for (int i = 0; i < size; i++)
				{
					JSONObject jp = ja.getJSONObject(i);
					// System.out.println(jp.getString("id")+":"+jp.getString("p"));
					for (int i2 = 0; i2 < asize; i2++)
					{
						JSONObject obj = jsonArray.getJSONObject(i2);

						// System.out.println(obj.get("Goods_ID")+":"+jp.getString("id").substring(2));
						if (obj.get("Goods_ID").equals(jp.getString("id").substring(2)))
						{
							// System.out.println("aaaaa");
							obj.put("Goods_price", jp.get("p"));
						}
					}

				}

				// for(int i=0;i<asize;i++)
				// {
				// JSONObject j= jsonArray.getJSONObject(i);
				// //System.out.println("haha: id:"+obj.getString("Goods_ID")+"
				// p:"+obj.getString("Goods_price"));
				// //System.out.println(obj.toString().length());
				// String Crawling_time=j.getString("Crawling_time");
				// System.out.println("1.Crawling_time:"+Crawling_time);
				//
				// String Goods_ID=j.getString("Goods_ID");
				// System.out.println("2.Goods_ID:"+Goods_ID);
				//
				// String Goods_Name=j.getString("Goods_Name");
				// System.out.println("3.Goods_Name:"+Goods_Name);
				//
				//
				// String Goods_params=j.getString("Goods_params");
				// System.out.println("4.Goods_params:"+Goods_params);
				//
				// Boolean self_support=j.getBoolean("self_support");
				// System.out.println("5.self_support:"+ self_support);
				//
				// Boolean Return_Support=j.getBoolean("Return_Support");
				// System.out.println("6.Return_Support:"+ Return_Support);
				//
				// String Goods_brand=j.getString("Goods_brand");
				// System.out.println("7.Goods_brand:"+ Goods_brand);
				//
				// String classification=j.getString("classification");
				// System.out.println("8.classification:"+classification);
				//
				// String Goods_img=j.getString("Goods_img");
				// System.out.println("9.Goods_img:"+Goods_img);
				//
				// String comment_count=j.getString("comment_count");
				// System.out.println("10.comment_count:"+comment_count);
				//
				// String comment_goodrate=j.getString("comment_goodrate");
				// System.out.println("11.comment_goodrate:"+ comment_goodrate);
				//
				// String Goods_feature=j.getString("Goods_feature");
				// System.out.println("12.Goods_feature:"+Goods_feature);
				//
				// String Goods_price=j.getString("Goods_price");
				// System.out.println("13.Goods_price:"+Goods_price);
				//
				// String Shop_Name=j.getString("Shop_Name");
				// System.out.println("14.Shop_Name:"+Shop_Name);
				//
				// String Shop_ID=j.getString("Shop_ID");
				// System.out.println("15.Shop_ID:"+Shop_ID);
				// }
				//

				System.out.println("jsonArray" + jsonArray.toString().length());

				int jlen = jsonArray.length();
				for (int i = 0; i < jlen; i++)
				{
					// 要这样写
					String goods_info = new String(jsonArray.getJSONObject(i).toString());
					new Thread(new SendGoodInfoToServer(goods_info, ip, port2)).start();

				}

				for (int i = 0; i < jlen; i++)
				{
					jsonArray.remove(1);

    			}

			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}

}
