package bgu.spl.net.srv.bidi;

/* The ConnectionHandler should implement the new interface. Add calls for the new
Connections<T> interface. Notice that the ConnectionHandler<T> should now
work with the BidiMessagingProtocol<T> interface instead of MessagingProtocol<T>
 */

public class ConnectionHandlerImpl<T> implements ConnectionHandler<T> {

    public void send(T msg){
        return;
    }

    public void close() {
        return;
    }

}
