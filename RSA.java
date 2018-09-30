import java.math.BigInteger;

final class RSA {

   final BigInteger privateKey;
   final BigInteger publicKey;
   final BigInteger modulus;

   RSA(BigInteger publicKey, BigInteger privateKey, BigInteger modulus) {
      this.publicKey  = publicKey;
      this.privateKey = privateKey;
      this.modulus    = modulus;
   }

   static RSA bitLength(int i) {
      return new RSAGenerator(i).generate();
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
