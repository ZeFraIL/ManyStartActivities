package zeev.fraiman.manystartactivities.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    public interface OnParticlesAssembledListener {
        void onAssembled();
    }

    private RenderThread renderThread;
    private OnParticlesAssembledListener assembledListener;

    public ParticleSurfaceView(Context context) {
        super(context);
        init();
    }

    public ParticleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParticleSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getHolder().addCallback(this);
    }

    public void setOnParticlesAssembledListener(OnParticlesAssembledListener listener) {
        this.assembledListener = listener;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        renderThread = new RenderThread(holder);
        renderThread.setRunning(true);
        renderThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if (renderThread != null) {
            renderThread.setRunning(false);
            try {
                renderThread.join();
            } catch (InterruptedException ignored) {
            }
        }
    }

    private class RenderThread extends Thread {
        private final SurfaceHolder holder;
        private boolean isRunning = false;
        private final List<Particle> particles = new ArrayList<>();
        private final Paint particlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean hasNotifiedAssembled = false;

        RenderThread(SurfaceHolder holder) {
            this.holder = holder;
            particlePaint.setColor(Color.parseColor("#38BDF8"));
        }

        void setRunning(boolean running) {
            this.isRunning = running;
        }

        private void initParticles(int width, int height) {
            particles.clear();
            Random random = new Random();
            int count = 120;
            float centerX = width / 2f;
            float centerY = height / 2f;

            for (int i = 0; i < count; i++) {
                // Spawn at screen edges
                float startX, startY;
                if (random.nextBoolean()) {
                    startX = random.nextBoolean() ? 0 : width;
                    startY = random.nextFloat() * height;
                } else {
                    startX = random.nextFloat() * width;
                    startY = random.nextBoolean() ? 0 : height;
                }

                // Target points forming concentric circles in center
                double angle = (i / (double) count) * Math.PI * 2;
                float radius = 50 + (i % 4) * 35;
                float targetX = centerX + (float) (Math.cos(angle) * radius);
                float targetY = centerY + (float) (Math.sin(angle) * radius);

                particles.add(new Particle(startX, startY, targetX, targetY, 4 + random.nextFloat() * 6));
            }
        }

        @Override
        public void run() {
            int width = getWidth();
            int height = getHeight();
            if (width <= 0 || height <= 0) return;

            initParticles(width, height);

            while (isRunning) {
                Canvas canvas = null;
                try {
                    canvas = holder.lockCanvas();
                    if (canvas != null) {
                        synchronized (holder) {
                            // Clear background
                            canvas.drawColor(Color.parseColor("#0F172A"));

                            boolean allArrived = true;
                            for (Particle p : particles) {
                                p.update();
                                canvas.drawCircle(p.x, p.y, p.radius, particlePaint);

                                if (!p.isNearTarget()) {
                                    allArrived = false;
                                }
                            }

                            if (allArrived && !hasNotifiedAssembled) {
                                hasNotifiedAssembled = true;
                                if (assembledListener != null) {
                                    post(() -> assembledListener.onAssembled());
                                }
                            }
                        }
                    }
                } finally {
                    if (canvas != null) {
                        holder.unlockCanvasAndPost(canvas);
                    }
                }

                try {
                    Thread.sleep(16); // ~60 FPS
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    private static class Particle {
        float x, y;
        final float targetX, targetY;
        final float radius;

        Particle(float startX, float startY, float targetX, float targetY, float radius) {
            this.x = startX;
            this.y = startY;
            this.targetX = targetX;
            this.targetY = targetY;
            this.radius = radius;
        }

        void update() {
            x += (targetX - x) * 0.08f;
            y += (targetY - y) * 0.08f;
        }

        boolean isNearTarget() {
            return Math.abs(x - targetX) < 2f && Math.abs(y - targetY) < 2f;
        }
    }
}
