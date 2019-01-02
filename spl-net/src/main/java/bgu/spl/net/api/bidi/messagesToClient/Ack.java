package bgu.spl.net.api.bidi.messagesToClient;

import bgu.spl.net.api.bidi.messagesToServer.BasicMessageToServer;

public class Ack extends BasicMessageToClient {

    private short type;
    byte[] ACKmessage;
    byte[] optionalPart = null;


    public Ack(short type){
        super();
        setOpCode((short)10);
        this.type=type;
        setOpCode((short)10);
    }

    public Ack(short type, byte[] optionalPart){
        this(type);
        this.optionalPart = optionalPart;
    }

    @Override
    public byte[] encode() {
        byte[] returnValue=new byte[1<<10];
        byte[] additions=shortToBytes(getOpCode());//translation of the OpCode
        returnValue[0]=additions[0];
        returnValue[1]=additions[1];
        additions=shortToBytes(type);//translation of the MessageOpCode
        returnValue[2]=additions[0];
        returnValue[3]=additions[1];
        addOptionalPart();
        ACKmessage = returnValue;
        return returnValue;
    }

    private void addOptionalPart() {
        if (optionalPart == null)
            return;
        int ACKindex = ACKmessage.length -1;
        for (int i = 0; i < optionalPart.length; i++) {
            ACKmessage[ACKindex] = optionalPart[i];
            ACKindex++;
        }
    }
}

