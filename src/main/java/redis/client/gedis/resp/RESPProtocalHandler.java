package redis.client.gedis.resp;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static redis.client.gedis.resp.RESPProtocolConstants.*;

/**
 * Created by nischal.k on 15/05/17.
 */
public class RESPProtocalHandler {
    private static final int BUFFER_SIZE=1024;


    public void sendCommand(OutputStream outputStream, final String command, final String... args) throws IOException {
        writeArrays(outputStream, args.length+1);
        writeString(outputStream, command);
        for (String arg : args) {
            writeString(outputStream, arg);
        }
    }

    public String readReply(InputStream inputStream, int connectionTimeout) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        return readInputStream(bis, connectionTimeout);
    }

    private void writeArrays(OutputStream outputStream, int length) throws IOException {
        IOUtils.write(ASTERISK +""+length+CLRF, outputStream, RESPProtocolConstants.DEFAULT_CHARSET);
    }

    private void writeString(OutputStream outputSteam, String data) throws IOException {
        String var = DOLLAR +""+ data.length() + CLRF + data + CLRF;
        IOUtils.write(var, outputSteam, RESPProtocolConstants.DEFAULT_CHARSET);
    }

    private String readInputStream(BufferedInputStream in, int connectionTimeout) throws IOException {
        byte[] contents = new byte[BUFFER_SIZE];
        String strFileContents = "";

        long timeoutExpiredMs = System.currentTimeMillis() + connectionTimeout;
        while(in.available()==0) {
            //wait till input is available
            if (System.currentTimeMillis() >= timeoutExpiredMs) {
                // we timed out
                throw new IOException("could not input fron redis");
            }
        }

        while (in.available() > 0) {
            strFileContents += new String(contents, 0, in.read(contents));
        }
        return strFileContents;
    }
}
