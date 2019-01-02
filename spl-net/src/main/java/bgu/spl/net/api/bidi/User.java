package bgu.spl.net.api.bidi;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import bgu.spl.net.api.bidi.messagesToClient.Notification;
import bgu.spl.net.api.bidi.messagesToServer.BasicMessageToServer;

public class User {
    private AtomicInteger connectionID;
    private String name;
    private String password;
    private List<String> followers;//list of followers of this user
    private List<String> following;//list of users that the user is following
    private List<Notification> AwaitingNotifications;// NEED TO IMPLEMENT NOTIFICATION


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

    public AtomicInteger getConnectionId() {
        return this.connectionID;
    }

    public boolean followOrUnfollow(int followOrUnfollow, String name) {
        if (followOrUnfollow == 0)
            return addToFollowing(name);
        return removeFromFollowing(name);
    }


    private boolean addToFollowing(String name) {
        if (following.contains(name))
            return false;
        following.add(name);
        return true;
    }

    private boolean removeFromFollowing(String name) {
        if (!following.contains(name))
            return false;
        following.remove(name);
        return true;
    }

    public List<String> getFollowers() {
        return followers;
    }






    private static class Pair {

        String name;
        AtomicInteger connId;

        public Pair(String name, int connId) {
            this.name = name;
            this.connId = new AtomicInteger(connId);
        }

        public AtomicInteger getConnId() {
            return connId;
        }

        public String getName() {
            return name;
        }


    }


    public List<Notification> getAwaitingNotifications() {
        return AwaitingNotifications;
    }

}



