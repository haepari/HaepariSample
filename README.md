# Haepari Sample - Android Base Project

클린 아키텍처 기반의 안드로이드 베이스 프로젝트입니다. 최신 안드로이드 개발 트렌드와 모범 사례를 적용했습니다.

## 📋 프로젝트 개요

이 프로젝트는 여러 안드로이드 앱을 빠르게 개발할 수 있도록 설계된 베이스 템플릿입니다.

### 기본 화면 구성
- **Splash**: 앱 시작 시 로딩 화면
- **Login**: 로그인 화면 (스킵 가능)
- **Home**: 메인 홈 화면

## 🏗️ 아키텍처

### 멀티모듈 구조
```
HaepariSample/
├── app/                    # 앱 모듈
├── domain/                 # 도메인 레이어 (비즈니스 로직)
├── data/                   # 데이터 레이어 (Repository, DataSource)
├── presentation/           # 프레젠테이션 레이어 (UI, ViewModel)
└── buildSrc/              # 빌드 로직 및 의존성 관리
```

### 기술 스택

#### 아키텍처 & 패턴
- **Clean Architecture**: 레이어 분리를 통한 유지보수성 향상
- **MVVM Pattern**: ViewModel + LiveData/StateFlow
- **Multi-Module**: 기능별 모듈 분리

#### 의존성 주입
- **Hilt**: 의존성 주입 프레임워크

#### 네트워킹
- **Retrofit**: REST API 통신
- **OkHttp**: HTTP 클라이언트
- **Gson**: JSON 파싱

#### 로컬 데이터
- **Room**: 로컬 데이터베이스
- **DataStore**: 설정 및 환경설정 저장

#### UI
- **Jetpack Compose**: 선언형 UI 프레임워크
- **Material 3**: 머티리얼 디자인 3
- **Navigation Component**: 화면 전환 관리

#### 비동기 처리
- **Kotlin Coroutines**: 비동기 프로그래밍
- **Flow**: 리액티브 데이터 스트림

#### 로깅 & 크래시 리포팅
- **Timber**: 로그 관리
- **Firebase Crashlytics**: 크래시 리포팅

#### 기타
- **Lottie**: 애니메이션
- **Widget**: 홈 화면 위젯 지원

## 📱 주요 기능

### 1. 인증 시스템
- DataStore 기반 인증 상태 관리
- 로그인 스킵 기능 지원
- 자동 로그인 체크

### 2. 네비게이션
- Jetpack Navigation Compose
- 로그인 상태에 따른 자동 화면 전환
- Deep Link 지원 준비

### 3. 위젯
- 기본 위젯 템플릿 제공
- 앱 실행 연동

## 🛠️ 빌드 설정

### Gradle 버전 카탈로그
`gradle/libs.versions.toml`에서 모든 의존성 버전 관리

### 빌드 설정
- **minSdk**: 28
- **targetSdk**: 36
- **compileSdk**: 36
- **JVM Target**: 17

### 빌드 타입
- **Debug**: 디버그 빌드 (패키지명 .debug 접미사)
- **Release**: 릴리즈 빌드 (ProGuard 적용)

## 🚀 시작하기

### 1. Firebase 설정
Firebase Console에서 프로젝트 생성 후 `google-services.json` 파일을 다운로드하여 `app/` 디렉토리에 교체하세요.

```bash
# 현재 google-services.json은 템플릿입니다.
# Firebase Console에서 실제 파일로 교체해야 합니다.
```

### 2. 빌드 및 실행
```bash
./gradlew clean build
./gradlew installDebug
```

### 3. 로그인 스킵 설정
로그인 없이 앱을 사용하려면:
1. 로그인 화면에서 "로그인 없이 계속" 버튼 클릭
2. 또는 코드에서 `PreferencesManager.setSkipLogin(true)` 호출

## 📦 모듈 설명

### app
- MainActivity
- Application 클래스
- Widget 구현
- 리소스 파일

### domain
- Use Cases
- Domain Models
- Repository Interfaces

### data
- Repository 구현
- Room Database
- Retrofit API
- DataStore

### presentation
- Compose UI
- ViewModels
- Navigation
- UI Theme

## 🔧 커스터마이징

### API 엔드포인트 변경
`data/src/main/java/.../remote/api/ApiService.kt` 파일에서 `BASE_URL` 수정

```kotlin
companion object {
    const val BASE_URL = "https://your-api-endpoint.com/"
}
```

### 앱 이름 및 패키지명 변경
1. `buildSrc/src/main/kotlin/AppConfig.kt`에서 `applicationId` 수정
2. `app/src/main/res/values/strings.xml`에서 `app_name` 수정
3. 패키지 리팩토링 (Android Studio: Refactor > Rename Package)

### 테마 커스터마이징
`presentation/src/main/java/.../ui/theme/` 디렉토리에서 색상, 타이포그래피 수정

## 📝 개발 가이드

### 새로운 화면 추가
1. `presentation/ui/` 하위에 화면별 패키지 생성
2. Screen Composable 작성
3. ViewModel 생성
4. `NavGraph.kt`에 라우트 추가
5. `Screen.kt`에 sealed class 추가

### 새로운 API 추가
1. `data/remote/api/ApiService.kt`에 엔드포인트 정의
2. `data/repository/`에 Repository 구현
3. `domain/repository/`에 Repository 인터페이스 정의
4. `domain/usecase/`에 UseCase 생성
5. ViewModel에서 UseCase 사용

### Room 엔티티 추가
1. `data/local/database/entity/`에 Entity 클래스 생성
2. `data/local/database/dao/`에 DAO 인터페이스 생성
3. `AppDatabase.kt`에 Entity 및 DAO 등록

## 🧪 테스팅

### 단위 테스트
```bash
./gradlew test
```

### 계측 테스트
```bash
./gradlew connectedAndroidTest
```

## 📄 라이선스

이 프로젝트는 베이스 템플릿으로 자유롭게 사용 및 수정 가능합니다.

## 🤝 기여

프로젝트 개선 아이디어나 버그 리포트는 언제나 환영합니다!

## 📞 문의

프로젝트 관련 문의사항이 있으시면 이슈를 등록해주세요.

---

**Happy Coding! 🎉**
