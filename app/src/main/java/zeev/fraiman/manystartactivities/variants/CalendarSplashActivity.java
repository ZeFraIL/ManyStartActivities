package zeev.fraiman.manystartactivities.variants;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CalendarView;
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

public class CalendarSplashActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "calendar_streak_prefs";
    private static final String KEY_LAST_DAY = "last_launch_day_of_year";
    private static final String KEY_STREAK = "current_streak_count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_calendar);

        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvStreak = findViewById(R.id.tvCalendarStreak);
        CalendarView calendarView = findViewById(R.id.calendarViewSplash);
        ImageView ivClockHand = findViewById(R.id.ivClockHand);
        Button btnEnter = findViewById(R.id.btnEnterCalendar);

        // Highlight today on CalendarView
        calendarView.setDate(System.currentTimeMillis(), false, true);

        // Calculate daily streak
        int streak = updateAndCalculateStreak();
        tvStreak.setText(getString(R.string.v25_streak, streak));

        // Clock hand spinning backwards animation settling into logo
        ObjectAnimator clockAnim = ObjectAnimator.ofFloat(ivClockHand, "rotation", 0f, -1080f);
        clockAnim.setDuration(1800);
        clockAnim.setInterpolator(new DecelerateInterpolator());
        clockAnim.start();

        btnEnter.setOnClickListener(v -> {
            TargetActivity.start(this, getString(R.string.v25_title), getString(R.string.trigger_v25));
            finish();
        });
    }

    private int updateAndCalculateStreak() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Calendar calendar = Calendar.getInstance();
        int todayDay = calendar.get(Calendar.DAY_OF_YEAR);

        int lastDay = prefs.getInt(KEY_LAST_DAY, -1);
        int currentStreak = prefs.getInt(KEY_STREAK, 0);

        if (lastDay == -1) {
            // First launch
            currentStreak = 1;
        } else if (todayDay == lastDay + 1) {
            // Consecutive day launch
            currentStreak++;
        } else if (todayDay != lastDay) {
            // Missed a day -> reset streak to 1
            currentStreak = 1;
        }

        prefs.edit()
                .putInt(KEY_LAST_DAY, todayDay)
                .putInt(KEY_STREAK, currentStreak)
                .apply();

        return currentStreak;
    }
}
