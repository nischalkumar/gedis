package redis.client.gedis;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static redis.client.gedis.RESPProtocolConstants.ASTERISK_BYTE;
import static redis.client.gedis.RESPProtocolConstants.DEFAULT_CHARSET;

/**
 * Created by nischal.k on 15/05/17.
 */
public class RESPProtocalHandler {


    public void sendCommand(OutputStream outputStream, final String command, final String... args) throws IOException {
        outputStream.write(ASTERISK_BYTE);
        writeCLRF(outputStream,Integer.toString(args.length+1));
        writeString(outputStream, command);
        for(String arg: args) {
            writeString(outputStream, arg);
        }
    }

    private void writeCLRF(OutputStream outputStream, String data) throws IOException {
        data= data+"\r\n";
        IOUtils.write(data, outputStream, DEFAULT_CHARSET);
    }

    private void writeString(OutputStream outputSteam, String data) throws IOException {
        String var = "$"+data.length()+"\r\n"+data+"\r\n";
        IOUtils.write(var, outputSteam, DEFAULT_CHARSET);
    }

    public String readErrorLineIfPossible(InputStream is){
        try {
            return IOUtils.toString(is, DEFAULT_CHARSET);
        } catch (IOException e) {
            //eat silently error msg
            return null;
        }
    }

    public String readReply(InputStream inputStream) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        bis.mark(1);
        int byte1 = bis.read();
        bis.reset();
        if(byte1=='$') {
            String resultString = getString(bis);
            if("$-1\r\n".equals(resultString)) {
                return null;
            }
            return parseSimpleString(resultString);
        }
        if(byte1=='+')
        {
            String resultString = getString(bis);
            return parseSimpleString(resultString);
        }
        return null;
    }


    private String parseSimpleString(String resultString) {
        return  resultString.substring(resultString.lastIndexOf('\n', resultString.length()-2)+1, resultString.length()-2);
    }

    String getString(BufferedInputStream in) throws IOException {
        byte[] contents = new byte[1024];

        int bytesRead = 0;
        String strFileContents="";
        while(in.available()>0 && (bytesRead = in.read(contents)) != -1) {
            strFileContents += new String(contents, 0, bytesRead);
        }
        return strFileContents;
    }
}
