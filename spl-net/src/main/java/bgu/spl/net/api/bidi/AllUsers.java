package bgu.spl.net.api.bidi;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public  class  AllUsers {

     private static class HolderOfAllUsers{
         private static AllUsers allUsers= new AllUsers();
     }

     public static AllUsers getInstance() {
         return HolderOfAllUsers.allUsers;
     }

    private List<Pair> registeredUsers = new CopyOnWriteArrayList<>();
    private ConcurrentHashMap<String, User> loggedInUsers=new ConcurrentHashMap<>();//Users that have logged in
    private ConcurrentHashMap<Integer, String> IDsToNames=new  ConcurrentHashMap<>();//Map between  a user's ID to his name
    private ConcurrentHashMap<String, String> userPosts = new ConcurrentHashMap<>(); // map between users and their posts
    private ConcurrentHashMap<String, String> userPMs = new ConcurrentHashMap<>(); // map between users and their PMs.


public void registerUser(String name, User user){
        registeredUsers.add(new Pair(name, user));
    }

public void logInAUser(String name, User user){
         loggedInUsers.put(name,user);
     }

public void logOutAUser(int connectionId){
String userName=IDsToNames.get(connectionId);
IDsToNames.remove(connectionId);
loggedInUsers.remove(userName);
}

public User getUserByName(String name) {
    return loggedInUsers.get(name);
}//

public User getUserByConnectionId(int connectionId) {
    String name = IDsToNames.get(connectionId);
    return getUserByName(name);
}

public void MapConnection(Integer connectionNum, String userName){
         IDsToNames.put(connectionNum,userName);
     }

public boolean checkIfLoggedIn(int connectionId) {
    String name = getName(connectionId);
    if (name != null)
        return loggedInUsers.get(name) != null;
    throw new RuntimeException("connectionId is not found in IDsToNames hash map");
}

public String getName(int connectionId) {
    return IDsToNames.get(connectionId);
}

public int getConnectionId(String name) {
    return loggedInUsers.get(name).getConnectionId().intValue();
}

public boolean checkIfRegistered(String name){
    for (Pair pair : registeredUsers) {
        if (pair.equals(name))
            return true;
    }
    return false;
}

public List<Pair> getRegisteredUsers(){
    return registeredUsers;
}

private static class Pair {

    String name;
    User user;

    public Pair(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public boolean equals(String name) {
        return this.name == name;
    }
}

}
