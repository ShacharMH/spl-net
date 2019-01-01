package bgu.spl.net.api.bidi;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import bgu.spl.net.api.bidi.messagesToClient.Notification;

public class User {
    private AtomicInteger connectionID;
    private String name;
    private String password;
    private List<Integer> followers;//list of followers of this user, represented by their connection ID
    private List<Integer> following;//list of users that the user is following, represented by their connection ID
    private List<Notification> awaitingNotifications;// NEED TO IMPLEMENT NOTIFICATION


    public User(String name,String password){
        this.name=name;
        this.password=password;
        this.followers = new CopyOnWriteArrayList<>();
        this.following = new CopyOnWriteArrayList<>();
    }

    public String getPassword(){
        return this.password;
    }

    public void setConnectionID(Integer connectionID){
        this.connectionID= new AtomicInteger(connectionID);
    }

    public List<Notification> getAwaitingNotifications() {
        return awaitingNotifications;
    }
}


