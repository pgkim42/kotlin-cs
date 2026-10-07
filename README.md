# Kotlin과 CS 학습 저장소

이 저장소는 Kotlin과 컴퓨터 과학 기초를 학습하기 위한 자료입니다.

## 목적

이 저장소는 두 가지 목적으로 만들어졌습니다.

**첫 번째 목적**: Kotlin JVM 백엔드 개발을 배웁니다.  
**두 번째 목적**: 후배 백엔드 엔지니어(3년 경력 이하)의 인터뷰를 준비합니다.

## 저장소 구조

이 저장소는 두 개의 주요 디렉토리로 구성됩니다.

### kotlin 디렉토리

`kotlin/` 디렉토리는 5개의 모듈로 구성됩니다.

| 모듈 | 주제 | 내용 |
|------|------|------|
| `01-basics` | 기초 문법 | 변수, 함수, null 안전성, data class |
| `02-collections` | 컬렉션 | List, Map, Sequence, 확장 함수 |
| `03-oop` | 객체 지향 | 클래스, 인터페이스, Sealed Class |
| `04-coroutines` | 코루틴 | suspend 함수, async, Flow |
| `05-spring` | Spring Boot | REST API, DI, CRUD |

Spring Boot 학습은 `05-spring` 모듈에서 시작됩니다.

### cs 디렉토리

`cs/` 디렉토리는 5개의 주제로 구성됩니다.

| 주제 | 내용 |
|------|------|
| `os` | 프로세스, 스레드, 메모리 관리 |
| `networking` | HTTP, TCP/UDP, DNS |
| `data-structures-algorithms` | 자료구조, 정렬, 탐색 |
| `databases` | SQL, 인덱스, 트랜잭션 |
| `system-design` | 확장성, 캐싱, 메시지 큐 |

각 주제 디렉토리에는 README 파일이 있습니다.

## 환경 요구사항

다음 소프트웨어를 설치하십시오.

- JDK 17 이상
- IntelliJ IDEA 또는 VS Code (Kotlin 플러그인 필요)
- Gradle 8.5 (프로젝트에 포함되어 있음)

## 저장소 시작하기

### 1단계: 저장소 복제

터미널에서 다음 명령을 실행하십시오.

```bash
git clone https://github.com/pgkim42/kotlin-cs.git
cd kotlin-cs
```

### 2단계: 프로젝트 빌드

프로젝트를 빌드하십시오.

```bash
./gradlew build
```

빌드가 완료되면 모든 모듈이 컴파일됩니다.

### 3단계: 모듈 실행

특정 모듈을 실행하려면 다음 명령을 사용하십시오.

```bash
# 01-basics 모듈 실행
./gradlew :kotlin:01-basics:run

# 02-collections 모듈 실행
./gradlew :kotlin:02-collections:run

# 04-coroutines 모듈 실행
./gradlew :kotlin:04-coroutines:run
```

각 모듈은 예제 코드를 실행합니다.

### 4단계: 테스트 실행

전체 테스트를 실행하십시오.

```bash
./gradlew test
```

특정 모듈만 테스트하려면 다음 명령을 사용하십시오.

```bash
./gradlew :kotlin:01-basics:test
```

## 학습 방법

### 추천 학습 순서

Kotlin과 CS를 병렬로 학습하십시오.  
한 주에 Kotlin 모듈 하나와 CS 주제 하나를 공부하십시오.

| 주차 | Kotlin 모듈 | CS 주제 | 학습 내용 |
|------|-------------|---------|----------|
| 1주차 | `01-basics` | `os` | 변수, 함수, null 안전성 / 프로세스, 스레드 |
| 2주차 | `02-collections` | `networking` | 컬렉션, 확장 함수 / HTTP, TCP/UDP |
| 3주차 | `03-oop` | `data-structures-algorithms` | 클래스, 인터페이스 / 자료구조, 정렬 |
| 4주차 | `04-coroutines` | `databases` | 비동기, Flow / SQL, 인덱스 |
| 5주차 | `05-spring` | `system-design` | Spring Boot, REST / 확장성, 캐싱 |

### 효과적인 학습 방법

다음 방법을 따르십시오.

1. 예제 코드를 실행하십시오.
2. 코드를 수정하고 결과를 확인하십시오.
3. 테스트 코드를 읽으십시오.
4. CS 개념을 Kotlin으로 구현하십시오.
5. 배운 내용을 조합하여 작은 프로젝트를 만드십시오.

## Kotlin 모듈 상세 내용

### 01-basics: Kotlin 기초

이 모듈은 Kotlin의 기본 문법을 다룹니다.

- `val`과 `var`로 변수 선언
- 함수 정의
- Null 안전성
- `when` 표현식
- `data class`

### 02-collections: 컬렉션과 확장 함수

이 모듈은 Kotlin의 컬렉션 API를 다룹니다.

- List, Set, Map
- Sequence (지연 평가)
- 확장 함수
- 고차 함수 (map, filter, fold)

### 03-oop: 객체 지향 프로그래밍

이 모듈은 Kotlin의 객체 지향 기능을 다룹니다.

- 클래스와 인터페이스
- 추상 클래스
- Sealed Class
- 예외 처리
- 위임 (Delegation)

### 04-coroutines: 코루틴 기초

이 모듈은 Kotlin의 비동기 프로그래밍을 다룹니다.

- `suspend` 함수
- `launch`와 `async`
- `Flow`
- 구조화된 동시성

### 05-spring: Spring Boot

이 모듈은 Spring Boot를 사용한 백엔드 개발을 다룹니다.

- Spring Boot 프로젝트 구성
- REST API 작성
- Dependency Injection
- 간단한 CRUD 구현

## CS 주제 상세 내용

### os: 운영체제

운영체제의 핵심 개념을 다룹니다.

- 프로세스와 스레드
- 메모리 관리 (페이징, 가상 메모리)
- CPU 스케줄링
- 동기화와 데드락
- 파일 시스템

### networking: 네트워킹

네트워크 프로토콜과 통신 원리를 다룹니다.

- OSI 7계층, TCP/IP
- HTTP/HTTPS
- TCP vs UDP
- DNS
- 로드 밸런싱

### data-structures-algorithms: 자료구조와 알고리즘

자료구조와 알고리즘의 기초를 다룹니다.

- 시간/공간 복잡도
- 배열, 리스트, 스택, 큐, 해시
- 트리, 그래프, 힙
- 정렬, 탐색
- DFS, BFS, DP

### databases: 데이터베이스

데이터베이스 이론과 실무를 다룹니다.

- SQL 기초
- 인덱스
- 트랜잭션 (ACID)
- 정규화
- NoSQL

### system-design: 시스템 디자인

확장 가능한 시스템 설계를 다룹니다.

- 확장성 (Scale-up vs Scale-out)
- 캐싱 전략
- 메시지 큐
- 실전 예제 (URL 단축, 뉴스피드, 채팅)

## 인터뷰 준비

각 CS 주제의 README 하단에는 핵심 질문이 있습니다.  
인터뷰 전에 이 질문에 답할 수 있는지 확인하십시오.

## 참고 자료

### Kotlin 학습 자료

- [Kotlin 공식 문서](https://kotlinlang.org/docs/home.html)
- [Kotlin Koans](https://play.kotlinlang.org/koans/)
- [Spring Boot with Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin/)

### CS 학습 자료

- [운영체제 강의 (이화여대 반효경)](http://www.kocw.net/home/search/kemView.do?kemId=1046323)
- [네트워크 개념 정리](https://github.com/JaeYeopHan/Interview_Question_for_Beginner/tree/master/Network)
- [알고리즘 문제 사이트 (백준)](https://www.acmicpc.net/)

## 기여하기

오류를 발견하거나 개선 사항이 있으면 이슈를 등록하거나 Pull Request를 보내주십시오.

## 라이선스

MIT License
