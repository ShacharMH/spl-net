package bgu.spl.net.api.bidi.messagesToServer;

import bgu.spl.net.api.bidi.AllUsers;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.User;
import bgu.spl.net.api.bidi.messagesToClient.Error;

public class UserList extends BasicMessageToServer {


    public UserList(){
        super();
    }
    @Override
    protected Object decode(byte nextByte) {
        return this;
    }

    @Override
    protected void process(int ConnectionID, Connections connections, BidiMessagingProtocol bidiMessagingProtocol) {
        if (!AllUsers.getInstance().checkIfLoggedIn(ConnectionID)) {
            connections.send(ConnectionID, new Error((short) 7));
        }
        else{
            byte[] ACKmessage = new byte[1<<10];
            short numOfRegisteredUsers=(short)AllUsers.getInstance().getRegisteredUsers().size();
            byte[] additions=shortToBytes(numOfRegisteredUsers);
            ACKmessage[0]=additions[0];
            ACKmessage[1]=additions[1];
            User[] OrderedUserNames=new User[numOfRegisteredUsers];
            int i=0;
            for(User user:AllUsers.getInstance().getRegisteredUsers()){
                OrderedUserNames[i]=user;
                i++;
            }
        }
    }
}
