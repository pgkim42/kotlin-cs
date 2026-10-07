# 01. Kotlin 기초

## 학습 목표
- 변수 선언 (`val`, `var`)
- 함수 정의와 사용
- Null 안전성 (`?`, `!!`, `?.`)
- `when` 표현식
- `data class`

## 실행 방법
```bash
./gradlew :kotlin:01-basics:run
```

## 테스트 실행
```bash
./gradlew :kotlin:01-basics:test
```

## 주요 개념

### 변수 선언
- `val`: 읽기 전용 변수 (immutable)
- `var`: 변경 가능한 변수 (mutable)

### Null 안전성
Kotlin은 기본적으로 null을 허용하지 않습니다.
- `String?`: null을 허용하는 타입
- `?.`: 안전한 호출 연산자
- `!!`: null이 아님을 단언

### when 표현식
Java의 switch보다 강력한 패턴 매칭 기능을 제공합니다.

### data class
데이터를 담는 클래스를 간결하게 정의할 수 있으며, `equals()`, `hashCode()`, `toString()`, `copy()` 메서드를 자동으로 생성합니다.
