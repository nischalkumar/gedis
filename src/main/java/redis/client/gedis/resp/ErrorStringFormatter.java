package redis.client.gedis.resp;

/**
 * Created by nischal.k on 16/05/17.
 */
public class ErrorStringFormatter implements Formatter {
    @Override
    public String format(String respString) {
        int startIndex = respString.indexOf("-");
        return respString.substring(startIndex+1, respString.length()-2);
    }
}
