package com.example.mydemo2;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.os.BuildCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.i("AAA","ch:"+BuildConfig.ch);
        TextView textView = findViewById(R.id.textView);

        String webUrl = BuildConfig.webUrl;
        if(!TextUtils.isEmpty(webUrl)){
            WebView webView = findViewById(R.id.webView);
            // 启用 JavaScript
            webView.getSettings().setJavaScriptEnabled(true);
            // 启用 DOM storage API
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setAllowContentAccess(true);
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Log.e("WebView", "Error: " + description);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    Log.i("WebView", "Page loaded: " + url);
                }
            });
            webView.loadUrl(webUrl);
        }

        textView.setText(BuildConfig.ch+"\n"+webUrl);
    }
}