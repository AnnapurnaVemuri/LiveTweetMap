m Members:
Srilakshmi Chintala(sc3772@columbia.edu)
Madhuri Palle(msp2167@columbia.edu)
 
Project Description:
Real time tweet map, which takes live stream twitter feed and plots it on a map. Our map features include:
-Plotting all tweets on map 
-The tweets are grouped based on latitude,longitude. As we zoom out a marker with the count of the cluster of tweets is displayed, as we zoom in the clusters are broken down.
-Plotting the tweets based on pre-defined key words
-Renders a heat map of all tweets
 
Technologies used:
-Frame work used was REST services on tomcat (used example code from tutorial)
-Back end in java
-Front end in html and java script and css
-Database used was Amazon RDS MySql
-Used jquery  to make ajax calls
-Google maps api version 3  for rendering the map
-Twitter 4j api’s for getting the stream feed
 
Steps to run the app:
-Install and copy the project into eclipse workspace and open using project explorer.
-Right click  and run on local server(Tomcat).
-Open a browser and paste following url:
- http://localhost:8080/HelloWorldServlet/html/map.html

Real Time WebApp URL:
http://cloudtweetmap.elasticbeanstalk.com//html/map.html
