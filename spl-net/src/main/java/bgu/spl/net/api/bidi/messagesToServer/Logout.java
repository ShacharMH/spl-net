package bgu.spl.net.api.bidi.messagesToServer;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.AllUsers;
import bgu.spl.net.api.bidi.messagesToClient.Error;
public class Logout extends BasicMessageToServer {
    @Override
    protected Object decode(byte nextByte) {
        return this; //there are no strings or chars we need to decode, we are handling the bytes themselves
    }

    @Override
    protected void process(int ConnectionID, Connections connections, BidiMessagingProtocol bidiMessagingProtocol) {
        if (!AllUsers.getInstance().checkIfLoggedIn(ConnectionID))//if user isn't logged in
            connections.send(ConnectionID,new Error((short)3));
        else{

        }
    }
}
