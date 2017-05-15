package redis.client.gedis;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by nischal.k on 15/05/17.
 */
public class RESPProtocolConstants {
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 6379;
    public static final int DEFAULT_TIMEOUT = 2000;

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static final byte DOLLAR_BYTE = '$';
    public static final byte ASTERISK_BYTE = '*';
    public static final byte PLUS_BYTE = '+';
    public static final byte MINUS_BYTE = '-';
    public static final byte COLON_BYTE = ':';

    private final byte[] clrf= new byte[] {'\r','\n'};
    public static final byte[] ASTERISK_BYTE_CLRF = {'*','\r','\n'};
}
