package bgu.spl.net.api.bidi.messagesToServer;

import bgu.spl.net.api.bidi.*;
import bgu.spl.net.api.bidi.messagesToClient.Ack;
import bgu.spl.net.api.bidi.messagesToClient.Error;
import bgu.spl.net.api.bidi.messagesToClient.Ack;
public class Logout extends BasicMessageToServer {
    @Override
    public Object decode(byte nextByte) {
        return this; //there are no strings or chars we need to decode, we are handling the bytes themselves
    }

    @Override
    public void process(int ConnectionID, ConnectionsImpl connections, myBidiMessagingProtocol bidiMessagingProtocol) {
        if (!AllUsers.getInstance().checkIfLoggedIn(ConnectionID))//if user isn't logged in
            connections.send(ConnectionID,new Error((short)3));
        else{
            AllUsers.getInstance().logOutAUser(ConnectionID);
            connections.disconnect(ConnectionID);
            connections.send(ConnectionID,new Ack((short)3));

        }
    }
}
