package redis.client.gedis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by nischal.k on 16/05/17.
 */
public class ClientTest {
    Client client;

    @Before
    public void before() {
        client = new Client();
    }

    @After
    public void close() {
        client.close();
    }

    @Test
    public void set() throws Exception {
        client.set("foo","bar");
        Assert.assertEquals("bar", client.get("foo"));
    }

    @Test
    public void getMissingKey() throws Exception {
        String result = client.get("hari_om_hari_om");
        Assert.assertEquals(null, result);
    }

}