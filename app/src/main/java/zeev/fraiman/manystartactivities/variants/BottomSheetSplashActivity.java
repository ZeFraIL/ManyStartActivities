package zeev.fraiman.manystartactivities.variants;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class BottomSheetSplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private View cardBottomSheet;
    private BottomSheetBehavior<View> sheetBehavior;
    private boolean isNavigated = false;
    private boolean userInteracted = false;

    private final Runnable bounceNudgeRunnable = this::performBounceHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_bottom_sheet);

        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cardBottomSheet = findViewById(R.id.cardBottomSheet);
        Button btnEnterGuest = findViewById(R.id.btnEnterGuest);
        Button btnSheetOptions = findViewById(R.id.btnSheetOptions);

        sheetBehavior = BottomSheetBehavior.from(cardBottomSheet);

        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                userInteracted = true;
                handler.removeCallbacks(bounceNudgeRunnable);

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    navigateToTarget(getString(R.string.trigger_v23_drag));
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        btnEnterGuest.setOnClickListener(v -> navigateToTarget(getString(R.string.trigger_v23_guest)));
        btnSheetOptions.setOnClickListener(v -> navigateToTarget(getString(R.string.v23_btn_options)));

        // Schedule 3-second Y-translation bounce hint animation if user hasn't dragged sheet
        handler.postDelayed(bounceNudgeRunnable, 3000);
    }

    private void performBounceHint() {
        if (userInteracted || isNavigated || isFinishing() || isDestroyed()) return;

        cardBottomSheet.animate()
                .translationY(-50f)
                .setDuration(250)
                .withEndAction(() -> cardBottomSheet.animate()
                        .translationY(0f)
                        .setDuration(250)
                        .withEndAction(() -> {
                            if (!userInteracted && !isNavigated) {
                                handler.postDelayed(bounceNudgeRunnable, 3000);
                            }
                        }));
    }

    private synchronized void navigateToTarget(String triggerMechanism) {
        if (isNavigated) return;
        isNavigated = true;
        handler.removeCallbacks(bounceNudgeRunnable);

        TargetActivity.start(this, getString(R.string.v23_title), triggerMechanism);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
