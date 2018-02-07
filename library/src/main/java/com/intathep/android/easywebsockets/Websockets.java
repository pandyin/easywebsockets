package com.intathep.android.easywebsockets;

import android.support.annotation.NonNull;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import timber.log.Timber;

public abstract class Websockets {

    private Request.Builder requestBuilder;
    private WebSocket socket;

    private BehaviorSubject<EasyWebsocketsConnectionState> connectionState;
    private PublishSubject<String> message;

    Websockets(@NonNull String url) {
        requestBuilder = new Request.Builder();
        requestBuilder.url(url);

        connectionState = BehaviorSubject.create(EasyWebsocketsConnectionState.CLOSED);
        message = PublishSubject.create();
    }

    protected abstract OkHttpClient client() throws Exception;

    public void headers(@NonNull String... namesAndValues) {
        requestBuilder.headers(Headers.of(namesAndValues));
    }

    public void open() {
        try {
            Request request = requestBuilder.build();
            socket = client().newWebSocket(request, new EasyWebsocketsListener(connectionState, message));
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    public void close() {
        if (socket != null) {
            socket.close(1000, "damn!");
        }
    }

    public void immediatelyClose() {
        if (socket != null) {
            socket.cancel();
        }
    }

    public void send(String text) {
        if (socket != null) {
            socket.send(text);
        }
    }

    public Observable<EasyWebsocketsConnectionState> connectionState() {
        return connectionState;
    }

    public Observable<String> message() {
        return message;
    }
}
