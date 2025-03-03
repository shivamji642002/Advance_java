package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class insertDemo {

	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=true","root","ji642002");
        PreparedStatement ps = con.prepareStatement("insert into register values('1','shivam','shivamji642002@gmail.com')");
        
        int i =ps.executeUpdate();
        
        if(i>0) {
        	System.out.println("Succefully");
        }
        else {
        	System.out.println("Failed");
        }
        System.out.println(i);
      
	}

}
