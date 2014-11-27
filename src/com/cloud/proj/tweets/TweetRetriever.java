package com.cloud.proj.tweets;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TweetRetriever {
	private static int batchSize;
	private static int purgeBatchSize;
	private static TweetGet getTweets;

	public static void main(String[] args) throws UnknownHostException {
		String hostname = InetAddress.getLocalHost().getHostAddress();
		System.out.println(hostname);
		if (args.length > 0) {
			batchSize = Integer.parseInt(args[0]);	
		}
		if (args.length > 1) {
			purgeBatchSize = Integer.parseInt(args[1]);	
		}
		launchTweetGetter();
		launchTweetPurger();
	}

	private static void launchTweetPurger() {
	}

	private static void launchTweetGetter() {
		getTweets = new TweetGet(batchSize);
	}
}
