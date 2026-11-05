package zeev.fraiman.manystartactivities.variants;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class BreathingSplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private View mainView;
    private ImageView ivLogo;
    private TextView tvPrompt;

    private ValueAnimator pulseAnimator;
    private boolean isAwakening = false;
    private boolean isNavigated = false;

    private final Runnable wakeRunnable = this::onAppAwakened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_breathing);

        mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivLogo = findViewById(R.id.ivBreathingLogo);
        tvPrompt = findViewById(R.id.tvBreathingPrompt);

        startSleepingPulse();

        mainView.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    onHoldStart();
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    onHoldRelease();
                    return true;
            }
            return false;
        });
    }

    private void startSleepingPulse() {
        if (pulseAnimator != null) pulseAnimator.cancel();

        pulseAnimator = ValueAnimator.ofFloat(0.92f, 1.08f);
        pulseAnimator.setDuration(1600);
        pulseAnimator.setRepeatCount(ValueAnimator.INFINITE);
        pulseAnimator.setRepeatMode(ValueAnimator.REVERSE);
        pulseAnimator.addUpdateListener(anim -> {
            float scale = (float) anim.getAnimatedValue();
            ivLogo.setScaleX(scale);
            ivLogo.setScaleY(scale);
        });
        pulseAnimator.start();
    }

    private void onHoldStart() {
        if (isNavigated) return;
        isAwakening = true;

        tvPrompt.setText(R.string.v13_prompt_waking);
        tvPrompt.setTextColor(Color.WHITE);

        // Accelerate pulse
        if (pulseAnimator != null) pulseAnimator.cancel();
        pulseAnimator = ValueAnimator.ofFloat(0.85f, 1.25f);
        pulseAnimator.setDuration(350);
        pulseAnimator.setRepeatCount(ValueAnimator.INFINITE);
        pulseAnimator.setRepeatMode(ValueAnimator.REVERSE);
        pulseAnimator.addUpdateListener(anim -> {
            float scale = (float) anim.getAnimatedValue();
            ivLogo.setScaleX(scale);
            ivLogo.setScaleY(scale);
        });
        pulseAnimator.start();

        // Animate background lightening
        ValueAnimator colorAnim = ValueAnimator.ofObject(new ArgbEvaluator(), Color.parseColor("#0F172A"), Color.parseColor("#334155"));
        colorAnim.setDuration(1200);
        colorAnim.addUpdateListener(anim -> mainView.setBackgroundColor((int) anim.getAnimatedValue()));
        colorAnim.start();

        handler.postDelayed(wakeRunnable, 1200);
    }

    private void onHoldRelease() {
        if (isNavigated) return;
        isAwakening = false;
        handler.removeCallbacks(wakeRunnable);

        tvPrompt.setText(R.string.v13_prompt_sleep);
        tvPrompt.setTextColor(Color.parseColor("#94A3B8"));

        // Revert background color to dark
        mainView.setBackgroundColor(Color.parseColor("#0F172A"));

        // Revert to gentle sleeping pulse
        startSleepingPulse();
    }

    private synchronized void onAppAwakened() {
        if (isNavigated) return;
        isNavigated = true;

        tvPrompt.setText(R.string.v13_awake);
        mainView.setBackgroundColor(Color.parseColor("#1E293B"));

        ivLogo.postDelayed(() -> {
            TargetActivity.start(BreathingSplashActivity.this, getString(R.string.v13_title), getString(R.string.trigger_v13));
            finish();
        }, 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        if (pulseAnimator != null) pulseAnimator.cancel();
    }
}
