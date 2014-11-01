package com.srccodes.example;

import com.example.helloworld.core.SampleConnect;
import com.example.helloworld.core.Saying;
import com.example.helloworld.core.TweetGet;
import com.example.helloworld.core.Tweets;
import com.example.helloworld.core.TwitCluster;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
 
@Path("/hello-world")
@Produces( MediaType.APPLICATION_JSON )
public class HelloWorldResource {
 
    private final SampleConnect db;
    private TweetGet insertTweetsToDB;
    public HelloWorldResource()   {
    
        this.db=new  SampleConnect();
    }


    @GET
    @Path("/getAllTweets")
    public  List<Tweets> getAllTweets(@QueryParam("count") Integer count)  {
    	try{
    		  int value=count.intValue();
    		 return  db.getAllFeeds(value);
    	}catch(Exception e){
    		 return  db.getAllFeeds(100);
    	}
       
    }

    
    @GET
    
    @Path("/getGroupedTweets")
    public  List<TwitCluster> getGroupedTweets(@QueryParam("round") Integer round,@QueryParam("count") Integer count) throws Exception  {
    	try{
    		  int roundInt=round.intValue();
    		  int countInt=count.intValue();
    		 return  db.getClusteredFeeds(roundInt,countInt);
    	}catch(Exception e){
    		return  db.getClusteredFeeds(4, 100);
    	}
 
    
     
    }
    
    
    @GET
    @Path("/getTweetsByKeyword")
    public  List<TwitCluster> getGroupedTweets(@QueryParam("hashTag") String hashTag, @QueryParam("round") Integer round,@QueryParam("count") Integer count) throws Exception  {
    	try{
    		 return  db.getClusteredFeedsByTag(hashTag, round,count);
    	}catch(Exception e){
    		return  db.getClusteredFeedsByTag("Love",0, 100);
    	}
 
    
     
    }
    
    
    
    @GET
    @Path("/insertTweetsToDB")
    public  boolean insertTweetsToDB(@QueryParam("count") Integer count)  {
    	try{
    		  int value=count.intValue();
    		  insertTweetsToDB=new TweetGet();
    		  insertTweetsToDB.insertTweetsToDB(count);
    		 return  true;
    	}catch(Exception e){
    		  insertTweetsToDB=new TweetGet();
    		  insertTweetsToDB.insertTweetsToDB(count);
    		 return true;
    	}
       
    }

    @GET
    @Path("/stopTweetStream")
    public  boolean stopTweetStream()  {
    	try{
    	 
    	 if(insertTweetsToDB!=null)
    		  insertTweetsToDB.stopTweetStream();
    		 return  true;
    	}catch(Exception e){
    		if(insertTweetsToDB!=null)
    				insertTweetsToDB.stopTweetStream();
    		 return true;
    	}
       
    }
}