package bgu.spl.net.api.bidi;

//************* start with this *****************
/* Implement Connections<T> to hold a list of the new ConnectionHandler interface
for each active client. Use it to implement the interface functions. Notice that
given a connections implementation, any protocol should run. This means that you
keep your implementation of Connections on T.
 */
public class ConnectionsImpl<T> implements Connections<T> {

    public boolean send(int connectionId, T msg) {
        return false;
    }

    public void broadcast(T msg) {
        return;
    }

    public void disconnect(int connectionId) {
        return;
    }
}
