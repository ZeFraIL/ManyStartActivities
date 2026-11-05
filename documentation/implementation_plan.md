# Implementation Plan - Add Variants 24 and 25

Implement 2 new start screen variants proposed by the user (Mirror CameraX Start and Calendar Streak), expanding the catalog to 25 variants total. All UI text remains in English.

## User Review Required

> [!IMPORTANT]
> - Catalog item titles will now expand from 1 to 25.
> - Variant 24 (Mirror CameraX Start): Uses CameraX (`PreviewView`) for a front camera mirror feed with an overlaid logo mask/crown, runtime permission handling, and an explicit privacy disclaimer ("Camera feed is processed live on-device only and never stored").
> - Variant 25 (Calendar Streak): Displays `CalendarView` highlighting today's date, calculates daily launch streaks via `SharedPreferences`, and animates rotating clock hands settling into the logo.

## Proposed Changes

### Dependencies & Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/Users/zeevf/AndroidStudioProjects/ManyStartActivities/gradle/libs.versions.toml)
- Add CameraX version and libraries (`camera-core`, `camera-camera2`, `camera-lifecycle`, `camera-view`).

#### [MODIFY] [build.gradle.kts](file:///C:/Users/zeevf/AndroidStudioProjects/ManyStartActivities/app/build.gradle.kts)
- Include CameraX implementation dependencies.

#### [MODIFY] [AndroidManifest.xml](file:///C:/Users/zeevf/AndroidStudioProjects/ManyStartActivities/app/src/main/AndroidManifest.xml)
- Add `android.permission.CAMERA` permission and register `CameraSplashActivity` and `CalendarSplashActivity`.

---

### Strings & Catalog Update

#### [MODIFY] [strings.xml](file:///C:/Users/zeevf/AndroidStudioProjects/ManyStartActivities/app/src/main/res/values/strings.xml)
- Add English string resources and educational explanations for Variants 24 and 25.

#### [MODIFY] [MainActivity.java](file:///C:/Users/zeevf/AndroidStudioProjects/ManyStartActivities/app/src/main/java/zeev/fraiman/manystartactivities/MainActivity.java)
- Register Variants 24 and 25 in the catalog `RecyclerView`.

---

### New Variant Activities & Layouts

#### [NEW] [CameraSplashActivity.java](file:///C:/Users/zeevf/AndroidStudioProjects/ManyStartActivities/app/src/main/java/zeev/fraiman/manystartactivities/variants/CameraSplashActivity.java) & [activity_splash_camera.xml](file:///C:/Users/zeevf/AndroidStudioProjects/ManyStartActivities/app/src/main/res/layout/activity_splash_camera.xml)
- **Variant 24 (Mirror CameraX Start):** `PreviewView` with front camera binding, camera mask overlay, privacy disclaimer banner, and runtime permission request logic.

#### [NEW] [CalendarSplashActivity.java](file:///C:/Users/zeevf/AndroidStudioProjects/ManyStartActivities/app/src/main/java/zeev/fraiman/manystartactivities/variants/CalendarSplashActivity.java) & [activity_splash_calendar.xml](file:///C:/Users/zeevf/AndroidStudioProjects/ManyStartActivities/app/src/main/res/layout/activity_splash_calendar.xml)
- **Variant 25 (Calendar Streak):** `CalendarView` with today's date highlighted, `SharedPreferences` streak tracker, and rotating clock hand animation settling into `@drawable/logo`.

---

## Verification Plan

### Automated Build
- Execute `./gradlew assembleDebug` to verify compilation with CameraX dependencies.

### Manual Verification
- Verify catalog displays 25 items.
- Launch Variant 24 to test CameraX front camera binding, privacy disclaimer, and permission flow.
- Launch Variant 25 to test CalendarView date highlighting, streak counter, and clock rotation animation.
