package zeev.fraiman.manystartactivities.variants;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class MotionSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_motion);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView ivLogo = findViewById(R.id.ivMotionLogo);
        TextView tvTitle = findViewById(R.id.tvMotionTitle);
        TextView tvTagline = findViewById(R.id.tvMotionTagline);

        // Hide initially
        ivLogo.setAlpha(0f);
        ivLogo.setScaleX(0.3f);
        ivLogo.setScaleY(0.3f);

        tvTitle.setAlpha(0f);
        tvTitle.setTranslationX(-300f);

        tvTagline.setAlpha(0f);
        tvTagline.setTranslationY(100f);

        // 1. Logo scale & fade in
        ObjectAnimator logoAlpha = ObjectAnimator.ofFloat(ivLogo, "alpha", 0f, 1f);
        ObjectAnimator logoScaleX = ObjectAnimator.ofFloat(ivLogo, "scaleX", 0.3f, 1f);
        ObjectAnimator logoScaleY = ObjectAnimator.ofFloat(ivLogo, "scaleY", 0.3f, 1f);
        AnimatorSet logoSet = new AnimatorSet();
        logoSet.playTogether(logoAlpha, logoScaleX, logoScaleY);
        logoSet.setDuration(800);

        // 2. Title slide in from left & fade in
        ObjectAnimator titleAlpha = ObjectAnimator.ofFloat(tvTitle, "alpha", 0f, 1f);
        ObjectAnimator titleTransX = ObjectAnimator.ofFloat(tvTitle, "translationX", -300f, 0f);
        AnimatorSet titleSet = new AnimatorSet();
        titleSet.playTogether(titleAlpha, titleTransX);
        titleSet.setDuration(700);

        // 3. Tagline slide up from bottom & fade in
        ObjectAnimator taglineAlpha = ObjectAnimator.ofFloat(tvTagline, "alpha", 0f, 1f);
        ObjectAnimator taglineTransY = ObjectAnimator.ofFloat(tvTagline, "translationY", 100f, 0f);
        AnimatorSet taglineSet = new AnimatorSet();
        taglineSet.playTogether(taglineAlpha, taglineTransY);
        taglineSet.setDuration(600);

        // Sequence: Logo -> Title -> Tagline
        AnimatorSet fullSequence = new AnimatorSet();
        fullSequence.playSequentially(logoSet, titleSet, taglineSet);
        fullSequence.setInterpolator(new AccelerateDecelerateInterpolator());

        fullSequence.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ivLogo.postDelayed(() -> {
                    if (!isFinishing() && !isDestroyed()) {
                        TargetActivity.start(MotionSplashActivity.this, getString(R.string.v4_title), getString(R.string.trigger_v4));
                        finish();
                    }
                }, 1000);
            }
        });

        fullSequence.start();
    }
}
