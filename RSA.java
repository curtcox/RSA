import java.math.BigInteger;
import java.security.SecureRandom;

final class RSA {


   private final BigInteger privateKey;
   private final BigInteger publicKey;
   private final BigInteger modulus;

   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   private RSA(BigInteger p, BigInteger q, BigInteger phi) {
      modulus    = p.multiply(q);                                  
      publicKey  = new BigInteger("65537");     // common value in practice = 2^16 + 1
      privateKey = publicKey.modInverse(phi);
   }

   // generate an N-bit (roughly) public and private key
   static RSA bitLength(int bits) {
      int bitLength = bits/2;
      int certainlyPrime = 100;
      BigInteger p = new BigInteger(bitLength, certainlyPrime, random);
      BigInteger q = new BigInteger(bitLength, certainlyPrime, random);
      BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
      return new RSA(p,q,phi);
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
