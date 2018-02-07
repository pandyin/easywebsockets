package com.intathep.android.easywebsockets;

public enum EasyWebsocketsConnectionState {

    OPEN("connecting"),
    CLOSING("connected"),
    CLOSED("disconnecting");

    private final String apiKey;

    EasyWebsocketsConnectionState(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public static EasyWebsocketsConnectionState fromApiKey(String apiKey) {
        for (EasyWebsocketsConnectionState connectionState : EasyWebsocketsConnectionState.values()) {
            if (connectionState.getApiKey().equals(apiKey)) {
                return connectionState;
            }
        }
        return CLOSED;
    }
}
