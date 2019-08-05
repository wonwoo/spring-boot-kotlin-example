package com.example.message

import com.example.account.Account
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@DataJpaTest
class MessageTest(@Autowired val entityManager: TestEntityManager,
                  @Autowired val messageRepository: MessageRepository) {

    @Test
    fun newMessageHas() {
        val account = this.entityManager.persist(
            Account("wonwoo", "passwd"))
        val message = Message("test message", account)
        val saved = this.messageRepository.save(message)
        assertThat(saved.message).isEqualTo("test message")
    }

    @Test
    fun findAllMessages() {
        val account = this.entityManager.persist(
            Account("wonwoo", "passwd"))
        this.messageRepository.save(Message("test message", account))
        this.messageRepository.save(Message("ok test kotlin", account))
        val findMessages = this.messageRepository.findAll()
        assertThat(findMessages).hasSize(8)
    }

    @Test
    fun findByMessage() {
        val account = this.entityManager.persist(
            Account("wonwoo", "passwd"))
        val message = this.messageRepository.save(Message("test message", account))
        val findMessage = this.messageRepository.findById(message.id!!)
        assertThat(findMessage.get().message).isEqualTo("test message")
    }
}