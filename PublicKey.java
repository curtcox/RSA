import java.math.BigInteger;

final class PublicKey {

    final Key key;

    PublicKey(Key key) {
        this.key = key;
    }

    BigInteger encrypt(BigInteger message) {
        return key.map(message);
    }

    boolean verify(BigInteger message, BigInteger signature) {
        return message.equals(key.map(signature));
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        PublicKey that = (PublicKey) o;
        return key.equals(that.key);
    }

    @Override
    public String toString() {
        return "key=" + key;
    }

}
