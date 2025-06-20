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


### 1. CalendarView (달력)
- 사용자가 원하는 날짜를 선택할 수 있습니다.  
- 선택한 날짜에 해당하는 일기를 작성하거나 확인할 수 있습니다.

### 2. EditText (일기 작성)
- 선택한 날짜에 대한 일기를 자유롭게 작성할 수 있습니다.

### 3. Fused Location API + 지오코더(Geocoder)
- 앱 실행 시 현재 위치(GPS)를 자동으로 감지합니다.  
- 지오코더(Geocoder)를 통해 위치를 도시 이름으로 변환하여 표시합니다.

### 4. 쉐어드 프리퍼런스(Shared Preference) 또는 내부 저장소
- 작성한 일기는 자동으로 저장됩니다.  
- 날짜와 도시명을 키로 하여 저장되며, 다시 해당 날짜를 선택하면 불러올 수 있습니다.

---

## 시연 이미지
1. 메인 화면 (달력 및 도착지 정보 안내)

![메인 화면](https://github.com/seyoungjung589/SkyBalance/blob/main/main.png)


2. 비행 일정 관리

![일정 관리 화면](https://github.com/seyoungjung589/SkyBalance/blob/main/schedule.png)

3. 일기 기능

![일기 기능 화면](https://github.com/seyoungjung589/SkyBalance/blob/main/diary.png)




