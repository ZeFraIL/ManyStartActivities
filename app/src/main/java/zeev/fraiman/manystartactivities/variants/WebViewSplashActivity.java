package zeev.fraiman.manystartactivities.variants;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class WebViewSplashActivity extends AppCompatActivity {

    private WebView webView;
    private boolean isNavigated = false;

    public class WebBridge {
        @JavascriptInterface
        public void onStartApp(String triggerData) {
            runOnUiThread(() -> {
                if (isNavigated) return;
                isNavigated = true;
                TargetActivity.start(WebViewSplashActivity.this, getString(R.string.v20_title), getString(R.string.trigger_v20) + " (" + triggerData + ")");
                finish();
            });
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_webview);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        webView = findViewById(R.id.webViewSplash);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new WebBridge(), "WebBridge");

        webView.loadUrl("file:///android_asset/splash.html");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
        }
    }
}
