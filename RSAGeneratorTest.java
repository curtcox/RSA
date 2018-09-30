import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RSAGeneratorTest {

    @Test
    public void can_create() {
        new RSAGenerator(10);
    }

    @Test
    public void key_sizes_80() {
        RSAGenerator generator = new RSAGenerator(80);
        RSA rsa = generator.generate();
        assertSize(10,rsa.publicKey);
        assertSize(10,rsa.privateKey);
    }

    @Test
    public void key_sizes_800() {
        RSAGenerator generator = new RSAGenerator(800);
        RSA rsa = generator.generate();
        assertSize(100,rsa.publicKey);
        assertSize(100,rsa.privateKey);
    }

    @Test
    public void key_sizes_1024() {
        RSAGenerator generator = new RSAGenerator(1024);
        RSA rsa = generator.generate();
        assertSize(128,rsa.publicKey);
        assertSize(128,rsa.privateKey);
    }

    @Test
    public void key_sizes_1600() {
        RSAGenerator generator = new RSAGenerator(1600);
        RSA rsa = generator.generate();
        assertSize(200,rsa.publicKey);
        assertSize(200,rsa.privateKey);
    }

    @Test
    public void key_sizes_3200() {
        RSAGenerator generator = new RSAGenerator(3200);
        RSA rsa = generator.generate();
        assertSize(400,rsa.publicKey);
        assertSize(400,rsa.privateKey);
    }

    private void assertSize(int size, Key key) {
        assertTrue(Math.abs(size-key.key.toByteArray().length)<2);
        assertTrue(Math.abs(size-key.modulus.toByteArray().length)<2);
    }

}
