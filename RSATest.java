import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RSATest {

    @Test
    public void can_create() {
        assertNotNull(new RSA(10));
    }
}
