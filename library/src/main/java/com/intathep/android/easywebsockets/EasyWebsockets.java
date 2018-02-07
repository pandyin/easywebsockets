package com.intathep.android.easywebsockets;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class EasyWebsockets extends Websockets {

    private final long readTimeOut;
    private final long writeTimeout;
    private final long connectTimeout;
    private final long pingInterval;
    private final TimeUnit timeUnit;
    private final boolean retryOnConnectionFailure;

    public EasyWebsockets(String url,
                          long readTimeOut,
                          long writeTimeout,
                          long connectTimeout,
                          long pingInterval,
                          TimeUnit timeUnit,
                          boolean retryOnConnectionFailure) {
        super(url);
        this.readTimeOut = readTimeOut;
        this.writeTimeout = writeTimeout;
        this.connectTimeout = connectTimeout;
        this.pingInterval = pingInterval;
        this.timeUnit = timeUnit;
        this.retryOnConnectionFailure = retryOnConnectionFailure;
    }

    @Override
    protected OkHttpClient client() throws Exception {
        return new OkHttpClient.Builder()
                .readTimeout(readTimeOut, timeUnit)
                .writeTimeout(writeTimeout, timeUnit)
                .connectTimeout(connectTimeout, timeUnit)
                .pingInterval(pingInterval, timeUnit)
                .retryOnConnectionFailure(retryOnConnectionFailure)
                .build();
    }

    public static Builder builder() {
        return new EasyWebsockets.Builder();
    }

    public static class Builder {

        private long readTimeOut = 10_000;
        private long writeTimeout = 10_000;
        private long connectTimeout = 10_000;
        private long pingInterval = 0;
        private TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        private boolean retryOnConnectionFailure = true;

        private Builder() {
        }

        public Builder readTimeOut(long readTimeOut) {
            this.readTimeOut = readTimeOut;
            return this;
        }

        public Builder writeTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder pingInterval(long pingInterval) {
            this.pingInterval = pingInterval;
            return this;
        }

        public Builder timeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public Builder retryOnConnectionFailure(boolean retryOnConnectionFailure) {
            this.retryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

        public EasyWebsockets build(String url) {
            return new EasyWebsockets(url,
                    readTimeOut,
                    writeTimeout,
                    connectTimeout,
                    pingInterval,
                    timeUnit,
                    retryOnConnectionFailure);
        }
    }
}
