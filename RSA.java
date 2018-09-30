import java.math.BigInteger;
import java.security.SecureRandom;

final class RSA {


   final BigInteger privateKey;
   final BigInteger publicKey;
   final BigInteger modulus;

   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   private RSA(BigInteger publicKey, BigInteger privateKey, BigInteger modulus) {
      this.publicKey  = publicKey;
      this.privateKey = privateKey;
      this.modulus    = modulus;
   }

   // generate an N-bit (roughly) public and private key
   static RSA bitLength(int bits) {
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

   static BigInteger generatePublicKey(BigInteger phi) {
      while (true) {
         BigInteger publicKey = new BigInteger(phi.bitLength(), random);
         if (suitable(publicKey,phi)) {
            return  publicKey;
         }
      }
   }

   static boolean suitable(BigInteger publicKey,BigInteger phi) {
      return  publicKey.compareTo(one) > 0 &&
              publicKey.compareTo(phi) < 0            &&
              publicKey.gcd(phi).equals(one);
   }


   BigInteger encrypt(BigInteger message) {
      return message.modPow(publicKey, modulus);
   }

   BigInteger decrypt(BigInteger encrypted) {
      return encrypted.modPow(privateKey, modulus);
   }

   public String toString() {
      return
          "public  = " + publicKey  + "\n" +
          "private = " + privateKey + "\n" +
          "modulus = " + modulus;
   }
 
}
