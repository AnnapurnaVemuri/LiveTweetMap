package com.example.helloworld.core;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SampleConnect {
	
	String dbName = System.getProperty("CloudDB"); 
	static String userName = System.getProperty("madhuripalle"); 
	static String password = System.getProperty("madhuripalle"); 
	String hostname = System.getProperty("clouddb.c64omft7fp1u.us-west-2.rds.amazonaws.com");
	String port = System.getProperty("3306");
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static String DB_URL = "jdbc:mysql://clouddb.c64omft7fp1u.us-west-2.rds.amazonaws.com:3306/CloudDB?user=madhuripalle&password=madhuripalle";
	private static Statement stmt=null;
	Connection conn = null;
	public SampleConnect()  {
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			 try {
				conn = DriverManager.getConnection(DB_URL,userName,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Connection createConn(){
		 try {
				Class.forName("com.mysql.jdbc.Driver");
				 try {
					return DriverManager.getConnection(DB_URL,userName,password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	public void insertToDb(Tweets t) throws Exception
	{
		if(conn==null)
			conn=createConn();
		stmt = conn.createStatement();
	      String sql = "INSERT INTO TwitData(userid,username,status,latitude,longitude,category) " +
	              "VALUES ("+t.userid+",'"+ t.username +"','"+t.status+"',"+t.latitude+","+t.longitude+",'"+t.category+"')";
	          stmt.executeUpdate(sql);
	          stmt.close();
	}
	public List<Tweets> getAllFeeds(int count) {
		List<Tweets> tc=new ArrayList<Tweets>();
		try {
			if(conn==null)
				conn=createConn();
			stmt=conn.createStatement();

		String sql = "SELECT * FROM TwitData limit " + count;
		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){	 
			Tweets t=new Tweets( rs.getLong("userid"),  rs.getString("username"), rs.getString("status"),     rs.getDouble("latitude"),rs.getDouble("longitude"),rs.getString("category"));
			tc.add(t);
	      }
		return tc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tc;
		}
	
	}
		
	public List<TwitCluster> getClusteredFeeds(int zoom, int count) {
		List<TwitCluster> tc=new ArrayList<TwitCluster>();
		try {
			stmt=conn.createStatement();
		String sql = "SELECT round(latitude,"+zoom+") as lat, round(longitude,"+zoom+") as longi, count(*) as count FROM TwitData group by round(latitude,"+zoom+"),round(longitude,"+zoom+") ";
		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){
			TwitCluster t=new TwitCluster(rs.getDouble("lat"),rs.getDouble("longi"),rs.getInt("count"));
			tc.add(t);

	      }
		stmt=conn.createStatement();
		 return tc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return tc;
		}
	
	}
	public List<TwitCluster> getClusteredFeedsByTag(String hashTag, int zoom, int count) {
		List<TwitCluster> tc=new ArrayList<TwitCluster>();
		try {
			stmt=conn.createStatement();
		String sql = "SELECT round(latitude,"+zoom+") as lat, round(longitude,"+zoom+") as longi, count(*) as count FROM TwitData where category='"+ hashTag +"'  group by round(latitude,"+zoom+"),round(longitude,"+zoom+") ";
		ResultSet rs = stmt.executeQuery(sql);
	
		while(rs.next()){
			TwitCluster t=new TwitCluster(rs.getDouble("lat"),rs.getDouble("longi"),rs.getInt("count"));
			tc.add(t);

	      }
		stmt=conn.createStatement();
		 return tc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return tc;
		}
	
	}
}
