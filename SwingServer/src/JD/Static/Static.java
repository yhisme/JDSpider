package JD.Static;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Static
{
	
	//前面两个过滤用
	public static BlockingQueue<String> filter1 = new  ArrayBlockingQueue<String>(10000);
	public static HashSet<String> filter2 =new HashSet<>();
	//这个才是真正的url
	public static HashSet<String> GoodsUrl =new HashSet<>();
	public static ConcurrentLinkedQueue<String> Pageurl= new ConcurrentLinkedQueue<>();
	
	
	public static BlockingQueue<String> GoodID =new ArrayBlockingQueue<String>(20000);
	
	public static Connection conn=getConn();
    public static Connection getConn()
	{

    	
		 String driver = "com.mysql.jdbc.Driver";
		    //显示中文要字符集
		    String url = "jdbc:mysql://localhost:3306/jd?useUnicode=true&characterEncoding=utf8";
		    String username = windows.Server.user;
		    String password = windows.Server.password;
		  
		    Connection conn = null;
		    try {
		        Class.forName(driver); //classLoader,加载对应驱动
		        conn = (Connection) DriverManager.getConnection(url, username, password);
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        

		    }
		    return conn;
	}
    
    
    //
    public static ConcurrentLinkedQueue<String> GoodsID= new ConcurrentLinkedQueue<>();
    

}
