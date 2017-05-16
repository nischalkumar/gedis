package redis.client.gedis.resp;

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

    public static final char DOLLAR_BYTE = '$';
    public static final char ASTERISK_BYTE = '*';
    public static final char PLUS_BYTE = '+';
    public static final char MINUS_BYTE = '-';
    public static final char COLON_BYTE = ':';

    public static final String CLRF = "\r\n";
    public static final String NULL_RESPONSE = "$-1\r\n";
    public static final String OK_RESPONSE = "+OK\r\n";
    public static final String WRONG_TYPE=":0\r\n-";

}
