package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.EditText
import android.widget.TextView


class MainActivity : Activity() {
    lateinit var webview: WebView
    lateinit var gestureDetector:GestureDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WebView.setWebContentsDebuggingEnabled(true)
        webview = this.findViewById(R.id.wv)
        webview.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webview.settings.loadsImagesAutomatically = false
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url)
                return false // then it is not handled by default action
            }
        }
        webview.webChromeClient = object : WebChromeClient(){
        }
        webview.loadUrl("http://m.xbiquge.la")

        gestureDetector = GestureDetector(this, object : SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                Log.d("m", "onDoubleTap")
                var txt:TextView
                var dialog = AlertDialog.Builder(this@MainActivity).apply {
                    setView(EditText(this@MainActivity).apply {
                        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200)
                        setText(webview.url ?: "", TextView.BufferType.EDITABLE)
                        txt=this
                    })
                    setNeutralButton("OK", DialogInterface.OnClickListener { _: DialogInterface, _: Int ->
                        webview.loadUrl(txt.text.toString())
                    })
                }.create()
                dialog.show()
                txt.setSelectAllOnFocus(true)
                txt.requestFocus()
                return true
            }
        })

        webview.setOnTouchListener { _: View, motionEvent: MotionEvent ->
            gestureDetector.onTouchEvent(motionEvent)
            false
        }
    }

    override fun onBackPressed() {
        webview.goBack()
    }


}
