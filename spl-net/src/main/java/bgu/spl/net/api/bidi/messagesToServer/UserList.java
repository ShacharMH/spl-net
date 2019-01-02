package bgu.spl.net.api.bidi.messagesToServer;

import bgu.spl.net.api.bidi.AllUsers;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.User;
import bgu.spl.net.api.bidi.messagesToClient.Ack;
import bgu.spl.net.api.bidi.messagesToClient.Error;

import java.util.Collections;


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
        if (!AllUsers.getInstance().checkIfLoggedIn(ConnectionID)) {//if sending user isn't logged in
            connections.send(ConnectionID, new Error((short) 7));
        }
        else{//if user is logged in
            byte[] ACKmessage = new byte[1<<10];
            short numOfRegisteredUsers=(short)AllUsers.getInstance().getRegisteredUsers().size();
            byte[] additions=shortToBytes(numOfRegisteredUsers);//converting number of Registered Users to bytes
            ACKmessage[0]=additions[0];
            ACKmessage[1]=additions[1];
            User[] OrderedUserNames=new User[numOfRegisteredUsers];
            int i=0;
            for(User user:AllUsers.getInstance().getListOfRegisteredUsers()){//OrderedUserNames is sorted
                OrderedUserNames[i]=user;
                i++;
            }
            int index = 2;
            // add all relevant user names
            for (User name: OrderedUserNames) {
                byte[] nameInBytes = name.getName().getBytes();
                for (int j = 0; j < nameInBytes.length; j++) {
                    ACKmessage[index] = nameInBytes[i];
                    index++;
                }
                ACKmessage[index] = 0;
                index++;
            }
            connections.send(ConnectionID, new Ack((short) 7, ACKmessage));

        }
    }
}
