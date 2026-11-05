# Анализ базовых вариантов (1-6) - Русский

- **1. StandardSplashActivity (Вариант 1):** Классическая модель с масштабированием логотипа, кнопкой продолжения и автотаймером `CountDownTimer` на 7 секунд.
- **2. PreloadSplashActivity (Вариант 2):** Симуляция фоновых задач загрузки с помощью `LinearProgressIndicator` и обновлением статусов.
- **3. OnboardingSplashActivity (Вариант 3):** Карусель из 3 вводных слайдов на `ViewPager2` с индикатором точек.
- **4. InteractiveSplashActivity (Вариант 4):** Интерактивный вход: свайп вверх или 3 тапа по логотипу.
- **5. MotionSplashActivity (Вариант 5):** Последовательная анимация нескольких элементов через `AnimatorSet`.
- **6. SystemSplashActivity (Вариант 6):** Официальный стандарт Android 12+ (`androidx.core:core-splashscreen`).
