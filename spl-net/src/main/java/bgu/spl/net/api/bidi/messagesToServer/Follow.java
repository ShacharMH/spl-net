package bgu.spl.net.api.bidi.messagesToServer;

import bgu.spl.net.api.bidi.AllUsers;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.User;
import bgu.spl.net.api.bidi.messagesToClient.Error;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Follow extends BasicMessageToServer {

    private boolean sendErrorMessage;// changes to false once one of the un/follow succeeds.
    private boolean isUserLoggedIn;
    private int index;
    private int userIndex;
    private int followOrUnfollow;
    private byte[] bytesUserName;
    private byte[] bytesNumOfUsers;
    private short numOfUsers;
    private String userName;
    private List<String> userNameList;
    private AllUsers allUsers;

    public Follow() {
        super();
        this.sendErrorMessage = true;
        this.index = 0;
        this.followOrUnfollow = -1;
        this.bytesNumOfUsers = new byte[2];
        this.bytesUserName = new byte[1<<10];
        this.userIndex = 0;
        this.userNameList = new CopyOnWriteArrayList<>();
        allUsers = AllUsers.getInstance();
    }


    public Object decode(byte nextByte) {
        if (index == 0) { // this byte is follow/unfollow
            followOrUnfollow = nextByte;
            index++;
        } else if (index == 1) { // the following two bytes represent the number of users to follow/unfollow
            bytesNumOfUsers[0] = nextByte;
            index++;
        } else if (index == 2) {
            bytesNumOfUsers[1] = nextByte;
            index++;
            numOfUsers = bytesToShort(bytesNumOfUsers);
        } else {
            if (nextByte != 0 && index > 2) {
                bytesUserName[userIndex] = nextByte;
                index++;
                userIndex++;
            } else {
                userName = new String(bytesNumOfUsers, 0, userIndex-1, StandardCharsets.UTF_8);
                userNameList.add(userName);
                index++;
                userIndex = 0;
            }
        }
        return this;
    }

    public void process(int ConnectionID, Connections connections, BidiMessagingProtocol bidiMessagingProtocol) {
        if (!allUsers.checkIfLoggedIn(ConnectionID)) { // user isn't logged in - send an error message
            connections.send(ConnectionID, new Error((short) 4));
            return;
        }
        User user = allUsers.getUserByConnectionId(ConnectionID);
        if (followOrUnfollow == 0) { // follow
            for (String tmp : userNameList) {

            }
        } else {

        }
    }

    private short bytesToShort ( byte[] byteArr)
    {
        short result = (short) ((byteArr[0] & 0xff) << 8);
        result += (short) (byteArr[1] & 0xff);
        return result;
    }
}
