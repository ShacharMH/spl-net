package bgu.spl.net.api.bidi;

import java.util.concurrent.ConcurrentHashMap;

 public  class  AllUsers {

     private static class HolderOfAllUsers{
         private static AllUsers allUsers= new AllUsers();
     }

     public static AllUsers getInstance() {
         return HolderOfAllUsers.allUsers;
     }

    private ConcurrentHashMap<String, User> registeredUsers=new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, User> onlineUsers=new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, String> connectionIDs=new  ConcurrentHashMap<>();


public void registerUser(String name, User user){
        registeredUsers.put(name,user);
    }


public boolean checkIfRegistered(String name){
    return registeredUsers.containsKey(name);
}

}
