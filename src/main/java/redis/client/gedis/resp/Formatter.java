package redis.client.gedis.resp;

/**
 * Created by nischal.k on 16/05/17.
 */
public interface Formatter {
    String format(String respString);
}
