# 04. 코루틴 기초

## 학습 목표
- 코루틴 기본 개념
- `suspend` 함수
- `launch`와 `async`
- `Flow` 기초
- 구조화된 동시성 (Structured Concurrency)

## 실행 방법
```bash
./gradlew :kotlin:04-coroutines:run
```

## 테스트 실행
```bash
./gradlew :kotlin:04-coroutines:test
```

## 주요 개념

### 코루틴이란?
경량 스레드로, 비동기 프로그래밍을 간결하게 작성할 수 있습니다.
- 스레드보다 훨씬 가벼움
- 수십만 개의 코루틴을 동시에 실행 가능

### suspend 함수
코루틴 내에서만 호출할 수 있는 일시 중단 가능한 함수입니다.

### launch vs async
- `launch`: 결과를 반환하지 않는 코루틴 시작 (Job 반환)
- `async`: 결과를 반환하는 코루틴 시작 (Deferred<T> 반환)

### Flow
비동기 데이터 스트림을 처리합니다.
- Cold stream: 구독(collect)될 때만 실행
- 연산자: `map`, `filter`, `take` 등

### 구조화된 동시성
코루틴은 항상 특정 스코프 내에서 실행되며, 부모 코루틴이 취소되면 자식 코루틴도 자동으로 취소됩니다.
