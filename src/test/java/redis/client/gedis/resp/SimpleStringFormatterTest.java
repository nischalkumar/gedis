package redis.client.gedis.resp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by nischal.k on 17/05/17.
 */
public class SimpleStringFormatterTest {
    SimpleStringFormatter simpleStringFormatter;

    @Before
    public void before() {
        simpleStringFormatter = new SimpleStringFormatter();
    }

    @Test
    public void simpleString() {
        String simpleString="+OK\r\n$0\r\n\r\n";
        String response = (String)simpleStringFormatter.format(simpleString);
        Assert.assertEquals("", response);
    }

}