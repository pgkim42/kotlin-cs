package com.pgkim42.kotlincs.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

// suspend 함수: 일시 중단 가능한 함수
suspend fun fetchUserFromApi(userId: Int): String {
    delay(1000)  // 네트워크 요청 시뮬레이션 (1초 대기)
    return "사용자 $userId"
}

suspend fun fetchUserDetailsFromApi(userId: Int): String {
    delay(800)  // 네트워크 요청 시뮬레이션
    return "사용자 $userId 상세정보"
}

// Flow를 반환하는 함수
fun getNumbers(): Flow<Int> = flow {
    println("Flow 시작")
    for (i in 1..5) {
        delay(500)  // 비동기 작업 시뮬레이션
        emit(i)  // 값 방출
    }
}

// 온도 센서를 시뮬레이션하는 Flow
fun temperatureSensor(): Flow<Int> = flow {
    var temp = 20
    repeat(10) {
        delay(300)
        temp += (-2..2).random()
        emit(temp)
    }
}

fun main() = runBlocking {
    // 기본 코루틴
    println("=== 기본 코루틴 ===")
    launch {
        delay(1000)
        println("World!")
    }
    println("Hello")
    delay(1500)  // launch가 끝날 때까지 대기
    
    // async를 이용한 동시 실행
    println("\n=== async로 동시 실행 ===")
    val time = measureTimeMillis {
        // 순차 실행
        val user1 = fetchUserFromApi(1)
        val user2 = fetchUserFromApi(2)
        println("순차 실행: $user1, $user2")
    }
    println("순차 실행 시간: ${time}ms")
    
    val time2 = measureTimeMillis {
        // 동시 실행
        val user1Deferred = async { fetchUserFromApi(1) }
        val user2Deferred = async { fetchUserFromApi(2) }
        val user1 = user1Deferred.await()
        val user2 = user2Deferred.await()
        println("동시 실행: $user1, $user2")
    }
    println("동시 실행 시간: ${time2}ms")
    
    // 구조화된 동시성
    println("\n=== 구조화된 동시성 ===")
    coroutineScope {
        launch {
            delay(500)
            println("Task 1")
        }
        launch {
            delay(300)
            println("Task 2")
        }
        println("Both tasks started")
    }
    println("Both tasks completed")
    
    // Flow 기초
    println("\n=== Flow 기초 ===")
    getNumbers()
        .map { it * 2 }
        .filter { it > 4 }
        .collect { value ->
            println("수신: $value")
        }
    
    // Flow 변환
    println("\n=== Flow 변환 ===")
    temperatureSensor()
        .map { temp -> "섭씨 ${temp}도 = 화씨 ${temp * 9 / 5 + 32}도" }
        .take(5)  // 처음 5개만
        .collect { println(it) }
    
    // Flow 빌더
    println("\n=== Flow 빌더 ===")
    flowOf(1, 2, 3, 4, 5)
        .onEach { delay(200) }
        .collect { println("flowOf: $it") }
    
    // 예외 처리
    println("\n=== 예외 처리 ===")
    try {
        coroutineScope {
            launch {
                delay(100)
                throw Exception("코루틴 에러!")
            }
            launch {
                delay(500)
                println("이 줄은 실행되지 않음")
            }
        }
    } catch (e: Exception) {
        println("예외 발생: ${e.message}")
    }
    
    // withContext를 이용한 컨텍스트 전환
    println("\n=== withContext ===")
    val result = withContext(Dispatchers.Default) {
        // CPU 집약적 작업을 위한 디스패처
        var sum = 0
        repeat(100) {
            sum += it
        }
        sum
    }
    println("계산 결과: $result")
    
    // 타임아웃
    println("\n=== 타임아웃 ===")
    try {
        withTimeout(1500) {
            repeat(5) { i ->
                println("작업 $i")
                delay(500)
            }
        }
    } catch (e: TimeoutCancellationException) {
        println("타임아웃 발생!")
    }
}
