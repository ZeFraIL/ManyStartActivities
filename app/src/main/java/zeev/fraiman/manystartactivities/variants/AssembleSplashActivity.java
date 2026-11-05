package zeev.fraiman.manystartactivities.variants;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class AssembleSplashActivity extends AppCompatActivity {

    private View centerTarget;
    private View pieceTL;
    private View pieceTR;
    private View pieceBL;
    private View pieceBR;
    private TextView tvPrompt;
    private int assembledCount = 0;
    private boolean isNavigated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_assemble);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        centerTarget = findViewById(R.id.centerTarget);
        pieceTL = findViewById(R.id.cardPieceTL);
        pieceTR = findViewById(R.id.cardPieceTR);
        pieceBL = findViewById(R.id.cardPieceBL);
        pieceBR = findViewById(R.id.cardPieceBR);
        tvPrompt = findViewById(R.id.tvAssemblePrompt);

        // Click listeners on each piece to assemble faster
        pieceTL.setOnClickListener(v -> animatePieceToCenter(pieceTL, -40, -40, 300));
        pieceTR.setOnClickListener(v -> animatePieceToCenter(pieceTR, 40, -40, 300));
        pieceBL.setOnClickListener(v -> animatePieceToCenter(pieceBL, -40, 40, 300));
        pieceBR.setOnClickListener(v -> animatePieceToCenter(pieceBR, 40, 40, 300));

        // Automatic assembly after 1.5s delay
        centerTarget.postDelayed(() -> {
            if (!isFinishing() && !isDestroyed()) {
                animatePieceToCenter(pieceTL, -40, -40, 1000);
                animatePieceToCenter(pieceTR, 40, -40, 1000);
                animatePieceToCenter(pieceBL, -40, 40, 1000);
                animatePieceToCenter(pieceBR, 40, 40, 1000);
            }
        }, 1500);
    }

    private void animatePieceToCenter(View piece, float targetOffsetDx, float targetOffsetDy, long duration) {
        if (piece.getTag() != null) return; // Already assembled
        piece.setTag("assembled");

        int[] pieceLoc = new int[2];
        int[] targetLoc = new int[2];
        piece.getLocationOnScreen(pieceLoc);
        centerTarget.getLocationOnScreen(targetLoc);

        float dx = (targetLoc[0] + centerTarget.getWidth() / 2f) - (pieceLoc[0] + piece.getWidth() / 2f) + targetOffsetDx;
        float dy = (targetLoc[1] + centerTarget.getHeight() / 2f) - (pieceLoc[1] + piece.getHeight() / 2f) + targetOffsetDy;

        ObjectAnimator animX = ObjectAnimator.ofFloat(piece, "translationX", 0f, dx);
        ObjectAnimator animY = ObjectAnimator.ofFloat(piece, "translationY", 0f, dy);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animX, animY);
        set.setDuration(duration);
        set.setInterpolator(new OvershootInterpolator(1.2f));

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                assembledCount++;
                if (assembledCount >= 4 && !isNavigated) {
                    onAllAssembled();
                }
            }
        });
        set.start();
    }

    private synchronized void onAllAssembled() {
        if (isNavigated) return;
        isNavigated = true;

        tvPrompt.setText(R.string.v8_status_assembled);

        centerTarget.postDelayed(() -> {
            TargetActivity.start(AssembleSplashActivity.this, getString(R.string.v8_title), getString(R.string.trigger_v8));
            finish();
        }, 800);
    }
}
