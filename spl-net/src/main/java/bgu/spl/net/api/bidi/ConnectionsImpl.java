package bgu.spl.net.api.bidi;
import bgu.spl.net.srv.bidi.ConnectionHandler;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//************* start with this *****************
/* Implement Connections<T> to hold a list of the new ConnectionHandler interface
for each active client. Use it to implement the interface functions. Notice that
given a connections implementation, any protocol should run. This means that you
keep your implementation of Connections on T.

This interface should map a unique ID for each active client
connected to the server

 */
public class ConnectionsImpl<T> implements Connections<T> {

    private ConcurrentHashMap<AtomicInteger, ConnectionHandler> connectedUsers;
    private AtomicInteger connectionId;

    public ConnectionsImpl() {
        this.connectedUsers = new ConcurrentHashMap<>();
        this.connectionId = new AtomicInteger(1);
    }

    public void addToConnectedUsers(ConnectionHandler connectionHandler) {
        connectedUsers.putIfAbsent(connectionId, connectionHandler);
        connectionId.incrementAndGet();
    }

    /* sends a message T to client represented by the given connId
     */
    public boolean send(int connectionId, T msg) {
        ConnectionHandler connectionHandler = connectedUsers.get(new AtomicInteger(connectionId));
        synchronized (connectionHandler) {
            if (connectionHandler != null) {
                connectionHandler.send(msg);
                return true;
            } else { // assuming that if someone uses send, then the user is logged in

            }
            return false;
        }
    }

    public int getConnectionId() {
        return connectionId.intValue();
    }

    /* sends a message T to all active clients. This
includes clients that has not yet completed log-in by the BGS protocol.
Remember, Connections<T> belongs to the server pattern
implemenration, not the protocol!.
     */
    public void broadcast(T msg) {
        connectedUsers.forEach((connId, connHandler) -> connHandler.send(msg));
    }

    /* removes active client connId from map.
     */
    public void disconnect(int connectionId) {
        connectedUsers.remove(new AtomicInteger(connectionId));
    }
}
