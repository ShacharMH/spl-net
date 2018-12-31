package bgu.spl.net.api.bidi.messagesToServer;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;


public abstract class BasicMessageToServer {


    protected BasicMessageToServer(){}

    protected abstract Object decode(byte nextByte);

    protected abstract void process(int ConnectionID, Connections connections,BidiMessagingProtocol bidiMessagingProtocol);
}
