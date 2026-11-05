package zeev.fraiman.manystartactivities.variants;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class RitualSplashActivity extends AppCompatActivity {

    private ImageView ivDishLogo;
    private FrameLayout saltContainer;
    private TextView tvPrompt;
    private int saltCount = 0;
    private boolean isSeasoned = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_ritual);

        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivDishLogo = findViewById(R.id.ivDishLogo);
        saltContainer = findViewById(R.id.layoutSaltContainer);
        tvPrompt = findViewById(R.id.tvRitualPrompt);

        mainView.setOnTouchListener((v, event) -> {
            int action = event.getActionMasked();
            if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
                spawnSaltParticles();
                return true;
            }
            return false;
        });
    }

    private void spawnSaltParticles() {
        if (isSeasoned) return;

        int containerW = saltContainer.getWidth() > 0 ? saltContainer.getWidth() : 200;

        for (int i = 0; i < 14; i++) {
            View salt = new View(this);
            int size = (int) (6 + Math.random() * 8);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size, size);
            salt.setLayoutParams(params);
            salt.setBackgroundColor(Color.WHITE);

            float startX = (float) (Math.random() * containerW);
            salt.setTranslationX(startX);
            salt.setTranslationY(0f);
            saltContainer.addView(salt);

            float dx = (float) ((Math.random() - 0.5) * 60);
            float dy = (float) (100 + Math.random() * 120);

            salt.animate()
                    .translationXBy(dx)
                    .translationYBy(dy)
                    .alpha(0.85f)
                    .setDuration(400)
                    .start();
        }

        saltCount++;
        float newAlpha = Math.min(1.0f, 0.25f + (saltCount * 0.18f));
        ivDishLogo.animate().alpha(newAlpha).setDuration(250).start();

        if (newAlpha >= 0.99f && !isSeasoned) {
            onSeasoned();
        }
    }

    private synchronized void onSeasoned() {
        if (isSeasoned) return;
        isSeasoned = true;

        tvPrompt.setText(R.string.v14_seasoned);

        ivDishLogo.animate()
                .scaleX(1.15f)
                .scaleY(1.15f)
                .setDuration(300)
                .withEndAction(() -> ivDishLogo.postDelayed(() -> {
                    TargetActivity.start(RitualSplashActivity.this, getString(R.string.v14_title), getString(R.string.trigger_v14));
                    finish();
                }, 600));
    }
}
