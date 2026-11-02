package com.abinash.youtube
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
class MainActivity:AppCompatActivity(){lateinit var web:WebView;lateinit var full:FrameLayout;var custom:View?=null;var callback:WebChromeClient.CustomViewCallback?=null
@SuppressLint("SetJavaScriptEnabled")override fun onCreate(b:Bundle?){super.onCreate(b);setContentView(R.layout.activity_main);web=findViewById(R.id.web);full=findViewById(R.id.full);web.settings.apply{javaScriptEnabled=true;domStorageEnabled=true;databaseEnabled=true;mediaPlaybackRequiresUserGesture=false;allowFileAccess=true;allowContentAccess=true;mixedContentMode=WebSettings.MIXED_CONTENT_NEVER_ALLOW};web.webViewClient=WebViewClient();web.webChromeClient=object:WebChromeClient(){override fun onShowCustomView(v:View,c:CustomViewCallback){custom=v;callback=c;full.addView(v,FrameLayout.LayoutParams(-1,-1));full.visibility=View.VISIBLE;web.visibility=View.GONE}override fun onHideCustomView(){hideFull()}};web.loadUrl("file:///android_asset/www/index.html");onBackPressedDispatcher.addCallback(this,object:OnBackPressedCallback(true){override fun handleOnBackPressed(){when{custom!=null->hideFull();web.canGoBack()->web.goBack();else->finish()}}})}
fun hideFull(){custom?.let{full.removeView(it)};custom=null;full.visibility=View.GONE;web.visibility=View.VISIBLE;callback?.onCustomViewHidden();callback=null}}
