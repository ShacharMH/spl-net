package bgu.spl.net.api.bidi.messagesToServer;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;


public abstract class BasicMessageToServer {


    protected BasicMessageToServer(){}

    protected abstract Object decode(byte nextByte);

    protected abstract void process(int ConnectionID, Connections connections,BidiMessagingProtocol bidiMessagingProtocol);

    public byte[] shortToBytes(short input){
        byte[] bytes=new byte[2];
        bytes[0]=(byte)(input >> 8 & 0xff);
        bytes[1]=(byte)(input & 0xff);
        return bytes;
    }
}
