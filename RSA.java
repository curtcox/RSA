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
      BigInteger publicKey;
      do publicKey = new BigInteger(phi.bitLength(), random);
      while (unsuitable(publicKey,phi));
      return publicKey;
   }

   static boolean unsuitable(BigInteger publicKey,BigInteger phi) {
      return   publicKey.compareTo(BigInteger.ONE) <= 0 ||
               publicKey.compareTo(phi) >= 0            ||
              !publicKey.gcd(phi).equals(BigInteger.ONE);
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
