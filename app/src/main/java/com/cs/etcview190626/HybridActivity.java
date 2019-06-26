package com.cs.etcview190626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HybridActivity extends AppCompatActivity {

    EditText message;
    Button send;
    WebView hybridwebview;

    //JavaScript가 호출할 수 있는 Method를 소유한 Class
    class AndroidJavaScriptInterface{
        Context context;
        android.os.Handler handler = new android.os.Handler();

        //생성자
        public AndroidJavaScriptInterface(Context context){
            this.context = context;
        }

        @JavascriptInterface
        public void showToastMessage(final String message){
            handler.post(new Runnable() {
                @Override
                public void run() {
                     Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybrid);

        message = (EditText)findViewById(R.id.message);
        send = (Button)findViewById(R.id.send);
        hybridwebview = (WebView)findViewById(R.id.hybridwebview);

        //Redirect 되는 주소도 WebView에 출력할 수 있도록 설정
        hybridwebview.setWebViewClient(new WebViewClient());
        //JavaScript를 사용할 수 있도록 설정
        hybridwebview.getSettings().setJavaScriptEnabled(true);
        //Web View에 Loading
        hybridwebview.loadUrl("http://192.168.0.105:8080");

        //JavaScript 함수가 Method를 호출할 수 있도록 설정
        hybridwebview.addJavascriptInterface(
                new AndroidJavaScriptInterface(HybridActivity.this), "MYApp");

        send.setOnClickListener((view)->{
            //입력한 내용 가져오기
            String mes = message.getText().toString();
            //JavaScript 함수 호출
            hybridwebview.loadUrl("javascript:showDisplayMessage('" + mes + "')");
            Toast.makeText(HybridActivity.this, "Message 전송", Toast.LENGTH_LONG).show();
        });

    }
}
