package redis.client.gedis.exception;

/**
 * Created by nischal.k on 16/05/17.
 */
public class GedisWrongTypeException extends RuntimeException{
    public GedisWrongTypeException() {
    }

    public GedisWrongTypeException(String message) {
        super(message);
    }

    public GedisWrongTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GedisWrongTypeException(Throwable cause) {
        super(cause);
    }

    public GedisWrongTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
