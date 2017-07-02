package JD.server.getinfo;

import java.io.IOException;
import java.sql.SQLException;

public class Main
{
	public static void main(String[] args) throws IOException, SQLException
	{
		ServerAllocatingGetGoodsInfoWork s=new ServerAllocatingGetGoodsInfoWork(9996);
        s.start();
	}

}
