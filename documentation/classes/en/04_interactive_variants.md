# Interactive Variants Analysis (Variants 7-12) - English

---

## 📌 1. Class: FlashlightSplashActivity (Variant 7)
- **Role:** Spotlight reveal erasing dark overlay under finger touch using `FlashlightOverlayView` (`PorterDuff.Mode.CLEAR`).
- **Simple Explanation:** A spotlight game: drag finger across the dark screen to shine light and reveal the logo.

---

## 📌 2. Class: AssembleSplashActivity (Variant 8)
- **Role:** 4 corner logo pieces flying to center via `OvershootInterpolator` spring physics. Tapping pieces speeds up assembly.
- **Simple Explanation:** A spring puzzle where 4 pieces fly from corners to center like magnets.

---

## 📌 3. Class: PendulumSplashActivity (Variant 9)
- **Role:** `SensorManager` accelerometer tilt integration swaying logo dynamically as device tilts.
- **Simple Explanation:** Pendulum effect: tilting phone left or right sways the logo accordingly.

---

## 📌 4. Class: UnboxingSplashActivity (Variant 10)
- **Role:** Gift box ribbon unwrapping via downward swipe gesture (`GestureDetector`, `ViewPropertyAnimator`).
- **Simple Explanation:** Gift unboxing ritual: swipe ribbon down to unwrap and open the logo box.

---

## 📌 5. Class: TypewriterSplashActivity (Variant 11)
- **Role:** Letter-by-letter string typing with `Handler.postDelayed()`, backspacing a typo, and typing correction.
- **Simple Explanation:** Human typewriter effect typing, pausing at a typo, backspacing, and correcting.

---

## 📌 6. Class: PortalSplashActivity (Variant 12)
- **Role:** Camera zooming into blurred destination preview with `RenderEffect` blur removal (API 31+).
- **Simple Explanation:** Camera flies into a blurred target preview as blur vanishes over 2.5 seconds.
