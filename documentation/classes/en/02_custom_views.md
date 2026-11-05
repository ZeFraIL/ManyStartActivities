# Custom Views Analysis (English)

---

## 📌 1. Class: FlashlightOverlayView

### 1. General Information
- **Class Name:** `FlashlightOverlayView`
- **Type:** Custom View (Extends `View`)
- **Role in Application:** Renders a solid dark canvas overlay and erases a circular spot under touch coordinates using `PorterDuff.Mode.CLEAR` to create a flashlight spotlight effect.
- **Interaction with Other Components:** Embedded in `activity_splash_flashlight.xml` over the logo image, managed by `FlashlightSplashActivity`.

### 2. Variables (Fields)
| Name | Type | Purpose | Where Used |
|---|---|---|---|
| `overlayBitmap` | `Bitmap` | Offscreen bitmap buffer for dark overlay mask | `onSizeChanged()`, `onDraw()` |
| `overlayCanvas` | `Canvas` | Canvas wrapper for drawing onto `overlayBitmap` | `onSizeChanged()`, `onDraw()` |
| `clearPaint` | `Paint` | Paint configured with `PorterDuff.Mode.CLEAR` | `init()`, `onDraw()` |
| `touchX`, `touchY` | `float` | Touch coordinates | `onTouchEvent()`, `onDraw()` |
| `spotRadius` | `float` | Spotlight circle radius (220px) | `init()`, `onDraw()` |

### 3. Key Methods
- `init()`: Configures paint with `PorterDuffXfermode(PorterDuff.Mode.CLEAR)`.
- `onDraw(Canvas canvas)`: If touch is active, draws a clear circle at `(touchX, touchY)` onto `overlayCanvas` and renders `overlayBitmap` onto main canvas.
- `onTouchEvent(MotionEvent event)`: Updates touch coordinates and calls `invalidate()`.

### 8. Explanation in Simple Words
`FlashlightOverlayView` is like **scratch-off black paper placed over a picture**. As you drag your finger across the screen, it erases the black layer right under your touch so you can see the logo underneath!

---

## 📌 2. Class: ParticleSurfaceView

### 1. General Information
- **Class Name:** `ParticleSurfaceView`
- **Type:** Custom SurfaceView (Extends `SurfaceView`, implements `SurfaceHolder.Callback`)
- **Role in Application:** Runs a particle physics engine in a dedicated background thread (`RenderThread`), rendering 120 particles converging from screen edges to form the logo shape.
- **Interaction with Other Components:** Embedded in `activity_splash_particle.xml`, managed by `ParticleSplashActivity`, notifying completion via `OnParticlesAssembledListener`.

### 2. Key Internal Classes
- **`Particle`:** Holds position `(x, y)`, target coordinates `(targetX, targetY)`, and radius. `update()` moves particle towards target using `x += (targetX - x) * 0.08f`.
- **`RenderThread`:** Background thread executing the render loop at ~60 FPS off the UI thread via `SurfaceHolder.lockCanvas()` and `unlockCanvasAndPost()`.

### 8. Explanation in Simple Words
`ParticleSurfaceView` is like a **movie screen with its own private graphics engine**. Instead of straining the phone's main UI thread, it runs a background worker thread at 60 frames per second to calculate and draw 120 glowing light dots flying together to assemble the logo!
