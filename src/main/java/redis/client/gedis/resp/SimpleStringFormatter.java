package redis.client.gedis.resp;

import static redis.client.gedis.resp.RESPProtocolConstants.OK_RESPONSE;

/**
 * Created by nischal.k on 16/05/17.
 */
public class SimpleStringFormatter implements Formatter{
    @Override
    public String format(String respString) {
        int startIndex = respString.indexOf('\n', OK_RESPONSE.length());
        return respString.substring(startIndex+1, respString.length() - 2);
    }
}
