package redis.client.gedis;

import redis.client.gedis.resp.RespFormatter;

/**
 * Created by nischal.k on 16/05/17.
 */
public class Client extends RedisConnection implements Command{
    private final RespFormatter respFormatter;

    public Client() {
        respFormatter = new RespFormatter();
    }

    public Client(String host, int port) {
        super(host, port);
        respFormatter = new RespFormatter();
    }

    public Client(String host, int port, int connectionTimeout) {
        super(host, port, connectionTimeout);
        respFormatter = new RespFormatter();
    }

    @Override
    public void set(String key, String value) {
        sendCommand(RedisCommand.SET, key, value);
    }

    @Override
    public String get(String key) {
        sendCommand(RedisCommand.GET, key);
        return respFormatter.format(processReply());
    }
}
