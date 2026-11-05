package zeev.fraiman.manystartactivities.variants;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.GestureDetector;
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

public class InteractiveSplashActivity extends AppCompatActivity {

    private ImageView ivLogo;
    private TextView tvPromptTap;
    private TextView tvPromptSwipe;
    private int tapCount = 0;
    private boolean isUnlocked = false;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_interactive);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivLogo = findViewById(R.id.ivLogoInteractive);
        tvPromptSwipe = findViewById(R.id.tvPromptSwipe);
        tvPromptTap = findViewById(R.id.tvPromptTap);

        updateTapText();

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1 != null && e2 != null) {
                    float deltaY = e1.getY() - e2.getY();
                    if (deltaY > 100 && Math.abs(velocityY) > 100) {
                        unlock(getString(R.string.trigger_v3_swipe));
                        return true;
                    }
                }
                return false;
            }
        });

        View mainView = findViewById(R.id.main);
        mainView.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });

        ivLogo.setOnClickListener(v -> {
            if (isUnlocked) return;
            tapCount++;
            updateTapText();

            // Bounce animation on tap
            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.2f, 1.0f);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.2f, 1.0f);
            ObjectAnimator.ofPropertyValuesHolder(ivLogo, scaleX, scaleY).setDuration(200).start();

            if (tapCount >= 3) {
                unlock(getString(R.string.trigger_v3_tap));
            }
        });
    }

    private void updateTapText() {
        tvPromptTap.setText(getString(R.string.v3_prompt_tap, tapCount));
    }

    private void unlock(String triggerMechanism) {
        if (isUnlocked) return;
        isUnlocked = true;

        tvPromptSwipe.setText(R.string.v3_unlocked);

        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.8f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.8f);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f);

        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(ivLogo, scaleX, scaleY, alpha);
        anim.setDuration(600);
        anim.start();

        ivLogo.postDelayed(() -> {
            TargetActivity.start(InteractiveSplashActivity.this, getString(R.string.v3_title), triggerMechanism);
            finish();
        }, 600);
    }
}
