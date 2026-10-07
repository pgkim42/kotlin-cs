package com.pgkim42.kotlincs.basics

// 데이터 클래스: equals, hashCode, toString, copy 메서드 자동 생성
data class Person(
    val name: String,
    val age: Int,
    val email: String?  // null 허용
)

// 기본 함수: 인자의 합을 반환
fun add(a: Int, b: Int): Int {
    return a + b
}

// 표현식 본문 함수 (간결한 형태)
fun multiply(a: Int, b: Int) = a * b

// when 표현식: Java의 switch보다 강력
fun describe(obj: Any): String = when (obj) {
    1 -> "하나"
    "Hello" -> "인사"
    is Long -> "Long 타입"
    !is String -> "String이 아님"
    else -> "알 수 없음"
}

// null 안전성 예제
fun getLength(str: String?): Int {
    // 안전한 호출: str이 null이면 null 반환, 아니면 length 반환
    return str?.length ?: 0  // 엘비스 연산자: null이면 0 반환
}

fun main() {
    // 변수 선언
    val immutableValue = "변경 불가"  // val: 읽기 전용
    var mutableValue = "변경 가능"    // var: 변경 가능
    
    println("=== 변수 ===")
    println(immutableValue)
    mutableValue = "새로운 값"
    println(mutableValue)
    
    // 함수 호출
    println("\n=== 함수 ===")
    println("5 + 3 = ${add(5, 3)}")
    println("5 * 3 = ${multiply(5, 3)}")
    
    // when 표현식
    println("\n=== when 표현식 ===")
    println(describe(1))
    println(describe("Hello"))
    println(describe(100L))
    println(describe(10.5))
    
    // null 안전성
    println("\n=== Null 안전성 ===")
    val nullableString: String? = null
    val nonNullString: String = "안녕하세요"
    println("null 문자열 길이: ${getLength(nullableString)}")
    println("일반 문자열 길이: ${getLength(nonNullString)}")
    
    // data class
    println("\n=== Data Class ===")
    val person1 = Person("김철수", 30, "kim@example.com")
    val person2 = Person("이영희", 25, null)
    
    println(person1)
    println(person2)
    
    // copy 메서드: 일부 속성만 변경하여 새 객체 생성
    val person3 = person1.copy(age = 31)
    println("나이만 변경: $person3")
    
    // 구조 분해 선언
    val (name, age, email) = person1
    println("이름: $name, 나이: $age, 이메일: $email")
}
