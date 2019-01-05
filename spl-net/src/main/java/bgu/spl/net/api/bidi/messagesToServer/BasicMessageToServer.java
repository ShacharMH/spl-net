package bgu.spl.net.api.bidi.messagesToServer;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;


public abstract class BasicMessageToServer {


    public BasicMessageToServer(){}

    public abstract Object decode(byte nextByte);

    public abstract void process(int ConnectionID, Connections connections,BidiMessagingProtocol bidiMessagingProtocol);

    public byte[] shortToBytes(short input){
        byte[] bytes=new byte[2];
        bytes[0]=(byte)(input >> 8 & 0xff);
        bytes[1]=(byte)(input & 0xff);
        return bytes;
    }



}
