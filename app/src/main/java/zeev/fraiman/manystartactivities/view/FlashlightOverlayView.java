package zeev.fraiman.manystartactivities.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class FlashlightOverlayView extends View {

    private Bitmap overlayBitmap;
    private Canvas overlayCanvas;
    private Paint overlayPaint;
    private Paint clearPaint;
    private float touchX = -1f;
    private float touchY = -1f;
    private boolean isTouching = false;
    private float spotRadius;

    public FlashlightOverlayView(Context context) {
        super(context);
        init();
    }

    public FlashlightOverlayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlashlightOverlayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        spotRadius = 220f;

        overlayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        overlayPaint.setColor(Color.BLACK);

        clearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            overlayBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            overlayCanvas = new Canvas(overlayBitmap);
            resetOverlay();
        }
    }

    public void resetOverlay() {
        if (overlayCanvas != null) {
            overlayCanvas.drawColor(Color.BLACK, PorterDuff.Mode.SRC);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (overlayBitmap != null) {
            if (isTouching && touchX >= 0 && touchY >= 0) {
                // Erase dark overlay at touch position
                overlayCanvas.drawCircle(touchX, touchY, spotRadius, clearPaint);
            }
            canvas.drawBitmap(overlayBitmap, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                touchX = event.getX();
                touchY = event.getY();
                isTouching = true;
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isTouching = false;
                invalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }
}
