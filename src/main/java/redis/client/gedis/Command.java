package redis.client.gedis;

/**
 * Created by nischal.k on 16/05/17.
 */
public interface Command {
    void set(String key, String value);

    String get(String key);
}
