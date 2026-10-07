package com.pgkim42.kotlincs.basics

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BasicsTest {
    
    @Test
    fun `add 함수는 두 수를 더한다`() {
        assertEquals(8, add(5, 3))
        assertEquals(0, add(-5, 5))
    }
    
    @Test
    fun `multiply 함수는 두 수를 곱한다`() {
        assertEquals(15, multiply(5, 3))
        assertEquals(0, multiply(0, 100))
    }
    
    @Test
    fun `describe 함수는 객체를 설명한다`() {
        assertEquals("하나", describe(1))
        assertEquals("인사", describe("Hello"))
        assertEquals("Long 타입", describe(100L))
    }
    
    @Test
    fun `getLength 함수는 null 안전하게 길이를 반환한다`() {
        assertEquals(0, getLength(null))
        assertEquals(5, getLength("Hello"))
    }
    
    @Test
    fun `data class는 동등성을 올바르게 비교한다`() {
        val person1 = Person("김철수", 30, "kim@example.com")
        val person2 = Person("김철수", 30, "kim@example.com")
        val person3 = Person("이영희", 25, null)
        
        assertEquals(person1, person2)
        assert(person1 != person3)
    }
    
    @Test
    fun `data class copy는 새로운 객체를 생성한다`() {
        val original = Person("김철수", 30, "kim@example.com")
        val copied = original.copy(age = 31)
        
        assertEquals("김철수", copied.name)
        assertEquals(31, copied.age)
        assertEquals("kim@example.com", copied.email)
        assert(original !== copied)  // 참조가 다름
    }
}
