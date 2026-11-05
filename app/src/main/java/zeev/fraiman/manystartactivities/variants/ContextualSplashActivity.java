package zeev.fraiman.manystartactivities.variants;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class ContextualSplashActivity extends AppCompatActivity {

    private View mainView;
    private ImageView ivLogo;
    private TextView tvGreeting;
    private TextView tvBattery;
    private ObjectAnimator jitterAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_contextual);

        mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivLogo = findViewById(R.id.ivContextLogo);
        tvGreeting = findViewById(R.id.tvContextGreeting);
        tvBattery = findViewById(R.id.tvContextBattery);
        Button btnEnter = findViewById(R.id.btnEnterContextual);

        applyTimeBasedGreeting();
        applyBatteryContext();

        btnEnter.setOnClickListener(v -> {
            TargetActivity.start(this, getString(R.string.v15_title), getString(R.string.trigger_v15));
            finish();
        });
    }

    private void applyTimeBasedGreeting() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (hour >= 5 && hour < 12) {
            // Morning
            tvGreeting.setText(R.string.v15_morning);
            tvGreeting.setTextColor(Color.parseColor("#B45309"));
            mainView.setBackgroundColor(Color.parseColor("#FEF3C7")); // Warm amber tint
        } else if (hour >= 12 && hour < 18) {
            // Afternoon
            tvGreeting.setText(R.string.v15_afternoon);
            tvGreeting.setTextColor(Color.parseColor("#1D4ED8"));
            mainView.setBackgroundColor(Color.parseColor("#E0F2FE")); // Sky blue tint
        } else {
            // Evening / Night
            tvGreeting.setText(R.string.v15_evening);
            tvGreeting.setTextColor(Color.parseColor("#F1F5F9"));
            tvBattery.setTextColor(Color.parseColor("#CBD5E1"));
            mainView.setBackgroundColor(Color.parseColor("#0F172A")); // Slate dark tint
        }
    }

    private void applyBatteryContext() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);

        int level = -1;
        int scale = -1;
        if (batteryStatus != null) {
            level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        }

        int batteryPct = (int) ((level / (float) scale) * 100);
        if (batteryPct < 0) batteryPct = 50; // Fallback

        if (batteryPct < 15) {
            tvBattery.setText(getString(R.string.v15_battery_low, batteryPct));
            tvBattery.setTextColor(Color.parseColor("#DC2626"));

            // Shivering/shaking animation when low battery
            jitterAnim = ObjectAnimator.ofFloat(ivLogo, "translationX", -10f, 10f);
            jitterAnim.setDuration(80);
            jitterAnim.setRepeatCount(ValueAnimator.INFINITE);
            jitterAnim.setRepeatMode(ValueAnimator.REVERSE);
            jitterAnim.start();
        } else {
            tvBattery.setText(getString(R.string.v15_battery_normal, batteryPct));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (jitterAnim != null) {
            jitterAnim.cancel();
        }
    }
}
