/**
 * 
 */
package it.neosix.radici.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import it.neosix.radici.app.Main;

/**
 * @author NeosiX
 *
 */
public class DBConnect {

		private static String jdbcURL;
		//= "jdbc:sqlite:test1.db";
		
		private static Connection myConn = null;

		public static void setDB(String db){
			jdbcURL = "jdbc:sqlite:" + db;
			Main.logga("load DB: " + db);
		}
		
		public static Connection getConnection() {
			
			Main.logga("chiedo una connessione");
			
			if (myConn != null)
				return myConn;

			Connection c;
			try {
				c = DriverManager.getConnection(jdbcURL);
				Main.logga("connessi con " + c);
				//c = new PersistentConnection(c) ; // inibisce il metodo close()
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Impossibile connettersi al database", e);
			}

			//System.out.println("connessione: " + c);
			
			myConn = c;
			return c;

		}		

		public static void closeConnection() {

			if(myConn != null) {
			     try { 
			    	 myConn.close(); 
			    	 myConn = null;
			     } catch (Exception e) {} 
			}
		}

		
		//test
	    public static void main(String[] args) throws InterruptedException, SQLException {
	    	Connection conn = DBConnect.getConnection();
	    	conn.close();
	    	
	    	TimeUnit.SECONDS.sleep(5);
	    	conn = DBConnect.getConnection();
    		
	    }
	    	    
}
