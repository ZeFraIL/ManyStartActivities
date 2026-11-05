# Basic Variants Analysis (Variants 1-6) - English

---

## 📌 1. Class: StandardSplashActivity (Variant 1)
- **Role:** Classic student splash pattern with scaling logo, manual "Continue Now" button, and 7-second `CountDownTimer` auto-navigation.
- **Key APIs:** `ObjectAnimator`, `CountDownTimer`.
- **Simple Explanation:** The traditional model: logo grows gently, 7-second countdown ticks away, or user taps button to continue immediately.

---

## 📌 2. Class: PreloadSplashActivity (Variant 2)
- **Role:** Simulated preloading initialization tasks (network check, config loading, database prep) with `LinearProgressIndicator` and dynamic status text.
- **Key APIs:** `LinearProgressIndicator`, `Handler.postDelayed()`.
- **Simple Explanation:** Shows the user that the app is preparing assets behind the scenes before entering.

---

## 📌 3. Class: OnboardingSplashActivity (Variant 3)
- **Role:** 3-slide introductory carousel built with `ViewPager2` and `TabLayout` dot indicators.
- **Key APIs:** `ViewPager2`, `TabLayoutMediator`.
- **Simple Explanation:** A quick 3-card guide letting users preview features with Skip or Get Started buttons.

---

## 📌 4. Class: InteractiveSplashActivity (Variant 4)
- **Role:** Interactive unlock requiring user action: swipe up or triple-tap the logo.
- **Key APIs:** `GestureDetector`, `View.OnClickListener`.
- **Simple Explanation:** Interactive entry lock where swiping up or tapping the logo 3 times unlocks the app.

---

## 📌 5. Class: MotionSplashActivity (Variant 5)
- **Role:** Multi-element animation sequence using `AnimatorSet` to slide, scale, and fade components into place.
- **Key APIs:** `AnimatorSet`, `ObjectAnimator`.
- **Simple Explanation:** Movie intro style: logo scales in center, title slides from left, tagline emerges from bottom.

---

## 📌 6. Class: SystemSplashActivity (Variant 6)
- **Role:** Official Android 12+ SplashScreen API (`androidx.core:core-splashscreen`).
- **Key APIs:** `SplashScreen.installSplashScreen()`, `Theme.SplashScreen`.
- **Simple Explanation:** Modern official system standard providing seamless window background splash without blank frames.
