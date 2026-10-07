# 05. Spring Boot

## 학습 목표
- Spring Boot 기본 개념
- RESTful API 작성
- Dependency Injection
- 간단한 CRUD 구현

## 시작하기

이 모듈에서는 Spring Boot를 사용하여 첫 번째 REST API를 만듭니다.

### 프로젝트 생성 방법

1. **Spring Initializr 사용** (권장)
   - https://start.spring.io/ 방문
   - 설정:
     - Project: Gradle - Kotlin
     - Language: Kotlin
     - Spring Boot: 3.2.x (최신 안정 버전)
     - JVM: 17 이상
   - Dependencies 추가:
     - Spring Web
     - Spring Data JPA
     - H2 Database (개발용)
   - Generate로 프로젝트 다운로드

2. **현재 모듈 확장**
   - 위 build.gradle.kts에 필요한 의존성 추가
   - Application 클래스 생성
   - Controller, Service, Repository 레이어 구현

### 실행 방법
```bash
./gradlew :kotlin:05-spring:bootRun
```

### 테스트 실행
```bash
./gradlew :kotlin:05-spring:test
```

## 주요 개념

### REST API 기본 구조
```kotlin
@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {
    
    @GetMapping
    fun getAllUsers(): List<User> = userService.findAll()
    
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): User = userService.findById(id)
    
    @PostMapping
    fun createUser(@RequestBody user: User): User = userService.save(user)
    
    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): User =
        userService.update(id, user)
    
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) = userService.delete(id)
}
```

### 계층 구조
- **Controller**: HTTP 요청/응답 처리
- **Service**: 비즈니스 로직
- **Repository**: 데이터 접근

### Dependency Injection
Spring은 생성자 주입을 통해 의존성을 자동으로 관리합니다.
Kotlin에서는 `val`로 선언하면 간결하게 작성할 수 있습니다.

## 다음 단계

1. Spring Data JPA로 데이터베이스 연동
2. 예외 처리와 validation
3. Spring Security로 인증/인가
4. 테스트 작성 (MockMvc, WebTestClient)
5. 프로파일과 설정 관리

## 참고 자료
- [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)
- [Spring Boot Kotlin 가이드](https://spring.io/guides/tutorials/spring-boot-kotlin/)
