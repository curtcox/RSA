import java.math.BigInteger;

final class PrivateKey {

    final Key key;

    PrivateKey(Key key) {
        this.key = key;
    }

    BigInteger decrypt(BigInteger encrypted) {
        return key.map(encrypted);
    }

    BigInteger sign(BigInteger encrypted) {
        return key.map(encrypted);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        PrivateKey that = (PrivateKey) o;
        return key.equals(that.key);
    }

    @Override
    public String toString() {
        return "key=" + key;
    }

}
