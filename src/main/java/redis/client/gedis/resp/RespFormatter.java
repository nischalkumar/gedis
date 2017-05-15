package redis.client.gedis.resp;

import static redis.client.gedis.resp.RESPProtocolConstants.DOLLAR_BYTE;
import static redis.client.gedis.resp.RESPProtocolConstants.NULL_RESPONSE;

/**
 * Created by nischal.k on 16/05/17.
 */
public class RespFormatter {
    public String format(String request){
        if(NULL_RESPONSE.equals(request)) {
            return null;
        }
        if(request.charAt(0)==DOLLAR_BYTE){
            return parseSimpleString(request);
        }
        return request;
    }

    private String parseSimpleString(String resultString) {
        return resultString.substring(resultString.lastIndexOf('\n', resultString.length() - 2) + 1, resultString.length() - 2);
    }
}