package com.example.notika;

import java.util.ArrayList;
import java.util.List;

public class Topic {

    //  Variables to store data from firebase
    private String topic_name;
    private String subject;
    private String image;
    private ArrayList<String> sub_topics;

    //  Mandatory empty constructor
    public Topic(){

    }

    //  getter and setter methods
    public String getTopic_name(){
        return topic_name;
    }

    public void setTopic_name(String topic_name){
        this.topic_name = topic_name;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public ArrayList<String> getSub_topics(){
        return sub_topics;
    }

    public void setSub_topics(ArrayList<String> sub_topics){
        this.sub_topics = sub_topics;
    }

}
