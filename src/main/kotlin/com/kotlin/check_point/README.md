# 미션 2: 간단한 출석/포인트 시스템

## 목표
- 클래스를 최소 4개 이상으로 분리
- interface, data class, sealed class, enum, when, 컬렉션, 예외 처리, 테스트(선택) 사용

---

## 요구사항
### 1) 도메인
- User(id: Long, name: String)
- 사용자는 하루에 1번만 출석 가능(날짜 기준)
- 출석 시 포인트 지급

### 2) 포인트 정책
- 기본 출석: +10
- 연속 출석 보너스:
  - 3일 연속: +20 추가
  - 7일 연속: +50 추가
- 같은 날 중복 출석 시: 실패 처리(포인트 지급 X)

### 3) 기능
- checkIn(userId, date) : 출석 처리
- getPoint(userId) : 현재 포인트 조회
- getHistory(userId) : 출석 기록(날짜 목록) 조회
- leaderboard(topN) : 포인트 상위 N명 조회

### 4) 출력/실행
- main()에서 샘플 시나리오를 돌려 결과를 출력
- 입력 UI까지는 필수 아님(하드코딩 시나리오 OK)

---

## 설계 가이드(필수 클래스 분리)
### (1) Repository 계층
- UserRepository
  - findById(id), save(user)
- AttendanceRepository
  - exists(userId, date)
  - save(record)
  - findAllByUserId(userId)
> 처음에는 메모리(MutableMap, MutableList)로 구현하세요.

### (2) Service 계층
- AttendanceService
  - 정책 계산 + 중복 체크 + 저장을 담당

### (3) 결과 타입 (sealed class 권장)
출석 결과를 예외 대신 타입으로 반환:
```kotlin
sealed class CheckInResult {
    data class Success(val earned: Int, val totalPoint: Int, val streak: Int) : CheckInResult()
    data class AlreadyCheckedIn(val date: String) : CheckInResult()
    data class UserNotFound(val userId: Long) : CheckInResult()
}
```
when(result)로 출력 분기.

### (4) 포인트 정책 계산기 (interface)
```kotlin
interface PointPolicy {
    fun calculate(streak: Int): Int
}
```