package com.pgkim42.kotlincs.collections

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class CollectionsTest {
    
    @Test
    fun `확장 함수 addExclamation은 느낌표를 추가한다`() {
        assertEquals("Hello!", "Hello".addExclamation())
        assertEquals("Kotlin!", "Kotlin".addExclamation())
    }
    
    @Test
    fun `secondOrNull은 두 번째 요소를 반환한다`() {
        val list = listOf(1, 2, 3)
        assertEquals(2, list.secondOrNull())
        
        val singleElementList = listOf(1)
        assertNull(singleElementList.secondOrNull())
        
        val emptyList = listOf<Int>()
        assertNull(emptyList.secondOrNull())
    }
    
    @Test
    fun `중위 표기법 pow는 거듭제곱을 계산한다`() {
        assertEquals(8, 2 pow 3)
        assertEquals(25, 5 pow 2)
        assertEquals(1, 10 pow 0)
    }
    
    @Test
    fun `map 함수는 각 요소를 변환한다`() {
        val list = listOf(1, 2, 3, 4, 5)
        val doubled = list.map { it * 2 }
        assertEquals(listOf(2, 4, 6, 8, 10), doubled)
    }
    
    @Test
    fun `filter 함수는 조건에 맞는 요소만 선택한다`() {
        val list = listOf(1, 2, 3, 4, 5)
        val evens = list.filter { it % 2 == 0 }
        assertEquals(listOf(2, 4), evens)
    }
    
    @Test
    fun `fold는 초기값부터 누적 계산한다`() {
        val list = listOf(1, 2, 3, 4, 5)
        val sum = list.fold(0) { acc, value -> acc + value }
        assertEquals(15, sum)
        
        val sumWithInitial = list.fold(10) { acc, value -> acc + value }
        assertEquals(25, sumWithInitial)
    }
    
    @Test
    fun `groupBy는 키로 그룹화한다`() {
        val words = listOf("apple", "banana", "apricot", "blueberry")
        val grouped = words.groupBy { it.first() }
        
        assertEquals(2, grouped['a']?.size)  // apple, apricot
        assertEquals(2, grouped['b']?.size)  // banana, blueberry
        assertTrue(grouped['a']?.contains("apple") == true)
    }
    
    @Test
    fun `sequence는 지연 평가로 동작한다`() {
        var mapCount = 0
        var filterCount = 0
        
        val result = (1..10).asSequence()
            .map { 
                mapCount++
                it * 2 
            }
            .filter { 
                filterCount++
                it > 10 
            }
            .take(3)
            .toList()
        
        assertEquals(listOf(12, 14, 16), result)
        // 지연 평가이므로 필요한 만큼만 처리됨
        // take(3)이므로 결과 3개를 얻기 위해 원소 6, 7, 8을 처리
        assertTrue(mapCount >= 6)  // 최소 6번 이상 map 실행
        assertTrue(filterCount >= 6)  // 최소 6번 이상 filter 실행
    }
}
