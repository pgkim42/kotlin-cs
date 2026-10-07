package com.pgkim42.kotlincs.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class SpringExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringExampleApplication>(*args)
}

// 간단한 데이터 클래스
data class Message(val id: Long, val content: String)

// REST API 예제
@RestController
@RequestMapping("/api")
class MessageController {
    
    private val messages = mutableListOf(
        Message(1, "첫 번째 메시지"),
        Message(2, "두 번째 메시지")
    )
    
    @GetMapping("/messages")
    fun getAllMessages(): List<Message> = messages
    
    @GetMapping("/messages/{id}")
    fun getMessage(@PathVariable id: Long): Message? =
        messages.find { it.id == id }
    
    @PostMapping("/messages")
    fun createMessage(@RequestBody content: String): Message {
        val newMessage = Message(
            id = (messages.maxOfOrNull { it.id } ?: 0) + 1,
            content = content
        )
        messages.add(newMessage)
        return newMessage
    }
}
