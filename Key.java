import java.math.BigInteger;

final class Key {

   final BigInteger key;
   final BigInteger modulus;

   Key(BigInteger key, BigInteger modulus) {
      this.key     = key;
      this.modulus = modulus;
   }

   BigInteger map(BigInteger message) {
      return message.modPow(key, modulus);
   }

   @Override
   public int hashCode() {
      return key.hashCode() ^ modulus.hashCode();
   }

   @Override
   public boolean equals(Object o) {
      Key that = (Key) o;
      return key.equals(that.key) && modulus.equals(that.modulus);
   }

   @Override
   public String toString() {
      return "key=" + key + " modulus=" + modulus;
   }

}
