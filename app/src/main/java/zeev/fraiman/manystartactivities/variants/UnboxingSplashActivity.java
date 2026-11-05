package zeev.fraiman.manystartactivities.variants;

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

public class UnboxingSplashActivity extends AppCompatActivity {

    private View layoutRibbon;
    private ImageView ivGiftLogo;
    private TextView tvPrompt;
    private boolean isUnwrapped = false;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_unboxing);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layoutRibbon = findViewById(R.id.layoutRibbon);
        ivGiftLogo = findViewById(R.id.ivGiftLogo);
        tvPrompt = findViewById(R.id.tvUnboxingPrompt);

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1 != null && e2 != null) {
                    float deltaY = e2.getY() - e1.getY();
                    if (deltaY > 80 && Math.abs(velocityY) > 80) {
                        unwrapGift();
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

        layoutRibbon.setOnClickListener(v -> unwrapGift());
    }

    private void unwrapGift() {
        if (isUnwrapped) return;
        isUnwrapped = true;

        tvPrompt.setText(R.string.v10_unwrapped);

        // Ribbon unrolls downwards using ViewPropertyAnimator
        layoutRibbon.animate()
                .translationY(800f)
                .alpha(0f)
                .setDuration(600)
                .withEndAction(() -> {
                    layoutRibbon.setVisibility(View.GONE);

                    // Logo pop animation
                    ivGiftLogo.animate()
                            .scaleX(1.2f)
                            .scaleY(1.2f)
                            .setDuration(300)
                            .withEndAction(() -> ivGiftLogo.postDelayed(() -> {
                                TargetActivity.start(UnboxingSplashActivity.this, getString(R.string.v10_title), getString(R.string.trigger_v10));
                                finish();
                            }, 500));
                });
    }
}
