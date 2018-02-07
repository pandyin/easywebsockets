package com.intathep.android.easywebsockets;

import android.support.annotation.Nullable;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class EasyWebsocketsListener extends WebSocketListener {

    private final BehaviorSubject<EasyWebsocketsConnectionState> connectionState;
    private final PublishSubject<String> message;

    EasyWebsocketsListener(BehaviorSubject<EasyWebsocketsConnectionState> connectionState, PublishSubject<String> message) {
        this.connectionState = connectionState;
        this.message = message;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        connectionState.onNext(EasyWebsocketsConnectionState.OPEN);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        message.onNext(text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {

    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        connectionState.onNext(EasyWebsocketsConnectionState.CLOSING);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        connectionState.onNext(EasyWebsocketsConnectionState.CLOSED);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
        connectionState.onNext(EasyWebsocketsConnectionState.CLOSED);
    }
}
