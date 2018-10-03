import java.math.BigInteger;

final class RSA {

   final PrivateKey privateKey;
   final PublicKey publicKey;

   RSA(PublicKey publicKey, PrivateKey privateKey) {
      this.publicKey  = publicKey;
      this.privateKey = privateKey;
   }

   static RSA bitLength(int i) {
      return new RSAGenerator(i).generate();
   }

   BigInteger encrypt(BigInteger message) {
      return publicKey.encrypt(message);
   }

   boolean verify(BigInteger message, BigInteger signature) {
      return publicKey.verify(message,signature);
   }

   BigInteger decrypt(BigInteger encrypted) {
      return privateKey.decrypt(encrypted);
   }

   BigInteger sign(BigInteger encrypted) {
      return privateKey.sign(encrypted);
   }

   public String toString() {
      return "public=" + publicKey + " private=" + privateKey;
   }

}
