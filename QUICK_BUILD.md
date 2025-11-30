# ⚡ Quick Build Guide - coDY

## 🎯 Build APK nhanh nhất (để test/share)

### Windows:

```bash
# Debug APK (không cần keystore)
gradlew.bat assembleDebug

# File output:
# app\build\outputs\apk\debug\app-debug.apk
```

### Sau khi build xong:

1. Tìm file APK tại: `app\build\outputs\apk\debug\app-debug.apk`
2. Copy file này
3. Gửi cho bạn bè qua:
   - Telegram
   - Discord  
   - Google Drive
   - Dropbox
   - Email

### Cài đặt APK:

1. Chuyển file APK vào điện thoại Android
2. Mở file APK
3. Bật "Cài đặt từ nguồn không xác định" nếu được hỏi
4. Nhấn "Cài đặt"
5. Done! 🎉

---

## 📦 Build Release APK (cho production)

### Bước 1: Tạo Keystore (chỉ làm 1 lần)

```bash
keytool -genkey -v -keystore cody-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias cody
```

### Bước 2: Build

```bash
gradlew.bat assembleRelease
```

File output: `app\build\outputs\apk\release\app-release.apk`

---

## 🚀 Upload lên Google Drive (Cách dễ nhất)

1. Build APK debug
2. Upload file `app-debug.apk` lên Google Drive
3. Click chuột phải → "Chia sẻ" → "Lấy link"
4. Chọn "Bất kỳ ai có link đều có thể xem"
5. Copy link và share!

**Link mẫu:**
```
https://drive.google.com/file/d/YOUR_FILE_ID/view?usp=sharing
```

---

## 📱 Test trên điện thoại thật

### Cách 1: USB
1. Bật "Chế độ nhà phát triển" trên điện thoại
2. Bật "USB Debugging"
3. Kết nối USB với máy tính
4. Trong Android Studio, click Run ▶️

### Cách 2: Wireless (không cần dây)
1. Điện thoại và máy tính cùng WiFi
2. Android Studio → Pair Devices Using Wi-Fi
3. Quét QR code
4. Click Run ▶️

---

## 🎨 Tạo Icon App

Sử dụng tool online:
- https://romannurik.github.io/AndroidAssetStudio/
- https://icon.kitchen/

Upload logo coDY, chọn màu, download và thay thế trong:
`app/src/main/res/mipmap-*/`

---

## ✅ Checklist nhanh

- [ ] Build APK thành công
- [ ] Test trên ít nhất 1 thiết bị thật
- [ ] Tất cả tính năng hoạt động
- [ ] Không có crash
- [ ] Icon và tên app đúng
- [ ] Ready to share! 🚀
