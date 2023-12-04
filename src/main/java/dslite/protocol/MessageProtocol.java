package dslite.protocol;

import java.io.*;

public class MessageProtocol {
    public static byte[] encodeMessage(Message message) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeInt(message.getType().ordinal());
            oos.writeObject(message.getContent());
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Message decodeMessage(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            int messageTypeOrdinal = ois.readInt();
            MessageType messageType = MessageType.values()[messageTypeOrdinal];

            Object content = ois.readObject();

            return new Message<>(messageType, content);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
