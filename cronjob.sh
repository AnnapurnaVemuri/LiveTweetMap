<html><head></head><body><pre style="word-wrap: break-word; white-space: pre-wrap;">#!/bin/bash
java -cp  /home/ec2-user/madhuri_sri/TweetSave.jar com.cloud.proj.tweets.TweetRetriever | tee /var/www/tweetSave.hourly.log &amp;
sleep 300
echo 'closing Tweet'
ps aux | grep TweetSave.jar

pkill -f 'TweetSave'
echo 'closed Tweet Saving Job'
ps aux | grep TweetSave.jar
</pre></body></html>
