import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RSATest {

    @Test
    public void can_create() {
        assertNotNull(RSA.bitLength(10));
    }

    @Test
    public void message_survives_round_trip() {
        BigInteger message = new BigInteger("8675309");
        RSA rsa = RSA.bitLength(1000);
        assertEquals(message,rsa.decrypt(rsa.encrypt(message)));
    }

    @Test
    public void only_message_survives_round_trip() {
        BigInteger message = new BigInteger("8675309");
        BigInteger other = new BigInteger("8675310");
        RSA rsa = RSA.bitLength(1000);
        assertNotEquals(other,rsa.decrypt(rsa.encrypt(message)));
    }

    @Test
    public void public_key_is_always_different() {
        Set keys = new HashSet();
        int size = 100;
        for (int i=0; i<size; i++) {
            RSA rsa = RSA.bitLength(100);
            keys.add(rsa.publicKey);
        }
        assertEquals(size,keys.size());
    }

    @Test
    public void private_key_is_always_different() {
        Set keys = new HashSet();
        int size = 100;
        for (int i=0; i<size; i++) {
            RSA rsa = RSA.bitLength(100);
            keys.add(rsa.privateKey);
        }
        assertEquals(size,keys.size());
    }

    @Test
    public void modulus_is_always_different() {
        Set keys = new HashSet();
        int size = 100;
        for (int i=0; i<size; i++) {
            RSA rsa = RSA.bitLength(100);
            keys.add(rsa.modulus);
        }
        assertEquals(size,keys.size());
    }

}
