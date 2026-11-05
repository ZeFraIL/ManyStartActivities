package zeev.fraiman.manystartactivities.variants;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class MoodSplashActivity extends AppCompatActivity {

    private View mainView;
    private ChipGroup chipGroup;
    private Button btnContinue;
    private String selectedMoodText = "Productive 🚀";
    private int currentBgColor = Color.parseColor("#0F172A");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_mood);

        mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chipGroup = findViewById(R.id.chipGroupMood);
        btnContinue = findViewById(R.id.btnContinueMood);

        updateButtonText(getString(R.string.v22_chip_productive));

        chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (!checkedIds.isEmpty()) {
                int checkedId = checkedIds.get(0);
                Chip chip = findViewById(checkedId);
                if (chip != null) {
                    selectedMoodText = chip.getText().toString();
                    updateButtonText(selectedMoodText);
                    updateBackgroundColorForMood(checkedId);
                }
            }
        });

        btnContinue.setOnClickListener(v -> {
            String triggerMessage = getString(R.string.trigger_v22, selectedMoodText);
            TargetActivity.start(this, getString(R.string.v22_title), triggerMessage);
            finish();
        });
    }

    private void updateButtonText(String mood) {
        btnContinue.setText(getString(R.string.v22_btn_continue, mood));
    }

    private void updateBackgroundColorForMood(int checkedId) {
        int newColor;
        if (checkedId == R.id.chipCalm) {
            newColor = Color.parseColor("#064E3B"); // Emerald green tint
        } else if (checkedId == R.id.chipLearning) {
            newColor = Color.parseColor("#312E81"); // Indigo blue tint
        } else if (checkedId == R.id.chipRelaxed) {
            newColor = Color.parseColor("#701A75"); // Fuchsia purple tint
        } else {
            newColor = Color.parseColor("#0F172A"); // Slate dark tint (Productive)
        }

        ValueAnimator anim = ValueAnimator.ofObject(new ArgbEvaluator(), currentBgColor, newColor);
        anim.setDuration(400);
        anim.addUpdateListener(animation -> {
            currentBgColor = (int) animation.getAnimatedValue();
            mainView.setBackgroundColor(currentBgColor);
        });
        anim.start();
    }
}
