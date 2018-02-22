package com.intathep.android.easywebsockets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class TestActivity extends AppCompatActivity {

    private final static String URL = "wss://echo.websocket.org";

    private EasyWebsockets websockets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        websockets = EasyWebsockets.builder()
                .writeTimeout(10000)
                .readTimeOut(10000)
                .connectTimeout(10000)
                .pingInterval(10000)
                .timeUnit(TimeUnit.MILLISECONDS)
                .build(URL);

        websockets.headers("deviceModel", "Xiaomi A1",
                "deviceVersion", "");

        websockets.message().subscribe(new Action1<String>() {
            @Override
            public void call(String message) {

            }
        });

        websockets.connectionState().subscribe(new Action1<EasyWebsocketsConnectionState>() {
            @Override
            public void call(EasyWebsocketsConnectionState connectionState) {

            }
        });
    }

    private void open() {
        websockets.open();
    }

    private void close() {
        websockets.close();
    }

    private void immediatelyClose() {
        websockets.immediatelyClose();
    }

    private void send(String message) {
        websockets.send(message);
    }
}
