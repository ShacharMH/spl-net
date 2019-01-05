package bgu.spl.net.api.bidi;

import bgu.spl.net.srv.bidi.ConnectionHandler;

public interface ConnectionsExtention<T> extends Connections<T> {

    void addToConnectedUsers(ConnectionHandler connectionHandler);

}
