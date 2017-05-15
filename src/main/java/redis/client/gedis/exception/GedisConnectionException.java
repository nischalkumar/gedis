package redis.client.gedis.exception;

/**
 * Created by nischal.k on 15/05/17.
 */
public class GedisConnectionException extends RuntimeException{
    public GedisConnectionException() {
    }

    public GedisConnectionException(String message) {
        super(message);
    }

    public GedisConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public GedisConnectionException(Throwable cause) {
        super(cause);
    }

    public GedisConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
