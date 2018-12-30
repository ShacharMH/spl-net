package bgu.spl.net.api;

/* to implement
 */
public class MessageEncoderDecoderImpl<T> implements MessageEncoderDecoder<T> {

    /**
     * add the next byte to the decoding process
     *
     * @param nextByte the next byte to consider for the currently decoded
     * message
     * @return a message if this byte completes one or null if it doesnt.
     */
    public T decodeNextByte(byte nextByte){
        return null;
    }

    /**
     * encodes the given message to bytes array
     *
     * @param message the message to encode
     * @return the encoded bytes
     */
    public byte[] encode(T message){
        return null;
    }
}
