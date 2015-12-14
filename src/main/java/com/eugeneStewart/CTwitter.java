package com.eugeneStewart;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Eugene on 11/30/2015.
 */

public class CTwitter {


    public static ArrayList<String> Twittersearch(Twitter twitter, String artist) throws TwitterException, IOException{

// looks for entered text on twitter returns  array list
        Query query = new Query(artist);
        QueryResult result = twitter.search(query);
        ArrayList<String> listdata = new ArrayList<String>();

        for (Status istatus : result.getTweets()) {

            listdata.add(istatus.getUser().getScreenName() + " - " + istatus.getText());

        }
        return listdata;


    }
// log on
    public static Twitter Twitterlogon() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("tx8DJSpiBNut6KAm8p9mgfRE")
                .setOAuthConsumerSecret("LTkZANS7XzdE2cEC0kX3oTCfkK6bEfBItgnq2f6mStIy2Bn3x")
                .setOAuthAccessToken("4476950412-nDy0AE69CqcWsMop7bD3v8qpZcddG1vJn0YP8a")
                .setOAuthAccessTokenSecret("yRHRMPTtjcxRAN5Bo6sV45GI4b75PSS0VJhAq6lY6muy");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        return twitter;


    }

}