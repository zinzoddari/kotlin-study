# 미션: 간단한 주문 금액 계산기 (단일 클래스)

## 목적
- 코틀린 문법 최소 세트로 구현
- class, fun, when, data class 사용 경험
- 컬렉션(List)과 합계 계산 익히기

## 요구사항
### 1. 주문 아이템
- 각 아이템은 이름, 가격, 수량을 가진다.
- 가격과 수량은 정수만 사용한다.

### 2. 할인 규칙
- 총 주문 금액이
- 50,000원 이상 → 10% 할인
- 30,000원 이상 → 5% 할인
- 그 미만 → 할인 없음

### 3. 출력
- 할인 전 총 금액
- 할인 금액
- 최종 결제 금액

# 구현 제약 (중요)
- main 함수 포함
- 외부 라이브러리 사용 금지
- 콘솔 출력만 사용 (println)

# 시작 코드
```kotlin
class Order {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val items = listOf(
                Item("아메리카노", 4500, 2),
                Item("샌드위치", 7000, 1),
                Item("케이크", 6000, 1)
            )

            OrderCalculator().printResult(items)
        }
    }
}
```

```kotlin
data class Item(
    val name: String,
    val price: Int,
    val quantity: Int
)
```

```kotlin
class OrderCalculator {

    fun calculateTotal(items: List<Item>): Int {
        // TODO: 총 금액 계산
        return 0
    }

    fun calculateDiscount(total: Int): Int {
        // TODO: 할인 금액 계산
        return 0
    }

    fun printResult(items: List<Item>) {
        // TODO:
        // 1. 총 금액 계산
        // 2. 할인 금액 계산
        // 3. 최종 금액 계산
        // 4. 결과 출력
    }
}
```
# 출력 예시
```
총 주문 금액: 22000원
할인 금액: 0원
최종 결제 금액: 22000원
```