package com.pgkim42.kotlincs.oop

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class OopTest {
    
    @Test
    fun `Circle의 면적을 계산한다`() {
        val circle = Circle(5.0)
        val area = circle.area()
        assertEquals(Math.PI * 25, area, 0.001)
    }
    
    @Test
    fun `Rectangle의 면적을 계산한다`() {
        val rectangle = Rectangle(4.0, 6.0)
        assertEquals(24.0, rectangle.area())
    }
    
    @Test
    fun `fetchUser는 Success를 반환한다`() {
        val result = fetchUser(1)
        assertTrue(result is Result.Success)
        assertEquals("사용자 1", (result as Result.Success).data)
    }
    
    @Test
    fun `fetchUser는 Error를 반환한다`() {
        val result = fetchUser(-1)
        assertTrue(result is Result.Error)
        assertEquals("잘못된 ID입니다", (result as Result.Error).message)
    }
    
    @Test
    fun `fetchUser는 Loading을 반환한다`() {
        val result = fetchUser(0)
        assertTrue(result is Result.Loading)
    }
    
    @Test
    fun `sealed class를 when으로 처리한다`() {
        val results = listOf(
            fetchUser(1),
            fetchUser(-1),
            fetchUser(0)
        )
        
        val messages = results.map { result ->
            when (result) {
                is Result.Success -> "success"
                is Result.Error -> "error"
                Result.Loading -> "loading"
            }
        }
        
        assertEquals(listOf("success", "error", "loading"), messages)
    }
    
    @Test
    fun `validateAge는 유효한 나이를 허용한다`() {
        validateAge(25)  // 예외가 발생하지 않으면 성공
        validateAge(0)
        validateAge(150)
    }
    
    @Test
    fun `validateAge는 음수 나이에 예외를 던진다`() {
        val exception = assertFailsWith<InvalidAgeException> {
            validateAge(-5)
        }
        assertTrue(exception.message!!.contains("음수"))
    }
    
    @Test
    fun `validateAge는 너무 큰 나이에 예외를 던진다`() {
        val exception = assertFailsWith<InvalidAgeException> {
            validateAge(200)
        }
        assertTrue(exception.message!!.contains("너무 큽니다"))
    }
    
    @Test
    fun `runCatching은 성공을 감싼다`() {
        val result = runCatching { validateAge(30) }
        assertTrue(result.isSuccess)
    }
    
    @Test
    fun `runCatching은 실패를 감싼다`() {
        val result = runCatching { validateAge(200) }
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is InvalidAgeException)
    }
    
    @Test
    fun `위임된 Repository는 동작한다`() {
        val db = DatabaseRepository()
        val cached = CachedRepository(db)
        
        cached.save("테스트")
        val loaded = cached.load()
        assertEquals("테스트", loaded)
    }
}
