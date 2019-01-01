package bgu.spl.net.api.bidi.messagesToServer;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;

public class Follow extends BasicMessageToServer {



    public Object decode(byte nextByte) {
        return new Object();
    }

    public void process(int ConnectionID, Connections connections, BidiMessagingProtocol bidiMessagingProtocol) {
        return;
    }
}
