import java.math.BigInteger;
import java.security.SecureRandom;

final class RSAGenerator {

   private int bits;
   private static final BigInteger      one = new BigInteger("1");
   private static final SecureRandom random = new SecureRandom();

   // generate an N-bit (roughly) public and private key
   RSAGenerator(int bits) {
      this.bits = bits;
   }

   RSA generate() {
      int bitLength = bits/2;
      int certainlyPrime = 100;
      BigInteger p = new BigInteger(bitLength, certainlyPrime, random);
      BigInteger q = new BigInteger(bitLength, certainlyPrime, random);
      BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
      BigInteger publicKey = generatePublicKey(phi);
      BigInteger modulus = p.multiply(q);
      BigInteger privateKey = publicKey.modInverse(phi);
      return new RSA(publicKey,privateKey,modulus);
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
              publicKey.compareTo(phi) < 0            &&
              publicKey.gcd(phi).equals(one);
   }

}
