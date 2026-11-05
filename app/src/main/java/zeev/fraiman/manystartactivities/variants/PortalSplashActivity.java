package zeev.fraiman.manystartactivities.variants;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class PortalSplashActivity extends AppCompatActivity {

    private View cardPortalPreview;
    private View layoutOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_portal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cardPortalPreview = findViewById(R.id.cardPortalPreview);
        layoutOverlay = findViewById(R.id.layoutPortalOverlay);

        // Initial Portal state (small, transparent, blurred)
        cardPortalPreview.setScaleX(0.35f);
        cardPortalPreview.setScaleY(0.35f);
        cardPortalPreview.setAlpha(0.2f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            cardPortalPreview.setRenderEffect(RenderEffect.createBlurEffect(30f, 30f, Shader.TileMode.CLAMP));
        }

        startPortalFlyInAnimation();
    }

    private void startPortalFlyInAnimation() {
        ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
        anim.setDuration(2500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());

        anim.addUpdateListener(animation -> {
            float fraction = animation.getAnimatedFraction();

            float scale = 0.35f + (0.65f * fraction);
            float alpha = 0.2f + (0.8f * fraction);

            cardPortalPreview.setScaleX(scale);
            cardPortalPreview.setScaleY(scale);
            cardPortalPreview.setAlpha(alpha);

            layoutOverlay.setAlpha(1f - fraction);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                float blurRadius = Math.max(1f, 30f * (1f - fraction));
                try {
                    cardPortalPreview.setRenderEffect(RenderEffect.createBlurEffect(blurRadius, blurRadius, Shader.TileMode.CLAMP));
                } catch (Exception ignored) {
                }
            }
        });

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isFinishing() && !isDestroyed()) {
                    TargetActivity.start(PortalSplashActivity.this, getString(R.string.v12_title), getString(R.string.trigger_v12));
                    finish();
                }
            }
        });

        anim.start();
    }
}
