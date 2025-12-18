package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.MessageRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Message;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.MessageEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaMessageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Repository adapter for Message.
 * Implements MessageRepositoryPort to provide persistence operations using JpaMessageRepository.
 * Uses MessageEntityMapper to convert between domain model and entity.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlMessageRepositoryAdapter implements MessageRepositoryPort {

    private final JpaMessageRepository messageRepository;
    private final MessageEntityMapper messageEntityMapper;

    @Transactional
    @Override
    public void saveMessage(Message message) {
        log.info("Saving message to {}", message.getRecipient());
        messageRepository.save(messageEntityMapper.toEntity(message));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Message> findAllMessages() {
        log.info("Finding all messages");
        return messageRepository.findAll().stream()
                .map(messageEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Message> findMessagesByRecipientId(Long recipientId) {
        log.info("Finding messages received by user with ID {}", recipientId);
        return messageRepository.findByRecipientId(recipientId).stream()
                .map(messageEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Message> findMessageById(Long id) {
        log.info("Finding message with ID {}", id);
        return messageRepository.findById(id).map(messageEntityMapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean hasNewMessages(Long recipientId) {
        log.info("Checking for new messages for user with ID {}", recipientId);
        return messageRepository.existsByRecipientIdAndSeenFalse(recipientId);
    }

    @Transactional
    @Override
    public void deleteMessageById(Long id) {
        log.info("Deleting message with ID {}", id);
        messageRepository.deleteById(id);
    }

}
