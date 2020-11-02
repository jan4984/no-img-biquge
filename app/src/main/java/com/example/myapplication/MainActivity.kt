package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient


class MainActivity : Activity() {
    lateinit var webview: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webview = this.findViewById(R.id.wv)
        webview.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webview.settings.loadsImagesAutomatically = false
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url)
                return false // then it is not handled by default action
            }
        }
        webview.loadUrl("http://m.xbiquge.la")
    }

    override fun onBackPressed() {
        webview.goBack()
    }

}