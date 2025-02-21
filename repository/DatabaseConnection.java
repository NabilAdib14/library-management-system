package repository;
import java.sql.*;

public class DatabaseConnection{
	public Connection con;
	public Statement st;
	public ResultSet result;
	
	public DatabaseConnection(){}

	public void openConnection(){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library management system", "root", "");
			st = con.createStatement();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void closeConnection(){
		try{
			if(con!=null){con.close();}
			if(st!=null){st.close();}
			if(result!=null){result.close();}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}	
}
