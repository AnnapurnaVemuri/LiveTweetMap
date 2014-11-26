package com.cloud.proj.tweets;
public class TweetRetriever {
	private static int batchSize;

	public static void main(String[] args) {
		batchSize = Integer.parseInt(args[0]);
	}

}
