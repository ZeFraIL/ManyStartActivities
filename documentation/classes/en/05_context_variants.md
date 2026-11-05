# Context Variants Analysis (Variants 13-18) - English

---

## 📌 1. Class: BreathingSplashActivity (Variant 13)
- **Role:** Sleeping logo pulse (`ValueAnimator`). Holding finger down (`ACTION_DOWN`) accelerates pulse and wakes app.
- **Simple Explanation:** App is sleeping and breathing gently; press and hold to wake it up.

---

## 📌 2. Class: RitualSplashActivity (Variant 14)
- **Role:** Culinary domain ritual: multi-touch pinching or tapping to salt the dish and reveal logo particles.
- **Simple Explanation:** Cooking ritual: tap with 2 fingers to sprinkle salt and reveal the app logo.

---

## 📌 3. Class: ContextualSplashActivity (Variant 15)
- **Role:** Greeting & theme adapting to time of day (`Calendar`) and low battery jitter (<15%, `BatteryManager`).
- **Simple Explanation:** Environmentally aware: says "Good Morning" or "Good Evening", and shivers if battery is low.

---

## 📌 4. Class: QuoteSplashActivity (Variant 16)
- **Role:** Random Shakespeare quote selection, saving view counts and preventing consecutive repeats in `SharedPreferences`.
- **Simple Explanation:** Displays a new Shakespeare quote each time, remembering total quote views in memory.

---

## 📌 5. Class: SlideSplashActivity (Variant 17)
- **Role:** Slide-to-unlock slider snapping back to 0% if released before reaching 100%.
- **Simple Explanation:** Slide-to-unlock mechanism: drag slider all the way to 100% to enter.

---

## 📌 6. Class: VideoSplashActivity (Variant 18)
- **Role:** Looping silent background video using `VideoView` / `MediaPlayer` (`R.raw.splash_video`).
- **Simple Explanation:** Live video background playing behind logo in a silent loop.
