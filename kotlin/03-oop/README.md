# 03. 객체 지향 프로그래밍

## 학습 목표
- 클래스와 객체
- 인터페이스 (Interface)
- 추상 클래스 (Abstract Class)
- Sealed Class와 Sealed Interface
- 예외 처리 (Exception Handling)
- 위임 (Delegation)

## 실행 방법
```bash
./gradlew :kotlin:03-oop:run
```

## 테스트 실행
```bash
./gradlew :kotlin:03-oop:test
```

## 주요 개념

### 클래스와 생성자
- 주 생성자 (Primary Constructor)
- 부 생성자 (Secondary Constructor)
- 초기화 블록 (`init`)

### 인터페이스
- 다중 인터페이스 구현 가능
- 기본 구현 제공 가능

### Sealed Class
제한된 상속 계층 구조를 정의하여 타입 안전성을 높입니다.
- `when` 표현식과 함께 사용 시 모든 케이스를 검사할 수 있음
- 컴파일 타임에 서브클래스가 확정됨

### 예외 처리
- `try-catch-finally`
- Kotlin의 모든 예외는 unchecked exception
- `runCatching`으로 함수형 스타일 예외 처리

### 위임
- `by` 키워드로 구현 위임
- 프로퍼티 위임 (`lazy`, `observable` 등)
