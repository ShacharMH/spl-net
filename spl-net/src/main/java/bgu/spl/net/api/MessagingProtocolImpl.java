package bgu.spl.net.api;

// to implement
public class MessagingProtocolImpl<T> implements MessagingProtocol<T> {

    /**
     * process the given message
     * @param msg the received message
     * @return the response to send or null if no response is expected by the client
     */
    public T process(T msg) {
        return null;
    }

    /**
     * @return true if the connection should be terminated
     */
    public boolean shouldTerminate() {
        return false;
    }
}
