package redis.client.gedis.resp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by nischal.k on 17/05/17.
 */
public class BulkStringFormatterTest {
    BulkStringFormatter bulkStringFormatter;

    @Before
    public void before() {
        bulkStringFormatter = new BulkStringFormatter();
    }

    @Test
    public void format() throws Exception {
        String bulkString="$6\r\nhariom\r\n";
        String response = (String) bulkStringFormatter.format(bulkString);
        Assert.assertEquals("hariom", response);
    }

    @Test
    public void formatEmpty() throws Exception {
        String bulkString="$0\r\n\r\n";
        String response = (String) bulkStringFormatter.format(bulkString);
        Assert.assertEquals("", response);
    }

}