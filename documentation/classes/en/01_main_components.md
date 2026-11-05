# Main Components Analysis (English)

---

## 📌 1. Class: MainActivity

### 1. General Information
- **Class Name:** `MainActivity`
- **Type:** Activity (Main Screen)
- **Role in Application:** Acts as the central catalog host of the application. Displays a list of all 25 splash screen variants in a `RecyclerView` and handles educational info dialogs.
- **Interaction with Other Components:** Initializes `VariantAdapter` with `SplashVariant` model objects, launches individual splash screen activities, or displays educational information dialogs (`MaterialAlertDialogBuilder`).

### 2. Variables (Fields)
| Name | Type | Purpose | Where Used |
|---|---|---|---|
| `rvVariants` | `RecyclerView` | Main list container for variant cards | `onCreate()` |
| `variants` | `List<SplashVariant>` | Data list holding 25 `SplashVariant` model items | `onCreate()` |
| `adapter` | `VariantAdapter` | RecyclerView adapter linking list items to UI cards | `onCreate()` |

### 3. Methods

**Method Name:** `onCreate`
- **Type:** `protected`
- **Return Value:** `void`
- **Parameters:** `savedInstanceState` (`Bundle`)
- **What it does:**
  1. Enables edge-to-edge drawing (`WindowCompat.enableEdgeToEdge`).
  2. Sets layout `R.layout.activity_main`.
  3. Configures system insets padding.
  4. Finds `RecyclerView` and attaches `LinearLayoutManager`.
  5. Populates `variants` list with 25 model instances containing IDs, titles, descriptions, explanations, and Activity classes.
  6. Creates `VariantAdapter` implementing `OnVariantActionListener`.
  7. Attaches adapter to `RecyclerView`.

**Method Name:** `showVariantInfoDialog`
- **Type:** `private`
- **Return Value:** `void`
- **Parameters:** `variant` (`SplashVariant`)
- **What it does:** Constructs and displays a `MaterialAlertDialogBuilder` dialog showing the variant title, detailed educational explanation, a "Got It" close button, and a "Launch Demo" button.

### 8. Explanation in Simple Words
Think of `MainActivity` as the **table of contents of a textbook** or a **restaurant menu**. It shows all 25 available options, lets you read what each option is about (Info button), and lets you try out any option you want (Launch Demo button).

---

## 📌 2. Class: TargetActivity

### 1. General Information
- **Class Name:** `TargetActivity`
- **Type:** Activity (Destination Screen)
- **Role in Application:** Serves as the success destination screen reached after completing any of the 25 splash screen variants.
- **Interaction with Other Components:** Receives Intent Extras specifying the source variant name and transition trigger mechanism, and provides a button to return to `MainActivity`.

### 2. Variables (Fields)
| Name | Type | Purpose | Where Used |
|---|---|---|---|
| `EXTRA_SOURCE_VARIANT` | `String` (constant) | Intent Extra key for source variant name | `start()`, `onCreate()` |
| `EXTRA_TRIGGER_MECHANISM` | `String` (constant) | Intent Extra key for transition trigger | `start()`, `onCreate()` |
| `tvSourceVariant` | `TextView` | Displays source variant title | `onCreate()` |
| `tvTriggerMechanism` | `TextView` | Displays transition trigger explanation | `onCreate()` |
| `btnBackToCatalog` | `Button` | Button returning user to `MainActivity` | `onCreate()` |

### 3. Methods
- `start(Context context, String sourceVariant, String triggerMechanism)`: Static helper method constructing Intent with extras and starting `TargetActivity`.
- `onCreate(Bundle savedInstanceState)`: Reads Intent extras, updates UI text, and handles back button click with `FLAG_ACTIVITY_CLEAR_TOP`.

### 8. Explanation in Simple Words
`TargetActivity` is like the **finish line of a race**. No matter which splash screen you launched or what gesture/sensor/timer was used, when you arrive here, the screen confirms: "Welcome! You reached the main app via trigger X."

---

## 📌 3. Class: SplashVariant

### 1. General Information
- **Class Name:** `SplashVariant`
- **Type:** Model Class
- **Role in Application:** Holds the metadata for a single splash screen pattern item in the catalog.
- **Fields:** `id`, `title`, `description`, `explanation`, `targetActivityClass`.

### 8. Explanation in Simple Words
`SplashVariant` is like an **ID card for a splash screen variant**. It stores all information about that option: its name, description, educational explanation, and which screen to open.

---

## 📌 4. Class: VariantAdapter

### 1. General Information
- **Class Name:** `VariantAdapter`
- **Type:** Adapter (`RecyclerView.Adapter`)
- **Role in Application:** Binds the `SplashVariant` data list to card items in `RecyclerView`.
- **Interface:** `OnVariantActionListener` callbacks for `onVariantClick` and `onInfoClick`.

### 8. Explanation in Simple Words
`VariantAdapter` is the **waiter of the RecyclerView**. It takes the data items, puts them onto card views, and reports back to `MainActivity` when a button is clicked.
