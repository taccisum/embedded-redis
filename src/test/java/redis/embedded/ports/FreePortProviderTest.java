package redis.embedded.ports;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tac
 * @since 08/10/2018
 */
public class FreePortProviderTest {
    @Test
    public void next() throws Exception {
        FreePortProvider provider = new FreePortProvider();
        for (int i = 0; i < 100; i++) {
            int port = provider.next();
            Assert.assertTrue(port >= 40000 && port <= 60000);
        }
    }

    @Test
    public void isFreePort() throws Exception {
        Assert.assertTrue(FreePortProvider.isFreePort(60000));
    }
}