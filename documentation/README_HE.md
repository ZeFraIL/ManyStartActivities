# 📱 תיעוד אפליקציית אנדרואיד (רמה 10/10) - ManyStartActivities

---

### 🧾 מידע כללי
- **שם הפרויקט:** ManyStartActivities (Start Screens Showcase / הפגנת מסכי פתיחה באנדרואיד)
- **מחבר:** זאב פריימן (Zeev Fraiman)
- **תאריך:** פברואר 2025
- **שפה:** Java
- **סביבת פיתוח:** Android Studio (Gradle / AGP 9.5.0)
- **גרסת אנדרואיד:** minSdk 28 (Android 9.0) / targetSdk 37 (Android 15+)

---

### 🎯 מטרת הפרויקט
- **איזו בעיה האפליקציה פותרת:** תלמידים וסטודנטים לפיתוח אנדרואיד נוטים ליצור מסכי פתיחה (Splash Screens) חדגוניים, בנאליים ומשעממים, המורכבים בדרך כלל מלוגו קבוע, טיימר שרירותי של 5 שניות וכפתור מעבר. האפליקציה מספקת קטלוג הדגמה מקיף של 25 תבניות שונות, יצירתיות, אינטראקטיביות ופדגוגיות של מסכי פתיחה.
- **מדוע בעיה זו חשובה:** מסך הפתיחה הוא הרושם הראשון (First Impression) של המשתמש מהאפליקציה. מעבר לחוויית משתמש (UX) מעולה, מסכי פתיחה מתקדמים מהווים כלי לימודי יוצא מן הכלל המכסה מגוון רחב של טכנולוגיות אנדרואיד (אנימציות, חיישנים, מולטימדיה, CameraX, SurfaceView, WebView, SharedPreferences, ג'סטות ו-Material Design 3).
- **קהל יעד:** מורים, מרצים, תלמידים וסטודנטים לפיתוח אפליקציות אנדרואיד, וכן מפתחים המחפשים השראה ודוגמאות קוד מעשיות.

---

### 📌 דרישות האפליקציה

#### דרישות פונקציונליות
1. **קטלוג ראשי (`MainActivity`):** הצגת רשימת 25 תבניות מסכי פתיחה ב-`RecyclerView` מבוסס Material 3 CardView.
2. **מידע לימודי בדיאלוג ("Info"):** לכל פריט בקטלוג כפתור מידע המציג דיאלוג מפורט (`MaterialAlertDialogBuilder`) המסביר את היתרון ה-UXי ואת טכנולוגיות האנדרואיד/API שנלמדות בקוד.
3. **מעבר ושקיפות בתוצאה (`TargetActivity`):** כל מסך פתיחה מעביר נתונים למסך יעד מרכזי, המציג באופן שקוף באיזה מנגנון בוצע המעבר (טיימר, כפתור, ג'סטה, חיישן, שחרור סליידר וכד') עם כפתור חזרה לקטלוג.
4. **25 תבניות מסכי פתיחה מגוונות:**
   - **תבנית 1 (Standard Student Splash):** לוגו עם אנימציית סקייל, כפתור ידני וטיימר אוטומטי (`CountDownTimer`).
   - **תבנית 2 (Pre-loading & Init Splash):** סימולציית טעינת נתונים ובדיקות רשת ברוקגראונד עם `LinearProgressIndicator`.
   - **תבנית 3 (Mini-Onboarding Slider):** קרוסלת 3 שקופיות הסבר מבוססת `ViewPager2` ואינדיקטור נקודות.
   - **תבנית 4 (Interactive Gesture Entrance):** שחרור מסך באמצעות ג'סטת סווייפ למעלה או 3 טאפים על הלוגו.
   - **תבנית 5 (Sequential Motion Assembly):** אנימציה מדורגת מורכבת של מספר אלמנטים באמצעות `AnimatorSet`.
   - **תבנית 6 (System Splash Theme - Android 12+):** שילוב הרכיב הרשמי `androidx.core:core-splashscreen` וערכת נושא חלון.
   - **תבנית 7 (Flashlight Reveal):** חשיפת הלוגו באמצעות פנס דינמי שנמחק בגרור אצבע (`PorterDuff.Mode.CLEAR`).
   - **תבנית 8 (Assemble Logo - Spring Puzzle):** 4 חלקי לוגו מפוזרים בפינות הטסים למרכז באנימציית קפיץ (`OvershootInterpolator`).
   - **תבנית 9 (Pendulum Tilt Physics):** קריאת חיישן תנועה (`SensorManager` / акселеромטר) לנדנוד הלוגו לפי הטיית המכשיר.
   - **תבנית 10 (Swipe Unboxing):** פתיחת אריזת מתנה וגרירת סרט עטיפה למטה באמצעות `GestureDetector` ו-`ViewPropertyAnimator`.
   - **תבנית 11 (Typewriter with Typo Correction):** הדפסת טקסט אות-אחר-אות ב-`Handler.postDelayed()`, ביצוע אופציות אנושיות, מחיקת שגיאה ב-Backspace ותיקונה.
   - **תבנית 12 (Portal Fly-In):** "תעופה" של המצלמה לתוך תצוגה מקדימה של המסך הבא תוך הסרת טשטוש (`RenderEffect` ב-Android 12+).
   - **תבנית 13 (App Breathing - Hold to Wake):** הלוגו "ישן" בדופק איטי; לחיצה ממושכת (`ACTION_DOWN`) מאיצה את הדופק ומעירה את האפליקציה.
   - **תבנית 14 (Domain Micro-Ritual):** ריטואל תחום (בישול) – המלחת המסך במולטי-טאפ ליצירת חלקיקי מלח החושפים את הלוגו.
   - **תבנית 15 (Contextual Greeting):** התאמת ברכה וצבעים לפי שעת היממה (`Calendar`) ורעדי לוגו במקרה של סוללה חלשה (<15%, `BatteryManager`).
   - **תבנית 16 (Quote of the Day):** הצגת ציטוט אקראי של שקספיר, שמירת היסטוריה ומניעת חזרתיות ב-`SharedPreferences`.
   - **תבנית 17 (Slide to Start):** סליידר שחרור בשיטת "Slide to unlock" עם קפיצה חזרה ל-0% אם לא הושלם ל-100%.
   - **תבנית 18 (Video Background):** וידאו רקע בלולאה שקטה (`VideoView` / `MediaPlayer`).
   - **תבנית 19 (Interactive Map & Pin Drop):** זום מפה מרוחק המתמקד בקואורדינטות באר שבע ונפילת נעץ לוגו.
   - **תבנית 20 (HTML5 / JS Hybrid):** טעינת קנבס HTML5 מ-`assets` וגשר תקשורת `@JavascriptInterface` להפעלת ניווט נייטיב.
   - **תבנית 21 (Particle Physics Engine):** מנוע 120 חלקיקים הרץ בשרשור רקע נפרד ב-`SurfaceView`.
   - **תבנית 22 (Mood Selector Persona):** בחירת מצב רוח ב-Material 3 `ChipGroup` המשנה את צבעי הנושא ומעבירה נתונים ב-`Intent`.
   - **תבנית 23 (Bottom Sheet Entrance):** שטורקה נשלפת ב-`CoordinatorLayout` עם אנימציית "הקפצה" כעבור 3 שניות של חוסר פעילות.
   - **תבנית 24 (Mirror CameraX Start):** מראה חיה של המצלמה הקדמית עם CameraX, מסכת לוגו ובאנר שקוף לשמירה על פרטיות.
   - **תבנית 25 (Calendar Streak & Time Context):** יומן מודגש (`CalendarView`), חישוב רצף ימי שימוש ב-`SharedPreferences` וסיבוב מחוגים.

#### דרישות לא-פונקציונליות
- **ביצועים:** תגובתיות מלאה וקצב רענון של 60 FPS. חישובי פיזיקה כבדים מתבצעים בשרשור נפרד ב-`SurfaceView`.
- **שמישות (Usability):** ממשק משתמש מלא באנגלית, קריא ומונגש, שימוש ב-Material Design 3 components.
- **אמינות ומניעת שגיאות:** טיפול בניהול מחזור חיים של טיימרים ושרשורים ב-`onDestroy()`, ומנגנוני פולבק (Fallback) כשחיישן או מצלמה אינם זמינים.

---

### 🧠 ארכיטקטורה כללית
- **הגישה שנבחרה:** Catalog Pattern Multi-Activity / Model-View-Adapter (MVA).
- **מדוע גישה זו נבחרה:** כיוון שמדובר באפליקציה לימודית-הדגמתית, הפרדה של כל תבנית מסך פתיחה ל-`Activity` עצמאית לחלוטין מאפשרת לתלמידים ולמורים לבדוק, להבין ולהעתיק את הקוד של מסך מסוים כרכיב בודד (Standalone Unit) ללא תלויות סבוכות במערכות ניווט מרכזיות.
- **רכיבי המערכת המרכזיים:**
  - `MainActivity`: קטלוג מרכזי עם `RecyclerView` ודיאלוגי מידע לימודיים.
  - `SplashVariant`: מודל הנתונים.
  - `VariantAdapter`: אדפטר הרשימה.
  - `TargetActivity`: מסך יעד סופי.
  - חבילת `zeev.fraiman.manystartactivities.variants`: מכילה את 25 מסכי הפתיחה.
  - חבילת `zeev.fraiman.manystartactivities.view`: מכילה רכיבי תצוגה מותאמים אישית (`FlashlightOverlayView`, `ParticleSurfaceView`).

---

### 🧩 דיאגרמת UML
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

**הסבר:**
- **הפרדת חבילות (Packages):** החלוקה ל-`model`, `adapter`, `variants`, ו-`view` יוצרת הפרדה ברורה בין הלוגיקה העסקית, תצוגת הקטלוג, והמימושים של מסכי הפתיחה.
- **יכולת הרחבה (Scalability):** ניתן להוסיף תבניות חדשות בקלות על ידי יצירת מחלקה חדשה בחבילת `variants` וספק המידע ב-`MainActivity`.

---

### 🧩 תיאור מפורט של המחלקות המרכזיות

#### 📌 מחלקה: MainActivity
- **תפקיד:** מסך הקטלוג הראשי.
- **אחריות:** אתחול ה-`RecyclerView`, בנאי הנתונים של 25 התבניות, והצגת דיאלוג המידע הלימודי.
- **מתודות מרכזיות:**
  - `onCreate(Bundle savedInstanceState)` — הגדרת ה-Layout, Edge-to-Edge וה-`RecyclerView`.
  - `showVariantInfoDialog(SplashVariant variant)` — הצגת דיאלוג `MaterialAlertDialogBuilder` עם ההסבר הלימודי וכפתור הפעלה.

#### 📌 מחלקה: TargetActivity
- **תפקיד:** מסך היעד המרכזי אליו מגיעים מכל מסכי הפתיחה.
- **אחריות:** קריאת ה-`Intent` Extras והצגת שם מסך המקור וסיבת הטריגר שהביאה למעבר.

#### 📌 מחלקת מודל: SplashVariant
- **תפקיד:** מודל הנתונים של תבנית מסך פתיחה.
- **שדות מרכזיים:** `id`, `title`, `description`, `explanation`, `targetActivityClass`.

#### 📌 מחלקה: FlashlightOverlayView
- **תפקיד:** View מותאם אישית לחשיפת פנס.
- **אחריות:** ציור שכבה שחורה ומחיקת קטע מעגלי ברדיוס שמתחת לאצבע בשיטת `PorterDuff.Mode.CLEAR`.

#### 📌 מחלקה: ParticleSurfaceView
- **תפקיד:** SurfaceView מותאם אישית למנוע חלקיקים.
- **אחריות:** מנהל שרשור רקע נפרד (`RenderThread`) המחשב מיקום, מהירות ומשיכה של 120 חלקיקים ומצייר אותם ב-`SurfaceHolder` ב-60 FPS.

---

### 🔄 דיאגרמת זרימת עבודת האפליקציה (Workflow)
1. **טעינת הקטלוג:** המשתמש פותח את האפליקציה ומגיע ל-`MainActivity` המציגה 25 כרטיסיות.
2. **עיון במידע הלימודי:** המשתמש לוחץ על כפתור "Info" בכרטיסיה וקורא את ההסבר ה-UXי והטכנולוגי.
3. **הפעלת הדגמה:** המשתמש לוחץ על "Launch Demo".
4. **ריצת מסך הפתיחה:** ה-Activity הנבחרת מופעלת ומבצעת את הלוגיקה שלה (אינטראקציה, אנימציה, חיישן, טיימר וכד').
5. **מעבר למסך היעד:** בסיום הלוגיקה מופעלת `TargetActivity` המציגה את סיבת המעבר.
6. **חזרה:** בלחיצה על "Return to Catalog" המשתמש חוזר לקטלוג.

---

### 🎨 ניתוח UI/UX
- **שפת הממשק:** כל הטקסטים והממשקים מוצגים באנגלית מלאה, מה שמכין את התלמידים לפיתוח אפליקציות בינלאומיות.
- **עיצוב Material Design 3:** שימוש ב-CardView, Chips, Material Buttons, ו-BottomSheet.
- **משוב מיקרו-אינטראקציות:** מתן משוב ויזואלי מיידי לכל נגיעה (אלמנטים מקפצים, שינוי צבעי רקע, רעד סוללה חלשה).

---

### ⚙️ עבודה עם שרשורים (Threads & Async)
- **UI Thread / Handler:** שימוש ב-`Handler(Looper.getMainLooper())` להדפסת טקסט במכונת כתיבה ואינטרוולים קצרים.
- **Background RenderThread (`SurfaceView`):** ב-`ParticleSurfaceView` חישובי הפיזיקה והציור מתבצעים בשרשור נפרד כדי למנוע קיפאון בממשק (ANR).
- **מניעת דליפות זיכרון:** מחיקת Callbacks של Handler וביטול אובייקטי `Animator` ושרשורים במתודת `onDestroy()`.

---

### 💾 עבודה עם נתונים
- **שמירה מקומית ב-`SharedPreferences`:**
  - ב-`QuoteSplashActivity`: שמירת מדד ציטוט אחרון (למניעת חזרתיות) ומדד סך הכל צפיות.
  - ב-`CalendarSplashActivity`: שמירת יום השנה של הפעלה אחרונה וחישוב רצף ימים רצופים (Daily Streak).

---

### 🌐 עבודה עם מדיה, HTML5 ורשת
- **`VideoView` / `MediaPlayer`:** הפעלת קובץ וידאו בלולאה שקטה בברקאונד (`R.raw.splash_video`).
- **`WebView` ו-`JavascriptInterface`:** טעינת HTML5 קנבס מקומי מ-`assets/splash.html` והגדרת Web Bridge המאפשר לקוד JavaScript לקרוא למתודה נייטיב באנדרואיד.

---

### 🔐 אבטחה ופרטיות (Privacy & Security)
- **דיסקליימר מצלמה:** ב-`CameraSplashActivity` מוצג באנר בולט המבהיר למשתמש כי שידור המצלמה מבוצע בזמן אמת על המכשיר בלבד, ואינו נשמר, מוקלט או מועבר לענן.

---

### 🧪 בדיקות (Testing)
- **בנייה אוטומטית:** הרצת `./gradlew assembleDebug` לווידוא תקינות קומפילציה מלאה.
- **בדיקות ידניות:** בדיקת הרשאות בזמן ריצה (Camera Permission), בדיקת מנגנוני פולבק (חיישן/וידאו חסר), ובדיקת תנועה בין מסכים.

---

### 🐞 טיפול בשגיאות ופוליבקים (Error Handling)
- **חיישן לא זמין:** במידה והאפליקציה רצה על אימולטור ללא акселеромטר, מופעל מצב סימולציה אוטומטי.
- **קובץ וידאו חסר:** במידה ואין קובץ `splash_video.mp4` ב-`res/raw/`, מופעל פולבק של אנימציית זוהר רכה.

---

### ⚡ ביצועים (Performance Optimization)
- ציור בשרשור נפרד ב-`SurfaceView`.
- טעינת תמונות ורכיבים בצורה יעילה ללא העמסת זיכרון (Memory Churn).

---

### 🚀 אפשרויות הרחבה
- הוספת מסכי פתיחה נוספים בקלות על ידי יצירת מחלקה חדשה בחבילת `variants` והוספת הפריט לקטלוג.
