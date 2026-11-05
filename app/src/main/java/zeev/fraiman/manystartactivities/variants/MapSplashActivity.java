package zeev.fraiman.manystartactivities.variants;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class MapSplashActivity extends AppCompatActivity {

    private View layoutMapGrid;
    private View layoutPinMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_map);

        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layoutMapGrid = findViewById(R.id.layoutMapGrid);
        layoutPinMarker = findViewById(R.id.layoutPinMarker);

        startMapZoomAndPinDrop();
    }

    private void startMapZoomAndPinDrop() {
        // 1. Map Zoom from space scale 0.3 -> 1.0
        layoutMapGrid.setScaleX(0.3f);
        layoutMapGrid.setScaleY(0.3f);
        layoutMapGrid.setAlpha(0.2f);

        ObjectAnimator mapZoomX = ObjectAnimator.ofFloat(layoutMapGrid, "scaleX", 0.3f, 1.0f);
        ObjectAnimator mapZoomY = ObjectAnimator.ofFloat(layoutMapGrid, "scaleY", 0.3f, 1.0f);
        ObjectAnimator mapAlpha = ObjectAnimator.ofFloat(layoutMapGrid, "alpha", 0.2f, 1.0f);

        AnimatorSet mapSet = new AnimatorSet();
        mapSet.playTogether(mapZoomX, mapZoomY, mapAlpha);
        mapSet.setDuration(1200);

        // 2. Pin drop from sky with bounce
        layoutPinMarker.setTranslationY(-600f);
        layoutPinMarker.setAlpha(0f);

        ObjectAnimator pinDropY = ObjectAnimator.ofFloat(layoutPinMarker, "translationY", -600f, 0f);
        ObjectAnimator pinAlpha = ObjectAnimator.ofFloat(layoutPinMarker, "alpha", 0f, 1f);

        AnimatorSet pinSet = new AnimatorSet();
        pinSet.playTogether(pinDropY, pinAlpha);
        pinSet.setDuration(800);
        pinSet.setInterpolator(new OvershootInterpolator(1.4f));

        // Sequence: Zoom -> Drop Pin -> Transition
        AnimatorSet fullSequence = new AnimatorSet();
        fullSequence.playSequentially(mapSet, pinSet);

        fullSequence.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                layoutPinMarker.postDelayed(() -> {
                    if (!isFinishing() && !isDestroyed()) {
                        TargetActivity.start(MapSplashActivity.this, getString(R.string.v19_title), getString(R.string.trigger_v19));
                        finish();
                    }
                }, 1000);
            }
        });

        fullSequence.start();
    }
}
