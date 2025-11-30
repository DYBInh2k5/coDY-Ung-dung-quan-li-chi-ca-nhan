# 🤖 GitHub Actions - Auto Build APK

## Cách hoạt động:

Mỗi khi bạn push code lên GitHub, workflow này sẽ tự động:

1. ✅ Checkout code
2. ☕ Setup JDK 17
3. 🔨 Build APK debug
4. 📦 Upload APK as artifact
5. 🚀 Tạo GitHub Release với APK

## Cách sử dụng:

### 1. Push code lên GitHub:
```bash
git add .
git commit -m "Update features"
git push
```

### 2. Xem quá trình build:
- Vào repository trên GitHub
- Click tab "Actions"
- Xem workflow đang chạy

### 3. Tải APK:

**Cách 1 - Từ Artifacts:**
- Vào Actions → Click vào workflow run
- Scroll xuống "Artifacts"
- Download "coDY-debug-apk"

**Cách 2 - Từ Releases:**
- Vào tab "Releases"
- Click vào release mới nhất
- Download file APK

## Thời gian build:

- Lần đầu: ~5-7 phút
- Lần sau: ~3-4 phút (có cache)

## Lưu ý:

- Workflow chạy tự động khi push lên branch `main`
- Có thể chạy thủ công từ tab Actions → "Run workflow"
- APK được lưu trong 90 ngày
- Mỗi lần push tạo 1 release mới với version tăng dần

## Chia sẻ APK:

Link release: https://github.com/DYBInh2k5/coDY-Ung-dung-quan-li-chi-ca-nhan/releases

Gửi link này cho bạn bè để họ tải APK!
