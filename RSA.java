import java.math.BigInteger;

final class RSA {

   final Key privateKey;
   final Key publicKey;

   RSA(Key publicKey, Key privateKey) {
      this.publicKey  = publicKey;
      this.privateKey = privateKey;
   }

   static RSA bitLength(int i) {
      return new RSAGenerator(i).generate();
   }

   BigInteger encrypt(BigInteger message) {
      return publicKey.map(message);
   }

   boolean verify(BigInteger message, BigInteger signature) {
      return message.equals(publicKey.map(signature));
   }

   BigInteger decrypt(BigInteger encrypted) {
      return privateKey.map(encrypted);
   }

   BigInteger sign(BigInteger encrypted) {
      return privateKey.map(encrypted);
   }

   public String toString() {
      return "public=" + publicKey + " private=" + privateKey;
   }

}
