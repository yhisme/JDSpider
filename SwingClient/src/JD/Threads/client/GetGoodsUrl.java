package JD.Threads.client;

import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class GetGoodsUrl extends Thread
{
	private final CloseableHttpClient httpClient;
	private final HttpContext context;
	private final HttpGet httpget;

	public GetGoodsUrl(CloseableHttpClient httpClient, HttpGet httpget)
	{
		this.httpClient = httpClient;
		this.context = HttpClientContext.create();
		this.httpget = httpget;
	}

	@Override
	public void run()
	{
    
	
			try
			{

				CloseableHttpResponse response = httpClient.execute(httpget, context);
				HttpEntity entity = response.getEntity();
				try
				{

					if (entity != null)
					{
						String body = EntityUtils.toString(entity, "UTF-8");

						// 正则
						String regex2 = "data-sku=\"[0-9]+\" ven";
						Pattern p2 = Pattern.compile(regex2);
						Matcher m2 = p2.matcher(body);

						// 判断是否开始入队
						int i = 0;
						while (m2.find())
						{
							i++;

							String s = m2.group();

							String url = s.substring(10, s.length() - 5);
							// System.out.println(url);
							JD.client.ClientGetGoodsID.GoodsUrl.append(url + " ");
						}
						
						System.out.println("URL:"+httpget.getURI()+"共获取"+i+"个商品ID");
					    JD.windows.Client.client_info.insert("URL:"+httpget.getURI()+"共获取"+i+"个商品ID"+JD.windows.Client.client_info.getText().length()+"\n",JD.windows.Client.client_info.getText().length());
					    JD.windows.Client.client_info.setCaretPosition(JD.windows.Client.client_info.getText().length());
					    JD.windows.Client.IDcount.addAndGet(i);
					    if(JD.windows.Client.client_info.getText().length()>100000)
					    {
					    	JD.windows.Client.client_info.setText(JD.windows.Client.client_info.getText().substring(50000, 100000));
					    }
					}

					
				} finally
				{

					EntityUtils.consume(entity);
					response.close();

				}
			} catch (Exception e)
			{
				try{
					File f =new File("c://yh//yh.txt");
					FileOutputStream out= new FileOutputStream(f);
					String ex=(e.getMessage()+"url:"+httpget.getURI());

					out.write(ex.getBytes());
					out.close();
					}catch(Exception e1)		
					{
						e1.printStackTrace();
					}
				
				

			} 
		}
	}


