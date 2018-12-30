package bgu.spl.net.api.bidi;

/* to implement
 */
public class BidiMessagingProtocolImpl<T> implements BidiMessagingProtocol<T> {

    public void start(int connectionId, Connections<T> connections){
        return;
    }

    public void process(T message){
        return;
    }

    /**
     * @return true if the connection should be terminated
     */
    public boolean shouldTerminate() {
        return false;
    }

}
