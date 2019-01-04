package bgu.spl.net.srv;

import bgu.spl.net.api.MessageEncoderDecoderImpl;
import bgu.spl.net.api.bidi.BidiMessagingProtocolImpl;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.ConnectionsImpl;

public class main<T> {

    public static void main(String args[]) {
//        int port = Integer.parseInt(args[0]);
        int port = 666;
//        int numOfThreads = Integer.parseInt(args[1]);
        int numOfThreads = 6;

        Reactor reactor = Server.reactor(
                numOfThreads,
                port,
                () ->  new BidiMessagingProtocolImpl(),// protocol factory
                MessageEncoderDecoderImpl::new// encde factory
        );
        reactor.serve();
    }

}
