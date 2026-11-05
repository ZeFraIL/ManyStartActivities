# 📱 توثيق تطبيق أנדרואיד (المستوى 10/10) - ManyStartActivities

---

### 🧾 معلومات عامة
- **اسم المشروع:** ManyStartActivities (معرض شاشات البدء / Start Screens Showcase)
- **المؤلف:** زئيف فرايمان (Zeev Fraiman)
- **التاريخ:** فبراير 2025
- **لغة البرمجة:** Java
- **بيئة التطوير:** Android Studio (Gradle / AGP 9.5.0)
- **إصدار أندرويد:** minSdk 28 (Android 9.0) / targetSdk 37 (Android 15+)

---

### 🎯 هدف المشروع
- **المشكلة التي يحلها التطبيق:** غالباً ما يقوم طلاب تطوير أندرويد بإنشاء شاشات بدء (Splash Screens) نمطية ومملة (شعار ثابت، مؤقت زمن لمدة 5 ثوانٍ، وزر انتقال عادي). يوفر هذا التطبيق معรضاً تعليمياً وتفاعلياً يضم 25 نمطاً مختلفاً ومبتكراً لشاشات البدء.
- **أهمية المشروع:** شاشة البدء هي الانطباع الأول للمستخدم. وإلى جانب تقديم تجربة مستخدم (UX) ممتازة، تعتبر شاشات البدء المتقدمة أداة تعليمية رائعة تغطي مجموعة واسعة من تقنيات أندرويد (الرسوم المتحركة، الحساسات، الوسائط المتعددة، CameraX، SurfaceView، WebView، SharedPreferences، الإيماءات و Material Design 3).
- **الجمهور المستهدف:** المعلمون والمحاضرون والطلاب في مجال تطوير تطبيقات أندرويد، والمطورون الذين يبحثون عن الإلهام وأمثلة برمجية عملية.

---

### 📌 متطلبات التطبيق

#### المتطلبات الوظيفية
1. **الكتالوج الرئيسي (`MainActivity`):** عرض قائمة تضم 25 نمطاً لشاشات البدء في `RecyclerView` يعتمد على بطاقات Material 3.
2. **مربع حوار تعليمي ("Info"):** تحتوي كل بطاقة في الكتالوج على زر معلومات يفتح مربع حوار (`MaterialAlertDialogBuilder`) يشرح فوائد تجربة المستخدم والتقنيات/واجهات البرمجة المستعملة.
3. **الانتقال الشفاف للهدف (`TargetActivity`):** تنقل كل شاشة بدء البيانات إلى شاشة هدف مركزية تعรض بوضوح اسم النمط وآلية الانتقال (مؤقت، زر، إيماءة، حساس، سحب إلخ) مع زر للعودة للكتالوج.
4. **25 نمطاً متنوعاً لشاشات البدء:**
   - **النمط 1 (Standard Student Splash):** رسوم متحركة لتكبير الشعار، زر يدوي ومؤقت تلقائي (`CountDownTimer`).
   - **النمط 2 (Pre-loading & Init Splash):** محاكاة تحميل البيانات والتحقق من الشبكة باستخدام `LinearProgressIndicator`.
   - **النمط 3 (Mini-Onboarding Slider):** شريط تعليمي مكون من 3 شرائح باستخدام `ViewPager2` ومؤشر نقاط.
   - **النمط 4 (Interactive Gesture Entrance):** إلغاء قفل الشاشة بإيماءة سحب للأعلى أو 3 نقرات على الشعار.
   - **النمط 5 (Sequential Motion Assembly):** رسوم متحركة متتالية لعناصر متعددة باستخدام `AnimatorSet`.
   - **النمط 6 (System Splash Theme - Android 12+):** دمج مكتبة `androidx.core:core-splashscreen` الرسمية وسمة النافذة.
   - **النمط 7 (Flashlight Reveal):** كشف الشعار باستخدام كشاف ضوئي يمسح الطبقة المظلمة (`PorterDuff.Mode.CLEAR`).
   - **النمط 8 (Assemble Logo - Spring Puzzle):** 4 قطع للشعار في الزوايا تتجمع في المركز بحركة نابضية (`OvershootInterpolator`).
   - **النمط 9 (Pendulum Tilt Physics):** استخدام حساس الحركة (`SensorManager` / التسارع) لأرجحة الشعار حسب إمالة الجهاز.
   - **النمط 10 (Swipe Unboxing):** فتح علبة هدية وسحب الشريط للأسفل باستخدام `GestureDetector` و `ViewPropertyAnimator`.
   - **النمط 11 (Typewriter with Typo Correction):** طباعة النص حرفاً بحرف عبر `Handler.postDelayed()` وتصحيح الخطأ بمفتاح Backspace.
   - **النمط 12 (Portal Fly-In):** انغماس الكاميرا داخل معاينة الشاشة التالية مع إزالة التمويه (`RenderEffect` في Android 12+).
   - **النمط 13 (App Breathing - Hold to Wake):** نبض الشعار النائم (`ValueAnimator`)؛ الضغط المطول (`ACTION_DOWN`) يسرع النبض ويوقظ التطبيق.
   - **النمط 14 (Domain Micro-Ritual):** طقس طهي خاص بالدومين – رش الملح بلمس متعدد لكشف الشعار.
   - **النمط 15 (Contextual Greeting):** التكيف مع الوقت (`Calendar`) واهتزاز الشعار عند انخفاض البطارية (<15%، `BatteryManager`).
   - **النمط 16 (Quote of the Day):** اقتباس عشوائي لشكسبير مع حفظ عدد المشاهدات ومنع التكرار عبر `SharedPreferences`.
   - **النمط 17 (Slide to Start):** شريط سحب "Slide to start" يعود إلى 0% إذا لم يتم إكماله إلى 100%.
   - **النمط 18 (Video Background):** فيديو خلفية صامت يتكرر في حلقة مغلقة (`VideoView` / `MediaPlayer`).
   - **النمط 19 (Interactive Map & Pin Drop):** تكبير خريطة بئر السبع وإسقاط دبوس الشعار.
   - **النمط 20 (HTML5 / JS Hybrid):** تحميل HTML5 Canvas من `assets` واستخدام `@JavascriptInterface` للانتقال الأصلي.
   - **النمط 21 (Particle Physics Engine):** محرك 120 جسيماً يعمل في خيط خلفي مستقل عبر `SurfaceView`.
   - **النمط 22 (Mood Selector Persona):** اختيار المزاج في Material 3 `ChipGroup` مع تغيير لون السمة ونقل البيانات.
   - **النمط 23 (Bottom Sheet Entrance):** لوحة سفيلة ناعمة في `CoordinatorLayout` مع حركة قفز تنبيهية بعد 3 ثوانٍ.
   - **النمط 24 (Mirror CameraX Start):** بث مباشر من الكاميرا الأمامية عبر CameraX مع قناع الشعار وإخلاء مسؤولية الخصوصية.
   - **النمط 25 (Calendar Streak & Time Context):** تقويم محدد (`CalendarView`)، وحساب الأيام المتتالية في `SharedPreferences` ودوران العقارب.

#### المتطلبات غير الوظيفية
- **الأداء:** أداء سلس بمعدل 60 إطاراً في الثانية (60 FPS).
- **سهولة الاستخدام (Usability):** واجهة باللغة الإنجليزية 100% مع تصميم عصري يعتمد على Material Design 3.
- **الاعتمادية:** إغلاق وإلغاء جميع المؤقتات والخيوط في `onDestroy()` مع وجود آليات بديلة (Fallback) عند غياب الحساسات أو الكاميرا.

---

### 🧠 المعمارية العامة
- **النمط المتبع:** Catalog Pattern Multi-Activity / Model-View-Adapter (MVA).
- **سبب اختيار هذا النمط:** لفصل كل نمط شاشة بدء في `Activity` مستقلة تماماً، مما يسهל على الطلاب والمعلمين فهم واقتباس الكود الخاص بكل شاشة كوحدة مستقلة (Standalone Unit).
- **המكونات الرئيسية:**
  - `MainActivity`: الكتالوج الرئيسي مع `RecyclerView` ومربعات الحوار.
  - `SplashVariant`: نموذج البيانات.
  - `VariantAdapter`: محول القائمة.
  - `TargetActivity`: الشاشة الهدف الشاملة.
  - `zeev.fraiman.manystartactivities.variants`: الحزمة التي تحتوي على 25 شاشة بدء.
  - `zeev.fraiman.manystartactivities.view`: الحزمة التي تحتوي على العروض المخصصة (`FlashlightOverlayView` و `ParticleSurfaceView`).

---

### 🧩 مخطط UML
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

---

### 🔄 سيناريو تدفق العمل (Workflow)
1. **تحميل الكتالوج:** يفتح المستخدم التطبيق ليصل إلى `MainActivity` التي تعرض 25 بطاقة.
2. **استعراض المعلومات:** ينقر المستخدم على زر "Info" في البطاقة لقراءة التفاصيل التعليمية والتقنية.
3. **تشغيل العرض:** ينقر المستخدم على "Launch Demo".
4. **تنفيذ شاشة البدء:** تعمل الـ Activity المحددة وتنفذ منطقها الخاص.
5. **الانتقال للهدف:** تفتح `TargetActivity` وتوضح آلية الانتقال.
6. **العودة:** النقر على "Return to Catalog" يرجع المستخدم للكتالوج.

---

### ⚙️ التعامل مع الخيوط (Threads & Async)
- **UI Thread / Handler:** استخدام `Handler(Looper.getMainLooper())` للتأخيرات والطباعة المتتالية.
- **خيط الخلفية (`SurfaceView`):** يقوم `ParticleSurfaceView` بالحسابات والفيزياء في خيط مستقل لمنع تجميد الواجهة (ANR).

---

### 💾 إدارة البيانات
- **التخزين المحلي باستخدام `SharedPreferences`:**
  - في `QuoteSplashActivity`: حفظ عدد المشاهدات وتجنب تكرار الاقتباسات.
  - في `CalendarSplashActivity`: حفظ آخر يوم تشغيل وحساب الأيام المتتالية (Daily Streak).

---

### 🌐 التعامل مع الوسائط و HTML5
- **`VideoView` / `MediaPlayer`:** تشغيل فيديو خلفية صامت ومكرר (`R.raw.splash_video`).
- **`WebView` و `JavascriptInterface`:** تحميل HTML5 Canvas محلي من `assets/splash.html` مع جسר لتشغيل الانتقال الأصلي.

---

### 🔐 الأمان والخصوصية (Security & Privacy)
- **إشعار الخصوصية للكاميرا:** تعרض `CameraSplashActivity` إشعاراً واضحاً يؤكد أن بث الكاميرا يعالج محلياً على الجهاز فقط ولا يتم حفظه أو رفعه.

---

### 🧪 الاختبارات (Testing)
- **البناء الآلي:** التحقق عبر 명령 `./gradlew assembleDebug`.
- **الاختبار اليدوي:** فحص أذونات الكاميرا وآليات البدائل (Fallback) والتنقل عبر جميع الشاشات ה-25.
