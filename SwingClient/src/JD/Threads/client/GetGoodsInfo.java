package JD.Threads.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class GetGoodsInfo extends Thread
{
	private final CloseableHttpClient httpClient;
	private final HttpContext context;
	private final HttpGet httpget;
	private final String id;

	public GetGoodsInfo(CloseableHttpClient httpClient, HttpGet httpget,String id)
	{
		this.httpClient = httpClient;
		this.context = HttpClientContext.create();
		this.httpget = httpget;
		this.id=id;
	}

	@Override
	public void run()
	{
		try
		{
			 JSONObject j= new JSONObject();

			//0 获取body
			int i = 0;
			String body = "";
			while (true)
			{

				i++;
				// HttpClient httpClient=HttpClients.createDefault();
				HttpClient httpClient2 = HttpClients.createDefault();
				HttpGet get = new HttpGet("http://item.jd.com/"+id+".html");// 10691283909.html");
				get.setHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
				get.setHeader(":authority", "item.jd.com");
				get.setHeader(":method", "GET");
				get.setHeader(":path", "/10071043028.html");
				get.setHeader(":scheme", "https");
				get.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
				get.setHeader("accept-encoding", "gzip, deflate, sdch");
				get.setHeader("accept-language", "zh-CN,zh;q=0.8");
				get.setHeader("cache-control", "max-age=0");
				get.setHeader("if-modified-since", "Thu, 11 May 2017 06:20:55 GMT");
				get.setHeader("cookie",
						"n=0; __utmz=122270672.1493804802.1.1.utmcsr=jd.com|utmccn=(referral)|utmcmd=referral|utmcct=/brand.aspx; ipLoc-djd=1-72-2799-0; mt_xid=V2_52007VwMQUl5dUVIbTilcVzILGlJeDE5ZFxwfQABnBkdOVQpaWgNLS1hVYgdBU10IAFovShhcA3sCFk5cUENZHkIZWA5iCiJQbVhiWh5PHVsMZwsQYl1eVl4%3D; ipLocation=%u5317%u4EAC; areaId=1; _jrda=1; wlfstk_smdl=dqen7rww09dufrizjc17ta9fqp0ebgpp; unpl=V2_ZzNtbUNQExF2WBJXfxteA2IAEQ5KU0FHcAAWBCwcWgUzUxZfclRCFXMUR1BnGVwUZwYZWEZcRhZFCEdkex5fDGQzEFtGVEMcdQF2ZHgZbA1XAxBdQVJAHXEPdmR9HWw1s5a5hcnAEVQ30fbxrbPyNWAGFVtLXkYRRQl2VUtSMgQqAxBdQVJAHXEPdlVLGg%3d%3d; CCC_SE=ADC_owMeSiInJACA2ioAPFmVTK9Z9t9YR6LEpdEZxAshhLji%2fZPJ8rWFVfWKyoK8kRnAxwDtHjDERNQTODIXZ37iEmxPR24uL5U5OAIa5ox4YXiVoIw5Dbt79qlvbrYh69w3EWOKIxtIuDSNEygujH6ok1NfTLS6OpsZjgdoHiSueGFZcWIR9bOeqIo1DgN0i5pfwf7LyNXOb0nRvOPvIE%2bH32hkhkzDiy4wjXeIbK0Sm24gxiWLtIDebr%2fNEiYJdVt%2b1X91MWk%2bSonNwteXKXXAxeqy11IHS37OLphTdvzaV2yJWHx5B8RFL7NfK%2bVsbDlYzcW4KWRZAaBJLTnLCBmdz3j3qIA7L5pZCtR8LnhNXDIueUjDKZvL2Twt4zgUQntH9ELsdvl3IzdS6%2f%2fSaY9V%2bo3H%2b3Th%2fwscPXPAbxRowg1mOpnJGJgin5acWooF6nOMW1g3pXWzKD4Oo8srLdH4j%2f7K4oTQZVOd8%2ftAR%2bfTyOT2hHXTlqcjP4j4EbxI3N67MVj%2fa6pwF9Pgof%2flG4LimKiyM5HZD7LEqVaqbyGh9Wsy2OjH0Q48IEsIrTaIMqMZftSOFk0hxBFATcP9NKZ2sWfY8OJ7%2bBwoJjAP0vGkvmYRhnun3l%2b7c5SuDjWGw%2fRu; __jda=122270672.131242956.1473433603.1494483461.1494484911.35; __jdb=122270672.1.131242956|35.1494484911; __jdc=122270672; __jdv=122270672|c.duomai.com|t_16282_37521818|uniongou|06a52ae25337422b953c49aaf471ea53|1494484910532; __jdu=131242956; 3AB9D23F7A4B3C9B=QHYZT35YOWZBXZ7W4K5I6WFDMFCTYF3M2T4FZNXKHHIBRJXRAFDI7RS4MPWNPL");
				CloseableHttpResponse response = (CloseableHttpResponse) httpClient2.execute(get);
				HttpEntity entity = response.getEntity();
				body = EntityUtils.toString(entity, "UTF-8");
				// System.out.println(body);
				//System.out.println("请求了" + i + ":" + body.length());
				if (i == 15)
					break;
				if (body.length() > 1000)
					break;

				EntityUtils.consume(entity);
				get.releaseConnection();
				response.close();

			}
			
		
			//1.爬取时间
			Date date =new Date();
			String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
			//System.out.println("1.爬取时间"+time);
			j.put("Crawling_time", time);
			
			//2.商品ID	 
			 j.put("Goods_ID", id);
			 //System.out.println("2.商品ID:"+id);
			 
			 
			// 3商品名字
			String regex = "<title>.{0,300}</title>";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(body);
			if (m.find())
			{

				String s = m.group();
				s = s.substring(7, s.length() - 24);
				//System.out.println(s);

				j.put("Goods_Name", s);
				//System.out.println("3.商品名称:" + s);
			} else
			{
				j.put("Goods_Name", "null");
			}
			
			//4.商品参数
			String regex_params = "<li title='.+'>.+</li>";
			Pattern p_params = Pattern.compile(regex_params);
			Matcher m_params = p_params.matcher(body);
			StringBuffer params = new StringBuffer("");
			while (m_params.find()) {
				params.append(m_params.group());
			}
			if (params.toString().length() >= 3) {
				//System.out.println("商品参数:" + params.toString());
				j.put("Goods_params", params.toString());
			} else {
				//System.out.println("商品参数:没有商品参数");
				j.put("Goods_params","null");
			}
			
			//5.是否自营
			String regex2 = "<span>JD</span>自营";
			Pattern p2 = Pattern.compile(regex2);
			Matcher m2 = p2.matcher(body);
			if(m2.find())
			{
			
				
				//System.out.println("5.是自营");
				j.put("self_support", true);

			}
			else
			{
				//System.out.println("6.不是自营");
				j.put("self_support", false);
			}
			
			
			//6.是否支持7天
			if(body.indexOf("不支持7天无理由退货")!=-1)
			{
				//System.out.println("6.不支持。。");
				j.put("Return_Support", false);
			}
			else
			{
				
				//System.out.println("6.支持。。");
				j.put("Return_Support", true);
				
			}
			
			//7.品牌
			String regex3 = "<li title=.{0,40}>";
			Pattern p3 = Pattern.compile(regex3);
			Matcher m3 = p3.matcher(body);
			if(m3.find())
			{
			
				String s3 = m3.group();
				String brand=s3.substring(11, s3.length()-2);
				//System.out.println("7.品牌:"+brand);
				j.put("Goods_brand", brand);

			}else
			{
				j.put("Goods_brand", "null");
			}
			
			
			//8.商品分类
			StringBuffer productclassification = new StringBuffer("");
			int productclassification_start = 0;
			int productclassification_end = 0;
			while (true) {

				productclassification_start = body.indexOf("mbNav", productclassification_end);
				productclassification_end = body.indexOf("</a>", productclassification_start);

				if (productclassification_start == -1) {
					break;
				}
				productclassification
						.append(body.substring(productclassification_start + 9, productclassification_end) + ">");

			}
			if (productclassification.toString().length() >= 3) {
				//System.out.println("商品分类:"
						//+ productclassification.toString().substring(0, productclassification.toString().length() - 1));
			            j.put("classification",productclassification.toString().substring(0, productclassification.toString().length() - 1));
			
			} else {
				//System.out.println("商品分类:没有商品分类");
				j.put("classification", "null");
			}
		   
			//9 img 
			String imgurl="Goods_img_url:";
			String regex9 = "<li.*>.*\\.jpg";
			Pattern p9 = Pattern.compile(regex9);
			Matcher m9 = p9.matcher(body);
			while(m9.find())
			{
			
				String s9 = m9.group();
				String img=s9.substring(11, s9.length()-2);
				//System.out.println("imgs:"+img);
				
				int startn=img.indexOf("src='");
				int endn=img.indexOf("'",startn+5);
				if(startn!=-1&&endn!=-1)
				{
				
				//System.out.println(img.substring(startn+7, endn));
				imgurl=imgurl+img.substring(startn+7, endn)+" ";
				}
				else
				{
					int startn2=img.indexOf("src=\"");
					int endn2=img.indexOf("\"",startn+7);
					
					if(startn2==-1||endn2==-1)
					{
					
					//System.out.println(img.substring(startn+7, endn));
					imgurl=imgurl+img.substring(startn+7, endn)+" ";
					}
					
				
				}
		

			}
			j.put("Goods_img", imgurl);
			
			
             //10 评论
    		
    		//HttpClient httpClient3=HttpClients.createDefault();
    		HttpGet get3 =new HttpGet("https://club.jd.com/comment/productPageComments.action?callback=fetchJSON_comment98vv311&productId="+id+"&score=0&sortType=5&page=0&pageSize=1&isShadowSku=0");
    		CloseableHttpResponse response3 = (CloseableHttpResponse) httpClient.execute(get3);
    		HttpEntity entity2 = response3.getEntity();
    		String body2 = EntityUtils.toString(entity2, "UTF-8");
    		
    		//10评论总数
    		int yh=body2.indexOf("commentCountStr\":\"");
    		int yh2=body2.indexOf("\"",yh+19);
    	 //System.out.println("commentCount,.....................:"+yh+","+yh2);//body2.substring(yh+20,yh2));
    	   //System.out.println(body2.substring(yh+18,yh2));    
    	    j.put("comment_count",body2.substring(yh+18,yh2) );
    	    
    	    //11.好评率
			int yh3 = body2.indexOf("goodRate\"");
			int yh4 = body2.indexOf("\"", yh3 + 9);
		//	System.out.println("goodRate:................................" + body2.substring(yh3, yh4));
            j.put("comment_goodrate", body2.substring(yh3+10, yh4-1));
    	    
            //特点
    			String regex6 = ",\"name\":.{0,20},\"s";
    			Pattern p6 = Pattern.compile(regex6);
    			Matcher m6= p6.matcher(body2);
    			
    			if(m6.find()==false)
    			{	
    				j.put("Goods_feature","null");
    			}
    			String Goods_feature="";    			
    			while (m6.find()) {
    				
    				
    			     String str=m6.group();
  
    				
    					String feature=str.substring(9,str.length()-4);
    					//System.out.println("12.特点"+feature);
    					if(str.indexOf("pin")==-1)
    					Goods_feature=Goods_feature+feature+" ";
                        
    				}
    			j.put("Goods_feature", Goods_feature);
    			

    				
    				
    			
    			EntityUtils.consume(entity2);
				get3.releaseConnection();
				response3.close();
    			
    			
    			//13,14,  .店铺 name
				String regex_shopname = "title=\".{0,300}\" clstag=\"shangpin\\|keycount\\|product\\|dianpuname";
				Pattern p_shopname = Pattern.compile(regex_shopname);
				Matcher m_shopname = p_shopname.matcher(body);
				if (m_shopname.find()) {
					//System.out.println("店铺名字:" + m_shopname.group().substring(7, m_shopname.group().length() - 46));
					j.put("Shop_Name",  m_shopname.group().substring(7, m_shopname.group().length() - 46));
				}else{
					//System.out.println("店铺名字:"+"没有店铺");
					j.put("Shop_Name","null");
				}
    			//店铺id
    			int s3=body.indexOf("data-vid=\"");
    			int e3=body.indexOf("\"",s3+11);
    			if(s3!=-1||e3!=-1)
    			{
    			  //System.out.println("14.店铺id:"+body.substring(s3+10,e3));
    			  j.put("Shop_ID",body.substring(s3+10,e3));
    			}
    			else
    			{
    				j.put("1Shop_ID","null");
    			}
    		
    			//处理无效数据
			if (j.getString("Shop_ID").indexOf("html") != -1)
			{
				j.remove("Shop_ID");
				j.put("Shop_ID", "null");
			}
    		
			if(j.getString("Shop_ID").indexOf("ISBN") != -1)
			{
				j.remove("Goods_brand");
				j.put("Goods_brand", "null");
			}
    			
    		 JD.client.ClientGetGoodsInfo.jsonArray.put(j);
    			
    			
    		System.out.println("获取到商品 ,ID为:"+id);
    		JD.windows.Client.client_info.append("获取到商品 ,ID为:"+id+"\n");
    		JD.windows.Client.client_info.setCaretPosition(JD.windows.Client.client_info.getText().length());
    		JD.windows.Client.Infocount.incrementAndGet();
    		if(JD.windows.Client.client_info.getText().length()>100000)
    		{
    			JD.windows.Client.client_info.setText(JD.windows.Client.client_info.getText().substring(50000, 100000));
    		}

			
			
			
		} catch (Exception e)
		{
			
		}

	
	}
}
