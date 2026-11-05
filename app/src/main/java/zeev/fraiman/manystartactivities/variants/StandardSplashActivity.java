package zeev.fraiman.manystartactivities.variants;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class StandardSplashActivity extends AppCompatActivity {

    private CountDownTimer timer;
    private boolean isNavigated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_standard);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView ivLogo = findViewById(R.id.ivLogo);
        TextView tvTimerCountdown = findViewById(R.id.tvTimerCountdown);
        Button btnContinue = findViewById(R.id.btnContinue);

        // Logo scale animation over 5 seconds
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.7f, 1.3f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.7f, 1.3f);
        ObjectAnimator logoAnimator = ObjectAnimator.ofPropertyValuesHolder(ivLogo, scaleX, scaleY);
        logoAnimator.setDuration(5000);
        logoAnimator.start();

        // 7-second countdown timer for auto-navigation
        timer = new CountDownTimer(7000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) + 1;
                tvTimerCountdown.setText(getString(R.string.v0_timer_running, seconds));
            }

            @Override
            public void onFinish() {
                navigateToTarget(getString(R.string.trigger_v0_timer));
            }
        };
        timer.start();

        btnContinue.setOnClickListener(v -> navigateToTarget(getString(R.string.trigger_v0_button)));
    }

    private synchronized void navigateToTarget(String triggerMechanism) {
        if (isNavigated) {
            return;
        }
        isNavigated = true;
        if (timer != null) {
            timer.cancel();
        }
        TargetActivity.start(this, getString(R.string.v0_title), triggerMechanism);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
