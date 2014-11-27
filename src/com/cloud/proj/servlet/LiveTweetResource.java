package com.cloud.proj.servlet;

import com.cloud.proj.commons.Tweets;
import com.cloud.proj.db.utils.DataBaseHelper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.List;
 
@Path("/live-tweets")
@Produces( MediaType.APPLICATION_JSON )
public class LiveTweetResource {
 
    private final DataBaseHelper db;
    
    public LiveTweetResource()   {  
        this.db= new DataBaseHelper();
    }
    
    @GET
    @Path("/getGroupedTweets")
    public  List<Tweets> getGroupedTweets(@QueryParam("round") Integer round,@QueryParam("count") Integer count) throws Exception  {
    	try{
    		  int roundInt=round.intValue();
    		  int countInt=count.intValue();
    		 return  db.getClusteredFeeds(roundInt,countInt);
    	} catch(Exception e){
    		return  db.getClusteredFeeds(4, 100);
    	}  
    }
    
    @GET
    @Path("/getTweetsByKeyword")
    public  List<Tweets> getGroupedTweets(@QueryParam("hashTag") String hashTag, @QueryParam("round") Integer round,@QueryParam("count") Integer count) throws Exception  {
    	try {
    		 return  db.getClusteredFeedsByTag(hashTag, round,count);
    	} catch(Exception e){
    		return  db.getClusteredFeedsByTag("Love",0, 100);
    	}     
    }
}