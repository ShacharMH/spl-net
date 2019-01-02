package bgu.spl.net.api.bidi.messagesToServer;

import bgu.spl.net.api.bidi.AllUsers;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.User;
import bgu.spl.net.api.bidi.messagesToClient.Error;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Post extends BasicMessageToServer {

    byte[] bytes;
    int index;
    private String string;
    private String[] parsedString;
    private List<Integer> taggedUsers;
    private List<Integer> usersToSendPostTo;
    private AllUsers allUsers;
    private short PostOpCode = 5;


    public Post() {
        super();
        allUsers = AllUsers.getInstance();
    }

    protected Object decode(byte nextByte) {
        if (nextByte != '\0') {
            bytes[index] = nextByte;
            index++;
        } else {
            string = new String(bytes, 0, index-1, StandardCharsets.UTF_8);
        }
        return this;
    }

    protected void process(int ConnectionID, Connections connections, BidiMessagingProtocol bidiMessagingProtocol) {
        // check if user is logged in, if not - send an error
        if (!allUsers.checkIfLoggedIn(ConnectionID)) {
            connections.send(ConnectionID, new Error(PostOpCode));
            return;
        }
        usersToSendPostTo = allUsers.getUserByConnectionId(ConnectionID).getFollowers();

        parsedString = string.split(" ");
        for (int i = 0; i < parsedString.length; i++) {
            String tmp = parsedString[i];
            if ((tmp.substring(0,1)).equals("@")) {
                String name = tmp.substring(1);
                int connectionId = allUsers.getConnectionId(name);
                taggedUsers.add(connectionId);
            }
        }
    }

    private void encodeACKmesage(int ConnectionId, Connections connections) {
        return;
    }
}
