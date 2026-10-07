package com.pgkim42.kotlincs.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CoroutinesTest {
    
    @Test
    fun `suspend 함수는 비동기로 실행된다`() = runTest {
        val result = fetchUserFromApi(1)
        assertEquals("사용자 1", result)
    }
    
    @Test
    fun `async는 동시 실행을 가능하게 한다`() = runTest {
        val time = currentTime
        
        val deferred1 = async { fetchUserFromApi(1) }
        val deferred2 = async { fetchUserFromApi(2) }
        
        val result1 = deferred1.await()
        val result2 = deferred2.await()
        
        assertEquals("사용자 1", result1)
        assertEquals("사용자 2", result2)
        
        // 동시 실행이므로 2초가 아니라 1초 정도 소요
        val elapsed = currentTime - time
        assertTrue(elapsed < 1500)
    }
    
    @Test
    fun `launch는 새 코루틴을 시작한다`() = runTest {
        var executed = false
        
        launch {
            delay(100)
            executed = true
        }
        
        // launch는 즉시 반환되므로 아직 실행되지 않음
        assertTrue(!executed)
        
        advanceTimeBy(150)
        
        // 시간이 지나면 실행됨
        assertTrue(executed)
    }
    
    @Test
    fun `Flow는 값을 순차적으로 방출한다`() = runTest {
        val results = mutableListOf<Int>()
        
        getNumbers()
            .take(3)
            .collect { results.add(it) }
        
        assertEquals(listOf(1, 2, 3), results)
    }
    
    @Test
    fun `Flow map은 값을 변환한다`() = runTest {
        val results = getNumbers()
            .map { it * 2 }
            .take(3)
            .toList()
        
        assertEquals(listOf(2, 4, 6), results)
    }
    
    @Test
    fun `Flow filter는 값을 필터링한다`() = runTest {
        val results = getNumbers()
            .filter { it % 2 == 0 }
            .toList()
        
        assertEquals(listOf(2, 4), results)
    }
    
    @Test
    fun `withTimeout은 시간 초과 시 예외를 던진다`() = runTest {
        try {
            withTimeout(500) {
                delay(1000)
            }
            assertTrue(false, "TimeoutCancellationException이 발생해야 함")
        } catch (e: TimeoutCancellationException) {
            // 예상된 동작
            assertTrue(true)
        }
    }
    
    @Test
    fun `구조화된 동시성은 자식 코루틴을 기다린다`() = runTest {
        var task1Done = false
        var task2Done = false
        
        coroutineScope {
            launch {
                delay(100)
                task1Done = true
            }
            launch {
                delay(200)
                task2Done = true
            }
        }
        
        // coroutineScope가 끝나면 모든 자식이 완료됨
        assertTrue(task1Done)
        assertTrue(task2Done)
    }
    
    @Test
    fun `flowOf는 즉시 Flow를 생성한다`() = runTest {
        val results = flowOf(1, 2, 3, 4, 5).toList()
        assertEquals(listOf(1, 2, 3, 4, 5), results)
    }
}
