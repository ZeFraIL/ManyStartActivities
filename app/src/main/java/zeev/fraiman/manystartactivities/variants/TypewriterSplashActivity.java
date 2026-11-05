package zeev.fraiman.manystartactivities.variants;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class TypewriterSplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private TextView tvText;

    private final String initialTypoText = "Welcome to Start Skeen";
    private final String correctEnding = "Screen!";
    private final int prefixLength = "Welcome to Start ".length();

    private int currentIndex = 0;
    private String currentText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_typewriter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvText = findViewById(R.id.tvTypewriterText);

        // Start typing step 1 (with intentional typo)
        typeNextChar();
    }

    private void typeNextChar() {
        if (isFinishing() || isDestroyed()) return;

        if (currentIndex < initialTypoText.length()) {
            currentText = initialTypoText.substring(0, currentIndex + 1);
            tvText.setText(currentText + "|");
            currentIndex++;
            handler.postDelayed(this::typeNextChar, 90);
        } else {
            // Pause at typo before backspacing
            handler.postDelayed(this::backspaceTypo, 500);
        }
    }

    private void backspaceTypo() {
        if (isFinishing() || isDestroyed()) return;

        if (currentText.length() > prefixLength) {
            currentText = currentText.substring(0, currentText.length() - 1);
            tvText.setText(currentText + "|");
            handler.postDelayed(this::backspaceTypo, 110);
        } else {
            // Start typing correct ending
            currentIndex = 0;
            handler.postDelayed(this::typeCorrectChar, 250);
        }
    }

    private void typeCorrectChar() {
        if (isFinishing() || isDestroyed()) return;

        if (currentIndex < correctEnding.length()) {
            currentText = initialTypoText.substring(0, prefixLength) + correctEnding.substring(0, currentIndex + 1);
            tvText.setText(currentText + "|");
            currentIndex++;
            handler.postDelayed(this::typeCorrectChar, 100);
        } else {
            // Remove cursor line and complete
            tvText.setText(currentText);
            handler.postDelayed(() -> {
                if (!isFinishing() && !isDestroyed()) {
                    TargetActivity.start(TypewriterSplashActivity.this, getString(R.string.v11_title), getString(R.string.trigger_v11));
                    finish();
                }
            }, 700);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
