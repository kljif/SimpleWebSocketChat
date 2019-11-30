package com.example.kljif.simplechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.WebSocketImpl;

import java.net.URI;
import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity {
    public Button wsConnectButton, wsSendMessageButton;
    public TextView wsStateTextView, wsRecvMessageTextView;
    public EditText sendEditText, userNameEditText;

    private static String ServerIP = "192.168.3.4";
    private static String ServerPORT = "8080";

    private WebSocketClientListner ws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebSocketImpl.DEBUG = true;
        try {
            ws = new WebSocketClientListner(new URI("ws://" + ServerIP + ":" + ServerPORT + "/MyWebSocketServer/WebSocketServer"), this);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        wsStateTextView = findViewById(R.id.stateTextView);
        wsRecvMessageTextView = findViewById(R.id.receiveTextView);

        wsConnectButton = findViewById(R.id.connectButton);
        wsSendMessageButton = findViewById(R.id.sendButton);

        sendEditText = findViewById(R.id.sendEditText);
        userNameEditText = findViewById(R.id.userNameEditText);

        wsConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ws.isOpen()) {
                    ws.connect();
                }
            }
        });

        wsSendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ws.isOpen()) {
                    ws.send(userNameEditText.getText().toString() + " : " + sendEditText.getText().toString());
                }
            }
        });
    }
}
