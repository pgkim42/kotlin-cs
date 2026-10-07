# Kotlin & CS 학습 저장소

Kotlin JVM 백엔드 개발과 후배 백엔드 인터뷰 대비를 위한 학습 저장소입니다.

## 📚 목표

이 저장소는 두 가지 목표를 가지고 있습니다:

1. **Kotlin 학습**: JVM 백엔드 개발을 위한 Kotlin 기초부터 Spring Boot까지
2. **CS 인터뷰 준비**: 후배 백엔드 엔지니어(~3년 경력)를 위한 컴퓨터 과학 기초

## 🗂️ 구조

```
kotlin-cs/
├── kotlin/              # Kotlin 학습 경로
│   ├── 01-basics/       # 기초 문법
│   ├── 02-collections/  # 컬렉션과 확장 함수
│   ├── 03-oop/          # 객체 지향
│   ├── 04-coroutines/   # 코루틴 기초
│   └── 05-spring/       # Spring Boot 시작
└── cs/                  # CS 이론 정리
    ├── os/                      # 운영체제
    ├── networking/              # 네트워킹
    ├── data-structures-algorithms/  # 자료구조와 알고리즘
    ├── databases/               # 데이터베이스
    └── system-design/           # 시스템 디자인
```

## 🚀 시작하기

### 환경 요구사항

- **JDK**: 17 이상
- **IDE**: IntelliJ IDEA 또는 VS Code (Kotlin 플러그인)
- **Gradle**: 8.5 (wrapper 포함)

### 프로젝트 실행

#### 1. 저장소 클론
```bash
git clone https://github.com/pgkim42/kotlin-cs.git
cd kotlin-cs
```

#### 2. 프로젝트 빌드
```bash
./gradlew build
```

#### 3. 특정 모듈 실행
```bash
# 01-basics 예제 실행
./gradlew :kotlin:01-basics:run

# 02-collections 예제 실행
./gradlew :kotlin:02-collections:run

# 04-coroutines 예제 실행
./gradlew :kotlin:04-coroutines:run
```

#### 4. 테스트 실행
```bash
# 전체 테스트
./gradlew test

# 특정 모듈 테스트
./gradlew :kotlin:01-basics:test
```

## 📖 학습 방법

### 추천 학습 순서

이 저장소는 병렬 학습을 권장합니다. Kotlin 한 단계를 학습할 때마다 CS 주제 하나를 함께 공부하세요.

#### 1주차: 기초 다지기
- **Kotlin**: `01-basics` - 변수, 함수, null 안전성, data class
- **CS**: `os/` - 프로세스/스레드, 메모리 관리

#### 2주차: 컬렉션과 네트워킹
- **Kotlin**: `02-collections` - List, Map, Sequence, 확장 함수
- **CS**: `networking/` - HTTP/HTTPS, TCP/UDP

#### 3주차: 객체 지향과 자료구조
- **Kotlin**: `03-oop` - 클래스, 인터페이스, Sealed Class
- **CS**: `data-structures-algorithms/` - 기본 자료구조, 정렬/탐색

#### 4주차: 비동기와 데이터베이스
- **Kotlin**: `04-coroutines` - suspend, async, Flow
- **CS**: `databases/` - SQL, 인덱스, 트랜잭션

#### 5주차 이후: Spring과 시스템 디자인
- **Kotlin**: `05-spring` - Spring Boot, REST API
- **CS**: `system-design/` - 확장성, 캐싱, 메시지 큐

### 학습 팁

1. **코드를 직접 실행하세요**: 각 예제를 실행하고 코드를 수정해보세요.
2. **테스트 코드를 읽으세요**: 테스트는 코드 사용법의 좋은 예시입니다.
3. **CS 개념을 Kotlin으로 구현해보세요**: 자료구조나 알고리즘을 Kotlin으로 작성해보세요.
4. **작은 프로젝트를 만드세요**: 배운 내용을 조합하여 간단한 애플리케이션을 만들어보세요.

## 📝 Kotlin 모듈 상세

### 01. Kotlin 기초
- 변수 선언 (`val`, `var`)
- 함수 정의
- Null 안전성
- `when` 표현식
- `data class`

### 02. 컬렉션과 확장 함수
- List, Set, Map
- Sequence (지연 평가)
- 확장 함수
- 고차 함수 (map, filter, fold)

### 03. 객체 지향 프로그래밍
- 클래스와 인터페이스
- 추상 클래스
- Sealed Class
- 예외 처리
- 위임 (Delegation)

### 04. 코루틴 기초
- `suspend` 함수
- `launch`와 `async`
- `Flow`
- 구조화된 동시성

### 05. Spring Boot
- Spring Boot 시작하기
- REST API 작성
- Dependency Injection
- 간단한 CRUD

## 💡 CS 주제 상세

### 운영체제 (OS)
- 프로세스와 스레드
- 메모리 관리 (페이징, 가상 메모리)
- CPU 스케줄링
- 동기화와 데드락
- 파일 시스템

### 네트워킹
- OSI 7계층, TCP/IP
- HTTP/HTTPS
- TCP vs UDP
- DNS
- 로드 밸런싱

### 자료구조와 알고리즘
- 시간/공간 복잡도
- 배열, 리스트, 스택, 큐, 해시
- 트리, 그래프, 힙
- 정렬, 탐색
- DFS, BFS, DP

### 데이터베이스
- SQL 기초
- 인덱스
- 트랜잭션 (ACID)
- 정규화
- NoSQL

### 시스템 디자인
- 확장성 (Scale-up vs Scale-out)
- 캐싱 전략
- 메시지 큐
- 실전 예제 (URL 단축, 뉴스피드, 채팅)

## 🎯 인터뷰 준비

각 CS 주제 폴더의 README 하단에는 "인터뷰 대비 핵심 질문"이 포함되어 있습니다.
면접 전에 이 질문들에 답할 수 있는지 확인하세요.

## 🔗 유용한 자료

### Kotlin
- [Kotlin 공식 문서](https://kotlinlang.org/docs/home.html)
- [Kotlin Koans](https://play.kotlinlang.org/koans/)
- [Spring Boot with Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin/)

### CS 학습
- [운영체제 강의 (이화여대 반효경)](http://www.kocw.net/home/search/kemView.do?kemId=1046323)
- [네트워크 개념 정리](https://github.com/JaeYeopHan/Interview_Question_for_Beginner/tree/master/Network)
- [알고리즘 문제 사이트](https://www.acmicpc.net/) - 백준 온라인 저지

## 🤝 기여

학습 중 오류를 발견하거나 개선 사항이 있다면 이슈를 등록하거나 PR을 보내주세요!

## 📄 라이선스

MIT License

---

**즐거운 학습 되세요! 🚀**
