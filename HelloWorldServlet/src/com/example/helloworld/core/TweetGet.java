package com.example.helloworld.core;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.FilterQuery;

/**
 * <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */

public  class TweetGet {
    /**
     * Main entry of this application.
     *
     * @param args
     * @throws Exception 
     */
	TwitterStream twitterStream =null;
	StatusListener listener=null;
	static String keywords[] = {"beiber","love","ebola","modi","1989","girl","suarez","apple","lol","god","boy","baby"};
	
	public void stopTweetStream(){
		if( listener!=null)
			twitterStream.removeListener(listener);
 		twitterStream.shutdown();
		twitterStream=null;
		
	}
	
    public void insertTweetsToDB(int count)  {
        final SampleConnect db;
		try {
    	 ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true)
           .setOAuthConsumerKey("")
           .setOAuthConsumerSecret("")
           .setOAuthAccessToken("")
           .setOAuthAccessTokenSecret("");
			db = new SampleConnect();
        if(twitterStream==null){
        	twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        }
             	StatusListener listener = new StatusListener() {
        	int   count=0;	
            @Override
            public void onStatus(Status status) {
            if(count>100000) return;
            for(String filter :keywords){
            	  try{
            	if((status.getText().toLowerCase()).contains(filter)){
            		 System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText() + "---" + status.getHashtagEntities().toString());
                     Tweets t=new Tweets(status.getUser().getId(),status.getUser().getScreenName(),status.getText(),status.getGeoLocation().getLatitude(),status.getGeoLocation().getLongitude(),
                    		 filter);
                   
                      System.out.println(status.getGeoLocation().getLatitude() + ": " + status.getGeoLocation().getLongitude());
                    	
                      db.insertToDb(t);
                      count=count+1;
            		}
            	  } catch (Exception e1) {
                 	 // TODO Auto-generated catch block
                 	 e1.printStackTrace();
                  }
               
            }
            
              
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        
        FilterQuery fq = new FilterQuery();
        fq.track(keywords);
        
        twitterStream.addListener(listener);
        twitterStream.filter(fq);
        twitterStream.sample();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
}