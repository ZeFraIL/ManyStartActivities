package zeev.fraiman.manystartactivities.variants;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class SlideSplashActivity extends AppCompatActivity {

    private ImageView ivLogo;
    private TextView tvPrompt;
    private SeekBar seekBar;
    private boolean isUnlocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_slide);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivLogo = findViewById(R.id.ivSlideLogo);
        tvPrompt = findViewById(R.id.tvSlidePrompt);
        seekBar = findViewById(R.id.seekBarSlide);

        updatePromptText(0);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (isUnlocked) return;
                updatePromptText(progress);

                if (progress >= 100) {
                    unlockApplication();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (isUnlocked) return;
                if (seekBar.getProgress() < 100) {
                    // Smooth snap back to 0
                    ObjectAnimator anim = ObjectAnimator.ofInt(seekBar, "progress", seekBar.getProgress(), 0);
                    anim.setDuration(300);
                    anim.start();
                }
            }
        });
    }

    private void updatePromptText(int progress) {
        tvPrompt.setText(getString(R.string.v17_prompt, progress));
    }

    private synchronized void unlockApplication() {
        if (isUnlocked) return;
        isUnlocked = true;

        seekBar.setEnabled(false);

        // Logo scale pop animation
        ivLogo.animate()
                .scaleX(1.25f)
                .scaleY(1.25f)
                .setDuration(300)
                .withEndAction(() -> ivLogo.postDelayed(() -> {
                    TargetActivity.start(SlideSplashActivity.this, getString(R.string.v17_title), getString(R.string.trigger_v17));
                    finish();
                }, 400));
    }
}
