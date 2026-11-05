package zeev.fraiman.manystartactivities.variants;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class PreloadSplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private LinearProgressIndicator progressBar;
    private TextView tvStatus;
    private boolean isNavigated = false;

    private static class PreloadStep {
        final int progress;
        final int stringResId;
        final long delayMs;

        PreloadStep(int progress, int stringResId, long delayMs) {
            this.progress = progress;
            this.stringResId = stringResId;
            this.delayMs = delayMs;
        }
    }

    private final PreloadStep[] steps = new PreloadStep[]{
            new PreloadStep(15, R.string.v1_status_network, 600),
            new PreloadStep(45, R.string.v1_status_user, 1200),
            new PreloadStep(80, R.string.v1_status_resources, 1900),
            new PreloadStep(100, R.string.v1_status_done, 2600)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_preload);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressBar = findViewById(R.id.progressBar);
        tvStatus = findViewById(R.id.tvStatus);

        progressBar.setProgress(0);
        tvStatus.setText(R.string.v1_status_start);

        for (PreloadStep step : steps) {
            handler.postDelayed(() -> {
                if (isFinishing() || isDestroyed()) {
                    return;
                }
                progressBar.setProgress(step.progress, true);
                tvStatus.setText(step.stringResId);

                if (step.progress >= 100) {
                    handler.postDelayed(this::finishPreloading, 400);
                }
            }, step.delayMs);
        }
    }

    private void finishPreloading() {
        if (isNavigated) {
            return;
        }
        isNavigated = true;
        TargetActivity.start(this, getString(R.string.v1_title), getString(R.string.trigger_v1));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
