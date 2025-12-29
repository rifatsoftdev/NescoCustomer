package com.rifatsoftdev.nescocustomer

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.rifatsoftdev.nescocustomer.ui.theme.NescoCustomerTheme

class MainActivity : ComponentActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NescoCustomerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WebViewScreen(
                        url = "https://customer.nesco.gov.bd/pre/panel",
                        modifier = Modifier.systemBarsPadding(),
                        onWebViewCreated = { webView = it }
                    )
                }
            }
        }
    }

    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}

@Composable
fun WebViewScreen(
    url: String,
    modifier: Modifier,
    onWebViewCreated: (WebView) -> Unit
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.cacheMode = WebSettings.LOAD_DEFAULT
                webViewClient = WebViewClient()
                loadUrl(url)
                onWebViewCreated(this)
            }
        }
    )
}
