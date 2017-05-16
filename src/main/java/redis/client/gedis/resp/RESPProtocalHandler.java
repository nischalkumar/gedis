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

    public String readReply(InputStream inputStream) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        return getString(bis);
    }

    private void writeArrays(OutputStream outputStream, int length) throws IOException {
        IOUtils.write(ASTERISK_BYTE+""+length+CLRF, outputStream, RESPProtocolConstants.DEFAULT_CHARSET);
    }

    private void writeString(OutputStream outputSteam, String data) throws IOException {
        String var = DOLLAR_BYTE +""+ data.length() + CLRF + data + CLRF;
        IOUtils.write(var, outputSteam, RESPProtocolConstants.DEFAULT_CHARSET);
    }

    private String getString(BufferedInputStream in) throws IOException {
        byte[] contents = new byte[BUFFER_SIZE];

        int bytesRead = 0;
        String strFileContents = "";
        while (in.available() > 0 && (bytesRead = in.read(contents)) != -1) {
            strFileContents += new String(contents, 0, bytesRead);
        }
        return strFileContents;
    }
}
