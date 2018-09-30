/******************************************************************************
 *  Compilation:  javac RSA.java
 *  Execution:    java RSA N
 *  
 *  Generate an N-bit public and private RSA key and use to encrypt
 *  and decrypt a random message.
 * 
 *  % java RSA 50
 *  public  = 65537
 *  private = 553699199426609
 *  modulus = 825641896390631
 *  message   = 48194775244950
 *  encrpyted = 321340212160104
 *  decrypted = 48194775244950
 *
 *  Known bugs (not addressed for simplicity)
 *  -----------------------------------------
 *  - It could be the case that the message >= modulus. To avoid, use
 *    a do-while loop to generate key until modulus happen to be exactly N bits.
 *
 *  - It's possible that gcd(phi, publicKey) != 1 in which case
 *    the key generation fails. This will only happen if phi is a
 *    multiple of 65537. To avoid, use a do-while loop to generate
 *    keys until the gcd is 1.
 *
 ******************************************************************************/

import java.math.BigInteger;
import java.security.SecureRandom;

final class Main {

   private final static SecureRandom random = new SecureRandom();


   public static void main(String[] args) {
      int n = Integer.parseInt(args[0]);
      RSA key = new RSA(n);
      println(key);
 
      // create random message, encrypt and decrypt
      BigInteger message = new BigInteger(n-1, random);

      //// create message by converting string to integer
      // String s = "test";
      // byte[] bytes = s.getBytes();
      // BigInteger message = new BigInteger(bytes);

      BigInteger encrypt = key.encrypt(message);
      BigInteger decrypt = key.decrypt(encrypt);
      println("message   = " + message);
      println("encrypted = " + encrypt);
      println("decrypted = " + decrypt);
   }

   static void println(Object message) {
      System.out.println(message);
   }
}
