import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RSATest {

    RSA rsa() {
        return rsa(100);
    }

    RSA rsa(int bitLength) {
        return RSA.bitLength(bitLength);
    }

    @Test
    public void can_create_10() {
        assertNotNull(rsa(10));
    }

    @Test
    public void can_create_100() {
        assertNotNull(rsa(100));
    }

    @Test
    public void can_create_1000() {
        assertNotNull(rsa(1000));
    }

    @Test
    public void can_create_2000() {
        assertNotNull(rsa(2000));
    }

    @Test
    public void message_survives_round_trip() {
        BigInteger message = new BigInteger("8675309");
        RSA rsa = rsa();
        assertEquals(message,rsa.decrypt(rsa.encrypt(message)));
    }

    @Test
    public void can_use_to_sign() {
        BigInteger message = new BigInteger("8675309");
        RSA rsa = rsa();
        BigInteger signature = rsa.sign(message);
        assertTrue(rsa.verify(message,signature));
    }

    @Test
    public void only_key_produces_valid_signature() {
        BigInteger message = new BigInteger("8675309");
        RSA rsa = rsa();
        BigInteger signature = rsa.sign(message);
        assertFalse(rsa().verify(message,signature));
    }

    @Test
    public void only_message_survives_round_trip() {
        BigInteger message = new BigInteger("8675309");
        BigInteger other = new BigInteger("8675310");
        RSA rsa = rsa();
        assertNotEquals(other,rsa.decrypt(rsa.encrypt(message)));
    }

    @Test
    public void public_key_is_always_different() {
        Set keys = new HashSet();
        int size = 100;
        for (int i=0; i<size; i++) {
            RSA rsa = rsa();
            keys.add(rsa.publicKey);
        }
        assertEquals(size,keys.size());
    }

    @Test
    public void private_key_is_always_different() {
        Set keys = new HashSet();
        int size = 100;
        for (int i=0; i<size; i++) {
            RSA rsa = rsa();
            keys.add(rsa.privateKey);
        }
        assertEquals(size,keys.size());
    }

    @Test
    public void private_key_modulus_is_always_different() {
        Set keys = new HashSet();
        int size = 100;
        for (int i=0; i<size; i++) {
            RSA rsa = rsa();
            keys.add(rsa.privateKey.key.modulus);
        }
        assertEquals(size,keys.size());
    }

    @Test
    public void public_key_modulus_is_always_different() {
        Set keys = new HashSet();
        int size = 100;
        for (int i=0; i<size; i++) {
            RSA rsa = rsa();
            keys.add(rsa.publicKey.key.modulus);
        }
        assertEquals(size,keys.size());
    }

}
