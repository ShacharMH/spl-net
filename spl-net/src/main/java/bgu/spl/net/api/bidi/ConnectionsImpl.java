package bgu.spl.net.api.bidi;

//************* start with this *****************
/* Implement Connections<T> to hold a list of the new ConnectionHandler interface
for each active client. Use it to implement the interface functions. Notice that
given a connections implementation, any protocol should run. This means that you
keep your implementation of Connections on T.

This interface should map a unique ID for each active client
connected to the server

 */
public class ConnectionsImpl<T> implements Connections<T> {

    /* sends a message T to client represented by the given connId
     */
    public boolean send(int connectionId, T msg) {
        return false;
    }

    /* sends a message T to all active clients. This
includes clients that has not yet completed log-in by the BGS protocol.
Remember, Connections<T> belongs to the server pattern
implemenration, not the protocol!.
     */
    public void broadcast(T msg) {
        return;
    }

    /* removes active client connId from map.
     */
    public void disconnect(int connectionId) {
        return;
    }
}
