package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webViewTest();
            }
        });
    }

    private void webViewTest() {
        WebView webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        Log.d("TEST", "BEFORE"); // LOGGED
        //webview.loadUrl(""); // Enabling this makes it work on all Android versions
        webview.evaluateJavascript("(function(){return 'test'})()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                Log.d("TEST", "From JS: " + s); // NEVER LOGGED on API 19-21
            }
        });
        Log.e("TEST", "AFTER"); // LOGGED
    }
}