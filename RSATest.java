import org.junit.jupiter.api.Test;

import java.math.BigInteger;

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

}
