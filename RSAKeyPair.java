import java.math.BigInteger;

final class RSAKeyPair {

   final PrivateKey privateKey;
   final PublicKey publicKey;

   RSAKeyPair(PublicKey publicKey, PrivateKey privateKey) {
      this.publicKey  = publicKey;
      this.privateKey = privateKey;
   }

   static RSAKeyPair bitLength(int i) {
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
