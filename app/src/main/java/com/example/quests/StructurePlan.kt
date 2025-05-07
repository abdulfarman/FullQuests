/**
app/
├── data/
│   ├── model/              # DTOs and raw data models (API, DB)
│   ├── remote/             # Retrofit APIs or Firebase access
│   ├── repository/         # Repository implementations
│   └── local/              # Room DB, SharedPreferences, etc.

├── domain/
│   ├── model/              # Business models (used across use-cases/UI)
│   ├── repository/         # Repository interfaces (abstractions)
│   └── usecase/            # Business logic use-cases

├── presentation/
│   ├── screen/
│   │   ├── home/
│   │   │   ├── HomeScreen.kt
│   │   │   ├── HomeViewModel.kt
│   │   │   └── HomeUiState.kt
│   │   └── login/
│   │       ├── LoginScreen.kt
│   │       ├── LoginViewModel.kt
│   │       └── LoginUiState.kt
│   ├── navigation/         # NavGraph, routes
│   ├── components/         # Reusable composables
│   └── theme/              # Theme, typography, colors

├── di/
│   └── AppModule.kt        # Hilt or Koin modules

├── utils/                  # Utility classes, extensions

└── MainActivity.kt
*/
