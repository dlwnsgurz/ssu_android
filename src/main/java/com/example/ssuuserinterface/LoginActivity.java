package com.example.ssuuserinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.ssuuserinterface.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public WebView webView;
    private WebSettings webSettings;
    private EditText idField;
    private EditText pwField;
    private Button loginButton;
    private Button crawlingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idField = (EditText) findViewById(R.id.idField);
        pwField = (EditText) findViewById(R.id.pwField);
        loginButton = (Button) findViewById(R.id.loginButton);
        crawlingButton = (Button) findViewById(R.id.crawlButton);
        webView = (WebView) findViewById(R.id.webView);


        webView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        webSettings = webView.getSettings(); //세부 세팅 등록
        webSettings.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
        webSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        webSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        webSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        webSettings.setSupportZoom(false); // 화면 줌 허용 여부
        webSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        webSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부

        webView.loadUrl("https://smartid.ssu.ac.kr/Symtra_sso/smln.asp?apiReturnUrl=https%3A%2F%2Fsaint.ssu.ac.kr%2FwebSSO%2Fsso.jsp"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시
        loginButton.setOnClickListener(this);
        crawlingButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View view){
                if (view == loginButton){
                    webView.evaluateJavascript("document.getElementById('userid').value =" + "'" + idField.getText().toString() + "'", null);
                    webView.evaluateJavascript("document.getElementById('pwd').value ="  + "'" + pwField.getText().toString() + "'", null);
                    webView.evaluateJavascript("document.querySelector('form').submit()", null);
                    Log.d("qweqweqweId : ", idField.getText().toString());
                    Log.d("qweqweqwePW : ", pwField.getText().toString());
                }else if(view == crawlingButton){
                    Intent intent = new Intent(getApplicationContext(),SubActivity_add.class);
                    intent.putExtra("webUrl",webView.getUrl().toString());
                    startActivity(intent);
                }
            };
}

