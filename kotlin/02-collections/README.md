# 02. 컬렉션과 확장 함수

## 학습 목표
- List, Set, Map 사용법
- 가변/불변 컬렉션 구분
- Sequence로 지연 평가 (lazy evaluation)
- 확장 함수 (Extension Functions)
- 고차 함수 (map, filter, fold 등)

## 실행 방법
```bash
./gradlew :kotlin:02-collections:run
```

## 테스트 실행
```bash
./gradlew :kotlin:02-collections:test
```

## 주요 개념

### List
- `listOf()`: 읽기 전용 리스트
- `mutableListOf()`: 변경 가능한 리스트

### Map
- `mapOf()`: 읽기 전용 맵
- `mutableMapOf()`: 변경 가능한 맵

### Sequence
대용량 데이터 처리 시 중간 연산을 지연시켜 성능을 향상시킵니다.
- `asSequence()`: 컬렉션을 시퀀스로 변환
- 터미널 연산(`toList()`, `count()` 등)이 호출될 때만 실행됨

### 확장 함수
기존 클래스를 수정하지 않고 새로운 함수를 추가할 수 있습니다.
