# 📱 Android Application Documentation (Level 10/10) - ManyStartActivities

---

### 🧾 General Information
- **Project Name:** ManyStartActivities (Start Screens Showcase)
- **Author:** Zeev Fraiman
- **Date:** February 2025
- **Language:** Java
- **Development Environment:** Android Studio (Gradle / AGP 9.5.0)
- **Android Versions:** minSdk 28 (Android 9.0) / targetSdk 37 (Android 15+)

---

### 🎯 Project Goal
- **Problem Solved:** Android development students frequently build generic, plain, and dull Splash Screens (typically a static logo, a hardcoded 5-second timer, and a basic transition button). This application provides a comprehensive educational showcase catalog of 25 distinct, creative, interactive, and pedagogical splash screen patterns.
- **Importance:** The splash screen is the user's first impression of an application. Beyond creating an engaging User Experience (UX), advanced splash screens serve as an outstanding teaching tool covering a broad range of Android technologies (animations, sensors, multimedia, CameraX, SurfaceView, WebView, SharedPreferences, gesture detection, and Material Design 3).
- **Target Audience:** Educators, lecturers, Android development students, and developers seeking inspiration and practical code patterns.

---

### 📌 Application Requirements

#### Functional Requirements
1. **Main Catalog (`MainActivity`):** Displays a list of 25 splash screen patterns in a Material 3 CardView-based `RecyclerView`.
2. **Educational Info Dialog ("Info"):** Every catalog item features an Info button displaying a detailed `MaterialAlertDialogBuilder` dialog explaining the UX benefits and Android APIs/technical concepts taught by that variant.
3. **Transparent Target Transition (`TargetActivity`):** Every splash screen passes intent extras to a destination screen, transparently displaying which variant launched it and the exact trigger mechanism (timer, button, gesture, sensor, slider, etc.) with a return button back to the catalog.
4. **25 Diverse Splash Screen Patterns:**
   - **Variant 1 (Standard Student Splash):** Scaling logo animation, manual Continue button, and automatic `CountDownTimer` fallback.
   - **Variant 2 (Pre-loading & Init Splash):** Background task simulation (network, configs, database) with `LinearProgressIndicator`.
   - **Variant 3 (Mini-Onboarding Slider):** 3-slide introduction carousel using `ViewPager2` and dot page indicators.
   - **Variant 4 (Interactive Gesture Entrance):** Screen unlock requiring a swipe-up gesture or triple-tapping the logo.
   - **Variant 5 (Sequential Motion Assembly):** Multi-element animation sequence using `AnimatorSet`.
   - **Variant 6 (System Splash Theme - Android 12+):** Official `androidx.core:core-splashscreen` API integration with window background theme.
   - **Variant 7 (Flashlight Reveal):** Custom Canvas drawing erasing a dark overlay under finger touch using `PorterDuff.Mode.CLEAR`.
   - **Variant 8 (Assemble Logo - Spring Puzzle):** 4 corner logo pieces flying to center via `OvershootInterpolator` spring physics.
   - **Variant 9 (Pendulum Tilt Physics):** `SensorManager` accelerometer tilt integration to sway logo dynamically.
   - **Variant 10 (Swipe Unboxing):** Gift box unboxing with downward ribbon swipe gesture via `GestureDetector` and `ViewPropertyAnimator`.
   - **Variant 11 (Typewriter with Typo Correction):** Letter-by-letter string typing using `Handler.postDelayed()`, backspacing a typo, and typing the correction.
   - **Variant 12 (Portal Fly-In):** Camera zooming into a blurred destination preview with `RenderEffect` blur removal (API 31+).
   - **Variant 13 (App Breathing - Hold to Wake):** Sleeping pulse animation (`ValueAnimator`); pressing and holding (`ACTION_DOWN`) accelerates pulse and wakes app.
   - **Variant 14 (Domain Micro-Ritual):** Culinary domain ritual – multi-touch pinching/tapping to salt a dish and reveal logo.
   - **Variant 15 (Contextual Greeting):** Greeting & theme adapting to time of day (`Calendar`) and low battery jitter (<15%, `BatteryManager`).
   - **Variant 16 (Quote of the Day):** Random Shakespeare quote selection, saving view counts and preventing consecutive repeats in `SharedPreferences`.
   - **Variant 17 (Slide to Start):** Slide-to-unlock slider snapping back to 0% if released before 100%.
   - **Variant 18 (Video Background):** Looping silent background video using `VideoView` / `MediaPlayer`.
   - **Variant 19 (Interactive Map & Pin Drop):** Map camera zooming into Beer Sheva coordinates and dropping a logo pin marker.
   - **Variant 20 (HTML5 / JS Hybrid):** Loads an HTML5 Canvas animation from `assets` with `@JavascriptInterface` bridge triggering native navigation.
   - **Variant 21 (Particle Physics Engine):** 120 particle objects converging off the UI thread in a custom `SurfaceView` render loop.
   - **Variant 22 (Mood Selector Persona):** Material 3 `ChipGroup` single-selection altering background theme tint and passing persona data via `Intent`.
   - **Variant 23 (Bottom Sheet Entrance):** `CoordinatorLayout` with peeked `BottomSheetBehavior` sheet and a 3-second Y-translation bounce hint animation.
   - **Variant 24 (Mirror CameraX Start):** Live front camera feed with CameraX, logo mask overlay, and transparent privacy disclaimer banner.
   - **Variant 25 (Calendar Streak & Time Context):** Highlighted `CalendarView`, daily launch streak calculation in `SharedPreferences`, and spinning clock hands.

#### Non-Functional Requirements
- **Performance:** 60 FPS smooth rendering. Heavy particle physics calculations run on a dedicated background thread in `SurfaceView`.
- **Usability:** 100% English UI, clean layout, fully accessible using Material Design 3 components.
- **Reliability & Error Handling:** Proper lifecycle cleanup for timers and threads in `onDestroy()`, with graceful fallback mechanisms when hardware sensors, camera, or video files are absent.

---

### 🧠 General Architecture
- **Chosen Pattern:** Catalog Pattern Multi-Activity / Model-View-Adapter (MVA).
- **Justification:** Since this is an educational demonstration project, isolating every splash screen pattern into its own standalone `Activity` allows students and teachers to inspect, understand, and copy specific code patterns as independent units without complex navigation framework coupling.
- **Key System Components:**
  - `MainActivity`: Main catalog host with `RecyclerView` and educational info dialogs.
  - `SplashVariant`: Data model.
  - `VariantAdapter`: Catalog adapter.
  - `TargetActivity`: Shared destination screen.
  - `zeev.fraiman.manystartactivities.variants`: Package housing all 25 splash screen Activities.
  - `zeev.fraiman.manystartactivities.view`: Package housing custom Views (`FlashlightOverlayView`, `ParticleSurfaceView`).

---

### 🧩 UML Diagram
```text
 +-------------------+         1..*         +-------------------+
 |   MainActivity    | -------------------> |    VariantAdapter |
 +-------------------+                      +-------------------+
           |                                          |
           | Opens Dialog                             | Renders Items
           v                                          v
 +----------------------------+             +-------------------+
 | MaterialAlertDialogBuilder |             |   SplashVariant   |
 +----------------------------+             +-------------------+
           |
           | Launches Demo
           v
 +--------------------------------------------------------------+
 |                  variants (25 Activities)                    |
 | (Standard, Preload, Onboarding, Gesture, Motion, System,     |
 |  Flashlight, Assemble, Pendulum, Unboxing, Typewriter,       |
 |  Portal, Breathing, Ritual, Contextual, Quote, Slide,        |
 |  Video, Map, WebView, Particle, Mood, BottomSheet, Camera,   |
 |  Calendar)                                                   |
 +--------------------------------------------------------------+
           |                                          |
           | Custom Views                             | Triggers Completion
           v                                          v
 +-----------------------------------+      +-------------------+
 | FlashlightOverlayView /           |      |   TargetActivity  |
 | ParticleSurfaceView               |      +-------------------+
 +-----------------------------------+
```

**Explanation:**
- **Package Separation:** Decouples models, adapters, variant activities, and custom views.
- **Scalability:** New splash variants can be easily added by introducing a new Activity class into `variants` and registering it in `MainActivity`.

---

### 🧩 Detailed Class Descriptions

#### 📌 Class: MainActivity
- **Role:** Main catalog activity.
- **Responsibilities:** Initializes `RecyclerView`, populates data for all 25 variants, and displays educational info dialogs.
- **Key Methods:**
  - `onCreate(Bundle savedInstanceState)` — Configures layout, edge-to-edge, and RecyclerView.
  - `showVariantInfoDialog(SplashVariant variant)` — Shows `MaterialAlertDialogBuilder` dialog with technical explanation and launch button.

#### 📌 Class: TargetActivity
- **Role:** Destination activity reached after splash completion.
- **Responsibilities:** Reads Intent extras and displays the source variant title and transition trigger mechanism.

#### 📌 Model Class: SplashVariant
- **Role:** Data model for catalog items.
- **Key Fields:** `id`, `title`, `description`, `explanation`, `targetActivityClass`.

#### 📌 Class: FlashlightOverlayView
- **Role:** Custom View for flashlight mask erasing.
- **Responsibilities:** Draws dark canvas overlay and erases circular spot at touch coordinates via `PorterDuff.Mode.CLEAR`.

#### 📌 Class: ParticleSurfaceView
- **Role:** Custom SurfaceView for particle physics rendering.
- **Responsibilities:** Manages dedicated background `RenderThread` calculating position, velocity, and attraction for 120 particles at 60 FPS.

---

### 🔄 Application Workflow Scenario
1. **Catalog Load:** User opens app and lands on `MainActivity` displaying 25 cards.
2. **Review Info:** User taps "Info" button on any card to read technical & UX details.
3. **Launch Demo:** User taps "Launch Demo".
4. **Splash Execution:** Selected variant Activity runs its custom logic (animations, gestures, sensors, timers).
5. **Target Transition:** `TargetActivity` opens, displaying transition trigger details.
6. **Return:** Tapping "Return to Catalog" brings user back to `MainActivity`.

---

### 🎨 UI/UX Analysis
- **100% English UI:** Prepares students for international software development standards.
- **Material Design 3:** Utilizes Material Cards, Chips, Buttons, and Bottom Sheets.
- **Micro-interactions:** Immediate visual feedback for touches, holds, battery alerts, and gestures.

---

### ⚙️ Threads & Async Operations
- **UI Thread / Handler:** Uses `Handler(Looper.getMainLooper())` for typewriter loops and interval timers.
- **Background RenderThread (`SurfaceView`):** `ParticleSurfaceView` handles physics and rendering off the UI thread to prevent ANR.
- **Memory Leak Prevention:** Removes Handler callbacks and cancels Animators/threads in `onDestroy()`.

---

### 💾 Data Management
- **Local Persistence with `SharedPreferences`:**
  - `QuoteSplashActivity`: Persists quote view count and last shown quote index to avoid consecutive duplicates.
  - `CalendarSplashActivity`: Stores last launch day-of-year and calculates consecutive launch streaks (Daily Streak).

---

### 🌐 Media, HTML5 & Networking
- **`VideoView` / `MediaPlayer`:** Plays silent looping video background (`R.raw.splash_video`).
- **`WebView` & `JavascriptInterface`:** Loads local HTML5 Canvas from `assets/splash.html` and exposes `WebBridge` for JS-to-Native calls.

---

### 🔐 Security & Privacy
- **Camera Privacy Banner:** `CameraSplashActivity` displays a clear privacy disclaimer assuring users that camera frames are processed on-device only and never stored or uploaded.

---

### 🧪 Testing
- **Automated Compilation:** Verified via `./gradlew assembleDebug`.
- **Manual Verification:** Tested runtime permissions (Camera), hardware fallback simulation modes, and intent navigation across all 25 variants.

---

### 🐞 Error Handling & Fallbacks
- **Missing Sensor:** Automates simulation fallback mode if run on emulators without an accelerometer.
- **Missing Video:** Displays fallback glowing background animation if `splash_video.mp4` is missing from `res/raw/`.

---

### ⚡ Performance Optimization
- Off-UI-thread rendering in SurfaceView.
- Efficient memory allocation avoiding GC thrashing during animations.

---

### 🚀 Scalability
- New splash screen patterns can be effortlessly added by creating an Activity in `variants` and adding an entry in `MainActivity`.
