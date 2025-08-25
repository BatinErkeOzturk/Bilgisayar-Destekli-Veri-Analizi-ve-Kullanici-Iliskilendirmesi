
package com.mycompany.analysisapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("username")
    private String username;

    @JsonProperty("name")
    private String name;

    @JsonProperty("followers_count")
    private int followers_count;

    @JsonProperty("following_count")
    private int following_count;

    @JsonProperty("language")
    private String language;

    @JsonProperty("region")
    private String region;

    @JsonProperty("tweets")
    private String[] tweets;

    @JsonProperty("following")
    private String[] following;

    @JsonProperty("followers")
    private String[] followers;
    
    public User(String username ,
            String name ,
            int followers_count ,
            int following_count ,
            String language ,
            String region,
            String[] tweets,
            String[] following,
            String[] followers){
        
        this.username = username ;
        this.name = name ;
        this.followers_count = followers_count ;
        this.following_count = following_count ;
        this.language = language ;
        this.region = region ;
        this.tweets = tweets;
        this.followers = followers;
        this.following = following;
         
    }
    
    public User(){
        
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getFollowersCount(){
        return followers_count;
    }
    
    public void setFollowersCount(int followers_count){
        this.followers_count = followers_count ;
    }
    
    public int getFollowingCount(){
        return following_count;
    }
    
    public void setFollowingCount(int following_count){
        this.following_count = following_count;
    }
    
    public String getLanguage(){
        return language;
    }
    
    public void setLanguage(String language){
        this.language = language;
    }
    
    public String getRegion(){
        return region;
    }
    
    public void setRegion(String region){
        this.region = region;
    }
    
    public String[] getTweets(){
        return tweets ;
    }
    
    public void setTweets(String[] tweets){
        this.tweets = tweets;
    }
    
    public String[] getFollowers(){
        return followers;
    }
    
    public void setFollowers(String[] followers){
        this.followers = followers;
    }
    
    public String[] getFollowing(){
        return following;
    }
    
    public void setFollowing(String[] following){
       this.following = following;
    }       
}
