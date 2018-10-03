import java.math.BigInteger;
import java.security.SecureRandom;

final class RSAGenerator {

   private final int bitLength;
   private static final BigInteger      one = new BigInteger("1");
   private static final SecureRandom random = new SecureRandom();

   // generate an N-bit (roughly) public and private key
   RSAGenerator(int bits) {
      bitLength = bits/2;
   }

   RSA generate() {
      BigInteger p = randomPrime();
      BigInteger q = randomPrime();
      BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
      BigInteger publicKey = generatePublicKey(phi);
      BigInteger privateKey = publicKey.modInverse(phi);
      BigInteger modulus = p.multiply(q);
      return new RSA(
              new PublicKey(new Key(publicKey,modulus)),
              new PrivateKey(new Key(privateKey,modulus)));
   }

   BigInteger randomPrime() {
      int certainlyPrime = 100;
      return new BigInteger(bitLength, certainlyPrime, random);
   }

   BigInteger generatePublicKey(BigInteger phi) {
      while (true) {
         BigInteger publicKey = possiblePublicKey(phi);
         if (suitable(publicKey,phi)) {
            return  publicKey;
         }
      }
   }

   BigInteger possiblePublicKey(BigInteger phi) {
      return new BigInteger(phi.bitLength(), random);
   }

   boolean suitable(BigInteger publicKey,BigInteger phi) {
      return  publicKey.compareTo(one) > 0 &&
              publicKey.compareTo(phi) < 0 &&
              publicKey.gcd(phi).equals(one);
   }

}
