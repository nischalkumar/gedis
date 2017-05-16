package redis.client.gedis.resp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by nischal.k on 17/05/17.
 */
public class ErrorStringFormatterTest {
    ErrorStringFormatter errorStringFormatter;

    @Before
    public void before() {
        errorStringFormatter = new ErrorStringFormatter();
    }
    @Test
    public void format() throws Exception {
        String wrongTypeString = ":0\r\n-WRONGTYPE Operation against a key holding the wrong kind of value\r\n";
        String response = errorStringFormatter.format(wrongTypeString);
        Assert.assertEquals("WRONGTYPE Operation against a key holding the wrong kind of value", response);
    }

}