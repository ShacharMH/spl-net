package bgu.spl.net.api.bidi;

import java.util.concurrent.ConcurrentHashMap;

 public  class  AllUsers {

     private static class HolderOfAllUsers{
         private static AllUsers allUsers= new AllUsers();
     }

     public static AllUsers getInstance() {
         return HolderOfAllUsers.allUsers;
     }

    private ConcurrentHashMap<String, User> registeredUsers=new ConcurrentHashMap<>();//Users that have registered
    private ConcurrentHashMap<String, User> loggedInUsers=new ConcurrentHashMap<>();//Users that have logged in
    private ConcurrentHashMap<Integer, String> IDsToNames=new  ConcurrentHashMap<>();//Map between  a user's ID to his name


public void registerUser(String name, User user){
        registeredUsers.put(name,user);
    }

public void logInAUser(String name, User user){
         loggedInUsers.put(name,user);
     }

public void MapConnection(Integer connectionNum, String userName){
         IDsToNames.put(connectionNum,userName);
     }

public boolean checkIfRegistered(String name){
    return registeredUsers.containsKey(name);
}

public boolean checkIfLoggedIn(String name){
         return loggedInUsers.containsKey(name);
}

public ConcurrentHashMap<String, User> getRegisteredUsers(){
    return registeredUsers;
}


}
