package bgu.spl.net.api.bidi;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import bgu.spl.net.api.bidi.messagesToClient.Notification;

public class User {
    private AtomicInteger connectionID;
    private String name;
    private String password;
    private int registrationTime;
    private List<String> followers;//list of followers of this user
    private List<String> following;//list of users that the user is following
    private List<Notification> AwaitingNotifications;// NEED TO IMPLEMENT NOTIFICATION
    private List<Notification> sentPMs;
    private List<Notification> sentPosts;


    public User(String name,String password){
        this.name=name;
        this.password=password;
        this.followers = new CopyOnWriteArrayList<>();
        this.following = new CopyOnWriteArrayList<>();
    }

    public void setRegistrationTime(int registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getPassword(){
        return this.password;
    }

    public void setConnectionID(Integer connectionID){
        this.connectionID= new AtomicInteger(connectionID);
    }

    public String getName(){
        return name;
    }

    public AtomicInteger getConnectionId() {
        return this.connectionID;
    }

    public boolean followOrUnfollow(int followOrUnfollow, String name) {
        if (followOrUnfollow == 0) // new follow
            return addToFollowing(name);
        return removeFromFollowing(name);
    }

    public void followerOrUnfollower(int followOrUnfollow, String name) {
        if (followOrUnfollow == 0) // new follower
            addToFollowers(name);
        removeFromFollowers(name);
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

    private void addToFollowers(String name) {
        if (followers.contains(name))
            return;
        followers.add(name);
    }

    private void removeFromFollowers(String name) {
        if (!followers.contains(name))
            return;
        followers.remove(name);
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public int getNumOfFollowing() {
        return following.size();
    }

    public int getNumOfFollowers() {
        return followers.size();
    }

    public void addToAwaitingNotifications(Notification notification) {
        AwaitingNotifications.add(notification);
    }

    public void savePost(Notification notification) {
        sentPosts.add(notification);
    }

    public void savePM(Notification notification) {
        sentPMs.add(notification);
    }

    public int getNumOfPosts() {
        return sentPosts.size();
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



