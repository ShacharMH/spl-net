package bgu.spl.net.api.bidi.messagesToServer;

import bgu.spl.net.api.bidi.AllUsers;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.messagesToClient.Error;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Post extends BasicMessageToServer {

    byte[] bytes;
    int index;
    private String string;
    private String[] parsedString;
    private List<String> taggedUsers;
    private List<String> usersToSendPostTo;
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
            bytes[index] = nextByte;
            string = new String(bytes, 0, index, StandardCharsets.UTF_8);
        }
        return this;
    }

    protected void process(int ConnectionID, Connections connections, BidiMessagingProtocol bidiMessagingProtocol) {
        // check if user is logged in, if not - send an error
        if (!allUsers.checkIfLoggedIn(ConnectionID)) {
            connections.send(ConnectionID, new Error(PostOpCode));
            return;
        }

        // initilizing the list of users to send post to to followers
        usersToSendPostTo = allUsers.getUserByConnectionId(ConnectionID).getFollowers();

        parsedString = string.split(" ");
        for (int i = 0; i < parsedString.length; i++) {
            String tmp = parsedString[i];
            // finding tagged users
            if ((tmp.substring(0,1)).equals("@")) {
                String name = tmp.substring(1);
                // adding tagged users to users to send post to
                if (!usersToSendPostTo.contains(name) & allUsers.checkIfRegistered(name))
                    usersToSendPostTo.add(name);
                taggedUsers.add(name);
            }
        }

        // we already have the message we need to send.
        // now we need to send it to users that are connected.
        // for users that are not connected - we need to add it to their pending mesages list
        for (String name : usersToSendPostTo) {
            if (allUsers.checkIfLoggedIn(name)){
                connections.send(allUsers.getConnectionId(name), bytes);
            } else {

            }
        }

    }

    private void encodeACKmesage(int ConnectionId, Connections connections) {
        return;
    }
}
