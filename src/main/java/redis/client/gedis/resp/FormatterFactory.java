package redis.client.gedis.resp;

import redis.client.gedis.exception.GedisWrongTypeException;

import static redis.client.gedis.resp.RESPProtocolConstants.*;

/**
 * Created by nischal.k on 16/05/17.
 */
public class FormatterFactory {
    private final BulkStringFormatter bulkStringFormatter;
    private final ErrorStringFormatter errorStringFormatter;
    private final SimpleStringFormatter simpleStringFormatter;

    public FormatterFactory() {
        simpleStringFormatter = new SimpleStringFormatter();
        errorStringFormatter = new ErrorStringFormatter();
        bulkStringFormatter = new BulkStringFormatter();
    }

    public Object format(String respString) {
        if(NULL_RESPONSE.equals(respString)) {
            return null;
        }
        if(OK_RESPONSE.equals(respString)) {
            return new StringBuilder().toString();
        }
        if(respString.charAt(0)== DOLLAR){
            return bulkStringFormatter.format(respString);
        }
        if(respString.startsWith(OK_RESPONSE)) {
            return simpleStringFormatter.format(respString);
        }
        if(respString.startsWith(WRONG_TYPE)) {
            String errorMsg = errorStringFormatter.format(respString);
            throw new GedisWrongTypeException(errorMsg);
        }
        throw new IllegalArgumentException(respString+ " is illegal");
    }
}
