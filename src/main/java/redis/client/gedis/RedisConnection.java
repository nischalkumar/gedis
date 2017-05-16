package redis.client.gedis;

import redis.client.gedis.exception.GedisConnectionException;
import redis.client.gedis.resp.RESPProtocalHandler;
import redis.client.gedis.resp.RESPProtocolConstants;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by nischal.k on 15/05/17.
 */
public class RedisConnection implements Closeable {
    private final String host;
    private final int port;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;
    private final int connectionTimeout;
    private final RESPProtocalHandler protocalHandler;

    public RedisConnection() {
        this.host= RESPProtocolConstants.DEFAULT_HOST;
        this.port= RESPProtocolConstants.DEFAULT_PORT;
        this.connectionTimeout = RESPProtocolConstants.DEFAULT_TIMEOUT;
        protocalHandler = new RESPProtocalHandler();
    }

    public RedisConnection(String host, int port) {
        this.host = host;
        this.port = port;
        this.connectionTimeout = RESPProtocolConstants.DEFAULT_TIMEOUT;
        protocalHandler = new RESPProtocalHandler();
    }

    public RedisConnection(String host, int port, int connectionTimeout) {
        this.host = host;
        this.port = port;
        this.connectionTimeout = connectionTimeout;
        protocalHandler = new RESPProtocalHandler();
    }


    public boolean isConnected() {
        return socket != null && socket.isBound() && !socket.isClosed() && socket.isConnected()
                && !socket.isInputShutdown() && !socket.isOutputShutdown();
    }

    public void close() throws IOException {
        disconnect();
    }

    public void sendCommand(final RedisCommand command, final String... args) {
        connect();
        try {
            protocalHandler.sendCommand(outputStream, command.name(), args);
        } catch (IOException e) {
            String error = null;
            try {
                error = protocalHandler.readReply(inputStream, connectionTimeout);
            } catch (IOException e1) {
                //eat the exception as this is error
            }
            if(error!=null && error.charAt(0)=='-') {
                throw new GedisConnectionException(error, e);
            }
        }
    }

    public String processReply() {
        try {
            return protocalHandler.readReply(inputStream, connectionTimeout);
        } catch (IOException e) {
                throw new GedisConnectionException("IOException while reading response", e);
        }
    }

    public synchronized void connect() {
        if (!isConnected()) {
            try {
                socket = new Socket();
                socket.setReuseAddress(true);
                socket.setKeepAlive(true);
                socket.setTcpNoDelay(true);
                socket.setSoLinger(true, 0);
                socket.connect(new InetSocketAddress(host, port), connectionTimeout);
                socket.setSoTimeout(connectionTimeout);

                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
            } catch (IOException ex) {
                throw new GedisConnectionException("Failed connecting to host "
                        + host + ":" + port, ex);
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                outputStream.flush();
                socket.close();
            } catch (IOException ex) {
                throw new GedisConnectionException(ex);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    //ignoring as new connection will create new socket for next time.
                }
            }
        }
    }
}
