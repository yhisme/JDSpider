package JD.server.getid;

import java.io.IOException;

public class Main2
{
	public static void main(String[] args) throws IOException
	{
		//2.Server
		ServerGetGoodsIDFromClient sgid = new ServerGetGoodsIDFromClient(9997);

		sgid.start();
	
	}

}
