package com.pgkim42.kotlincs.collections

// 확장 함수: String 클래스에 새로운 메서드 추가
fun String.addExclamation(): String = "$this!"

// 제네릭 확장 함수: 리스트의 두 번째 요소 반환 (없으면 null)
fun <T> List<T>.secondOrNull(): T? = if (this.size >= 2) this[1] else null

// 중위 표기법 함수 (infix)
infix fun Int.pow(exponent: Int): Int {
    var result = 1
    repeat(exponent) {
        result *= this
    }
    return result
}

fun main() {
    // List 예제
    println("=== List ===")
    val immutableList = listOf(1, 2, 3, 4, 5)
    val mutableList = mutableListOf("사과", "바나나", "딸기")
    
    mutableList.add("오렌지")
    println("가변 리스트: $mutableList")
    
    // 고차 함수 사용
    val doubled = immutableList.map { it * 2 }
    val filtered = immutableList.filter { it > 2 }
    println("2배: $doubled")
    println("2보다 큰 값: $filtered")
    
    // Map 예제
    println("\n=== Map ===")
    val scores = mapOf(
        "김철수" to 95,
        "이영희" to 88,
        "박민수" to 92
    )
    
    println("김철수의 점수: ${scores["김철수"]}")
    
    val mutableScores = mutableMapOf<String, Int>()
    mutableScores["정수진"] = 90
    println("가변 맵: $mutableScores")
    
    // Map 변환
    val passed = scores.filter { it.value >= 90 }
    println("90점 이상: $passed")
    
    // Sequence를 이용한 지연 평가
    println("\n=== Sequence (지연 평가) ===")
    val numbers = (1..1000).toList()
    
    // 즉시 평가: 중간 리스트가 생성됨
    val eagerResult = numbers
        .map { it * 2 }
        .filter { it > 10 }
        .take(5)
    
    // 지연 평가: 필요한 만큼만 계산
    val lazyResult = numbers.asSequence()
        .map { it * 2 }
        .filter { it > 10 }
        .take(5)
        .toList()
    
    println("즉시 평가 결과: $eagerResult")
    println("지연 평가 결과: $lazyResult")
    
    // fold와 reduce
    println("\n=== fold와 reduce ===")
    val sum = immutableList.fold(0) { acc, value -> acc + value }
    val product = immutableList.reduce { acc, value -> acc * value }
    println("합계 (fold): $sum")
    println("곱 (reduce): $product")
    
    // 확장 함수 사용
    println("\n=== 확장 함수 ===")
    val greeting = "안녕하세요"
    println(greeting.addExclamation())
    
    val list = listOf(1, 2, 3)
    println("두 번째 요소: ${list.secondOrNull()}")
    
    val emptyList = listOf<Int>()
    println("빈 리스트의 두 번째: ${emptyList.secondOrNull()}")
    
    // 중위 표기법
    println("\n=== 중위 표기법 ===")
    println("2의 3승: ${2 pow 3}")
    println("5의 2승: ${5 pow 2}")
    
    // 그룹화와 집계
    println("\n=== 그룹화 ===")
    val words = listOf("apple", "banana", "apricot", "blueberry", "avocado")
    val grouped = words.groupBy { it.first() }
    println("첫 글자로 그룹화: $grouped")
    
    val lengths = words.associateWith { it.length }
    println("단어 길이 맵: $lengths")
}
