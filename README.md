# RescuedAnimal

## 사용한 기술 스택
Clean Architecture
Kotlin
MVVM Pattern
Repository Pattern
DI(Hilt)
Retrofit
Moshi
Room
Navigation
Compose  
  
  
## 아키텍쳐
- data
- domain
- presentation  
  
  
## 문제 해결
1. Retrofit을 사용한 API 요청 시 데이터 변환 오류 발생  
   JsonDataException 예외처리  
     
3. 네트워크 연결 불안정 시 앱의 비정상 종료  
   Interceptor로 네트워크 연결 상태에 따라 IOException throw 후 에외처리  
     
5. UI에서 대량의 데이터 표시 시 성능 저하  
   Compose Lazy 위젯을 활용  
   api 호출할 때 pageNo와 numOfRows로 일정부분 만큼만 불러온 후 스크롤 감지하여 load more 구현  
     
7. 백그라운드 동기화 작업 시 배터리 소모 과다  
   
9. 데이터 변경 시 UI 업데이트 지연  
   Indicator 추가하여 로딩중임을 알림  
  
