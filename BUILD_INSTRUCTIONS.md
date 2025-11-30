# 🚀 Hướng dẫn Build APK cho coDY

## ⚠️ Yêu cầu trước khi build:

Để build APK, bạn **BẮT BUỘC** phải có:

### 1. Android Studio
- Download: https://developer.android.com/studio
- Cài đặt đầy đủ (bao gồm Android SDK)
- Kích thước: ~1-2GB

### 2. JDK 17
- Android Studio thường đã bao gồm JDK
- Hoặc download riêng: https://adoptium.net/

---

## 📱 Cách build APK:

### Bước 1: Mở project trong Android Studio

1. Mở Android Studio
2. File → Open
3. Chọn thư mục project này
4. Đợi Gradle sync (5-10 phút lần đầu)

### Bước 2: Build APK

**Cách 1 - Qua Android Studio (Dễ nhất):**
1. Build → Build Bundle(s) / APK(s) → Build APK(s)
2. Đợi build xong (2-5 phút)
3. Click "locate" để mở thư mục chứa APK
4. File APK ở: `app/build/outputs/apk/debug/app-debug.apk`

**Cách 2 - Qua Terminal:**
```bash
# Trong Android Studio, mở Terminal (Alt+F12)
gradlew assembleDebug

# Hoặc trên Windows Command Prompt:
gradlew.bat assembleDebug
```

### Bước 3: Lấy file APK

File APK sẽ ở:
```
app\build\outputs\apk\debug\app-debug.apk
```

Kích thước: ~10-20MB

---

## 📤 Chia sẻ APK

### Cách 1: Google Drive
1. Upload file `app-debug.apk` lên Google Drive
2. Click chuột phải → Chia sẻ
3. Chọn "Bất kỳ ai có link"
4. Copy link và gửi cho mọi người

### Cách 2: Telegram/Discord
- Gửi file APK trực tiếp trong chat

### Cách 3: GitHub Releases
1. Tạo release mới trên GitHub
2. Attach file APK
3. Publish release

---

## 📲 Hướng dẫn cài đặt (cho người nhận APK)

1. Tải file APK về điện thoại
2. Mở file APK
3. Nếu được hỏi, bật "Cài đặt từ nguồn không xác định"
4. Nhấn "Cài đặt"
5. Mở app coDY và enjoy! 🎉

---

## ❌ Không có Android Studio?

**Không thể build APK trực tiếp trên Windows mà không có Android Studio.**

Bạn có 2 lựa chọn:

### Option 1: Cài Android Studio (Recommended)
- Download và cài đặt
- Mất ~30-60 phút
- Sau đó có thể build APK

### Option 2: Sử dụng CI/CD (Advanced)
- Push code lên GitHub
- Sử dụng GitHub Actions để build
- Download APK từ Actions artifacts

---

## 🆘 Gặp lỗi?

### Lỗi: "SDK not found"
→ Cài đặt Android SDK qua Android Studio

### Lỗi: "Java not found"
→ Cài đặt JDK 17

### Lỗi: "Gradle sync failed"
→ Kiểm tra kết nối internet, sync lại

### Build quá lâu?
→ Lần đầu tiên sẽ lâu (10-15 phút) vì tải dependencies

---

## 💡 Tips

- Build lần đầu sẽ lâu, lần sau nhanh hơn
- File APK debug có thể cài trực tiếp
- Không cần keystore cho APK debug
- APK debug có thể lớn hơn release APK

---

## ✅ Checklist

- [ ] Đã cài Android Studio
- [ ] Đã mở project và sync Gradle thành công
- [ ] Build APK thành công
- [ ] Tìm thấy file app-debug.apk
- [ ] Test APK trên điện thoại
- [ ] Upload và share link

---

## 🎯 Tóm tắt nhanh

```
1. Cài Android Studio
2. Mở project
3. Build → Build APK(s)
4. Lấy file từ app/build/outputs/apk/debug/
5. Upload lên Drive
6. Share link
7. Done! 🚀
```
