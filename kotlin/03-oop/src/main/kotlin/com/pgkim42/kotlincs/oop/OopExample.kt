package com.pgkim42.kotlincs.oop

// 인터페이스 정의
interface Drawable {
    fun draw()
    
    // 기본 구현 제공 가능
    fun describe() {
        println("이것은 그릴 수 있는 객체입니다")
    }
}

// 추상 클래스
abstract class Shape : Drawable {
    abstract val name: String
    abstract fun area(): Double
}

// 구체 클래스: 원
class Circle(val radius: Double) : Shape() {
    override val name = "원"
    
    override fun area(): Double = Math.PI * radius * radius
    
    override fun draw() {
        println("$name 그리기: 반지름 = $radius")
    }
}

// 구체 클래스: 사각형
class Rectangle(val width: Double, val height: Double) : Shape() {
    override val name = "사각형"
    
    override fun area(): Double = width * height
    
    override fun draw() {
        println("$name 그리기: $width x $height")
    }
}

// Sealed Class: 제한된 계층 구조
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}

// API 응답을 시뮬레이션하는 함수
fun fetchUser(id: Int): Result<String> {
    return when {
        id < 0 -> Result.Error("잘못된 ID입니다")
        id == 0 -> Result.Loading
        else -> Result.Success("사용자 $id")
    }
}

// 예외 클래스
class InvalidAgeException(message: String) : Exception(message)

// 예외를 던지는 함수
fun validateAge(age: Int) {
    if (age < 0) {
        throw InvalidAgeException("나이는 음수일 수 없습니다: $age")
    }
    if (age > 150) {
        throw InvalidAgeException("나이가 너무 큽니다: $age")
    }
}

// 위임을 사용하는 클래스
interface Repository {
    fun save(data: String)
    fun load(): String
}

class DatabaseRepository : Repository {
    private var data: String = ""
    
    override fun save(data: String) {
        this.data = data
        println("DB에 저장: $data")
    }
    
    override fun load(): String {
        println("DB에서 로드: $data")
        return data
    }
}

// 위임을 통한 구현
class CachedRepository(
    private val database: Repository
) : Repository by database {
    private var cache: String? = null
    
    override fun load(): String {
        return cache ?: database.load().also { cache = it }
    }
}

// lazy 프로퍼티 위임
class ExpensiveObject {
    val expensiveValue: String by lazy {
        println("값 계산 중...")
        "계산된 값"
    }
}

fun main() {
    // 다형성
    println("=== 다형성 ===")
    val shapes: List<Shape> = listOf(
        Circle(5.0),
        Rectangle(4.0, 6.0),
        Circle(3.0)
    )
    
    shapes.forEach { shape ->
        shape.draw()
        println("${shape.name} 면적: ${shape.area()}")
        println()
    }
    
    // Sealed Class를 이용한 타입 안전한 분기
    println("=== Sealed Class ===")
    val results = listOf(
        fetchUser(1),
        fetchUser(-1),
        fetchUser(0)
    )
    
    results.forEach { result ->
        // when에서 모든 케이스를 처리하므로 else가 필요 없음
        val message = when (result) {
            is Result.Success -> "성공: ${result.data}"
            is Result.Error -> "에러: ${result.message}"
            Result.Loading -> "로딩 중..."
        }
        println(message)
    }
    
    // 예외 처리
    println("\n=== 예외 처리 ===")
    try {
        validateAge(25)
        println("나이 25: 유효함")
        
        validateAge(-5)
        println("이 줄은 실행되지 않음")
    } catch (e: InvalidAgeException) {
        println("예외 발생: ${e.message}")
    } finally {
        println("finally 블록 실행")
    }
    
    // runCatching을 이용한 함수형 예외 처리
    println("\n=== runCatching ===")
    val result1 = runCatching { validateAge(30) }
    val result2 = runCatching { validateAge(200) }
    
    println("결과 1: ${result1.isSuccess}")
    println("결과 2: ${result2.exceptionOrNull()?.message}")
    
    // 위임
    println("\n=== 위임 ===")
    val db = DatabaseRepository()
    val cached = CachedRepository(db)
    
    cached.save("테스트 데이터")
    println("첫 번째 로드:")
    cached.load()
    println("두 번째 로드 (캐시됨):")
    cached.load()
    
    // lazy 위임
    println("\n=== Lazy 위임 ===")
    val obj = ExpensiveObject()
    println("객체 생성됨")
    println("첫 번째 접근:")
    println(obj.expensiveValue)
    println("두 번째 접근:")
    println(obj.expensiveValue)
}
