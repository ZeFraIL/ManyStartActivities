package zeev.fraiman.manystartactivities.model;

import android.app.Activity;

public class SplashVariant {
    private final String id;
    private final String title;
    private final String description;
    private final String explanation;
    private final Class<? extends Activity> targetActivityClass;

    public SplashVariant(String id, String title, String description, String explanation, Class<? extends Activity> targetActivityClass) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.explanation = explanation;
        this.targetActivityClass = targetActivityClass;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getExplanation() {
        return explanation;
    }

    public Class<? extends Activity> getTargetActivityClass() {
        return targetActivityClass;
    }
}
