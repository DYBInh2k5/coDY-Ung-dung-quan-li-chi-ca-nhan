# coDY ✨

Your personal productivity companion with GenZ vibes! 🚀

Ứng dụng Android quản lý cá nhân được xây dựng bằng Kotlin và Jetpack Compose với thiết kế hiện đại, màu sắc vibrant và phong cách GenZ.

## Công nghệ sử dụng

- **Kotlin** - Ngôn ngữ lập trình chính
- **Jetpack Compose** - UI framework hiện đại
- **Material Design 3** - Thiết kế giao diện
- **Room Database** - Lưu trữ dữ liệu local
- **ViewModel & StateFlow** - Quản lý state
- **Navigation Compose** - Điều hướng giữa các màn hình
- **Coroutines & Flow** - Xử lý bất đồng bộ

## Tính năng

- ✅ **Quản lý công việc** - To-do list với mức độ ưu tiên
- 📝 **Ghi chú cá nhân** - Lưu trữ ghi chú nhanh
- 💰 **Quản lý chi tiêu** - Theo dõi chi tiêu theo danh mục
- 🎯 **Theo dõi thói quen** - Xây dựng thói quen tốt với streak tracking
- ⏰ **Lịch nhắc nhở** - Nhắc nhở công việc với tùy chọn lặp lại
- 🎨 Giao diện Material Design 3
- 💾 Lưu trữ dữ liệu offline với Room

## Cấu trúc dự án

```
app/
├── data/
│   ├── dao/           # Data Access Objects
│   ├── database/      # Room Database
│   ├── model/         # Data models
│   └── repository/    # Repository pattern
├── ui/
│   ├── navigation/    # Navigation setup
│   ├── screen/        # UI screens
│   ├── theme/         # Material theme
│   └── viewmodel/     # ViewModels
└── MainActivity.kt
```

## Yêu cầu

- Android Studio Hedgehog (2023.1.1) trở lên
- JDK 17
- Android SDK 24+
- Gradle 8.2+

## Cài đặt

1. Mở project trong Android Studio
2. Sync Gradle files
3. Chạy app trên emulator hoặc thiết bị thật

## 🚀 Build & Release

### Build APK nhanh (Debug):
```bash
gradlew.bat assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

### Build APK Release:
```bash
gradlew.bat assembleRelease
```

### Build AAB (cho Play Store):
```bash
gradlew.bat bundleRelease
```

📖 Xem chi tiết: [RELEASE_GUIDE.md](RELEASE_GUIDE.md) | [QUICK_BUILD.md](QUICK_BUILD.md)

## 📱 Download

- **Google Play Store**: Coming soon! 🚀
- **Direct APK**: [Releases](https://github.com/yourusername/cody-app/releases)
