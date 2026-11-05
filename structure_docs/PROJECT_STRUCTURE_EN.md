# ManyStartActivities Project Architecture & Structure (Student Guide)

---

## 1. The Big Picture

The **ManyStartActivities** application is an interactive educational showcase catalog displaying 25 distinct and creative ways to build an Android Splash Screen. The application consists of a main catalog host screen, a data model, custom views, and 25 standalone splash screen activities that pass transition trigger data to a shared destination screen.

### Real-Life Analogy: An Interactive Science Museum
Think of this application as a **large computer science museum**:
- **Main Entry Hall (`MainActivity`):** The reception desk displaying a decorated board with 25 exhibit rooms.
- **Exhibition Info Plaque ("Info" button):** An info plaque beside each exhibit explaining how it works.
- **Exhibit Rooms (25 Variant Activities):** 25 separate rooms. Each room houses a different hands-on exhibit (flashlight, spring puzzle, interactive map, camera mirror, particle physics).
- **Exit Gift Shop (`TargetActivity`):** The room you arrive at after finishing any exhibit, confirming completion and showing which room you just visited.

The code is separated into components so we don't dump all exhibits into a single messy room — this separation maintains clean structure, prevents crashes, and makes adding new exhibits effortless.

---

## 2. Directory and File Overview

The project source code is organized into clean packages:

```text
zeev.fraiman.manystartactivities/
├── MainActivity.java            # Main Catalog Host Activity
├── TargetActivity.java          # Shared Destination Activity
├── model/
│   └── SplashVariant.java       # Catalog Item Data Model
├── adapter/
│   └── VariantAdapter.java      # Adapter connecting data to RecyclerView
├── view/
│   ├── FlashlightOverlayView.java # Custom View for flashlight mask erasing
│   └── ParticleSurfaceView.java   # Custom SurfaceView for off-thread particles
└── variants/                    # Package housing 25 independent splash activities
    ├── StandardSplashActivity.java
    ├── PreloadSplashActivity.java
    ├── CameraSplashActivity.java
    └── ... (22 more activities)
```

### Explanation of Components and Directories:
1. **Activity:** An Android component that displays a window and handles user interaction. The `variants/` folder contains 25 such screens.
2. **Model (`SplashVariant.java`):** A clean data class holding in-memory metadata (title, description, educational explanation).
3. **Adapter (`VariantAdapter.java`):** The "middleman". It receives the data list and binds each item onto a visual card in `RecyclerView`.
4. **Custom View (`view/`):** Custom components drawn directly via Canvas code (such as flashlight spot erasing or particle physics).
5. **Resources (`res/`):**
   - `res/layout/`: XML files defining screen and card UI layouts.
   - `res/values/strings.xml`: Centralized English text string resources.
   - `res/drawable/`: Image and icon assets (including `logo.png`).

---

## 3. Component Relationships & Data Flow

Here is how the components interact in the application:

```text
[MainActivity]
      │
      ├── 1. Creates list ───> [SplashVariant (Models)]
      │                               │
      ├── 2. Passes to ───────────────┼───> [VariantAdapter]
      │                                            │
      ├── 3. User taps "Info"                       │ 4. User taps "Launch Demo"
      │          │                                 v
      v          v                     [Variant Activity (e.g., Camera)]
[MaterialAlertDialogBuilder]                       │
  (Displays educational info)                      │ 5. Completion trigger
                                                   v
                                           [TargetActivity]
                                    (Displays source & trigger info)
```

---

## 4. Concrete Project Example: Particle Physics Engine (Variant 21)

Let's trace a complete end-to-end user execution scenario:

1. **Catalog Click:** User taps "Launch Demo" on card #21 in the catalog.
2. **Activity Launch:** `MainActivity` constructs an Intent launching `ParticleSplashActivity`:
   ```java
   Intent intent = new Intent(MainActivity.this, ParticleSplashActivity.class);
   startActivity(intent);
   ```
3. **Layout Load:** `ParticleSplashActivity` sets layout `activity_splash_particle.xml` containing `ParticleSurfaceView`.
4. **Off-Thread Rendering:** `ParticleSurfaceView` spawns a background `RenderThread` calculating 120 particle positions and drawing them onto canvas at 60 FPS.
5. **Completion Callback:** When particles converge into the center logo shape, `ParticleSurfaceView` notifies the Activity via callback:
   ```java
   surfaceView.setOnParticlesAssembledListener(this::onParticlesConverged);
   ```
6. **Destination Transition:** The Activity navigates to `TargetActivity`:
   ```java
   TargetActivity.start(this, "21. Particle Physics Engine", "Particle Convergence Engine Completed");
   ```

---

## 5. Short Glossary

1. **Activity:** A single focused screen in an Android app containing UI and execution code.
2. **Intent:** A messaging object used to navigate between screens and pass data extras.
3. **RecyclerView:** An efficient UI container for displaying scrollable lists of items.
4. **Adapter:** A bridge component that binds raw data items to visual views on screen.
5. **Custom View:** A UI component drawn programmatically by the developer on a `Canvas`.
6. **SurfaceView:** A dedicated drawing surface allowing off-UI-thread background rendering for smooth graphics.
7. **SharedPreferences:** A lightweight key-value storage mechanism for persisting small app settings and counters on the device.
