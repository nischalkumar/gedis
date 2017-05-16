package redis.client.gedis.resp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by nischal.k on 16/05/17.
 */
public class RespFormatterTest {
    private RespFormatter respFormatter;

    @Before
    public void before() {
        respFormatter = new RespFormatter();
    }

    @Test
    public void formatBUlkString() {
        String bulkString="$6\r\nfoobar\r\n";
        String response = respFormatter.format(bulkString);
        Assert.assertEquals("foobar", response);
    }

    @Test
    public void formatEmptyBulkString() {
        String bulkString="$0\r\n\r\n";
        String response = respFormatter.format(bulkString);
        Assert.assertEquals("", response);
    }

    @Test
    public void formatNullBulkString() {
        String bulkString="$-1\r\n";
        String response = respFormatter.format(bulkString);
        Assert.assertEquals(null, response);
    }

    @Test
    public void formatSimpleStrings() {
        String simpleString="+OK\r\n";
        String response = respFormatter.format(simpleString);
        Assert.assertEquals("", response);
    }
}