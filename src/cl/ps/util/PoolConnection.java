package cl.ps.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;



public class PoolConnection {

	private static Stack<Connection> pool;
	
	private final static String username = App.getInstance().getProperty("database_username");
	private final static String password = App.getInstance().getProperty("database_password");
	private final static String url = App.getInstance().getProperty("database_url")+App.getInstance().getProperty("database_name");
	private final static String connectionURL = url+"?autoReconnect=true&user="+username+"&password="+password;	
	private final static Integer max_connections_idle = Integer.parseInt(App.getInstance().getProperty("max_pool_connection_idle"));
	private final static Integer timeout_reconnect = Integer.parseInt(App.getInstance().getProperty("timeout_reconnect"));
	
	
	public PoolConnection(){
		pool = new Stack<Connection>();
	}
	
	public synchronized Connection getConnection(){
		if(!pool.empty()){
			return (Connection) pool.pop();
		}else{
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				return DriverManager.getConnection(connectionURL);
			// Si falla espera 30 segundos y reintenta crear una conexion
			} catch (InstantiationException e1) {
				try {Thread.sleep(timeout_reconnect);} catch (InterruptedException e) {}
				getConnection();				
			} catch (IllegalAccessException e1) {
				try {Thread.sleep(timeout_reconnect);} catch (InterruptedException e) {}
				getConnection();
			} catch (ClassNotFoundException e1) {
				try {Thread.sleep(timeout_reconnect);} catch (InterruptedException e) {}
				getConnection();
			} catch (SQLException e1) {
				try {Thread.sleep(timeout_reconnect);} catch (InterruptedException e) {}
				getConnection();
			}
		}		
		return null;			
	}
	
	public synchronized void putConnection(Connection con){
		if(pool.size() == max_connections_idle)
			try { con.close();	} catch (SQLException e) {	e.printStackTrace(); }
		else
			pool.push(con);
	}
	
}
