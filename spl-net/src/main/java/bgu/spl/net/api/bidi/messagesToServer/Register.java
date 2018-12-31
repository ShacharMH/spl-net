package bgu.spl.net.api.bidi.messagesToServer;

import java.nio.charset.StandardCharsets;
import bgu.spl.net.api.bidi.AllUsers;
import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.api.bidi.BidiMessagingProtocol;
import bgu.spl.net.api.bidi.User;
import bgu.spl.net.api.bidi.messagesToClient.Error;


public class Register extends BasicMessageToServer {
private String name;
private String password;
private byte[] bytes;
private int indexOfUserName;//change name later  //holds the space of the USERNAME in bytes
private int indexOfUserPassword;//change name later  ////holds the space of the PASSWORD in bytes
private boolean finished;


    public Register(){
        super();
        bytes=new byte[1<<10];
        indexOfUserName=0;
        indexOfUserPassword=0;
        this.finished=false;
    }



    @Override
    protected Object decode(byte nextByte) {
        if(!finished) {//if we aren't finished reading the USERNAME
            if (nextByte != '\0') {
                bytes[indexOfUserName] = nextByte;
                indexOfUserName++;
            } else {//we have finished reading the input
                finished = true;
                indexOfUserPassword= indexOfUserName;//we will continue to submit the bytes from this value, and we know it symbolizes the spot where
            }
        }
         else{//we have finished reading the USERNAME
             if (nextByte!='\0'){
                 bytes[indexOfUserPassword]=nextByte;
                 indexOfUserPassword++;
            }
             else{//we have finished to read Both the USERNAME and the PASSWORD
                 name=new String(bytes,0,indexOfUserName-1, StandardCharsets.UTF_8);
                 password=new String(bytes,indexOfUserName,indexOfUserPassword-1, StandardCharsets.UTF_8);
                 return this;
             }
            }
         return null;
    }






    @Override
    protected void process(int ConnectionID, Connections connections, BidiMessagingProtocol bidiMessagingProtocol) {
        //If user is already registered:
        if(AllUsers.getInstance().checkIfRegistered(name)){
            System.out.println("User is already registered");
            connections.send(ConnectionID,new Error((short)1));//The constructed Error is the response to send back to this client.
        }
        else{
            User user=new User(name,password);
            AllUsers.getInstance().registerUser(name,user);
            //connections.send(ConnectionID,new ACK) //need to implement ACK message, the message sent back to the client.
        }

    }
}
