package redis.client.gedis.resp;

import static redis.client.gedis.resp.RESPProtocolConstants.DOLLAR_BYTE;
import static redis.client.gedis.resp.RESPProtocolConstants.NULL_RESPONSE;
import static redis.client.gedis.resp.RESPProtocolConstants.OK_RESPONSE;

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
        if(request.startsWith(OK_RESPONSE)) {
            return parseSimpleString(request);
        }
        return request;
    }

    private String parseSimpleString(String resultString) {
        int startIndex = resultString.indexOf('\n', OK_RESPONSE.length());
        return resultString.substring(startIndex+1, resultString.length() - 2);
    }
}
