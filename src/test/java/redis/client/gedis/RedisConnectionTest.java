package redis.client.gedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

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
        System.out.println(getCommand);
    }

    @Test
    public  void testCommandGETNULL() {
//        String setCommand = redisConnection.processReply();
        redisConnection.sendCommand(RedisCommand.GET,"fooAAAAA");
        String getCommand = redisConnection.processReply();
        System.out.println(getCommand);
    }

}