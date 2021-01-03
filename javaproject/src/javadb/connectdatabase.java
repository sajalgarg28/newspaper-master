package javadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectdatabase {

	public static Connection doconnection()
	{
		Connection connect = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/newspaper","root","");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return connect;
	}
//	public static void main(String[] args) {
//		Connection con=doconnection();
//		
//		if(con == null)
//		{
//			System.out.println("error");
//		}
//		else
//		{
//			System.out.println("connected");
//		}
//	}
}
