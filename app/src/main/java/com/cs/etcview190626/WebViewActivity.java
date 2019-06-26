package com.cs.etcview190626;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity {

    Button next, local;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        next = (Button)findViewById(R.id.next);
        local = (Button)findViewById(R.id.local);
        webView = (WebView)findViewById(R.id.webview);

        //WebView의 Option 설정
        //Redirect 되는 Page의 이동을 WebView가 처리 하도록 설정
        webView.setWebViewClient(new WebViewClient());

        //JavaScript와 화면 크기 변화를 가능하게 하는 Option
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);

        //WebView 의 URL Load
        webView.loadUrl("https://www.apple.com");

        //Next
        next.setOnClickListener((view)->{
            webView.loadUrl("https://www.google.com");
        });

        //Local File
        local.setOnClickListener((view)->{
            webView.loadUrl("file:///android_asset/test.html");
        });

    }
}
