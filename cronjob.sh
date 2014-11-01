#!/bin/bash
java -cp  /home/ec2-user/madhuri_sri/TweetSave.jar com.example.helloworld.core.MainClass | tee /var/www/tweetSave.hourly.log &
sleep 300
echo 'closing Tweet'
ps aux | grep TweetSave.jar

pkill -f 'TweetSave'
echo 'closed Tweet Saving Job'
ps aux | grep TweetSave.jar
