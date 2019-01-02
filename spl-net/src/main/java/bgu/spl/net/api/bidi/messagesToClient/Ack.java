package bgu.spl.net.api.bidi.messagesToClient;

import bgu.spl.net.api.bidi.messagesToServer.BasicMessageToServer;

public class Ack extends BasicMessageToClient {

    private short type;
    byte[] ACKmessage;


    public Ack(short type){
        super();
        setOpCode((short)10);
        this.type=type;
        setOpCode((short)10);
        encode();
    }

    public Ack(short type, byte[] optionalPart){
        new Ack(type);
        addOptionalPart(optionalPart);
    }

    @Override
    public byte[] encode() {
        byte[] returnValue=new byte[4];
        byte[] additions=shortToBytes(getOpCode());//translation of the OpCode
        returnValue[0]=additions[0];
        returnValue[1]=additions[1];
        additions=shortToBytes(type);//translation of the MessageOpCode
        returnValue[2]=additions[0];
        returnValue[3]=additions[1];
        ACKmessage = returnValue;
        return returnValue;
    }

    private void addOptionalPart(byte[] optionalPart) {
        int ACKindex = ACKmessage.length -1;
        for (int i = 0; i < optionalPart.length; i++) {
            ACKmessage[ACKindex] = optionalPart[i];
            ACKindex++;
        }
    }
}

