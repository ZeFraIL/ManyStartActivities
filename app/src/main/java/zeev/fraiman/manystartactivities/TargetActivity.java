package zeev.fraiman.manystartactivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class TargetActivity extends AppCompatActivity {

    public static final String EXTRA_SOURCE_VARIANT = "extra_source_variant";
    public static final String EXTRA_TRIGGER_MECHANISM = "extra_trigger_mechanism";

    public static void start(Context context, String sourceVariant, String triggerMechanism) {
        Intent intent = new Intent(context, TargetActivity.class);
        intent.putExtra(EXTRA_SOURCE_VARIANT, sourceVariant);
        intent.putExtra(EXTRA_TRIGGER_MECHANISM, triggerMechanism);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_target);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String sourceVariant = getIntent().getStringExtra(EXTRA_SOURCE_VARIANT);
        String triggerMechanism = getIntent().getStringExtra(EXTRA_TRIGGER_MECHANISM);

        TextView tvSourceVariant = findViewById(R.id.tvSourceVariant);
        TextView tvTriggerMechanism = findViewById(R.id.tvTriggerMechanism);
        Button btnBackToCatalog = findViewById(R.id.btnBackToCatalog);

        if (sourceVariant != null) {
            tvSourceVariant.setText(sourceVariant);
        }
        if (triggerMechanism != null) {
            tvTriggerMechanism.setText(triggerMechanism);
        }

        btnBackToCatalog.setOnClickListener(v -> {
            Intent intent = new Intent(TargetActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}
