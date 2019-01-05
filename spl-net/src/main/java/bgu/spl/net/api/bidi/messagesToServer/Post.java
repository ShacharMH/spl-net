package bgu.spl.net.api.bidi.messagesToServer;

import bgu.spl.net.api.bidi.AllUsers;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.User;
import bgu.spl.net.api.bidi.messagesToClient.Ack;
import bgu.spl.net.api.bidi.messagesToClient.Error;
import bgu.spl.net.api.bidi.messagesToClient.Notification;

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

    public Object decode(byte nextByte) {
        if (nextByte != '\0') {
            bytes[index] = nextByte;
            index++;
        } else {
            string = new String(bytes, 0, index, StandardCharsets.UTF_8);
            return this;
        }
        return null;
    }

    public void process(int ConnectionID, Connections connections, BidiMessagingProtocol bidiMessagingProtocol) {
        // check if user is logged in, if not - send an error
        if (!allUsers.checkIfLoggedIn(ConnectionID)) {
            connections.send(ConnectionID, new Error(PostOpCode));
            return;
        }

        // user:
        String userName = allUsers.getName(ConnectionID);
        User user = allUsers.getUserByConnectionId(ConnectionID);

        // initilizing the list of users to send post to to followers
        usersToSendPostTo = allUsers.getUserByConnectionId(ConnectionID).getFollowers();

        parsedString = string.split(" ");
        for (int i = 0; i < parsedString.length; i++) {
            String tmp = parsedString[i];
            // finding tagged users
            if ((tmp.substring(0,1)).equals("@")) {
                String name = tmp.substring(1);
                // adding tagged users to users to send post to
                if (!usersToSendPostTo.contains(name) && allUsers.checkIfRegistered(name))
                    usersToSendPostTo.add(name);
                taggedUsers.add(name);
            }
        }


        // preparing the notification:
        Notification notification = new Notification((char)PostOpCode, allUsers.getName(ConnectionID), string);

        // adding notification to relevant data structure:
        allUsers.savePost(userName, notification);
        user.savePost(notification);

        for (String name : usersToSendPostTo) {
            // if a user is logged in - we send him the message
            if (allUsers.checkIfLoggedIn(name)){
                connections.send(allUsers.getConnectionId(name), notification);
            } else { // if user is not logged in, we add a notification to its pending notif. list.
                allUsers.getUserByName(name).addToAwaitingNotifications(notification);
            }
        }

        // sending the ACK message
        connections.send(ConnectionID, new Ack(PostOpCode));

    }
}
