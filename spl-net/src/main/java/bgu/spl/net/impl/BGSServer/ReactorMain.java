package bgu.spl.net.impl.BGSServer;

import bgu.spl.net.api.MessageEncoderDecoderImpl;
import bgu.spl.net.api.bidi.BidiMessagingProtocolImpl;
import bgu.spl.net.srv.Server;

public class ReactorMain {

        @SuppressWarnings("unchecked")
        public static void main(String args[]) {

            //int port = Integer.parseInt(args[0]);
            int port = 666;
            //int numOfThreads = Integer.parseInt(args[1]);
            int numOfThreads = 6;

            Server.reactor(numOfThreads,
                    port, //port
                    () -> new BidiMessagingProtocolImpl() {
                    }, //protocol factory - have no idea what the problem is
                    MessageEncoderDecoderImpl::new //message encoder decoder factory
            ).serve();
        }

    }

