# Advanced Variants Analysis (Variants 19-25) - English

---

## 📌 1. Class: MapSplashActivity (Variant 19)
- **Role:** Interactive map camera zooming onto Beer Sheva coordinates (31.2530° N, 34.7915° E) and dropping logo pin marker.
- **Simple Explanation:** Map zooms in from space and drops a pin marker onto Beer Sheva.

---

## 📌 2. Class: WebViewSplashActivity (Variant 20)
- **Role:** Loads HTML5 Canvas from `assets/splash.html` with `@JavascriptInterface` bridge triggering native navigation.
- **Simple Explanation:** Hybrid HTML5 web page inside app; tapping JS button communicates directly with Java code.

---

## 📌 3. Class: ParticleSplashActivity (Variant 21)
- **Role:** Manages `ParticleSurfaceView` off-thread render loop displaying 120 particles converging into logo shape.
- **Simple Explanation:** 120 light dots fly from screen edges to assemble the logo in center.

---

## 📌 4. Class: MoodSplashActivity (Variant 22)
- **Role:** Material 3 `ChipGroup` asking "How are you feeling today?" ("🚀 Productive", "😌 Calm", "🤔 Learning", "🎮 Relaxed"), changing theme tint and passing chosen mood via Intent.
- **Simple Explanation:** Select your mood for the day, altering theme colors and personalizing entrance.

---

## 📌 5. Class: BottomSheetSplashActivity (Variant 23)
- **Role:** `CoordinatorLayout` with peeked `BottomSheetBehavior` container and a 3-second Y-translation bounce hint animation.
- **Simple Explanation:** Bottom sheet drawer with quick access buttons that bounces if untouched to hint dragability.

---

## 📌 6. Class: CameraSplashActivity (Variant 24)
- **Role:** Front camera mirror feed using CameraX `PreviewView`, logo mask overlay, and explicit privacy disclaimer banner.
- **Simple Explanation:** Live camera mirror with logo mask overlay and privacy guarantee that photo is never stored.

---

## 📌 7. Class: CalendarSplashActivity (Variant 25)
- **Role:** Highlighted `CalendarView`, daily launch streak calculation in `SharedPreferences`, and spinning clock hands into logo.
- **Simple Explanation:** Shows today on calendar, tracks consecutive daily app launch streaks, and spins clock hands.
