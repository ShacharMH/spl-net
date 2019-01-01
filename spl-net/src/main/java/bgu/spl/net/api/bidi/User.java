package bgu.spl.net.api.bidi;

import javax.management.Notification;
import java.util.Vector;

public class User {
    private Integer connectionID;
    private String name;
    private String password;
    private Vector<Integer> followers;//list of followers of this user, represented by their connection ID
    private Vector<Integer> following;//list of users that the user is following, represented by their connection ID
    //private Vector<Notification>// NEED TO IMPLEMENT NOTIFICATION


    public User(String name,String password){
        this.name=name;
        this.password=password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setConnectionID(Integer connectionID){
        this.connectionID=connectionID;
    }
}


