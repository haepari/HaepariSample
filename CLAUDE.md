# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Haepari Sample is a Clean Architecture-based Android template project using a multi-module structure. The project is designed to accelerate development of new Android apps with modern best practices.

**Default Screens**: Splash → Login (skippable) → Home

## Build Commands

### Essential Commands
```bash
# Clean build
./gradlew clean build

# Install debug build
./gradlew installDebug

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

### Module-specific Tests
```bash
# Test specific module
./gradlew :app:test
./gradlew :domain:test
./gradlew :data:test
./gradlew :presentation:test
```

## Architecture

### Multi-Module Structure
- **app**: Application class, MainActivity, widgets, and resources
- **domain**: Business logic layer (UseCases, Repository interfaces, Domain models)
- **data**: Data layer (Repository implementations, Room DB, Retrofit APIs, DataStore)
- **presentation**: UI layer (Jetpack Compose screens, ViewModels, Navigation, Theme)

**Dependency flow**: `app` → `presentation` → `domain` ← `data`

The `domain` module is pure Kotlin (no Android dependencies) and defines contracts that `data` implements.

### Key Technology Stack
- **UI**: Jetpack Compose + Material 3
- **DI**: Hilt (Dagger)
- **Networking**: Retrofit + OkHttp + Gson
- **Local Storage**: Room + DataStore (for preferences)
- **Async**: Kotlin Coroutines + Flow
- **Navigation**: Navigation Compose
- **Logging**: Timber
- **Crash Reporting**: Firebase Crashlytics

## Configuration Files

### Build Configuration
- **buildSrc/src/main/kotlin/AppConfig.kt**: Central build configuration (SDK versions, package name, version codes)
- **gradle/libs.versions.toml**: Version catalog for all dependencies

### API Configuration
- **data/.../remote/api/ApiService.kt**: Change `BASE_URL` constant for API endpoint
- **app/google-services.json**: Firebase configuration (replace with actual file from Firebase Console)

### Authentication State
- **data/.../local/datastore/PreferencesManager.kt**: Manages authentication state via DataStore
  - `IS_LOGGED_IN`: User authentication status
  - `SKIP_LOGIN`: Whether login was skipped
  - `USER_TOKEN`: Authentication token

## Navigation Flow

Navigation is centralized in `presentation/.../navigation/`:
- **NavGraph.kt**: Defines navigation graph with composable screens
- **Screen.kt**: Sealed class defining all routes (Splash, Login, Home)

The SplashScreen checks auth state (via PreferencesManager) and routes to Login or Home accordingly.

## Adding New Features

### Adding a Screen
1. Create package in `presentation/ui/<screen-name>/`
2. Create `<ScreenName>Screen.kt` (Composable)
3. Create `<ScreenName>ViewModel.kt` (if needed)
4. Add route in `Screen.kt` sealed class
5. Add composable route in `NavGraph.kt`

### Adding an API Endpoint
1. Define endpoint in `data/remote/api/ApiService.kt`
2. Create Repository implementation in `data/repository/`
3. Define Repository interface in `domain/repository/`
4. Create UseCase in `domain/usecase/`
5. Inject UseCase into ViewModel

### Adding a Room Entity
1. Create Entity in `data/local/database/entity/`
2. Create DAO in `data/local/database/dao/`
3. Register both in `data/local/database/AppDatabase.kt` `@Database` annotation
4. Increment database version if needed

## Hilt Dependency Injection

### Key DI Modules
- **data/di/NetworkModule.kt**: Provides Retrofit, OkHttp, ApiService
- **data/di/DatabaseModule.kt**: Provides Room Database and DAOs

All modules use `@InstallIn(SingletonComponent::class)` for app-wide singletons.

## Build Variants

### Debug Build
- Package suffix: `.debug`
- Debuggable, no minification
- Full logging enabled

### Release Build
- ProGuard enabled (minification + obfuscation)
- Resource shrinking enabled

## Widget Configuration

The project includes a sample widget implementation that is **disabled by default**.

### Widget Files
- **app/.../widget/SampleWidgetReceiver.kt**: Widget receiver implementation
- **app/res/xml/sample_widget_info.xml**: Widget provider configuration
- **app/res/layout/widget_sample.xml**: Widget layout

### Enabling the Widget
To enable the widget in your project:

1. Open `app/src/main/AndroidManifest.xml`
2. Find the Widget Receiver section (around line 31)
3. Change the attributes:
   ```xml
   <receiver
       android:name=".widget.SampleWidgetReceiver"
       android:enabled="true"
       android:exported="true">
   ```

### Disabling the Widget
To disable the widget (default state):
```xml
<receiver
    android:name=".widget.SampleWidgetReceiver"
    android:enabled="false"
    android:exported="false">
```

## Important Notes

- **Timber**: Use `Timber.d()`, `Timber.e()`, etc. for logging (not `Log`)
- **Coroutines**: Use `viewModelScope` in ViewModels for coroutines
- **Navigation**: Use `navController.navigate()` with `popUpTo` to manage back stack
- **DataStore**: All preference access is async (Flow-based)
- **Firebase**: Requires valid `google-services.json` from Firebase Console
- **Version Catalog**: Always reference dependencies via `libs.*` in build files
