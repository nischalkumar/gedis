package redis.client.gedis.resp;

import redis.client.gedis.exception.GedisWrongTypeException;

import static redis.client.gedis.resp.RESPProtocolConstants.*;

/**
 * Created by nischal.k on 16/05/17.
 */
public class RespFormatter {
    public String format(String request){
        if(NULL_RESPONSE.equals(request)) {
            return null;
        }
        if(OK_RESPONSE.equals(request)) {
            return new StringBuilder().toString();
        }
        if(request.charAt(0)==DOLLAR_BYTE){
            return parseBulkString(request);
        }
        if(request.startsWith(OK_RESPONSE)) {
            return parseSimpleString(request);
        }
        if(request.startsWith(WRONG_TYPE)) {
            String errorMsg = parseErrorMsg(request);
           throw new GedisWrongTypeException(errorMsg);
        }
        throw new IllegalArgumentException(request+ " is illegal");
    }

    private String parseErrorMsg(String request) {
        int startIndex = request.indexOf("-");
        return request.substring(startIndex+1, request.length()-2);
    }

    private String parseSimpleString(String resultString) {
        int startIndex = resultString.indexOf('\n', OK_RESPONSE.length());
        return resultString.substring(startIndex+1, resultString.length() - 2);
    }

    private String parseBulkString(String resultString){
        int startIndex = resultString.indexOf('\n');
        return resultString.substring(startIndex+1, resultString.length()-2);
    }
}
