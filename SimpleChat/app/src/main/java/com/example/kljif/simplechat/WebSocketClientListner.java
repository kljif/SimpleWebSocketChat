package com.example.kljif.simplechat;

import android.content.Context;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketClientListner extends WebSocketClient {

    MainActivity mainActivity;

    public WebSocketClientListner(URI serverUri, MainActivity mainActivity) {
        super(serverUri);
        this.mainActivity = mainActivity;
    }

    @Override
    public void onOpen(ServerHandshake handShakeData) {

        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivity.wsStateTextView.setText("Connected!!");
            }
        });
    }

    @Override
    public void onMessage(final String message) {

        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String now = mainActivity.wsRecvMessageTextView.getText().toString();
                mainActivity.wsRecvMessageTextView.setText(now + "\n" + message);
            }
        });
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivity.wsStateTextView.setText("DisConnected..");
            }
        });
    }

    @Override
    public void onError(Exception ex) {
        Log.e("Error", ex.getMessage());
    }
}
