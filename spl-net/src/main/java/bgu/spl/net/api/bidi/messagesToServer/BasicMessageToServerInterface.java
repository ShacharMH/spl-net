package bgu.spl.net.api.bidi.messagesToServer;

import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;

public interface BasicMessageToServerInterface<T> {

    Object decode(byte nextByte);

    void process(int ConnectionID, Connections connections, BidiMessagingProtocol bidiMessagingProtocol);
}
