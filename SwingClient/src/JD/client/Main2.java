package JD.client;

public class Main2
{
	public static void main(String[] args)
	{
		ClientGetGoodsInfo c=new ClientGetGoodsInfo("172.18.112.148", 9996, 9993,15);
		c.start();
	}

}
