package JD.client;

import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		//3.client port1连接分发任务服务器，port2连接收集信息服务器 第4个参数指定爬取线程数量
		ClientGetGoodsID c=new ClientGetGoodsID("172.18.112.148",9996,9997,15);
		c.start();
	}

}
