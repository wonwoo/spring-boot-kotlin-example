package com.example.message

import com.example.account.AccountRepository
import com.example.save
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
@Transactional(readOnly = true)
class MessageService(private val messageRepository: MessageRepository, private val accountRepository: AccountRepository) {

    fun findAll(): Flux<MessageDto> = messageRepository.findAll()
        .flatMap {
            accountRepository.findById(it.accountId)
                .map { account ->
                    it.toDto(account)
                }
        }

    @Transactional
    fun save(message: Message): Mono<Message> = messageRepository.save(message.toMono())

    @Transactional
    fun delete(id: Long): Mono<Void> = messageRepository.deleteById(id)
}