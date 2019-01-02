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

    public boolean followOrUnfollow(int followOrUnfollow, int connectionId) {
        if (connectionId == 0)
            return addToFollowing(connectionId);
        return removeFromFollowing(connectionId);
    }


    private boolean addToFollowing(int connectionId) {
        if (following.contains(connectionId))
            return false;
        following.add(connectionId);
        return true;
    }

    private boolean removeFromFollowing(int connectionId) {
        if (!following.contains(connectionId))
            return false;
        following.remove(following.indexOf(connectionId));
        return true;
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



