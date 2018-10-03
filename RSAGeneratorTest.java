import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RSAGeneratorTest {

    @Test
    public void can_create() {
        new RSAGenerator(10);
    }

    @Test
    public void key_sizes_80() {
        assertKeyBitSizes(80);
    }

    @Test
    public void key_sizes_800() {
        assertKeyBitSizes(800);
    }

    @Test
    public void key_sizes_1024() {
        assertKeyBitSizes(1024);
    }

    @Test
    public void key_sizes_1600() {
        assertKeyBitSizes(1600);
    }

    @Test
    public void key_sizes_3200() {
        assertKeyBitSizes(3200);
    }

    void assertKeyBitSizes(int bits) {
        RSAGenerator generator = new RSAGenerator(bits);
        RSA rsa = generator.generate();
        int size = bits / 8;
        assertSize(size,rsa.publicKey.key);
        assertSize(size,rsa.privateKey.key);
    }

    private void assertSize(int size, Key key) {
        assertTrue(Math.abs(size-key.key.toByteArray().length)<2);
        assertTrue(Math.abs(size-key.modulus.toByteArray().length)<2);
    }

}
