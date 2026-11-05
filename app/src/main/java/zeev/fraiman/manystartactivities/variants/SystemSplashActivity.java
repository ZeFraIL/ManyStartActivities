package zeev.fraiman.manystartactivities.variants;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class SystemSplashActivity extends AppCompatActivity {

    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_system);

        startTime = SystemClock.elapsedRealtime();

        // Keep system splash screen on screen for 1.5s to demonstrate state retention
        splashScreen.setKeepOnScreenCondition(() -> SystemClock.elapsedRealtime() - startTime < 1500);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSystemProceed = findViewById(R.id.btnSystemProceed);
        btnSystemProceed.setOnClickListener(v -> {
            TargetActivity.start(this, getString(R.string.v5_title), getString(R.string.trigger_v5));
            finish();
        });
    }
}
