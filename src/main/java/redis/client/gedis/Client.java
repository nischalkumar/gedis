package redis.client.gedis;

import redis.client.gedis.resp.FormatterFactory;

import java.io.IOException;

/**
 * Created by nischal.k on 16/05/17.
 */
public class Client extends RedisConnection implements Command{
    private final FormatterFactory respFormatter;

    public Client() {
        respFormatter = new FormatterFactory();
    }

    public Client(String host, int port) {
        super(host, port);
        respFormatter = new FormatterFactory();
    }

    public Client(String host, int port, int connectionTimeout) {
        super(host, port, connectionTimeout);
        respFormatter = new FormatterFactory();
    }

    @Override
    public void set(String key, String value) {
        sendCommand(RedisCommand.SET, key, value);
    }

    @Override
    public synchronized String get(String key) {
        sendCommand(RedisCommand.GET, key);
        return (String)respFormatter.format(processReply());
    }

    @Override
    public void close() {
        try {
            super.close();
        } catch (IOException e) {
            //eat exception as closing
        }
    }
}
