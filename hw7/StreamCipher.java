import java.util.Random;


public class StreamCipher extends java.lang.Object implements Cipher {

    private long seed;


    public StreamCipher(long seed) {
        this.seed = seed;
    }

   
    @Override
    public String encrypt(String message) {
        Random random = new Random(seed);
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
                        char encryptedChar = (char) (c ^ random.nextInt(0x10000));
            encryptedMessage.append(encryptedChar);
        }

        return encryptedMessage.toString();
    }

   
    @Override
    public String decrypt(String encryptedMessage) {
        Random random = new Random(seed);
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < encryptedMessage.length(); i++) {
            char c = encryptedMessage.charAt(i);
            
            // encrypted character
            char decryptedChar = (char) (c ^ random.nextInt(0x10000));
            decryptedMessage.append(decryptedChar);
        }

        return decryptedMessage.toString();
    }
}
