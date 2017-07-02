package JD.server.getinfo;

import java.io.IOException;

public class Main2
{
	public static void main(String[] args) throws IOException
	{
		SeverGetGoodInfoFromClient s=new SeverGetGoodInfoFromClient(9993);
		s.start();
	}

}
