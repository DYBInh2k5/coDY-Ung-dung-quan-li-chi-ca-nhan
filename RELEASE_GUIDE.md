# 🚀 Hướng dẫn xuất bản coDY App

## 📱 Cách 1: Google Play Store (Chính thức)

### Bước 1: Tạo Keystore (Chữ ký số)

Mở Terminal trong Android Studio và chạy:

```bash
keytool -genkey -v -keystore cody-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias cody
```

Nhập thông tin:
- Password cho keystore
- Tên, tổ chức, thành phố, quốc gia
- Password cho key

**⚠️ LƯU Ý QUAN TRỌNG:** 
- Lưu file `cody-release-key.jks` và passwords an toàn
- Mất keystore = không thể update app trên Play Store!

### Bước 2: Cấu hình Signing

Tạo file `keystore.properties` trong thư mục gốc:

```properties
storePassword=your_store_password
keyPassword=your_key_password
keyAlias=cody
storeFile=cody-release-key.jks
```

Thêm vào `.gitignore`:
```
keystore.properties
*.jks
```

### Bước 3: Build APK/AAB

**Build AAB (cho Play Store):**
```bash
gradlew.bat bundleRelease
```
File output: `app/build/outputs/bundle/release/app-release.aab`

**Build APK (để test):**
```bash
gradlew.bat assembleRelease
```
File output: `app/build/outputs/apk/release/app-release.apk`

### Bước 4: Upload lên Play Store

1. Truy cập: https://play.google.com/console
2. Tạo app mới
3. Điền thông tin:
   - Tên: coDY
   - Mô tả ngắn: Your personal productivity companion with GenZ vibes! 🚀
   - Danh mục: Productivity
   - Screenshots (cần 2-8 ảnh)
   - Icon app (512x512px)
4. Upload file AAB
5. Điền Privacy Policy (bắt buộc)
6. Submit để review (2-7 ngày)

**Chi phí:** $25 một lần (trọn đời)

---

## 📦 Cách 2: Chia sẻ APK trực tiếp (Miễn phí)

### Build APK Debug (nhanh nhất):
```bash
gradlew.bat assembleDebug
```
File: `app/build/outputs/apk/debug/app-debug.apk`

### Chia sẻ qua:
- **Google Drive / Dropbox**: Upload và share link
- **GitHub Releases**: Tạo release và attach APK
- **Telegram / Discord**: Gửi file trực tiếp
- **Website cá nhân**: Host file APK

**⚠️ Lưu ý:** 
- Người dùng cần bật "Cài đặt từ nguồn không xác định"
- Không có auto-update
- Ít tin cậy hơn Play Store

---

## 🌐 Cách 3: Alternative App Stores

### APKPure / APKMirror
- Miễn phí
- Không cần tài khoản developer
- Submit APK qua website

### F-Droid (Open Source)
- Miễn phí
- Chỉ cho app open source
- Cần public source code

### Amazon Appstore
- $99/năm
- Ít người dùng hơn Play Store

---

## 🎨 Chuẩn bị Assets

### Icon App (bắt buộc):
- 512x512px (Play Store)
- 192x192px (launcher icon)
- Định dạng: PNG, không trong suốt

### Screenshots:
- Tối thiểu 2 ảnh
- Kích thước: 1080x1920px hoặc 1080x2340px
- Chụp từ emulator hoặc thiết bị thật

### Feature Graphic (Play Store):
- 1024x500px
- Banner hiển thị trên Play Store

---

## 📝 Checklist trước khi release

- [ ] Test app trên nhiều thiết bị
- [ ] Kiểm tra tất cả tính năng hoạt động
- [ ] Viết Privacy Policy
- [ ] Tạo icon và screenshots đẹp
- [ ] Cập nhật version code và version name
- [ ] Build và test APK release
- [ ] Backup keystore file an toàn
- [ ] Viết changelog/release notes

---

## 🔐 Privacy Policy (Mẫu)

Vì app lưu dữ liệu local, Privacy Policy đơn giản:

```
Privacy Policy for coDY

Last updated: [Date]

coDY does not collect, store, or share any personal data. 
All data is stored locally on your device and never leaves your device.

We do not:
- Collect personal information
- Use analytics or tracking
- Share data with third parties
- Require internet connection

Contact: [Your Email]
```

---

## 💡 Tips

1. **Bắt đầu với APK sharing** để test với bạn bè
2. **Sau đó lên Play Store** khi đã có feedback tốt
3. **Tạo landing page** đơn giản để giới thiệu app
4. **Social media marketing** để quảng bá

---

## 🆘 Cần giúp đỡ?

- Play Console Help: https://support.google.com/googleplay/android-developer
- Android Developer Guide: https://developer.android.com/studio/publish
