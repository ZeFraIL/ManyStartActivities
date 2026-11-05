package zeev.fraiman.manystartactivities.variants;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import zeev.fraiman.manystartactivities.R;
import zeev.fraiman.manystartactivities.TargetActivity;

public class QuoteSplashActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "quote_splash_prefs";
    private static final String KEY_LAST_INDEX = "last_quote_index";
    private static final String KEY_VIEW_COUNT = "total_quotes_viewed";

    private static class ShakespeareQuote {
        final String text;
        final String source;

        ShakespeareQuote(String text, String source) {
            this.text = text;
            this.source = source;
        }
    }

    private final ShakespeareQuote[] quotes = new ShakespeareQuote[]{
            new ShakespeareQuote("\"To be, or not to be, that is the question.\"", "— William Shakespeare (Hamlet)"),
            new ShakespeareQuote("\"All the world's a stage, and all the men and women merely players.\"", "— William Shakespeare (As You Like It)"),
            new ShakespeareQuote("\"We know what we are, but know not what we may be.\"", "— William Shakespeare (Hamlet)"),
            new ShakespeareQuote("\"The course of true love never did run smooth.\"", "— William Shakespeare (A Midsummer Night's Dream)"),
            new ShakespeareQuote("\"Love all, trust a few, do wrong to none.\"", "— William Shakespeare (All's Well That Ends Well)"),
            new ShakespeareQuote("\"Better three hours too soon than a minute too late.\"", "— William Shakespeare (The Merry Wives of Windsor)"),
            new ShakespeareQuote("\"Listen to many, speak to a few.\"", "— William Shakespeare (Hamlet)"),
            new ShakespeareQuote("\"Sweet are the uses of adversity.\"", "— William Shakespeare (As You Like It)"),
            new ShakespeareQuote("\"Self-love, my liege, is not so vile a sin as self-neglecting.\"", "— William Shakespeare (Henry V)"),
            new ShakespeareQuote("\"Be not afraid of greatness. Some are born great, some achieve greatness...\"", "— William Shakespeare (Twelfth Night)")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_splash_quote);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvQuoteText = findViewById(R.id.tvQuoteText);
        TextView tvQuoteAuthor = findViewById(R.id.tvQuoteAuthor);
        TextView tvQuoteViewCount = findViewById(R.id.tvQuoteViewCount);
        Button btnEnter = findViewById(R.id.btnEnterQuote);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int lastIndex = prefs.getInt(KEY_LAST_INDEX, -1);
        int viewCount = prefs.getInt(KEY_VIEW_COUNT, 0);

        // Pick random quote avoiding immediate consecutive duplicate
        Random random = new Random();
        int selectedIndex = random.nextInt(quotes.length);
        if (selectedIndex == lastIndex && quotes.length > 1) {
            selectedIndex = (selectedIndex + 1) % quotes.length;
        }

        viewCount++;

        // Save updated SharedPreferences data
        prefs.edit()
                .putInt(KEY_LAST_INDEX, selectedIndex)
                .putInt(KEY_VIEW_COUNT, viewCount)
                .apply();

        ShakespeareQuote selectedQuote = quotes[selectedIndex];
        tvQuoteText.setText(selectedQuote.text);
        tvQuoteAuthor.setText(selectedQuote.source);
        tvQuoteViewCount.setText(getString(R.string.v16_views_count, viewCount));

        btnEnter.setOnClickListener(v -> {
            TargetActivity.start(this, getString(R.string.v16_title), getString(R.string.trigger_v16));
            finish();
        });
    }
}
