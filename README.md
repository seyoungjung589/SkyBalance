# SkyBalance

# SkyBalance - 항공 승무원 건강관리 앱

SkyBalance는 항공 승무원을 위한 **비행 일정 관리**, **도착 도시 정보 안내**, **개인 일기 기록** 기능을 제공하는 Android 앱입니다.

---

## 주요 기능


### 1. 비행 일정 관리 기능
- 캘린더에서 날짜를 선택해 비행 일정을 기록할 수 있습니다.
- 목적지를 직접 입력하면 해당 일자에 저장됩니다.

### 2. GPS 기반 도착지 정보 안내
- 현재 위치를 자동으로 인식하여 도시 정보를 가져옵니다.
- 도시별 맛집, 교통, 숙박, 팁 등을 자동으로 보여줍니다.

### 3. 일기 기능
- 사용자가 날짜별로 간단한 일기를 작성하고 저장할 수 있습니다.
- 작성한 일기는 나중에 다시 불러올 수 있습니다.
- 감정 기록, 건강 상태 메모, 피로도 체크 등 개인 컨디션 관리에도 활용 가능합니다.

---

## 사용 기술

- Android Studio (Kotlin)
- CalendarView, EditText, AlertDialog
- Google Fused Location API
- Geocoder (현재 위치 → 도시 이름 변환)
- SharedPreferences 또는 내부 저장소를 활용한 일기 저장 기능

---

## 시연 이미지
1. 메인 화면 (달력 및 도착지 정보 안내)

![메인 화면](https://github.com/seyoungjung589/SkyBalance/blob/main/main.png)


2. 비행 일정 관리

![일정 관리 화면](https://github.com/seyoungjung589/SkyBalance/blob/main/schedule.png)

3. 일기 기능

![일기 기능 화면](https://github.com/seyoungjung589/SkyBalance/blob/main/diary.png)


---

## 설치 방법

1. Android Studio에서 프로젝트 열기
2. 위치 권한 설정 허용
3. 에뮬레이터나 실제 기기에서 실행







