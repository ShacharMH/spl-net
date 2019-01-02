package bgu.spl.net.api.bidi.messagesToServer;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;

public class Stat extends BasicMessageToServer {

    public Stat() {
        super();
    }

    protected Object decode(byte nextByte) {
        return null;
    }

    protected void process(int ConnectionID, Connections connections, BidiMessagingProtocol bidiMessagingProtocol) {
        return;
    }

}
