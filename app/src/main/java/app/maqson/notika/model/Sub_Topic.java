package app.maqson.notika.model;

public class Sub_Topic {

    //  Variables to store data from firebase
    private String sub_topic_name;

    //  Mandatory empty constructor
    public Sub_Topic(){

    }

    //  getter and setter methods
    public String getSub_topic_name(){
        return sub_topic_name;
    }

    public void setSub_topic_name(String sub_topic_name){
        this.sub_topic_name = sub_topic_name;
    }
}
