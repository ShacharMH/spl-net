package bgu.spl.net.impl;

import bgu.spl.net.api.MessageEncoderDecoderImpl;
import bgu.spl.net.api.bidi.BidiMessagingProtocolImpl;

import bgu.spl.net.srv.Server;

public class mainThreadPerClient {

    public static void main(String[] args) {

        int port = 666;
        //int port = Integer.parseInt(args[0]);

        Server.threadPerClient(
                port, //port
                () -> new BidiMessagingProtocolImpl(), //protocol factory ..
                MessageEncoderDecoderImpl::new //message encoder decoder factory
        ).serve();
    }

}