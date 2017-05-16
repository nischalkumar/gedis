package redis.client.gedis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import redis.client.gedis.exception.GedisConnectionException;

import java.io.IOException;

/**
 * Created by nischal.k on 16/05/17.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class RedisConnectionTest {

    private RedisConnection redisConnection;

    @Before
    public void before() {
        redisConnection = new RedisConnection();
    }

    @After
    public void after() throws IOException {
        redisConnection.close();
    }

    @Test
    public  void testCommand() {
        redisConnection.sendCommand(RedisCommand.SET, "hari_om","lalalal");
//        String setCommand = redisConnection.processReply();
        redisConnection.sendCommand(RedisCommand.GET,"hari_om");
        String getCommand = redisConnection.processReply();
        Assert.assertEquals("+OK\r\n$7\r\nlalalal\r\n", getCommand);
    }

    @Test
    public void checkCloseable() {
        redisConnection.connect();
        try {
            redisConnection.close();
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test(expected = GedisConnectionException.class)
    public void invalidHost() {
        RedisConnection redisConnection = new RedisConnection("aaaaaaaaa",3333);
        redisConnection.connect();
    }

    @Test(expected = GedisConnectionException.class)
    public void invalidPort() {
        RedisConnection redisConnection = new RedisConnection("localhost",3333);
        redisConnection.connect();
    }

    @Test(expected = GedisConnectionException.class)
    public void verySmallTimeout() {
        RedisConnection redisConnection = new RedisConnection("localhost",3333, 1);
        redisConnection.connect();
    }
}